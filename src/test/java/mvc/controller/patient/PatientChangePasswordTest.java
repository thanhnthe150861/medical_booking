package mvc.controller.patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.AccountDB;
import mvc.dal.PatientDBContext;
import mvc.model.Account;
import mvc.model.Patient;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientChangePasswordTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;


    @Test
    public void testDoGet() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        PatientChangePassword patientChangePassword = new PatientChangePassword();
        Account account = (Account) session.getAttribute("doctor");
        PatientDBContext patientDBContext = new PatientDBContext();
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 2) {
            Patient patient = patientDBContext.getPatient(account);
            when(request.getRequestDispatcher("view/patient/patient-change-password.jsp")).thenReturn(requestDispatcher);
            patientChangePassword.doGet(request,response);
            verify(session).setAttribute("patient", patient);
            verify(requestDispatcher).forward(request,response);
        }
        patientChangePassword.doGet(request,response);
        verify(response).sendRedirect("login");
    }

    @Test
    public void testDOPost() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        PatientChangePassword patientChangePassword = new PatientChangePassword();
        AccountDB adb = new AccountDB();
        String oldpassword = request.getParameter("old-password");
        String newpassword = request.getParameter("new-password");
        String repassword = request.getParameter("re-password");
        when(request.getParameter(oldpassword)).thenReturn("password");
        when(request.getParameter(newpassword)).thenReturn("newpassword");
        when(request.getParameter(repassword)).thenReturn("newpassword");
        Account account = new Account();
        account.setPassword("123124");
        if(account.getPassword()==oldpassword){
            if (newpassword==repassword) {
                account.setPassword(newpassword);
                adb.UpdateAccount(account);
                when(request.getRequestDispatcher("view/patient/patient-change-password.jsp")).thenReturn(requestDispatcher);
                patientChangePassword.doPost(request,response);
                verify(request).setAttribute("mess", "Update successful");
                verify(requestDispatcher).forward(request,response);
            }else {
                patientChangePassword.doPost(request,response);
                verify(request).setAttribute("mess", "Confirm password incorrect");

            }
        }else {
//            patientChangePassword.doPost(request,response);
//            verify(request).setAttribute("mess", "Password incorrect");

        }




    }

}