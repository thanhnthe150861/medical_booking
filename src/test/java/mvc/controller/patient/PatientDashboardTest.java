package mvc.controller.patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.dal.PatientDBContext;
import mvc.model.Account;
import mvc.model.MedicalRecord;
import mvc.model.Patient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PatientDashboardTest {
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
        Account account = (Account) session.getAttribute("account");
        PatientChangePassword patientChangePassword = new PatientChangePassword();
        PatientDBContext patientDBContext = new PatientDBContext();
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 2) {
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            when(request.getParameter(id)).thenReturn("2");
            when(request.getParameter(status)).thenReturn("status");
            if (id != null && status != null) {
                // Cập nhật trạng thái của đặt lịch
                DoctorDBContext doctorDBContext = new DoctorDBContext();
                doctorDBContext.updateBookingStatus(id, status);
            }
            Patient patient = patientDBContext.getPatient(account);
            List<MedicalRecord> medicalRecordList = patientDBContext.getInforMyPatients(patient);
            String bill = request.getParameter("bill");
            String medical = request.getParameter("medical");
            when(request.getParameter(bill)).thenReturn("bill");
            when(request.getParameter(medical)).thenReturn("medical");
            if(bill != null && bill.equals("true")){
                when(request.getRequestDispatcher("view/patient/dashboard-bill.jsp")).thenReturn(requestDispatcher);
                patientChangePassword.doGet(request,response);
                verify(requestDispatcher).forward(request,response);
            } else if (medical != null && medical.equals("true")){
                when(request.getRequestDispatcher("view/patient/dashboard-medical-record.jsp")).thenReturn(requestDispatcher);
                patientChangePassword.doGet(request,response);
                verify(requestDispatcher).forward(request,response);
            }
            when(request.getRequestDispatcher("view/patient/patient-dashboard.jsp")).thenReturn(requestDispatcher);
            patientChangePassword.doGet(request,response);
            verify(requestDispatcher).forward(request,response);
            verify(session).setAttribute("patient", patient);
            verify(session).setAttribute("medicalRecordList", medicalRecordList);
        }
        patientChangePassword.doGet(request,response);
        verify(response).sendRedirect("login");
    }
}