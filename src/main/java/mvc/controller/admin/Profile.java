package mvc.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.AccountDB;
import mvc.model.Account;

import java.io.IOException;

@WebServlet(name = "Profile", value = "/profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 0){
        req.getRequestDispatcher("view/admin/profile.jsp").forward(req,resp);
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
                req.getRequestDispatcher("view/admin/profile.jsp").forward(req,resp);
            }else {
                req.setAttribute("mess", "Confirm password incorrect");
            }
        }else {
            req.setAttribute("mess", "Password incorrect");
        }
    }
}
