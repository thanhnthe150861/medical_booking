package mvc.controller.staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.AccountDB;
import mvc.dal.StaffDBContext;
import mvc.model.Account;
import mvc.model.Staff;

import java.io.IOException;

@WebServlet(name = "StaffChangePassword", value = "/staff_change_password")
public class StaffChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 2){
            StaffDBContext staffDBContext = new StaffDBContext();
            Staff staff = staffDBContext.getStaff(account);
            session.setAttribute("staff", staff);
            req.getRequestDispatcher("view/staff/staff-change-password.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDB adb = new AccountDB();
        String oldpassword = req.getParameter("old-password");
        String newpassword = req.getParameter("new-password");
        String repassword = req.getParameter("re-password");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if(account.getPassword().equals(oldpassword)){
            if (newpassword.equals(repassword)) {
                account.setPassword(newpassword);
                adb.UpdateAccount(account);
                req.setAttribute("mess", "Update successful");
                req.getRequestDispatcher("view/staff/staff-change-password.jsp").forward(req,resp);
            }else {
                req.setAttribute("mess", "Confirm password incorrect");
            }
        }else {
            req.setAttribute("mess", "Password incorrect");
        }
    }
}
