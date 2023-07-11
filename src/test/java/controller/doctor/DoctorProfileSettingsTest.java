package controller.doctor;

import dal.DoctorDBContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Doctor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DoctorProfileSettingsTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private Account account;
    private Doctor doctor;
    private DoctorDBContext doctorDBContext;
    private DoctorProfileSettings doctorProfileSettings;
    @Before
    public void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        account = Mockito.mock(Account.class);
        doctor = Mockito.mock(Doctor.class);
        doctorDBContext = Mockito.mock(DoctorDBContext.class);
        doctorProfileSettings = new DoctorProfileSettings();
    }
    @Test
    public void testDoPost() throws Exception {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        account = Mockito.mock(Account.class);
        doctor = Mockito.mock(Doctor.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(session.getAttribute("doctor")).thenReturn(doctor);
        when(request.getParameter("file")).thenReturn("fileName");
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("phone")).thenReturn("1234567890");
        when(request.getParameter("gender")).thenReturn("Male");
        when(request.getParameter("dob")).thenReturn("1990-01-01");

        verify(response).sendRedirect("doctor_profile_settings");
        verify(doctor).setUrl("fileName");
        verify(doctor).setName("John Doe");
        verify(doctor).setDob(Date.valueOf("1990-01-01"));
        verify(doctor).setGender("Male");
        verify(account).setPhone("1234567890");
    }

}