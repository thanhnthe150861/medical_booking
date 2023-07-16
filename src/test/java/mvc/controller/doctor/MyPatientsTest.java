package mvc.controller.doctor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.Account;
import mvc.model.Doctor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MyPatientsTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private Account account;
    @Mock
    private DoctorDBContext doctorDBContext;
    @Mock
    private Doctor doctor;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testDoGetWithAdminAccount() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 1) {
            when(doctorDBContext.getDoctor(account)).thenReturn(doctor);
            when(doctorDBContext.getMyPatient(doctor, "Completed")).thenReturn(new ArrayList<>());
            when(request.getRequestDispatcher("view/doctor/my-patients.jsp")).thenReturn(requestDispatcher);
            MyPatients myPatients = new MyPatients();
            myPatients.doGet(request, response);
            verify(request).setAttribute("patientList", new ArrayList<>());
            verify(requestDispatcher).forward(request, response);
        }
    }
}