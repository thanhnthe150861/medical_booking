package mvc.controller.staff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.PatientDBContext;
import mvc.model.Account;
import mvc.model.Patient;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "StaffProfileSettings", value = "/staff_profile_settings")
public class StaffProfileSettings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 3){


        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        //
        String fileName = req.getParameter("file");
        String name = req.getParameter("name");
        // Validate name_raw: should not contain special characters
        if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
            req.setAttribute("messError", "Name không được chứa ký tự đặc biệt");
            req.getRequestDispatcher("").forward(req,resp);
            return;
        }
        String phone = req.getParameter("phone");
        // Validate phone_raw: should only contain numbers and not exceed 10 digits
        if (!phone.matches("^[0-9]{10}$")) {
            req.setAttribute("messError", "Phone sai định dạng");
            req.getRequestDispatcher("").forward(req,resp);
            return;
        }
        String email = req.getParameter("email");
        // Validate email_raw: should be in the correct email format
        if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
            req.setAttribute("messError", "Email sai định dạng");
            req.getRequestDispatcher("").forward(req,resp);
            return;
        }
        String gender = req.getParameter("gender");
        Date dob = Date.valueOf(req.getParameter("dob"));
        //

    }
}
