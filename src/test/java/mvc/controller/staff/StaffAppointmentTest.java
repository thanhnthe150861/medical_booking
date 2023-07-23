package mvc.controller.staff;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StaffAppointmentTest {
    @Test
    public void testDoGetWithAdminAccount() throws Exception {
        // Create a mock HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);
        // Create a mock HttpServletResponse
        HttpServletResponse response = mock(HttpServletResponse.class);
        // Create a mock HttpSession
        HttpSession session = mock(HttpSession.class);
        // Create a mock Account object
        Account account = mock(Account.class);
        // Set up the mock objects
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(account.getIsAdmin()).thenReturn(3);
        // Create a mock RequestDispatcher
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("view/staff/staff-appointment.jsp")).thenReturn(dispatcher);
        // Create an instance of the StaffAppointment class
//        StaffAppointment staffAppointment = new StaffAppointment();
        // Call the doGet method
//        staffAppointment.doGet(request, response);
        // Verify that the request dispatcher is called with the correct argument
        verify(request, times(1)).getRequestDispatcher("view/staff/staff-appointment.jsp");
        // Verify that the forward method is called on the request dispatcher
        verify(dispatcher, times(1)).forward(request, response);
    }
}