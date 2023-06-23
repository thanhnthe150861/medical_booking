package controller.doctor;

import dal.DoctorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Account;
import model.Doctor;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "doctor_profile_settings", value = "/doctor_profile_settings")
public class doctor_profile_settings  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 1){
            DoctorDBContext doctorDBContext = new DoctorDBContext();
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            req.getRequestDispatcher("view/doctor/doctor-profile-settings.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        //
        String fileName = req.getParameter("file");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");
        Date dob = Date.valueOf(req.getParameter("dob"));
        //
        doctor.setUrl(fileName);
        doctor.setName(name);
        doctor.setDob(dob);
        doctor.setGender(gender);
        account.setPhone(phone);
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        doctorDBContext.updateDoctor(account, doctor);
        resp.sendRedirect("doctor_profile_settings");
    }
}
