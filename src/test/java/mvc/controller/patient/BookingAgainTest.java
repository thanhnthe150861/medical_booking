package mvc.controller.patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.dal.PatientDBContext;
import mvc.model.Account;
import mvc.model.Booking;
import mvc.model.Doctor;
import mvc.model.Slot;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingAgainTest {
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
            // Set up test data
            Account account = new Account();
            account.setIsAdmin(2);
            List<Slot> slotList = new ArrayList<>();
            BookingAgain bookingAgain = new BookingAgain();
            LocalDate today = LocalDate.now();
            String date = today.toString();
            String selectedDate = "2022-01-01";
            String selectedSlot = "slot1";
            String did = "123";
            MockitoAnnotations.initMocks(this);
            // Mocking the behavior of HttpServletRequest and HttpSession
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("account")).thenReturn(account);
            when(session.getAttribute("slotList")).thenReturn(slotList);
            when(session.getAttribute("date")).thenReturn(date);
            when(request.getParameter("datePicker")).thenReturn(selectedDate);
            when(request.getParameter("selectedSlot")).thenReturn(selectedSlot);
            when(request.getParameter("did")).thenReturn(did);
            // Mocking the behavior of DoctorDBContext and PatientDBContext
            DoctorDBContext doctorDBContext = Mockito.mock(DoctorDBContext.class);
            PatientDBContext patientDBContext = Mockito.mock(PatientDBContext.class);
            Doctor doctor = new Doctor();
            List<Booking> bookingList = new ArrayList<>();
            when(patientDBContext.getDoctorByPatient(did)).thenReturn(doctor);
            when(doctorDBContext.checkBookingMyDoctor(doctor, selectedDate)).thenReturn(bookingList);
            // Mocking the behavior of RequestDispatcher
            when(request.getRequestDispatcher("view/patient/booking-again.jsp")).thenReturn(requestDispatcher);
            // Perform the doGet method
            bookingAgain.doGet(request, response);
            // Verify the interactions and assertions
            verify(session).setAttribute("slotList", slotList);
            verify(session).setAttribute("date", selectedDate);
            verify(session).setAttribute("selectedSlot", selectedSlot);
            verify(session).setAttribute("did", did);
            verify(request).setAttribute("doctor", doctor);
            verify(request).setAttribute("bookingList", bookingList);
            verify(request).getRequestDispatcher("view/patient/booking-again.jsp");
        }
    }
