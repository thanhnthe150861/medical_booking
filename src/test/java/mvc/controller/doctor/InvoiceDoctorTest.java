package mvc.controller.doctor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.Account;
import mvc.model.Doctor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InvoiceDoctorTest {
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
        when(request.getSession()).thenReturn(session);
        InvoiceDoctor invoiceDoctor = new InvoiceDoctor();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 1){
            Doctor doctor = doctorDBContext.getDoctor(account);
            when(request.getRequestDispatcher("view/doctor/invoices.jsp")).thenReturn(requestDispatcher);
            invoiceDoctor.doGet(request,response);
            verify(session).setAttribute("doctor", doctor);
            verify(requestDispatcher).forward(request,response);
        }
        invoiceDoctor.doGet(request,response);
        verify(request).getRequestDispatcher("login");
    }
}