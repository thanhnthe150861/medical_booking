package mvc.controller.staff;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import mvc.model.Account;

import java.io.IOException;

@WebServlet(name = "ListInvoice", value = "/list_invoice")
public class ListInvoice extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 3) {
            request.getRequestDispatcher("view/staff/list-invoice.jsp").forward(request, response);
        }
        request.getRequestDispatcher("login");
    }
}