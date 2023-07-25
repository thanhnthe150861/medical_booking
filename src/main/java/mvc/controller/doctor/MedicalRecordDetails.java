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
        if (account != null) {
            String mid = req.getParameter("mid");
            if (account.getIsAdmin() == 1 || account.getIsAdmin() == 3) {
                if (mid != null) {
                    session.removeAttribute("bid");
                    session.setAttribute("mid", mid);
                    MedicalRecord medicalRecord = doctorDBContext.getTTByMedicalID(mid);
                    session.setAttribute("medicalRecord", medicalRecord);
                    req.getRequestDispatcher("view/doctor/add-medical-record.jsp").forward(req, resp);
                }
                String bid = req.getParameter("bid");
                if (bid != null) {
                    session.removeAttribute("mid");
                    session.setAttribute("bid", bid);
                    MedicalRecord medicalRecord = doctorDBContext.getTTByBookingID(bid);
                    session.setAttribute("medicalRecord", medicalRecord);
                    req.getRequestDispatcher("view/doctor/add-medical-record.jsp").forward(req, resp);
                }
            } else if (account.getIsAdmin() == 2) {
                MedicalRecord medicalRecord = doctorDBContext.getTTByMedicalID(mid);
                session.setAttribute("medicalRecord", medicalRecord);
                req.getRequestDispatcher("view/doctor/add-medical-record.jsp").forward(req, resp);
            }
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        String diagnosis = req.getParameter("diagnosis");
        String url = req.getParameter("url");
        String prescription = req.getParameter("prescription");
        String mid = req.getParameter("mid");
        if (!mid.isEmpty()) {
            MedicalRecord medicalRecord = doctorDBContext.getTTByMedicalID(mid);
            medicalRecord.setDiagnosis(diagnosis);
            medicalRecord.setPrescription(prescription);
            medicalRecord.setUrl(url);
            doctorDBContext.UpdateMedical(medicalRecord);

            session.setAttribute("medicalRecord", medicalRecord);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            req.getRequestDispatcher("view/doctor/add-medical-record.jsp").forward(req, resp);
        }
        String bid = req.getParameter("bid");
        if (!bid.isEmpty()) {
            MedicalRecord medicalRecord = doctorDBContext.getTTByBookingID(bid);
            medicalRecord.setDiagnosis(diagnosis);
            medicalRecord.setPrescription(prescription);
            medicalRecord.setUrl(url);
            if (medicalRecord.getId() == 0) {
                doctorDBContext.addMedical(medicalRecord);
                doctorDBContext.updateBookingStatus(bid, "Completed");
            } else {
                doctorDBContext.UpdateMedical(medicalRecord);
            }

            session.setAttribute("medicalRecord", medicalRecord);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            req.getRequestDispatcher("view/doctor/add-medical-record.jsp").forward(req, resp);
        }
    }
}
