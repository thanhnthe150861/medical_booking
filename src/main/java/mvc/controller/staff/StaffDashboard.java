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
import mvc.model.Staff;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "StaffDashboard", value = "/staff_dashboard")
public class StaffDashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc != null && acc.getIsAdmin() == 3) {
            StaffDBContext sdb = new StaffDBContext();
            Staff staff = sdb.getStaff(acc);
            session.setAttribute("staff", staff);
            List<Booking> bookingList = sdb.getBooking("Pending");
            session.setAttribute("bookingList", bookingList);
            String bid = req.getParameter("bid");
            String status = req.getParameter("status");
            if (bid != null && status != null) {
                sdb.updateBookingStatus(bid, status);
                req.setAttribute("messSuccess", "Cập nhật thành công");
                List<Booking> bookingList1 = sdb.getBooking("Pending");
                session.setAttribute("bookingList", bookingList1);
                req.getRequestDispatcher("view/staff/staff-dashboard.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("view/staff/staff-dashboard.jsp").forward(req, resp);
            }
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}