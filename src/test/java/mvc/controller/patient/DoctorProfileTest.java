package mvc.controller.patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.PatientDBContext;
import mvc.model.Account;
import mvc.model.Doctor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DoctorProfileTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void testDoGetWithValidAccount() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        Account account = new Account();
        Doctor doctor = new Doctor();
        DoctorProfile doctorProfile = new DoctorProfile();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 2) {
            when(request.getParameter("id")).thenReturn("1");
            PatientDBContext patientDBContext = new PatientDBContext();
            when(patientDBContext.getDoctorByPatient("1")).thenReturn(doctor);
            when(request.getRequestDispatcher("view/patient/doctor-profile.jsp")).thenReturn(requestDispatcher);
            doctorProfile .doGet(request, response);
            verify(requestDispatcher).forward(request,response);
        }

        doctorProfile .doGet(request, response);
        verify(request).getRequestDispatcher("login");
    }
}