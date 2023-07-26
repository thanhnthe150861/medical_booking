package mvc.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.AccountDB;
import mvc.model.Account;
import service.HashMD5;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Profile", value = "/profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 0){
        req.getRequestDispatcher("view/admin/profile.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("login");
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
            if (newpassword.equals(oldpassword)) {
                req.setAttribute("messError", "New password must not be same with old password");
                req.getRequestDispatcher("view/admin/profile.jsp").forward(req, resp);
            } else if (newpassword.equals(repassword)) {
                try {
                    account.setPassword(HashMD5.hashMD5(newpassword));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                adb.UpdateAccount(account);
                req.setAttribute("messSuccess", "Update successful");
                req.getRequestDispatcher("view/admin/profile.jsp").forward(req, resp);
            } else {
                req.setAttribute("messError", "Confirm password incorrect");
                req.getRequestDispatcher("view/admin/profile.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("messError", "Password incorrect");
            req.getRequestDispatcher("view/admin/profile.jsp").forward(req, resp);
        }
    }
}
