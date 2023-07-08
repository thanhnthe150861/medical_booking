package mvc.controller.login;

import mvc.dal.AccountDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;

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
        String email = req.getParameter("email");
        AccountDB adb = new AccountDB();
        Account account = adb.checkAccountExist(user);
        HttpSession session = req.getSession();
        if (account != null){
            if (account.getEmail().equals(email)){
                session.setAttribute("account", account);
                resp.sendRedirect("reset_password");
            }else {
                req.setAttribute("messError", "Email không đúng");
                req.getRequestDispatcher("view/login/forgot.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("messError", "User không tồn tại");
            req.getRequestDispatcher("view/login/forgot.jsp").forward(req,resp);
        }

    }
}
