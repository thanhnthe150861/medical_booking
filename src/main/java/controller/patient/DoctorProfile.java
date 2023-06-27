package controller.patient;

import dal.DoctorDBContext;
import dal.PatientDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Doctor;
import model.MedicalRecord;
import model.Patient;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DoctorProfile", value = "/doctor_profile")
public class DoctorProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        PatientDBContext patientDBContext = new PatientDBContext();
        if (account != null && account.getIsAdmin() == 2){
            // Lấy giá trị của các tham số id từ liên kết
            String id = req.getParameter("id");
            Doctor doctor = patientDBContext.getDoctorByPatient(id);
            session.setAttribute("doctor", doctor);
            req.getRequestDispatcher("view/patient/doctor-profile.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
