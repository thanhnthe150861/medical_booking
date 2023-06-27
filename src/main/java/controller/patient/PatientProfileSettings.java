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
import model.Patient;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "PatientProfileSettings", value = "/patient_profile_settings")
public class PatientProfileSettings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 2){
            PatientDBContext patientDBContext = new PatientDBContext();
            Patient patient = patientDBContext.getPatient(account);
            session.setAttribute("patient", patient);
        req.getRequestDispatcher("view/patient/patient-profile-settings.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        Patient patient = (Patient) session.getAttribute("patient");
        //
        String fileName = req.getParameter("file");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        Date dob = Date.valueOf(req.getParameter("dob"));
        //
        patient.setUrl(fileName);
        patient.setName(name);
        patient.setDob(dob);
        patient.setGender(gender);
        account.setPhone(phone);
        account.setEmail(email);
        PatientDBContext patientDBContext = new PatientDBContext();
        patientDBContext.UpdatePatient(account, patient);
        resp.sendRedirect("patient_profile_settings");
    }
}
