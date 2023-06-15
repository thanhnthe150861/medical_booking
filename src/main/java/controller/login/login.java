package controller.login;

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
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("view/login/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_raw = req.getParameter("username");
        String pass_raw = req.getParameter("password");
        AccountDB adb = new AccountDB();
        Account account = adb.getAccount(user_raw, pass_raw);
        HttpSession session = req.getSession();
        if(account == null){
            session.setAttribute("mess", "User or Password incorrect");
            req.getRequestDispatcher("view/login/login.jsp").forward(req,resp);
        }else{
            if(account.getIsAdmin() == 0){// Admin
                session.setAttribute("account", account);
                resp.sendRedirect("home");
            }else if(account.getIsAdmin() == 1){// Doctor
                session.setAttribute("account", account);
                resp.sendRedirect("home");
            } else if (account.getIsAdmin() == 2) {// Client
                session.setAttribute("account", account);
                resp.sendRedirect("home");
            }

        }

    }
}
