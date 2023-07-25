package mvc.controller.staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.StaffDBContext;
import mvc.model.Account;
import mvc.model.Booking;
import mvc.model.MedicalRecord;
import mvc.model.Staff;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "CreateInvoice", value = "/create_invoice")
public class CreateInvoice extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc != null && acc.getIsAdmin() == 3) {
            StaffDBContext sdb = new StaffDBContext();
            Staff staff = sdb.getStaff(acc);
            session.setAttribute("staff", staff);
            //Lấy ngày hôm nay
            LocalDate today = LocalDate.now();
            String date = today.toString();
            List<MedicalRecord> bookingList = sdb.getBookingDoneOnDay(date);
            session.setAttribute("bookingList", bookingList);
            req.getRequestDispatcher("view/staff/create-invoice.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("login");
    }

}