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
            Doctor doctor = doctorDBContext.getDoctor(account);
            req.setAttribute("doctor", doctor);
            String bid = req.getParameter("id");
            Patient patient = (Patient) session.getAttribute("patient");
            Booking booking = doctorDBContext.getBooking(bid);
            req.setAttribute("patient", patient);
            session.setAttribute("booking", booking);
            req.getRequestDispatcher("view/doctor/add-medical-record.jsp").forward(req, resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Booking booking = (Booking)session.getAttribute("booking");
        int bid = booking.getId();
        String diagnosis = req.getParameter("diagnosis");
        String url = req.getParameter("url");
        String prescription = req.getParameter("prescription");
        MedicalRecord record = new MedicalRecord();
        record.setBooking_id(bid);
        record.setDiagnosis(diagnosis);
        record.setPrescription(prescription);
        record.setUrl(url);
        DoctorDBContext dbContext = new DoctorDBContext();
        dbContext.addMedical(record);
        req.setAttribute("messSuccess", "Thêm mới thành công");
        req.getRequestDispatcher("view/doctor/add-medical-record.jsp").forward(req, resp);
    }
}