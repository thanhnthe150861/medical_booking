package mvc.controller.patient;

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

import java.io.IOException;

@WebServlet(name = "PatientChangePassword", value = "/patient_change_password")
public class PatientChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 2){
            PatientDBContext patientDBContext = new PatientDBContext();
            Patient patient = patientDBContext.getPatient(account);
            session.setAttribute("patient", patient);
            req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDB adb = new AccountDB();
        String oldpassword = req.getParameter("old-password");
        String newpassword = req.getParameter("new-password");
        String repassword = req.getParameter("re-password");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account.getPassword().equals(oldpassword)) {
            if (newpassword.equals(oldpassword)) {
                req.setAttribute("messError", "New password must not be same with old password");
                req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req, resp);
            } else if (newpassword.equals(repassword)) {
                account.setPassword(newpassword);
                adb.UpdateAccount(account);
                req.setAttribute("messSuccess", "Update successful");
                req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req, resp);
            } else {
                req.setAttribute("messError", "Confirm password incorrect");
                req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("messError", "Password incorrect");
            req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req, resp);
        }
    }
}
