package mvc.controller.patient;

import mvc.dal.PatientDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import mvc.model.Booking;
import mvc.model.Doctor;
import mvc.model.Patient;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyDoctor", value = "/my_doctor")
public class MyDoctor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 2){
            PatientDBContext patientDBContext = new PatientDBContext();
            Patient patient = patientDBContext.getPatient(account);
            List<Doctor> doctorList = patientDBContext.getMydoctor(patient, "Completed");
            session.setAttribute("patient", patient);
            session.setAttribute("doctorList", doctorList);
            req.getRequestDispatcher("view/patient/my-doctor.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
