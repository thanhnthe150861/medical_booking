package mvc.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.Account;
import mvc.model.Booking;
import mvc.model.Doctor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DoctorScheduleTimingsTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void testDoGet() throws Exception {
        // Set up mock objects
        MockitoAnnotations.initMocks(this);
        Account account = new Account();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 1) {
            DoctorDBContext doctorDBContext = mock(DoctorDBContext.class);
            Doctor doctor = new Doctor();
            List<Booking> bookingList = new ArrayList<>();
            when(doctorDBContext.getDoctor(account)).thenReturn(doctor);
            when(doctorDBContext.getBooking(doctor, "Confirmed")).thenReturn(bookingList);
            // Call the method to be tested
            DoctorScheduleTimings doctorScheduleTimings = new DoctorScheduleTimings();
            doctorScheduleTimings.doGet(request, response);
            // Verify the expected behavior
            verify(request, times(1)).setAttribute("bookingList", bookingList);
            verify(request, times(1)).getRequestDispatcher("view/doctor/doctor-schedule-timings.jsp");
            verify(request.getRequestDispatcher("view/doctor/doctor-schedule-timings.jsp"), times(1)).forward(request, response);
        }

    }

}