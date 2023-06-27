package controller.patient;

import dal.PatientDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Booking;
import model.Patient;

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
            List<Booking> bookingList = patientDBContext.getBooking(patient, "Completed");
            session.setAttribute("patient", patient);
            session.setAttribute("bookingList", bookingList);
            req.getRequestDispatcher("view/patient/my-doctor.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
