package mvc.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.Account;
import mvc.model.Doctor;
import mvc.model.MedicalRecord;

import java.io.IOException;
@WebServlet(name = "InvoiceView", value = "/invoice_view")
public class InvoiceView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        String bid = req.getParameter("bid");
        if (account != null && account.getIsAdmin() == 0){
            MedicalRecord bill = doctorDBContext.getTTByBillID(bid);
            req.setAttribute("bill", bill);
            req.getRequestDispatcher("view/doctor/invoice-view.jsp").forward(req,resp);
        }
        if (account != null && account.getIsAdmin() == 1){
            Doctor doctor = doctorDBContext.getDoctor(account);
            req.setAttribute("doctor", doctor);
            MedicalRecord bill = doctorDBContext.getTTByBillID(bid);
            req.setAttribute("bill", bill);
            req.getRequestDispatcher("view/doctor/invoice-view.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }
}
