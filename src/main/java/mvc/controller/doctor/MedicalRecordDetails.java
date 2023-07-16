package mvc.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MedicalRecordDetails", value = "/medical_record_details")
public class MedicalRecordDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1) {
            session.removeAttribute("medicalRecord");
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            String mid = req.getParameter("mid");
            if(mid != null){
                Patient patient = (Patient) session.getAttribute("patient");
                MedicalRecord medicalRecord = doctorDBContext.getMedicalRecord(mid);
                req.setAttribute("patient", patient);
                session.setAttribute("medicalRecord", medicalRecord);
            }
            String bid = req.getParameter("bid");
            if(bid != null){
                Patient patient = (Patient) session.getAttribute("patient");
                Booking booking = doctorDBContext.getBooking(bid);
                req.setAttribute("patient", patient);
                session.setAttribute("booking", booking);
            }
            //
            req.getRequestDispatcher("view/doctor/add-medical-record.jsp").forward(req, resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        String diagnosis = req.getParameter("diagnosis");
        String url = req.getParameter("url");
        String prescription = req.getParameter("prescription");
        String mid = req.getParameter("mid");
        if(!mid.isEmpty()){
            MedicalRecord medicalRecord = doctorDBContext.getMedicalRecord(mid);
            medicalRecord.setDiagnosis(diagnosis);
            medicalRecord.setPrescription(prescription);
            medicalRecord.setUrl(url);
            doctorDBContext.UpdateMedical(medicalRecord);
            session.setAttribute("medicalRecord", medicalRecord);
        }
        String bid = req.getParameter("bid");
        if(!bid.isEmpty()){
            MedicalRecord record = new MedicalRecord();
            record.setBooking_id(Integer.parseInt(bid));
            record.setDiagnosis(diagnosis);
            record.setPrescription(prescription);
            record.setUrl(url);
            DoctorDBContext dbContext = new DoctorDBContext();
            dbContext.addMedical(record);
            session.setAttribute("medicalRecord", record);
        }
        req.setAttribute("messSuccess", "Cập nhật thành công");
        req.getRequestDispatcher("view/doctor/add-medical-record.jsp").forward(req, resp);
    }
}
