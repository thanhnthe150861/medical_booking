package mvc.controller.doctor;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import static org.mockito.Mockito.*;
class BillDetailsTest {
    @InjectMocks
    BillDetails billDetails;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    HttpSession session;
    @Mock
    Account account;
    @Mock
    Doctor doctor;
    @Mock
    Patient patient;
    @Mock
    Bill bill;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoPost() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        String priceMedical = request.getParameter("priceMedical");
        String pricePrescription = request.getParameter("pricePrescription");
        when(request.getParameter(priceMedical)).thenReturn("priceMedical");
        when(request.getParameter(pricePrescription)).thenReturn("pricePrescription");

        String status = request.getParameter("status");
        String mid = request.getParameter("mid");
        when(request.getParameter(status)).thenReturn("status");
        when(request.getParameter(mid)).thenReturn("mid");
        if (mid!=null) {
            Bill bill = new Bill();
            bill.setMedical_record_id(Integer.parseInt(mid));
            bill.setPriceMedical(Float.parseFloat(priceMedical));
            bill.setPricePrescription(Float.parseFloat(pricePrescription));
            bill.setPayment_status(status);
            DoctorDBContext dbContext = new DoctorDBContext();
            dbContext.addBill(bill);

            MedicalRecord bills = dbContext.getBillByMedicalID(mid);
            BillDetails billDetail = new BillDetails();
            billDetail.doPost(request, response);
            verify(session).removeAttribute("medicalRecord");
            verify(session).removeAttribute("bills");
            verify(session).setAttribute("bills", bills);
        }
        String bid = request.getParameter("bid");
        when(request.getParameter(bid)).thenReturn("bid");
        if(bid!=null){
            Bill bill = new Bill();
            bill.setId(Integer.parseInt(bid));
            bill.setPriceMedical(Float.parseFloat(priceMedical));
            bill.setPricePrescription(Float.parseFloat(pricePrescription));
            bill.setPayment_status(status);
            DoctorDBContext dbContext = new DoctorDBContext();
            dbContext.UpdateBill(bill);

            MedicalRecord bills = dbContext.getBillByID(bid);
            BillDetails billDetail = new BillDetails();
            billDetail.doPost(request, response);
            verify(session).removeAttribute("medicalRecord");
            verify(session).removeAttribute("bills");
            verify(session).setAttribute("bills", bills);
        }

        BillDetails billDetail = new BillDetails();
        when(request.getRequestDispatcher("view/doctor/add-billing.jsp")).thenReturn(requestDispatcher);
//        billDetail.doPost(request, response);
//        verify(request).setAttribute("messSuccess", "Cập nhật thành công");
//        verify(request).getRequestDispatcher("view/doctor/add-billing.jsp").forward(request,response);

    }
@Test
    public void testDoGet() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        Account account = (Account) session.getAttribute("account");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        BillDetails billDetails1 = new BillDetails();
        if (account != null && account.getIsAdmin() == 1){
            Doctor doctor = doctorDBContext.getDoctor(account);
            String mid = request.getParameter("mid");
            Patient patient = (Patient) session.getAttribute("patient");
            when(request.getParameter(mid)).thenReturn("mid");
            when(session.getAttribute("patient")).thenReturn(patient);
            billDetails1.doGet(request,response);
            verify(session).setAttribute("doctor", doctor);
            if(patient == null){
                String pid = request.getParameter("pid");
                when(request.getParameter("pit")).thenReturn(pid);
                patient = doctorDBContext.getPatientByDoctor(pid);
                billDetails1.doGet(request,response);
                verify(session).setAttribute("patient", patient);
            }
            if(mid != null){
                MedicalRecord medicalRecord = doctorDBContext.getMedicalRecord(mid);
                billDetails1.doGet(request,response);
                verify(session).setAttribute("medicalRecord", medicalRecord);
                verify(session).setAttribute("patient", patient);
                verify(session).removeAttribute("bills");
                verify(session).removeAttribute("medicalRecord");
            }
            String bid = request.getParameter("bid");
            when(request.getParameter(bid)).thenReturn("bid");
            if(bid != null){
                MedicalRecord bills = doctorDBContext.getBillByID(bid);
                billDetails1.doGet(request,response);
                verify(session).setAttribute("bills", bills);
                verify(session).setAttribute("patient", patient);
                verify(session).removeAttribute("bills");
                verify(session).removeAttribute("medicalRecord");
            }

            when(request.getRequestDispatcher("view/doctor/add-billing.jsp")).thenReturn(requestDispatcher);
            billDetails1.doGet(request,response);
            verify(requestDispatcher).forward(request,response);
        }
        billDetails1.doGet(request,response);
        verify(response).sendRedirect("login");


    }

}