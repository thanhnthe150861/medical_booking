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
import java.util.List;

@WebServlet(name = "FormDetails", value = "/form_details")
public class FormDetails  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDB adb = new AccountDB();
        if (account != null && account.getIsAdmin() == 0){
            List<Rank> rankListDoctor = adb.getRankDoctor();
            List<Rank> rankListPatient = adb.getRankPatient();
            session.setAttribute("rankListDoctor",rankListDoctor);
            session.setAttribute("rankListPatient",rankListPatient);

            //lấy id để update
            String did = req.getParameter("did");
            String pid = req.getParameter("pid");
            String sid = req.getParameter("sid");
            //sau khi cập nhật xong thì xóa session đi để ko bị dính vào add
            session.removeAttribute("doctor");
            session.removeAttribute("patient");
            session.removeAttribute("staff");
            //Update
            if(did != null){
                session.setAttribute("did", did);
                Doctor doctor = adb.getDoctorByID(did);
                session.setAttribute("doctor", doctor);
                req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);
            } else if (pid != null){
                session.setAttribute("pid", pid);
                Patient patient = adb.getPatientByID(pid);
                session.setAttribute("patient", patient);
                req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
            } else if (sid != null){
                session.setAttribute("sid", sid);
                Staff staff = adb.getStaffByID(sid);
                session.setAttribute("staff", staff);
                req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
            }
            //Add
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
            } else {
                resp.sendRedirect("admin_dashboard");
            }
        }else {
            resp.sendRedirect("login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //Update
        String did = (String) session.getAttribute("did");
        String pid = (String) session.getAttribute("pid");
        String sid = (String) session.getAttribute("sid");
        if(did != null ){
            String pass = req.getParameter("password");
            String url = req.getParameter("file");
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
            Doctor doctor = (Doctor) session.getAttribute("doctor");
            doctor.getAccount().setStatus(status);
            doctor.getAccount().setPhone(phone);
            doctor.getAccount().setEmail(email);
            doctor.getAccount().setPassword(pass);
            doctor.getAccount().setIsAdmin(1);
            //
            doctor.setUrl(url);
            doctor.setName(name);
            doctor.setGender(gender);
            doctor.setDob(Date.valueOf(dob));
            doctor.setSpecialty(specialty);
            doctor.setRankId(Integer.parseInt(rank));
            adb.UpdateDoctor(doctor);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            session.removeAttribute("pid");
            session.removeAttribute("sid");
            req.getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(req,resp);

        } else if (pid != null) {
            String pass = req.getParameter("password");
            String url = req.getParameter("file");
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
            Patient patient = (Patient) session.getAttribute("patient");
            patient.getAccount().setStatus(status);
            patient.getAccount().setPhone(phone);
            patient.getAccount().setEmail(email);
            patient.getAccount().setPassword(pass);
            patient.getAccount().setIsAdmin(1);
            //
            patient.setUrl(url);
            patient.setName(name);
            patient.setGender(gender);
            patient.setDob(Date.valueOf(dob));
            patient.setRankId(Integer.parseInt(rank));
            adb.UpdatePatient(patient);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            session.removeAttribute("did");
            session.removeAttribute("sid");
            req.getRequestDispatcher("view/admin/form-patient-details.jsp").forward(req,resp);
        } else if (sid != null) {
            String pass = req.getParameter("password");
            String url = req.getParameter("file");
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String dob = req.getParameter("dob");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            Boolean status = Boolean.parseBoolean(req.getParameter("status"));

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
            Staff staff = (Staff) session.getAttribute("staff");
            staff.getAccount().setStatus(status);
            staff.getAccount().setPhone(phone);
            staff.getAccount().setEmail(email);
            staff.getAccount().setPassword(pass);
            staff.getAccount().setIsAdmin(1);
            //
            staff.setUrl(url);
            staff.setName(name);
            staff.setGender(gender);
            staff.setDob(Date.valueOf(dob));
            adb.UpdateStaff(staff);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            session.removeAttribute("pid");
            session.removeAttribute("did");
            req.getRequestDispatcher("view/admin/form-staff-details.jsp").forward(req,resp);
        }
        //Add
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
                account.setIsAdmin(3);
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
