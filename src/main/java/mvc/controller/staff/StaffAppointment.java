package mvc.controller.staff;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import mvc.model.Account;

import java.io.IOException;

@WebServlet(name = "StaffAppointment", value = "/staff_appointment")
public class StaffAppointment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("account");
        if(acc != null && acc.getIsAdmin() == 3){
            req.getRequestDispatcher("view/staff/staff-appointment.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
