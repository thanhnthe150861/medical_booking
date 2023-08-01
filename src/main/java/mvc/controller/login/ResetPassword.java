package mvc.controller.login;

import mvc.dal.AccountDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import service.HashMD5;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "ResetPassword", value = "/reset_password")
public class ResetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.getAttribute("account");
        req.getRequestDispatcher("view/login/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDB adb = new AccountDB();
        String password = req.getParameter("password");
        String rePassword = req.getParameter("repassword");
        if (password.equals(rePassword)) {
            try {
                account.setPassword(HashMD5.hashMD5(password));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            adb.UpdateAccount(account);
            req.setAttribute("mess", "Successfully");
            resp.sendRedirect("login");
        }

    }
}
