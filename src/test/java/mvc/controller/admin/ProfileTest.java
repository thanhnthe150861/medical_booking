package mvc.controller.admin;

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

class ProfileTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Test
    public void testDoGetTrue() throws Exception {
        MockitoAnnotations.initMocks(this);
        Profile profile = new Profile();
        Account account = new Account();
        account.setIsAdmin(0);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(request.getRequestDispatcher("view/admin/profile.jsp")).thenReturn(requestDispatcher);
        profile.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public  void testDoPost() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        AccountDB accountDB = new AccountDB();
        Profile profile = new Profile();
        String oldpassword = request.getParameter("old-password");
        String newpassword = request.getParameter("new-password");
        String repassword = request.getParameter("re-password");
        when(request.getParameter(oldpassword)).thenReturn("old-Password");
        when(request.getParameter(newpassword)).thenReturn("new-Password");
        when(request.getParameter(repassword)).thenReturn("re-Password");
        when(request.getSession()).thenReturn(session);
        Account account = new Account();
        when(session.getAttribute("account")).thenReturn(account);
        account.setPassword("old-Password");
        if (account.getPassword().equals(oldpassword)){
            if (newpassword.equals(repassword)){
                when(account.getPassword()).thenReturn(newpassword);
                 // Create a sample account
                accountDB.UpdateAccount(account);
                when(request.getAttribute("mess")).thenReturn("Confirm password incorrect");
                when(request.getRequestDispatcher("view/admin/admin-dashboard.jsp")).thenReturn(requestDispatcher);
                profile.doPost(request, response);
                verify(requestDispatcher).forward(request, response);
                verify(request).setAttribute("mess", "Update successful");
            }else {
                profile.doPost(request, response);
                verify(request).setAttribute("mess", "Confirm password incorrect");
            }
        }else {
            profile.doPost(request, response);
            verify(request).setAttribute("mess", "Password incorrect");
        }
    }
}