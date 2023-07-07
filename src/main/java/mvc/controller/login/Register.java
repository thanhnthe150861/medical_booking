package mvc.controller.login;

import mvc.dal.AccountDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.model.Account;

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
//        String phone_raw = req.getParameter("phone");
        String email_raw = req.getParameter("email");
        // Validate user_raw: should not contain special characters
        if (!user_raw.matches("^[a-zA-Z0-9_]*$")) {
            req.setAttribute("messError", "User không được chứa ký tự đặc biệt");
            req.getRequestDispatcher("view/login/register.jsp").forward(req,resp);
            return;
        }
        // Validate name_raw: should not contain special characters
        if (!name_raw.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
            req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
            req.getRequestDispatcher("view/login/register.jsp").forward(req,resp);
            return;
        }
        // Validate email_raw: should be in the correct email format
        if (!email_raw.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
            req.setAttribute("messError", "Email sai định dạng");
            req.getRequestDispatcher("view/login/register.jsp").forward(req,resp);
            return;
        }
        AccountDB adb = new AccountDB();
        if (adb.checkAccountExist(user_raw) == null) {
            Account account = new Account();
            account.setUsername(user_raw);
            account.setPassword(pass_raw);
//            account.setPhone(phone_raw);
            account.setEmail(email_raw);
            account.setIsAdmin(2);
            adb.Register(account, name_raw);
            req.setAttribute("messSuccess", "Tạo tài khoản thành công");
            req.getRequestDispatcher("view/login/register.jsp").forward(req,resp);
        } else {
            req.setAttribute("messError", "User đã tồn tại");
            req.getRequestDispatcher("view/login/register.jsp").forward(req,resp);
        }
    }
}
