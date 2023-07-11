package controller.login;

import dal.AccountDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class LoginTest {
    @Test
    public void testDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("view/login/login.jsp")).thenReturn(dispatcher);
        Login loginServlet = new Login();
        loginServlet.doGet(request, response);
        verify(session).invalidate();
        verify(dispatcher).forward(request, response);
    }
    @Test
    public void testDoPostWithAdminAccount() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AccountDB accountDB = mock(AccountDB.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Account account = new Account();
        account.setIsAdmin(0);
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("passwork")).thenReturn("123");
        when(request.getSession()).thenReturn(session);
        when(accountDB.getAccount("username", "passwork")).thenReturn(account);
        when(request.getRequestDispatcher("view/login/login.jsp")).thenReturn(dispatcher);
        Login loginServlet = new Login();
        loginServlet.doPost(request, response);
        verify(dispatcher).forward(request, response);
    }
    @Test
    public void testDoPostWithDoctorAccount() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AccountDB accountDB = mock(AccountDB.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Account account = new Account();
        account.setIsAdmin(1);
        when(request.getParameter("username")).thenReturn("doctor");
        when(request.getParameter("passwork")).thenReturn("123");
        when(request.getSession()).thenReturn(session);
        when(accountDB.getAccount("username", "passwork")).thenReturn(account);
        when(request.getRequestDispatcher("view/login/login.jsp")).thenReturn(dispatcher);
        Login loginServlet = new Login();
        loginServlet.doPost(request, response);
        verify(dispatcher).forward(request, response);
    }
    @Test
    public void testDoPostWithRegularAccount() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AccountDB accountDB = mock(AccountDB.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Account account = new Account();
        account.setIsAdmin(1);
        when(request.getParameter("username")).thenReturn("patient");
        when(request.getParameter("passwork")).thenReturn("123");
        when(request.getSession()).thenReturn(session);
        when(accountDB.getAccount("username", "passwork")).thenReturn(account);
        when(request.getRequestDispatcher("view/login/login.jsp")).thenReturn(dispatcher);
        Login loginServlet = new Login();
        loginServlet.doPost(request, response);
        verify(dispatcher).forward(request, response);
    }
}