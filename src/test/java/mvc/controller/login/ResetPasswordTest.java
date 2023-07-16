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

class ResetPasswordTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher requestDispatcher;
    @Test
    public void testDoGet() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("view/login/reset-password.jsp")).thenReturn(requestDispatcher);
        ResetPassword  resetPassword = new ResetPassword();
        resetPassword.doGet(request, response);
        verify(request).getRequestDispatcher("view/login/reset-password.jsp");
        verify(requestDispatcher).forward(request, response);
    }
    @Test
    public void testDoPost() throws IOException, ServletException {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        Account account = new Account();
        when(session.getAttribute("account")).thenReturn(account);
        AccountDB accountDB = new AccountDB();
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        when(request.getParameter(password)).thenReturn("password");
        when(request.getParameter(repassword)).thenReturn("repassword");
        if(password=="repassword"){
            account.setPassword(password);
            accountDB.UpdateAccount(account);
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.doPost(request,response);
            verify(request).setAttribute("mess", "Successfully");
            verify(response).sendRedirect("login");
        }
    }
}