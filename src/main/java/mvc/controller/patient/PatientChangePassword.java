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
import service.HashMD5;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "PatientChangePassword", value = "/patient_change_password")
public class PatientChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 2) {
            PatientDBContext patientDBContext = new PatientDBContext();
            Patient patient = patientDBContext.getPatient(account);
            session.setAttribute("patient", patient);
            req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req, resp);
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
                req.setAttribute("messError", "Mật khẩu mới không được trùng mật khẩu cũ");
                req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req, resp);
            } else if (newpassword.equals(repassword)) {
                try {
                    account.setPassword(HashMD5.hashMD5(newpassword));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                adb.UpdateAccount(account);
                req.setAttribute("messSuccess", "Cập nhật thành công");
                req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req, resp);
            } else {
                req.setAttribute("messError", "Xác nhận mật khẩu mới phải trùng mật khẩu mới");
                req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("messError", "Mật khẩu cũ sai");
            req.getRequestDispatcher("view/patient/patient-change-password.jsp").forward(req, resp);
        }
    }
}
