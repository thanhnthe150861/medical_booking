package controller;

import dal.AccountDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_raw = req.getParameter("username");
        String pass_raw = req.getParameter("password");
        AccountDB adb = new AccountDB();
        Account account = adb.getAccount(user_raw, pass_raw);
        if(account == null){
            req.setAttribute("mess", "fail");
            req.getRequestDispatcher("view/tb.jsp").forward(req,resp);
        }else{
            req.setAttribute("account", account);
            req.setAttribute("mess", "success");
            req.getRequestDispatcher("view/tb.jsp").forward(req,resp);
        }
    }
}
