package mvc.controller.doctor;

import mvc.dal.DoctorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mvc.model.Account;
import mvc.model.Doctor;


import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "DoctorProfileSettings", value = "/doctor_profile_settings")
public class DoctorProfileSettings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1){
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            req.getRequestDispatcher("view/doctor/doctor-profile-settings.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        //
        String fileName = req.getParameter("file");
        String name = req.getParameter("name");
        // Validate name_raw: should not contain special characters
        if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
            req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
            req.getRequestDispatcher("view/doctor/doctor-profile-settings.jsp").forward(req,resp);
            return;
        }
        String phone = req.getParameter("phone");
                // Validate phone_raw: should only contain numbers and not exceed 10 digits
        if (!phone.matches("^[0-9]{10}$")) {
            req.setAttribute("messError", "Phone sai định dạng");
            req.getRequestDispatcher("view/doctor/doctor-profile-settings.jsp").forward(req,resp);
            return;
        }
        String gender = req.getParameter("gender");
        Date dob = Date.valueOf(req.getParameter("dob"));
        //
        doctor.setUrl(fileName);
        doctor.setName(name);
        doctor.setDob(dob);
        doctor.setGender(gender);
        account.setPhone(phone);
        doctor.setAccount(account);
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        doctorDBContext.updateDoctor(doctor);
        resp.sendRedirect("doctor_profile_settings");
    }
}
