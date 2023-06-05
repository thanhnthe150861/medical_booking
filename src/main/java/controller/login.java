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

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_raw = req.getParameter("username");
        String pass_raw = req.getParameter("password");
        AccountDB adb = new AccountDB();
        Account account = adb.getAccount(user_raw, pass_raw);
        HttpSession session = req.getSession();
        if(account == null){
            session.setAttribute("mess", "Login Fail");
            req.getRequestDispatcher("view/login.jsp").forward(req,resp);
        }
        session.setAttribute("account", account);
        req.getRequestDispatcher("view/tb.jsp").forward(req,resp);
    }
}
