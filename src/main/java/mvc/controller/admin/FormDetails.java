package mvc.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.AccountDB;
import mvc.model.*;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "FormDetails", value = "/form_details")
public class FormDetails  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        Account account = (Account) session.getAttribute("account");
//        if (account != null && account.getIsAdmin() == 0){
            String str = req.getParameter("str");
            if(str == null){
                str = "";
            }
            if(str.equals("doctor")){
                session.setAttribute("str", str);
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
            } else if (str.equals("patient")) {
                session.setAttribute("str", str);
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
            } else if (str.equals("staff")) {
                session.setAttribute("str", str);
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
            }
                resp.sendRedirect("admin_dashboard");
//        }else {
//            resp.sendRedirect("login");
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String str = (String) session.getAttribute("str");
        if(str.equals("doctor")){
            String user = req.getParameter("username");
            String pass = req.getParameter("password");
            String url = req.getParameter("file");
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String dob = req.getParameter("dob");
            String specialty = req.getParameter("speciality");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            // Validate user: should not contain special characters
            if (!user.matches("^[a-zA-Z0-9_]*$")) {
                req.setAttribute("messError", "User không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                return;
            }
            // Validate name: should not contain special characters
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                return;
            }
            // Validate specialty: should not contain special characters
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                req.setAttribute("messError", "Speciality không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                req.setAttribute("messError", "Phone sai định dạng");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                req.setAttribute("messError", "Email sai định dạng");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
                return;
            }
            AccountDB adb = new AccountDB();
            if (adb.checkAccountExist(user) == null) {
                Account account = new Account();
                account.setStatus(true);
                account.setPhone(phone);
                account.setEmail(email);
                account.setUsername(user);
                account.setPassword(pass);
                account.setIsAdmin(1);
                Doctor doctor = new Doctor();
                doctor.setUserName(user);
                doctor.setUrl(url);
                doctor.setName(name);
                doctor.setGender(gender);
                doctor.setDob(Date.valueOf(dob));
                doctor.setSpecialty(specialty);
                doctor.setRankId(1);
                doctor.setAccount(account);
                adb.addNewDoctor(doctor);
                req.setAttribute("messSuccess", "Tạo tài khoản thành công");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
            } else {
                req.setAttribute("messError", "User đã tồn tại");
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
            }
        } else if (str.equals("patient")) {
            String user = req.getParameter("username");
            String pass = req.getParameter("password");
            String url = req.getParameter("file");
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String dob = req.getParameter("dob");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            // Validate user: should not contain special characters
            if (!user.matches("^[a-zA-Z0-9_]*$")) {
                req.setAttribute("messError", "User không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
                return;
            }
            // Validate name: should not contain special characters
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                req.setAttribute("messError", "Phone sai định dạng");
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                req.setAttribute("messError", "Email sai định dạng");
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
                return;
            }
            AccountDB adb = new AccountDB();
            if (adb.checkAccountExist(user) == null) {
                Account account = new Account();
                account.setStatus(true);
                account.setPhone(phone);
                account.setEmail(email);
                account.setUsername(user);
                account.setPassword(pass);
                account.setIsAdmin(2);
                Patient patient = new Patient();
                patient.setUserName(user);
                patient.setUrl(url);
                patient.setName(name);
                patient.setGender(gender);
                patient.setDob(Date.valueOf(dob));
                patient.setRankId(1);
                patient.setAccount(account);
                adb.addNewPatient(patient);
                req.setAttribute("messSuccess", "Tạo tài khoản thành công");
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
            } else {
                req.setAttribute("messError", "User đã tồn tại");
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
            }
        } else if (str.equals("staff")) {
            String user = req.getParameter("username");
            String pass = req.getParameter("password");
            String url = req.getParameter("file");
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String dob = req.getParameter("dob");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            // Validate user: should not contain special characters
            if (!user.matches("^[a-zA-Z0-9_]*$")) {
                req.setAttribute("messError", "User không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
                return;
            }
            // Validate name: should not contain special characters
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                req.setAttribute("messError", "Phone sai định dạng");
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                req.setAttribute("messError", "Email sai định dạng");
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
                return;
            }
            AccountDB adb = new AccountDB();
            if (adb.checkAccountExist(user) == null) {
                Account account = new Account();
                account.setStatus(true);
                account.setPhone(phone);
                account.setEmail(email);
                account.setUsername(user);
                account.setPassword(pass);
                account.setIsAdmin(2);
                Staff staff = new Staff();
                staff.setUserName(user);
                staff.setUrl(url);
                staff.setName(name);
                staff.setGender(gender);
                staff.setDob(Date.valueOf(dob));
                staff.setAccount(account);
                adb.addNewStaff(staff);
                req.setAttribute("messSuccess", "Tạo tài khoản thành công");
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
            } else {
                req.setAttribute("messError", "User đã tồn tại");
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
            }
        } else if (str == null){
            resp.sendRedirect("admin_dashboard");
        }
    }
}
