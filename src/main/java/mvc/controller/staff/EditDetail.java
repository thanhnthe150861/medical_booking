package mvc.controller.staff;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
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
@WebServlet(name = "EditDetail", value = "/edit_detail")
public class EditDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDB adb = new AccountDB();
        if (account != null && account.getIsAdmin() == 3) {
            List<Rank> rankListDoctor = adb.getRankDoctor();
            List<Rank> rankListPatient = adb.getRankPatient();
            session.setAttribute("rankListDoctor", rankListDoctor);
            session.setAttribute("rankListPatient", rankListPatient);

            //lấy id để update
            String did = request.getParameter("did");
            String pid = request.getParameter("pid");

            //sau khi cập nhật xong thì xóa session đi để ko bị dính vào add
            session.removeAttribute("doctor");
            session.removeAttribute("patient");

            //Update
            if (did != null) {
                session.setAttribute("did", did);
                Doctor doctor = adb.getDoctorByID(did);
                session.setAttribute("doctor", doctor);
                request.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(request, response);
            } else if (pid != null) {
                session.setAttribute("pid", pid);
                Patient patient = adb.getPatientByID(pid);
                session.setAttribute("patient", patient);
                request.getRequestDispatcher("view/staff/patient-detail.jsp").forward(request, response);
            }

            //Add
            String str = request.getParameter("str");
            if (str == null) {
                str = "";
            }
            if (str.equals("doctor")) {
                session.setAttribute("str", str);
                request.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(request, response);
            } else if (str.equals("patient")) {
                session.setAttribute("str", str);
                request.getRequestDispatcher("view/staff/patient-detail.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("staff_dashboard");
            }
        } else {
            request.getRequestDispatcher("login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //Update
        String did = (String) session.getAttribute("did");
        String pid = (String) session.getAttribute("pid");
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
                req.setAttribute("messError", "Tên không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(req, resp);
                return;
            }
            // Validate specialty: should not contain special characters
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                req.setAttribute("messError", "Chuyên môn không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(req, resp);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                req.setAttribute("messError", "Số điện thoại sai định dạng");
                req.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(req, resp);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                req.setAttribute("messError", "Email sai định dạng");
                req.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(req, resp);
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
                req.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(req, resp);
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
            session.removeAttribute("patient");
            session.setAttribute("doctor", doctor);
            req.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(req, resp);

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
                req.setAttribute("messError", "Tên không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/staff/patient-details.jsp").forward(req, resp);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                req.setAttribute("messError", "Số điện thoại sai định dạng");
                req.getRequestDispatcher("view/staff/patient-details.jsp").forward(req, resp);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                req.setAttribute("messError", "Email sai định dạng");
                req.getRequestDispatcher("view/staff/patient-details.jsp").forward(req, resp);
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
                req.getRequestDispatcher("view/staff/patient-details.jsp").forward(req, resp);
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
            session.removeAttribute("doctor");
            session.setAttribute("patient", patient);
            req.getRequestDispatcher("view/staff/patient-details.jsp").forward(req, resp);
        }
    }
}
