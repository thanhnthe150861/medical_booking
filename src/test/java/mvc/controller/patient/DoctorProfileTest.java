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
    public void testDoGet() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        Account account = (Account) session.getAttribute("account");
        when(session.getAttribute("account")).thenReturn(account);
        when(request.getSession()).thenReturn(session);
        PatientDBContext patientDBContext = new PatientDBContext();
        if (account != null && account.getIsAdmin() == 2){
            String id = request.getParameter("id");
            when(request.getParameter("id")).thenReturn("id");
            when(request.getRequestDispatcher("view/patient/doctor-profile.jsp")).thenReturn(requestDispatcher);
            Doctor doctor = patientDBContext.getDoctorByPatient(id);
            DoctorProfile doctorProfile = new DoctorProfile();
            doctorProfile.doGet(request,response);
            verify(session).setAttribute("doctor",doctor);
            verify(request).getRequestDispatcher("view/patient/doctor-profile.jsp").forward(request,response);
        }
        DoctorProfile doctorProfile = new DoctorProfile();
        doctorProfile.doGet(request,response);
        verify(response).sendRedirect("login");
    }
}