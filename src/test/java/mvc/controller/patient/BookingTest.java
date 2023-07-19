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
import mvc.model.Slot;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingTest {
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
        Bookings booking = new Bookings();
        when(request.getSession()).thenReturn(session);
        Account account = (Account) session.getAttribute("account");
        PatientDBContext patientDBContext = new PatientDBContext();
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 2) {
            //Lấy ngày hôm nay
            LocalDate today = LocalDate.now();
            String date = today.toString();
            String selectedDate = request.getParameter("datePicker");
            when(request.getParameter("selectedDate")).thenReturn(selectedDate);
            if (selectedDate != null) {
                booking.doGet(request, response);
                verify(session).removeAttribute("date");
                verify(session).setAttribute("date", selectedDate);
            }
            List<Slot> slotList = patientDBContext.getAllSlots();
            String selectedSlot = request.getParameter("selectedSlot");
            when(request.getAttribute("selectedSlot")).thenReturn(selectedSlot);
            when(request.getRequestDispatcher("view/patient/booking.jsp")).thenReturn(requestDispatcher);
            booking.doGet(request, response);
            verify(session).setAttribute("date", date);
            verify(session).setAttribute("slotList", slotList);
            verify(session).setAttribute("selectedSlot", selectedSlot);
            verify(requestDispatcher).forward(request, response);

        }
        booking.doGet(request, response);
        verify(response).sendRedirect("login");

    }
}


