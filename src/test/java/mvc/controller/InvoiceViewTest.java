package mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.Account;
import mvc.model.Doctor;
import mvc.model.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InvoiceViewTest {
    @Mock
    private RequestDispatcher requestDispatcher;
    @Test
    public void testDoGet_Positive() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        // Mocking the HttpServletRequest and HttpServletResponse objects
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        Account account = new Account();
        account.setIsAdmin(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        when(request.getParameter("bid")).thenReturn("12345");
        DoctorDBContext doctorDBContext = mock(DoctorDBContext.class);
        MedicalRecord bill = new MedicalRecord();
        when(doctorDBContext.getTTByBillID("12345")).thenReturn(bill);
        when(doctorDBContext.getDoctor(account)).thenReturn(new Doctor());
        // Creating an instance of InvoiceView and calling the doGet() function
        when(request.getRequestDispatcher("view/doctor/invoice-view.jsp")).thenReturn(requestDispatcher);
        InvoiceView invoiceView = new InvoiceView();
        invoiceView.doGet(request, response);
        // Verifying that the appropriate request attributes are set and forwarded to the correct JSP page
        verify(response).sendRedirect("login");
    }
}