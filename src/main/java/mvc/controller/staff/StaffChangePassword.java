package mvc.controller.staff;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import mvc.dal.AccountDB;
import mvc.dal.StaffDBContext;
import mvc.model.Account;
import mvc.model.Staff;

import java.io.IOException;

@WebServlet(name = "StaffChangePassword", value = "/staff_change_password")
public class StaffChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        StaffDBContext staffDBContext = new StaffDBContext();
        if (acc != null) {
            if (acc.getIsAdmin() == 3) {
                Staff staff = staffDBContext.getStaff(acc);
                session.setAttribute("staff", staff);
                request.getRequestDispatcher("view/staff/staff-change-password.jsp").forward(request, response);
            }
        }
        response.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDB accountDB = new AccountDB();
        String oldPass = request.getParameter("old-password");
        String newPass = request.getParameter("new-password");
        String rePass = request.getParameter("re-password");

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc.getPassword().equals(oldPass)) {
            if (newPass.equals(oldPass)) {
                request.getRequestDispatcher("view/staff/staff-change-password.jsp").forward(request, response);
            } else if (newPass.equals(rePass)) {
                acc.setPassword(newPass);
                accountDB.UpdateAccount(acc);
                request.getRequestDispatcher("view/staff/staff-dashboard.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("view/staff/staff-change-password.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("view/staff/staff-change-password.jsp").forward(request, response);
        }
    }
}
