package mvc.controller.doctor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.Account;
import mvc.model.Doctor;
import mvc.model.MedicalRecord;
import mvc.model.Patient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InvoiceDoctorTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher requestDispatcher;

    @Test
    public void testDoGet() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        Account account = (Account) session.getAttribute("account");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1) {
            Doctor doctor = doctorDBContext.getDoctor(account);
            String bid = request.getParameter("bid");
            when(request.getParameter(bid)).thenReturn("bid");
            MedicalRecord bill = doctorDBContext.getBillByID(bid);
            String pid = String.valueOf(bill.getBooking().getPatient_id());
            Patient patient = doctorDBContext.getPatientByDoctor(pid);
            when(request.getRequestDispatcher("view/doctor/invoice-view.jsp")).thenReturn(requestDispatcher);
            InvoiceView invoiceView = new InvoiceView();
            invoiceView.doGet(request, response);
            verify(request).setAttribute("doctor", doctor);
            verify(request).setAttribute("bill", bill);
            verify(request).setAttribute("patient", patient);
            // Verifying that the request dispatcher was called
            verify(requestDispatcher).forward(request, response);
        }
        InvoiceView invoiceView = new InvoiceView();
        invoiceView.doGet(request, response);
        verify(response).sendRedirect("login");
    }
}