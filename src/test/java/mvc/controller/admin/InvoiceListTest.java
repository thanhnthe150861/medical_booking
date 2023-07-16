package mvc.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class InvoiceListTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void testDoGet() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        Account account = new Account();
        account.setIsAdmin(0);
        InvoiceList invoiceList = new InvoiceList();
        when(session.getAttribute("account")).thenReturn(account);
        when(request.getRequestDispatcher("view/admin/invoice-report.jsp")).thenReturn(requestDispatcher);
        invoiceList.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
        verify(response).sendRedirect("login");
    }
}