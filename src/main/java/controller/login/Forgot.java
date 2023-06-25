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

@WebServlet(name = "Forgot", value = "/forgot")
public class Forgot extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login/forgot.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String phone = req.getParameter("phone_number");
        String email = req.getParameter("email");
        AccountDB adb = new AccountDB();
        Account account = adb.checkAccountExist(user);
        HttpSession session = req.getSession();
        if (account != null){
            if (account.getEmail().equalsIgnoreCase(email) && account.getPhone().equalsIgnoreCase(phone)){
                session.setAttribute("account", account);
                resp.sendRedirect("reset_password");
            }
        }
        req.setAttribute("mess", "Phone or Email incorrect!!!");
        req.getRequestDispatcher("view/login/forgot.jsp").forward(req,resp);
    }
}
