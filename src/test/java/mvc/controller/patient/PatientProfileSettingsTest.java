package mvc.controller.patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.PatientDBContext;
import mvc.model.Account;
import mvc.model.Patient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PatientProfileSettingsTest {
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
        when(request.getSession()).thenReturn(session);
        PatientProfileSettings patientProfileSettings = new PatientProfileSettings();
        Account account = (Account) session.getAttribute("account");
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 2){
            PatientDBContext patientDBContext = new PatientDBContext();
            Patient patient = patientDBContext.getPatient(account);
            when(request.getRequestDispatcher("view/patient/patient-profile-settings.jsp")).thenReturn(requestDispatcher);
            patientProfileSettings.doGet(request,response);
            verify(session).setAttribute("patient", patient);
            verify(requestDispatcher).forward(request,response);
        }
        patientProfileSettings.doGet(request,response);
        verify(response).sendRedirect("login");
    }

    @Test
    public void testValidName() {
        String validName = "patient";
        boolean isValid = validName.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");
        Assert.assertTrue(isValid);
    }
    @Test
    public void testValidEmail() {
        String validEmail = "test@example.com";
        boolean isValid = validEmail.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");
        Assert.assertTrue(isValid);
    }
    @Test
    public void testValidPhone() {
        String validPhone = "0868746@#$2";
        boolean isValid = validPhone.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");
        Assert.assertFalse(isValid);
    }

}