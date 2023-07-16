package mvc.controller.staff;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import mvc.model.Account;

import java.io.IOException;

@WebServlet(name = "StaffDashboard", value = "/staff_dashboard")
public class StaffDashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc != null && acc.getIsAdmin() == 3) {
            request.getRequestDispatcher("view/staff/staff-dashboard.jsp").forward(request, response);
        }
        response.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
