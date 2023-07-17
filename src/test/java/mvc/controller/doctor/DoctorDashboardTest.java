
package mvc.controller.doctor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DoctorDashboardTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testDoGetWithAdminAccount() throws Exception {
        DoctorDashboard doctorDashboard = new DoctorDashboard();
        MockitoAnnotations.initMocks(this);
        Account account = new Account();
        account.setIsAdmin(1);
        account.setUsername("doctor");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(request.getRequestDispatcher("view/doctor/doctor-dashboard.jsp")).thenReturn(requestDispatcher);
        // Act
        doctorDashboard.doGet(request, response);
        // Assert
        verify(requestDispatcher).forward(request, response);
    }
}