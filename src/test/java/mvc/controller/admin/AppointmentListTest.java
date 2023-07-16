package mvc.controller.admin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import mvc.model.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AppointmentListTest {
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
    public void testdoGet()throws Exception{
        MockitoAnnotations.initMocks(this);
        AppointmentList appointmentList = new AppointmentList();
        Account account = new Account();
        account.setIsAdmin(0);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(request.getRequestDispatcher("view/admin/appointment-list.jsp")).thenReturn(requestDispatcher);
        appointmentList.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
        assertEquals(session.getAttribute("account"), account);
        assertEquals(request.getRequestDispatcher("view/admin/appointment-list.jsp"),requestDispatcher);
    }
}