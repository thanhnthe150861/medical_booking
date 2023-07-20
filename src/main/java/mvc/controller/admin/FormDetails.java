package mvc.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mvc.dal.AccountDB;
import mvc.model.*;
import service.AWSS3Client;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Duration;
import java.util.List;

import static service.AWSS3Client.*;
import static service.AWSS3Client.BUCKET_NAME;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 50 //50MB
)
@WebServlet(name = "FormDetails", value = "/form_details")
public class FormDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDB adb = new AccountDB();
        if (account != null && account.getIsAdmin() == 0) {
            List<Rank> rankListDoctor = adb.getRankDoctor();
            List<Rank> rankListPatient = adb.getRankPatient();
            session.setAttribute("rankListDoctor", rankListDoctor);
            session.setAttribute("rankListPatient", rankListPatient);

            //lấy id để update
            String did = req.getParameter("did");
            String pid = req.getParameter("pid");
            String sid = req.getParameter("sid");
            //sau khi cập nhật xong thì xóa session đi để ko bị dính vào add
            session.removeAttribute("doctor");
            session.removeAttribute("patient");
            session.removeAttribute("staff");
            session.removeAttribute("did");
            session.removeAttribute("pid");
            session.removeAttribute("sid");
            session.removeAttribute("str");
            //Update
            if (did != null) {
                session.setAttribute("did", did);
                Doctor doctor = adb.getDoctorByID(did);
                session.setAttribute("doctor", doctor);
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
            } else if (pid != null) {
                session.setAttribute("pid", pid);
                Patient patient = adb.getPatientByID(pid);
                session.setAttribute("patient", patient);
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
            } else if (sid != null) {
                session.setAttribute("sid", sid);
                Staff staff = adb.getStaffByID(sid);
                session.setAttribute("staff", staff);
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
            }
            //Add
            String str = req.getParameter("str");
            if (str == null) {
                str = "";
            }
            switch (str) {
                case "doctor":
                    session.setAttribute("str", str);
                    req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                    break;
                case "patient":
                    session.setAttribute("str", str);
                    req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                    break;
                case "staff":
                    session.setAttribute("str", str);
                    req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                    break;
                default:
                    resp.sendRedirect("admin_dashboard");
                    break;
            }
        } else {
            req.getRequestDispatcher("login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //Update
        String did = (String) session.getAttribute("did");
        String pid = (String) session.getAttribute("pid");
        String sid = (String) session.getAttribute("sid");
        if (did != null) {
            String pass = req.getParameter("password");
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String dob = req.getParameter("dob");
            String specialty = req.getParameter("speciality");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String rank = req.getParameter("rank");
            Boolean status = Boolean.parseBoolean(req.getParameter("status"));

            // Validate name: should not contain special characters
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                return;
            }
            // Validate specialty: should not contain special characters
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                req.setAttribute("messError", "Speciality không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                req.setAttribute("messError", "Phone sai định dạng");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                req.setAttribute("messError", "Email sai định dạng");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                return;
            }
            AccountDB adb = new AccountDB();
            Doctor doctor = (Doctor) session.getAttribute("doctor");
            //lấy doctor để update

            //Lấy file từ jsp và up lên aws s3
            // Đường dẫn lưu trữ file
            String fileSavePath = AWSS3Client.fileSavePath; // Thay thế bằng đường dẫn thư mục lưu trữ file của bạn
// Đảm bảo thư mục tồn tại
            File fileSaveDir = new File(fileSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
// Lấy phần tải lên (upload) của file từ request
            Part part = req.getPart("file");
            long fileSize = part.getSize();
                long maxSize = 1024 * 1024 * 2; // 2MB
                if (fileSize > maxSize) {
                // Kích thước file vượt quá 2MB, xử lý thông báo lỗi tại đây
                req.setAttribute("messError", "Kích thước file không được vượt quá 2MB");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                return;
            }
            if (part != null && part.getSize() > 0) {
                String fileName = part.getSubmittedFileName();
// Lưu file vào đường dẫn đã chỉ định
                String filePath = fileSavePath + File.separator + fileName;
                part.write(filePath);
                // Gọi AWS
                //lưu file trên s3 với tên của user
                String KEY = doctor.getAccount().getUsername(); // Set the KEY according to the username

                String accessKeyID = ACCESS_KEY_ID; // Replace with your actual AWS access key
                String secretAccessKey = SECRET_ACCESS_KEY; // Replace with your actual AWS secret access key
                AwsCredentials awsCredentials = new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return accessKeyID;
                    }

                    @Override
                    public String secretAccessKey() {
                        return secretAccessKey;
                    }
                };
                AwsCredentialsProvider awsCredentialsProvider = new AwsCredentialsProvider() {
                    @Override
                    public AwsCredentials resolveCredentials() {
                        return awsCredentials;
                    }
                };

                S3Client s3Client = S3Client.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                S3Presigner s3Presigner = S3Presigner.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                //Put object to S3
                putObject(
                        s3Client, BUCKET_NAME, KEY,
                        Files.readAllBytes(Paths.get(filePath)));

                //Gen presignUrl
                var request =
                        GetObjectPresignRequest.builder()
                                .signatureDuration(Duration.ofDays(7))
                                .getObjectRequest(d -> d.bucket(BUCKET_NAME).key(KEY))
                                .build();
                String presignUrl = s3Presigner.presignGetObject(request).url().toString();
//kết thúc việc tải file và lấy link của file

                //nếu có thay đổi về ảnh sẽ được update
                doctor.setUrl(presignUrl);
            }
            doctor.getAccount().setStatus(status);
            doctor.getAccount().setPhone(phone);
            doctor.getAccount().setEmail(email);
            doctor.getAccount().setPassword(pass);
            //
            doctor.setName(name);
            doctor.setGender(gender);
            doctor.setDob(Date.valueOf(dob));
            doctor.setSpecialty(specialty);
            doctor.setRankId(Integer.parseInt(rank));
            adb.UpdateDoctor(doctor);

            req.setAttribute("messSuccess", "Cập nhật thành công");
            session.removeAttribute("pid");
            session.removeAttribute("sid");
            session.removeAttribute("staff");
            session.removeAttribute("patient");
            session.setAttribute("doctor", doctor);
            req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);

        } else if (pid != null) {
            String pass = req.getParameter("password");
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String dob = req.getParameter("dob");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String rank = req.getParameter("rank");
            Boolean status = Boolean.parseBoolean(req.getParameter("status"));

            // Validate name: should not contain special characters
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                req.setAttribute("messError", "Phone sai định dạng");
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                req.setAttribute("messError", "Email sai định dạng");
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                return;
            }
            AccountDB adb = new AccountDB();
            Patient patient = (Patient) session.getAttribute("patient");

            //Lấy file từ jsp và up lên aws s3
            // Đường dẫn lưu trữ file
            String fileSavePath = AWSS3Client.fileSavePath; // Thay thế bằng đường dẫn thư mục lưu trữ file của bạn
// Đảm bảo thư mục tồn tại
            File fileSaveDir = new File(fileSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
// Lấy phần tải lên (upload) của file từ request
            Part part = req.getPart("file");
            long fileSize = part.getSize();
            long maxSize = 1024 * 1024 * 2; // 2MB
            if (fileSize > maxSize) {
                // Kích thước file vượt quá 2MB, xử lý thông báo lỗi tại đây
                req.setAttribute("messError", "Kích thước file không được vượt quá 2MB");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                return;
            }
            if (part != null && part.getSize() > 0) {
                String fileName = part.getSubmittedFileName();
// Lưu file vào đường dẫn đã chỉ định
                String filePath = fileSavePath + File.separator + fileName;
                part.write(filePath);
                // Gọi AWS
                // lưu file trên s3 với tên của user
                String KEY = patient.getAccount().getUsername(); // Set the KEY according to the username

                String accessKeyID = ACCESS_KEY_ID; // Replace with your actual AWS access key
                String secretAccessKey = SECRET_ACCESS_KEY; // Replace with your actual AWS secret access key
                AwsCredentials awsCredentials = new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return accessKeyID;
                    }

                    @Override
                    public String secretAccessKey() {
                        return secretAccessKey;
                    }
                };
                AwsCredentialsProvider awsCredentialsProvider = new AwsCredentialsProvider() {
                    @Override
                    public AwsCredentials resolveCredentials() {
                        return awsCredentials;
                    }
                };

                S3Client s3Client = S3Client.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                S3Presigner s3Presigner = S3Presigner.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                //Put object to S3
                putObject(
                        s3Client, BUCKET_NAME, KEY,
                        Files.readAllBytes(Paths.get(filePath)));

                //Gen presignUrl
                var request =
                        GetObjectPresignRequest.builder()
                                .signatureDuration(Duration.ofDays(7))
                                .getObjectRequest(d -> d.bucket(BUCKET_NAME).key(KEY))
                                .build();
                String presignUrl = s3Presigner.presignGetObject(request).url().toString();
//kết thúc việc tải file và lấy link của file

//nếu có thay đổi về ảnh sẽ được update
                patient.setUrl(presignUrl);
            }
            patient.getAccount().setStatus(status);
            patient.getAccount().setPhone(phone);
            patient.getAccount().setEmail(email);
            patient.getAccount().setPassword(pass);
            //
            patient.setName(name);
            patient.setGender(gender);
            patient.setDob(Date.valueOf(dob));
            patient.setRankId(Integer.parseInt(rank));
            adb.UpdatePatient(patient);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            session.removeAttribute("did");
            session.removeAttribute("sid");
            session.removeAttribute("staff");
            session.removeAttribute("doctor");
            session.setAttribute("patient", patient);
            req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
        } else if (sid != null) {
            String pass = req.getParameter("password");
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String dob = req.getParameter("dob");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            Boolean status = Boolean.parseBoolean(req.getParameter("status"));

            // Validate name: should not contain special characters
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                req.setAttribute("messError", "Phone sai định dạng");
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                req.setAttribute("messError", "Email sai định dạng");
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                return;
            }
            AccountDB adb = new AccountDB();
            Staff staff = (Staff) session.getAttribute("staff");

            //Lấy file từ jsp và up lên aws s3
            // Đường dẫn lưu trữ file
            String fileSavePath = AWSS3Client.fileSavePath; // Thay thế bằng đường dẫn thư mục lưu trữ file của bạn
// Đảm bảo thư mục tồn tại
            File fileSaveDir = new File(fileSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
// Lấy phần tải lên (upload) của file từ request
            Part part = req.getPart("file");
            long fileSize = part.getSize();
            long maxSize = 1024 * 1024 * 2; // 2MB
            if (fileSize > maxSize) {
                // Kích thước file vượt quá 2MB, xử lý thông báo lỗi tại đây
                req.setAttribute("messError", "Kích thước file không được vượt quá 2MB");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                return;
            }
            if (part != null && part.getSize() > 0) {
                String fileName = part.getSubmittedFileName();
// Lưu file vào đường dẫn đã chỉ định
                String filePath = fileSavePath + File.separator + fileName;
                part.write(filePath);
                // Gọi AWS
                //lưu file trên s3 với tên của user
                String KEY = staff.getAccount().getUsername(); // Set the KEY according to the username

                String accessKeyID = ACCESS_KEY_ID; // Replace with your actual AWS access key
                String secretAccessKey = SECRET_ACCESS_KEY; // Replace with your actual AWS secret access key
                AwsCredentials awsCredentials = new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return accessKeyID;
                    }

                    @Override
                    public String secretAccessKey() {
                        return secretAccessKey;
                    }
                };
                AwsCredentialsProvider awsCredentialsProvider = new AwsCredentialsProvider() {
                    @Override
                    public AwsCredentials resolveCredentials() {
                        return awsCredentials;
                    }
                };

                S3Client s3Client = S3Client.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                S3Presigner s3Presigner = S3Presigner.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                //Put object to S3
                putObject(
                        s3Client, BUCKET_NAME, KEY,
                        Files.readAllBytes(Paths.get(filePath)));

                //Gen presignUrl
                var request =
                        GetObjectPresignRequest.builder()
                                .signatureDuration(Duration.ofDays(7))
                                .getObjectRequest(d -> d.bucket(BUCKET_NAME).key(KEY))
                                .build();
                String presignUrl = s3Presigner.presignGetObject(request).url().toString();
//kết thúc việc tải file và lấy link của file

                //nếu có thay đổi về ảnh sẽ được update
                staff.setUrl(presignUrl);
            }
            staff.getAccount().setStatus(status);
            staff.getAccount().setPhone(phone);
            staff.getAccount().setEmail(email);
            staff.getAccount().setPassword(pass);
            //
            staff.setName(name);
            staff.setGender(gender);
            staff.setDob(Date.valueOf(dob));
            adb.UpdateStaff(staff);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            session.removeAttribute("pid");
            session.removeAttribute("did");
            session.removeAttribute("doctor");
            session.removeAttribute("patient");
            session.setAttribute("staff", staff);
            req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
        }
        //Add
        String str = (String) session.getAttribute("str");
        switch (str) {
            case "doctor": {
                String user = req.getParameter("username");
                String pass = req.getParameter("password");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String dob = req.getParameter("dob");
                String specialty = req.getParameter("speciality");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                String rank = req.getParameter("rank");
                Boolean status = Boolean.parseBoolean(req.getParameter("status"));
                // Validate user: should not contain special characters
                if (!user.matches("^[a-zA-Z0-9_]*$")) {
                    req.setAttribute("messError", "User không được chứa ký tự đặc biệt");
                    req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                    return;
                }
                // Validate name: should not contain special characters
                if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                    req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                    req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                    return;
                }
                // Validate specialty: should not contain special characters
                if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                    req.setAttribute("messError", "Speciality không được chứa ký tự đặc biệt");
                    req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                    return;
                }
                // Validate phone: should only contain numbers and not exceed 10 digits
                if (!phone.matches("^[0-9]{10}$")) {
                    req.setAttribute("messError", "Phone sai định dạng");
                    req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                    return;
                }
                // Validate email: should be in the correct email format
                if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                    req.setAttribute("messError", "Email sai định dạng");
                    req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                    return;
                }
                AccountDB adb = new AccountDB();
                if (adb.checkAccountExist(user) == null) {
                    Account account = new Account();
                    account.setStatus(status);
                    account.setPhone(phone);
                    account.setEmail(email);
                    account.setUsername(user);
                    account.setPassword(pass);
                    account.setIsAdmin(1);
                    Doctor doctor = new Doctor();
                    doctor.setUserName(user);
                    doctor.setName(name);
                    doctor.setGender(gender);
                    doctor.setDob(Date.valueOf(dob));
                    doctor.setSpecialty(specialty);
                    doctor.setRankId(Integer.parseInt(rank));
                    doctor.setAccount(account);
                    //Lấy file từ jsp và up lên aws s3
                    // Đường dẫn lưu trữ file
                    String fileSavePath = AWSS3Client.fileSavePath; // Thay thế bằng đường dẫn thư mục lưu trữ file của bạn
// Đảm bảo thư mục tồn tại
                    File fileSaveDir = new File(fileSavePath);
                    if (!fileSaveDir.exists()) {
                        fileSaveDir.mkdirs();
                    }
// Lấy phần tải lên (upload) của file từ request
                    Part part = req.getPart("file");
                    long fileSize = part.getSize();
                    long maxSize = 1024 * 1024 * 2; // 2MB
                    if (fileSize > maxSize) {
                        // Kích thước file vượt quá 2MB, xử lý thông báo lỗi tại đây
                        req.setAttribute("messError", "Kích thước file không được vượt quá 2MB");
                        req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                        return;
                    }
                    if (part != null && part.getSize() > 0) {
                        String fileName = part.getSubmittedFileName();
// Lưu file vào đường dẫn đã chỉ định
                        String filePath = fileSavePath + File.separator + fileName;
                        part.write(filePath);
                        // Gọi AWS
                        //lưu file trên s3 với tên của user
                        String KEY = user; // Set the KEY according to the username

                        String accessKeyID = ACCESS_KEY_ID; // Replace with your actual AWS access key
                        String secretAccessKey = SECRET_ACCESS_KEY; // Replace with your actual AWS secret access key
                        AwsCredentials awsCredentials = new AwsCredentials() {
                            @Override
                            public String accessKeyId() {
                                return accessKeyID;
                            }

                            @Override
                            public String secretAccessKey() {
                                return secretAccessKey;
                            }
                        };
                        AwsCredentialsProvider awsCredentialsProvider = new AwsCredentialsProvider() {
                            @Override
                            public AwsCredentials resolveCredentials() {
                                return awsCredentials;
                            }
                        };

                        S3Client s3Client = S3Client.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                        S3Presigner s3Presigner = S3Presigner.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                        //Put object to S3
                        putObject(
                                s3Client, BUCKET_NAME, KEY,
                                Files.readAllBytes(Paths.get(filePath)));

                        //Gen presignUrl
                        var request =
                                GetObjectPresignRequest.builder()
                                        .signatureDuration(Duration.ofDays(7))
                                        .getObjectRequest(d -> d.bucket(BUCKET_NAME).key(KEY))
                                        .build();
                        String presignUrl = s3Presigner.presignGetObject(request).url().toString();
//kết thúc việc tải file và lấy link của file

                        //set url cho doctor nếu có upload ảnh lên
                        doctor.setUrl(presignUrl);
                    }

                    adb.addNewDoctor(doctor);
                    req.setAttribute("messSuccess", "Tạo tài khoản thành công");
                    req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                } else {
                    req.setAttribute("messError", "User đã tồn tại");
                    req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
                }
                break;
            }
            case "patient": {
                String user = req.getParameter("username");
                String pass = req.getParameter("password");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String dob = req.getParameter("dob");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                String rank = req.getParameter("rank");
                Boolean status = Boolean.parseBoolean(req.getParameter("status"));
                // Validate user: should not contain special characters
                if (!user.matches("^[a-zA-Z0-9_]*$")) {
                    req.setAttribute("messError", "User không được chứa ký tự đặc biệt");
                    req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                    return;
                }
                // Validate name: should not contain special characters
                if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                    req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                    req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                    return;
                }
                // Validate phone: should only contain numbers and not exceed 10 digits
                if (!phone.matches("^[0-9]{10}$")) {
                    req.setAttribute("messError", "Phone sai định dạng");
                    req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                    return;
                }
                // Validate email: should be in the correct email format
                if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                    req.setAttribute("messError", "Email sai định dạng");
                    req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                    return;
                }
                AccountDB adb = new AccountDB();
                if (adb.checkAccountExist(user) == null) {
                    Account account = new Account();
                    account.setStatus(status);
                    account.setPhone(phone);
                    account.setEmail(email);
                    account.setUsername(user);
                    account.setPassword(pass);
                    account.setIsAdmin(2);
                    Patient patient = new Patient();
                    patient.setUserName(user);
                    patient.setName(name);
                    patient.setGender(gender);
                    patient.setDob(Date.valueOf(dob));
                    patient.setRankId(Integer.parseInt(rank));
                    patient.setAccount(account);
                    //Lấy file từ jsp và up lên aws s3
                    // Đường dẫn lưu trữ file
                    String fileSavePath = AWSS3Client.fileSavePath; // Thay thế bằng đường dẫn thư mục lưu trữ file của bạn
// Đảm bảo thư mục tồn tại
                    File fileSaveDir = new File(fileSavePath);
                    if (!fileSaveDir.exists()) {
                        fileSaveDir.mkdirs();
                    }
// Lấy phần tải lên (upload) của file từ request
                    Part part = req.getPart("file");
                    long fileSize = part.getSize();
                    long maxSize = 1024 * 1024 * 2; // 2MB
                    if (fileSize > maxSize) {
                        // Kích thước file vượt quá 2MB, xử lý thông báo lỗi tại đây
                        req.setAttribute("messError", "Kích thước file không được vượt quá 2MB");
                        req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                        return;
                    }
                    if (part != null && part.getSize() > 0) {
                        String fileName = part.getSubmittedFileName();
// Lưu file vào đường dẫn đã chỉ định
                        String filePath = fileSavePath + File.separator + fileName;
                        part.write(filePath);
                        // Gọi AWS
                        //lưu file trên s3 với tên của user
                        String KEY = user; // Set the KEY according to the username

                        String accessKeyID = ACCESS_KEY_ID; // Replace with your actual AWS access key
                        String secretAccessKey = SECRET_ACCESS_KEY; // Replace with your actual AWS secret access key
                        AwsCredentials awsCredentials = new AwsCredentials() {
                            @Override
                            public String accessKeyId() {
                                return accessKeyID;
                            }

                            @Override
                            public String secretAccessKey() {
                                return secretAccessKey;
                            }
                        };
                        AwsCredentialsProvider awsCredentialsProvider = new AwsCredentialsProvider() {
                            @Override
                            public AwsCredentials resolveCredentials() {
                                return awsCredentials;
                            }
                        };

                        S3Client s3Client = S3Client.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                        S3Presigner s3Presigner = S3Presigner.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                        //Put object to S3
                        putObject(
                                s3Client, BUCKET_NAME, KEY,
                                Files.readAllBytes(Paths.get(filePath)));

                        //Gen presignUrl
                        var request =
                                GetObjectPresignRequest.builder()
                                        .signatureDuration(Duration.ofDays(7))
                                        .getObjectRequest(d -> d.bucket(BUCKET_NAME).key(KEY))
                                        .build();
                        String presignUrl = s3Presigner.presignGetObject(request).url().toString();
//kết thúc việc tải file và lấy link của file
//set url cho patient nếu có upload ảnh lên
                        patient.setUrl(presignUrl);
                    }

                    adb.addNewPatient(patient);
                    req.setAttribute("messSuccess", "Tạo tài khoản thành công");
                    req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                } else {
                    req.setAttribute("messError", "User đã tồn tại");
                    req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req, resp);
                }
                break;
            }
            case "staff": {
                String user = req.getParameter("username");
                String pass = req.getParameter("password");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String dob = req.getParameter("dob");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                Boolean status = Boolean.parseBoolean(req.getParameter("status"));
                // Validate user: should not contain special characters
                if (!user.matches("^[a-zA-Z0-9_]*$")) {
                    req.setAttribute("messError", "User không được chứa ký tự đặc biệt");
                    req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                    return;
                }
                // Validate name: should not contain special characters
                if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                    req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                    req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                    return;
                }
                // Validate phone: should only contain numbers and not exceed 10 digits
                if (!phone.matches("^[0-9]{10}$")) {
                    req.setAttribute("messError", "Phone sai định dạng");
                    req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                    return;
                }
                // Validate email: should be in the correct email format
                if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                    req.setAttribute("messError", "Email sai định dạng");
                    req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                    return;
                }
                AccountDB adb = new AccountDB();
                if (adb.checkAccountExist(user) == null) {
                    Account account = new Account();
                    account.setStatus(status);
                    account.setPhone(phone);
                    account.setEmail(email);
                    account.setUsername(user);
                    account.setPassword(pass);
                    account.setIsAdmin(3);
                    Staff staff = new Staff();
                    staff.setUserName(user);
                    staff.setName(name);
                    staff.setGender(gender);
                    staff.setDob(Date.valueOf(dob));
                    staff.setAccount(account);
                    //Lấy file từ jsp và up lên aws s3
                    // Đường dẫn lưu trữ file
                    String fileSavePath = AWSS3Client.fileSavePath; // Thay thế bằng đường dẫn thư mục lưu trữ file của bạn
// Đảm bảo thư mục tồn tại
                    File fileSaveDir = new File(fileSavePath);
                    if (!fileSaveDir.exists()) {
                        fileSaveDir.mkdirs();
                    }
// Lấy phần tải lên (upload) của file từ request
                    Part part = req.getPart("file");
                    long fileSize = part.getSize();
                    long maxSize = 1024 * 1024 * 2; // 2MB
                    if (fileSize > maxSize) {
                        // Kích thước file vượt quá 2MB, xử lý thông báo lỗi tại đây
                        req.setAttribute("messError", "Kích thước file không được vượt quá 2MB");
                        req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                        return;
                    }
                    if (part != null && part.getSize() > 0) {
                        String fileName = part.getSubmittedFileName();
// Lưu file vào đường dẫn đã chỉ định
                        String filePath = fileSavePath + File.separator + fileName;
                        part.write(filePath);
                        // Gọi AWS
                        //lưu file trên s3 với tên của user
                        String KEY = user; // Set the KEY according to the username

                        String accessKeyID = ACCESS_KEY_ID; // Replace with your actual AWS access key
                        String secretAccessKey = SECRET_ACCESS_KEY; // Replace with your actual AWS secret access key
                        AwsCredentials awsCredentials = new AwsCredentials() {
                            @Override
                            public String accessKeyId() {
                                return accessKeyID;
                            }

                            @Override
                            public String secretAccessKey() {
                                return secretAccessKey;
                            }
                        };
                        AwsCredentialsProvider awsCredentialsProvider = new AwsCredentialsProvider() {
                            @Override
                            public AwsCredentials resolveCredentials() {
                                return awsCredentials;
                            }
                        };

                        S3Client s3Client = S3Client.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                        S3Presigner s3Presigner = S3Presigner.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
                        //Put object to S3
                        putObject(
                                s3Client, BUCKET_NAME, KEY,
                                Files.readAllBytes(Paths.get(filePath)));

                        //Gen presignUrl
                        var request =
                                GetObjectPresignRequest.builder()
                                        .signatureDuration(Duration.ofDays(7))
                                        .getObjectRequest(d -> d.bucket(BUCKET_NAME).key(KEY))
                                        .build();
                        String presignUrl = s3Presigner.presignGetObject(request).url().toString();
//kết thúc việc tải file và lấy link của file
                        //set url cho staff nếu có upload ảnh lên
                        staff.setUrl(presignUrl);
                    }

                    adb.addNewStaff(staff);
                    req.setAttribute("messSuccess", "Tạo tài khoản thành công");
                    req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                } else {
                    req.setAttribute("messError", "User đã tồn tại");
                    req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req, resp);
                }
                break;
            }
            default:
                resp.sendRedirect("admin_dashboard");
                break;
        }
    }
}
