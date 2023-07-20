package mvc.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;

import java.io.IOException;

@WebServlet(name = "PatientList", value = "/patient_list")
public class PatientList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 0) {
            req.getRequestDispatcher("view/admin/patient-list.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("login");
    }
}
