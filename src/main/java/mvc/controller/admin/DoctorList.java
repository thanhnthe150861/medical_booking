package mvc.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DoctorList", value = "/doctor_list")
public class DoctorList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Account account = (Account) session.getAttribute("account");
//        if (account != null && account.getIsAdmin() == 0){
        req.getRequestDispatcher("view/admin/doctor-list.jsp").forward(req,resp);
//        }
//        resp.sendRedirect("login");
    }
}