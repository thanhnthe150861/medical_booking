package mvc.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.Account;
import mvc.model.Booking;
import mvc.model.Doctor;
import mvc.model.MedicalRecord;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "InvoiceDoctor", value = "/invoice_doctor")
public class InvoiceDoctor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1){
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            req.getRequestDispatcher("view/doctor/invoices.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("login");
    }
}
