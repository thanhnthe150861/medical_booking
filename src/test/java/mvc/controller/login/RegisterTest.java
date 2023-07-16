package mvc.controller.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.dal.AccountDB;
import mvc.model.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class RegisterTest {
    @Test
    public void testDoGet() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("view/login/register.jsp")).thenReturn(dispatcher);
        Register registerServlet = new Register();
        registerServlet.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher("view/login/register.jsp");
        verify(dispatcher).forward(request, response);
    }
    @Test
    public void testDoPost() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AccountDB accountDB = mock(AccountDB.class);
        Account account = new Account();
        when(request.getParameter("username")).thenReturn("testuser");
        when(request.getParameter("password")).thenReturn("testpass");
        when(request.getParameter("name")).thenReturn("Test User");
        when(request.getParameter("phone")).thenReturn("1234567890");
        when(request.getParameter("email")).thenReturn("testuser@example.com");
        Register registerServlet = new Register();
        registerServlet.doPost(request, response);
        verify(accountDB, times(1)).Register(account, "Test User");
        // Add additional verification for expected behavior
    }
}