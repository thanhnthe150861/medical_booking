package mvc.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.AccountDB;
import mvc.dal.PatientDBContext;
import mvc.dal.StaffDBContext;
import mvc.model.Account;
import mvc.model.Patient;
import mvc.model.Staff;
import service.HashMD5;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("view/login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userRaw = req.getParameter("username");
        String passRaw = req.getParameter("password");

        String hash_pass = null;
        Account account;
        HttpSession session = req.getSession();
        try {
            hash_pass = HashMD5.hashMD5(passRaw);


            AccountDB adb = new AccountDB();
            account = adb.getAccount(userRaw, hash_pass);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (account == null) {
            req.setAttribute("messError", "Tài khoản hoặc mật khẩu sai");
            req.getRequestDispatcher("view/login/login.jsp").forward(req, resp);
        } else if (!account.getStatus()) {
            req.setAttribute("messError", "Tài khoản bị vô hiệu hóa");
            req.getRequestDispatcher("view/login/login.jsp").forward(req, resp);
        } else {
            session.setAttribute("account", account);
            if (account.getIsAdmin() == 0) {
                resp.sendRedirect("admin_dashboard");
            } else if (account.getIsAdmin() == 1) {
                resp.sendRedirect("doctor_dashboard");
            } else if (account.getIsAdmin() == 2) {
                PatientDBContext patientDBContext = new PatientDBContext();
                Patient patient = patientDBContext.getPatient(account);
                session.setAttribute("patient", patient);
                resp.sendRedirect("home");
            } else if (account.getIsAdmin() == 3) {
                StaffDBContext staffDBContext = new StaffDBContext();
                Staff staff = staffDBContext.getStaff(account);
                session.setAttribute("staff", staff);
                resp.sendRedirect("staff_dashboard");
            }
        }

    }
}
