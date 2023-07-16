package mvc.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;

import java.io.IOException;

@WebServlet(name = "InvoiceList", value = "/invoice_list")
public class InvoiceList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            if (account.getIsAdmin() == 0 || account.getIsAdmin() == 3) {
                req.getRequestDispatcher("view/admin/invoice-report.jsp").forward(req, resp);
            }
        }
        resp.sendRedirect("login");
    }
}
