package mvc.controller.patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.PatientDBContext;
import mvc.model.Account;
import mvc.model.Doctor;
import mvc.model.Patient;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyDoctorTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void testDoGetWithAdminAccount() throws ServletException, IOException {
        // Arrange
        MockitoAnnotations.initMocks(this);
        Account account = (Account) session.getAttribute("account");
        when(session.getAttribute("account")).thenReturn(account);
        when(request.getSession()).thenReturn(session);
        if (account != null && account.getIsAdmin() == 2){
            PatientDBContext patientDBContext = new PatientDBContext();
            Patient patient = patientDBContext.getPatient(account);
            List<Doctor> doctorList = patientDBContext.getMydoctor(patient, "Completed");
            when(request.getRequestDispatcher("view/patient/my-doctor.jsp")).thenReturn(requestDispatcher);
            MyDoctor doctor = new MyDoctor();
            doctor.doGet(request,response);
            verify(session).setAttribute("patient", patient);
            verify(session).setAttribute("doctorList", doctorList);
            verify(request).getRequestDispatcher("view/patient/my-doctor.jsp").forward(request,response);
        }

    }

}