package controller;

import dal.AccountDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

import java.io.IOException;

@WebServlet(name = "reset", value = "/reset")
public class resetpassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.getAttribute("account");
        req.getRequestDispatcher("view/login/reset-password.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDB adb = new AccountDB();
        String password = req.getParameter("password");
        String rePassword = req.getParameter("repassword");
        if(password.equals(rePassword)){
            account.setPassword(password);
            adb.UpdateAccount(account);
            req.setAttribute("mess", "Successfully");
            resp.sendRedirect("login");
        }

    }
}
