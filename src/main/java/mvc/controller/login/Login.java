package mvc.controller.login;

import mvc.dal.AccountDB;
import mvc.dal.PatientDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import mvc.model.Patient;
import mvc.model.Staff;

import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
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
            req.setAttribute("messError", "Tài khoản hoặc mật khẩu sai");
            req.getRequestDispatcher("view/login/login.jsp").forward(req,resp);
        }else{
            if(account.getIsAdmin() == 0){
                session.setAttribute("account", account);
                resp.sendRedirect("admin_dashboard");
            } else if (account.getIsAdmin() == 1){
                session.setAttribute("account", account);
                resp.sendRedirect("doctor_dashboard");
            } else if (account.getIsAdmin() == 2){
                session.setAttribute("account", account);
                PatientDBContext patientDBContext = new PatientDBContext();
                Patient patient = patientDBContext.getPatient(account);
                session.setAttribute("patient", patient);
                resp.sendRedirect("home");
            }else if (account.getIsAdmin() == 3){
                session.setAttribute("account", account);

                Staff staff = null;
                session.setAttribute("staff", staff);
                resp.sendRedirect("home");
            }
        }

    }
}
