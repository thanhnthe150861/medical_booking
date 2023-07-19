package mvc.controller.doctor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MedicalRecordDetailsTest {
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
        MedicalRecordDetails medicalRecordDetails = new MedicalRecordDetails();
        when(request.getSession()).thenReturn(session);
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 1) {
            Doctor doctor = doctorDBContext.getDoctor(account);
            String mid = request.getParameter("mid");
            when(request.getParameter(mid)).thenReturn("mid");
            medicalRecordDetails.doGet(request, response);
            verify(session).removeAttribute("medicalRecord");
            verify(session).setAttribute("doctor", doctor);
            if (mid != null) {
                Patient patient = (Patient) session.getAttribute("patient");
                when(session.getAttribute("patient")).thenReturn(patient);
                MedicalRecord medicalRecord = doctorDBContext.getTTByMedicalID(mid);
                medicalRecordDetails.doGet(request, response);
                verify(request).setAttribute("patient", patient);
                verify(session).setAttribute("medicalRecord", medicalRecord);
            }
            String bid = request.getParameter("bid");
            when(request.getParameter(bid)).thenReturn("bid");
            if (bid != null) {
                Patient patient = (Patient) session.getAttribute("patient");
                when(session.getAttribute("patient")).thenReturn(patient);
                Booking booking = doctorDBContext.getBooking(bid);
                medicalRecordDetails.doGet(request,response);
                verify(request).setAttribute("patient", patient);
                verify(session).setAttribute("booking", booking);
            }
            when(request.getRequestDispatcher("view/doctor/add-medical-record.jsp")).thenReturn(requestDispatcher);
            medicalRecordDetails.doGet(request,response);
            verify(requestDispatcher).forward(request,response);
        }

        medicalRecordDetails.doGet(request,response);
        verify(response).sendRedirect("login");
    }
    @Test
    public void testDoPost() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        MedicalRecordDetails medicalRecordDetails = new MedicalRecordDetails();
        when(request.getSession()).thenReturn(session);
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        String diagnosis = request.getParameter("diagnosis");
        String url = request.getParameter("url");
        String prescription = request.getParameter("prescription");
        String mid = request.getParameter("mid");
        when(request.getParameter(diagnosis)).thenReturn("diagnosis");
        when(request.getParameter(url)).thenReturn("url");
        when(request.getParameter(prescription)).thenReturn("prescription");
        when(request.getParameter(mid)).thenReturn("mid");
        if(mid != null){
            MedicalRecord medicalRecord = doctorDBContext.getTTByMedicalID(mid);
            medicalRecord.setDiagnosis(diagnosis);
            medicalRecord.setPrescription(prescription);
            medicalRecord.setUrl(url);
            doctorDBContext.UpdateMedical(medicalRecord);
            medicalRecordDetails.doPost(request,response);
            verify(session).setAttribute("medicalRecord", medicalRecord);
        }
        String bid = request.getParameter("bid");
        when(request.getParameter(bid)).thenReturn("bid");
        if(bid != null){
            MedicalRecord record = new MedicalRecord();
            record.setBooking_id(Integer.parseInt(bid));
            record.setDiagnosis(diagnosis);
            record.setPrescription(prescription);
            record.setUrl(url);
            DoctorDBContext dbContext = new DoctorDBContext();
            medicalRecordDetails.doPost(request,response);
            verify(session).setAttribute("medicalRecord", record);
        }
        if(bid != null &&mid != null){
            when(request.getRequestDispatcher("view/doctor/add-medical-record.jsp")).thenReturn(requestDispatcher);
            medicalRecordDetails.doPost(request,response);
            verify(session).setAttribute("messSuccess", "Cập nhật thành công");
            verify(requestDispatcher).forward(request,response);
        }
    }
}