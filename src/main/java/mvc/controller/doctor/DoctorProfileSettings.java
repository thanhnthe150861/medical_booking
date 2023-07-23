package mvc.controller.doctor;

import jakarta.servlet.annotation.MultipartConfig;
import mvc.dal.DoctorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mvc.model.Account;
import mvc.model.Doctor;
import service.AWSS3Client;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Duration;

import static service.AWSS3Client.*;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 50 //50MB
)
@WebServlet(name = "DoctorProfileSettings", value = "/doctor_profile_settings")
public class DoctorProfileSettings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1) {
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            req.getRequestDispatcher("view/doctor/doctor-profile-settings.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        //
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");
        // Validate name_raw: should not contain special characters
        if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
            req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
            req.getRequestDispatcher("view/doctor/doctor-profile-settings.jsp").forward(req, resp);
            return;
        }
        // Validate phone_raw: should only contain numbers and not exceed 10 digits
        if (!phone.matches("^[0-9]{10}$")) {
            req.setAttribute("messError", "Phone sai định dạng");
            req.getRequestDispatcher("view/doctor/doctor-profile-settings.jsp").forward(req, resp);
            return;
        }
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
            req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req, resp);
            return;
        }
        if (part != null && part.getSize() > 0) {
            String fileName = part.getSubmittedFileName();
// Lưu file vào đường dẫn đã chỉ định
            String filePath = fileSavePath + File.separator + fileName;
            part.write(filePath);
            // Gọi AWS
            //lưu file trên s3 với tên của user
            String KEY = account.getUsername(); // Set the KEY according to the username

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

            doctor.setUrl(presignUrl);
        }
        doctor.setName(name);
        doctor.setDob(Date.valueOf(dob));
        doctor.setGender(gender);
        account.setPhone(phone);
        doctor.setAccount(account);
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        doctorDBContext.updateDoctor(doctor);
        resp.sendRedirect("doctor_profile_settings");
    }
}
