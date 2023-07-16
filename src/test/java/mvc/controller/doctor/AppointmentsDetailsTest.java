package mvc.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentsDetailsTest {
    @Test
    public void testDoGet() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        // Set up any necessary mocks or expectations
        AppointmentsDetails appointmentsDetails = new AppointmentsDetails();
        appointmentsDetails.doGet(request, response);
        // Assert the expected behavior or response

    }
    @Test
    public void testDoPost() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        // Set up any necessary mocks or expectations
        AppointmentsDetails appointmentsDetails = new AppointmentsDetails();
        appointmentsDetails.doPost(request, response);
        // Assert the expected behavior or response
    }
}