package controller.login;

import dal.AccountDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_raw = req.getParameter("username");
        String pass_raw = req.getParameter("password");
        String name_raw = req.getParameter("name");
        String phone_raw = req.getParameter("phone");
        String email_raw = req.getParameter("email");
        AccountDB adb = new AccountDB();
        Account account = new Account();
        account.setUsername(user_raw);
        account.setPassword(pass_raw);
        account.setPhone(phone_raw);
        account.setEmail(email_raw);
        account.setIsAdmin(2);
        adb.insertClient(account, name_raw);
        //
        req.getRequestDispatcher("view/login/register.jsp").forward(req,resp);
    }
}
