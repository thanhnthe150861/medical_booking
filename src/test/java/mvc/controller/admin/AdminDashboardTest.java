package mvc.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdminDashboardTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;
    private AdminDashboard adminDashboard;
    @Test
    public void testDoGetTrue() throws Exception {
        MockitoAnnotations.initMocks(this);
        AdminDashboard adminDashboard = new AdminDashboard();
        Account account = new Account();
        account.setIsAdmin(0);
        account.setUsername("Admin");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(request.getRequestDispatcher("view/admin/admin-dashboard.jsp")).thenReturn(requestDispatcher);
        adminDashboard.doGet(request, response);
        verify(requestDispatcher).forward(request, response);

    }
}