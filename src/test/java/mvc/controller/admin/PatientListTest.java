
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
        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.when;

class PatientListTest {
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
        PatientList patientList = new  PatientList();
        Account account = new Account();
        account.setIsAdmin(0);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(request.getRequestDispatcher("view/admin/patient-list.jsp")).thenReturn(requestDispatcher);
        patientList.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
        verify(response).sendRedirect("login");
    }
}