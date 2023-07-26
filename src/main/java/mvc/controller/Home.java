package mvc.controller;

import mvc.dal.AdminDBContext;
import mvc.dal.PatientDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import mvc.model.Doctor;
import mvc.model.MedicalRecord;
import mvc.model.Patient;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        AdminDBContext adb = new AdminDBContext();
        List<MedicalRecord> topDoctor = adb.getTop5Doctor();
        req.setAttribute("topDoctor", topDoctor);
        if (account != null) {
            PatientDBContext patientDBContext = new PatientDBContext();
            Patient patient = patientDBContext.getPatient(account);
            session.setAttribute("patient", patient);
        }
        req.getRequestDispatcher("view/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
