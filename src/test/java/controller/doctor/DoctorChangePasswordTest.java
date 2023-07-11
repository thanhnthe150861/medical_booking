package controller.doctor;

import dal.AccountDB;
import dal.DoctorDBContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Doctor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DoctorChangePasswordTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private Doctor doctor;
    @Mock
    private Account account;
    @Mock
    private DoctorDBContext doctorDBContext;
    @Mock
    private AccountDB accountDB;
    private DoctorChangePassword doctorChangePassword;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorChangePassword = new DoctorChangePassword();
    }
    @Test
    public void testDoPostWithMatchingPasswords() throws Exception {
        DoctorChangePassword doctorChangePassword = new DoctorChangePassword();
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(account.getPassword()).thenReturn("old-password");
        when(request.getParameter("old-password")).thenReturn("old-password");
        when(request.getParameter("new-password")).thenReturn("new-password");
        when(request.getParameter("re-password")).thenReturn("new-password");
        when(request.getRequestDispatcher("view/doctor/doctor-change-password.jsp")).thenReturn(requestDispatcher);
        doctorChangePassword.doPost(request, response);
        verify(request).setAttribute("mess", "Update successful");
        verify(requestDispatcher).forward(request, response);
    }
    @Test
    public void testDoPostWithNonMatchingPasswords() throws Exception {
        DoctorChangePassword doctorChangePassword = new DoctorChangePassword();
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(account.getPassword()).thenReturn("old-password");
        when(request.getParameter("old-password")).thenReturn("old-password");
        when(request.getParameter("new-password")).thenReturn("new-password");
        when(request.getParameter("re-password")).thenReturn("different-password");
        doctorChangePassword.doPost(request, response);
        verify(request).setAttribute("mess", "Confirm password incorrect");
    }
    @Test
    public void testDoPostWithIncorrectOldPassword() throws Exception {
        DoctorChangePassword doctorChangePassword = new DoctorChangePassword();
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(account.getPassword()).thenReturn("old-password");
        when(request.getParameter("old-password")).thenReturn("incorrect-password");
        doctorChangePassword.doPost(request, response);
        verify(request).setAttribute("mess", "Password incorrect");
    }
    @Test
    public void testDoGet() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(session.getAttribute("account")).thenReturn(account);
        when(account.getIsAdmin()).thenReturn(1);
        when(doctorDBContext.getDoctor(account)).thenReturn(doctor);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("view/doctor/doctor-change-password.jsp")).thenReturn(requestDispatcher);
        DoctorChangePassword doctorChangePassword = new DoctorChangePassword();
        doctorChangePassword.doGet(request, response);
//        verify(session).setAttribute("doctor", doctor);
        verify(requestDispatcher).forward(request, response);
    }
}