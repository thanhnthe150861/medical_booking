package mvc.controller.doctor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.Account;
import mvc.model.Doctor;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PatientProfileTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private DoctorDBContext doctorDBContext;
    private Account account;
    private Doctor doctor;
    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);
        doctorDBContext = mock(DoctorDBContext.class);
        account = mock(Account.class);
        doctor = mock(Doctor.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(doctorDBContext.getDoctor(account)).thenReturn(doctor);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }
    @Test
    public void shouldRedirectToLoginIfNotAdmin() throws ServletException, IOException, IOException {
        if (account != null && account.getIsAdmin() == 1) {
            PatientProfile profile = new PatientProfile();
            profile.doGet(request, response);
            verify(response).sendRedirect("login");
        }
    }
}