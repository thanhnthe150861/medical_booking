
        package mvc.controller.login;

        import jakarta.servlet.RequestDispatcher;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        import jakarta.servlet.http.HttpSession;
        import mvc.dal.AccountDB;
        import mvc.model.Account;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

        import java.io.IOException;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.when;

class ForgotTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void testDoGet_ForwardingException() throws ServletException, IOException {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(request.getRequestDispatcher("view/login/forgot.jsp")).thenReturn(requestDispatcher);
        // Act
        Forgot forgot = new Forgot();

        forgot.doGet(request, response);
        // Assert (expected exception)
        verify(request).getRequestDispatcher("view/login/forgot.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        when(request.getParameter(username)).thenReturn("username");
        when(request.getParameter(email)).thenReturn("email");
        AccountDB accountDB = new AccountDB();
        Account account = accountDB.checkAccountExist(email);
        when(request.getSession()).thenReturn(session);
        if (account != null) {
            if (account.getEmail().equals("email@gmail.com")) {
                Forgot forgot = new Forgot();
                when(request.getRequestDispatcher("view/login/forgot.jsp")).thenReturn(requestDispatcher);
                forgot.doPost(request, response);
                verify(session).setAttribute("account", account);
                verify(request).getRequestDispatcher("reset_password");
            } else {
                Forgot forgot = new Forgot();
                when(request.getRequestDispatcher("view/login/forgot.jsp")).thenReturn(requestDispatcher);
                forgot.doPost(request, response);
                verify(session).setAttribute("messError", "Email không đúng");
                verify(request).getRequestDispatcher("view/login/forgot.jsp").forward(request, response);
            }
        }
    }
}