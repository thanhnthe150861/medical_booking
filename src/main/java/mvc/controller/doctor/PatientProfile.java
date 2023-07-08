package mvc.controller.doctor;

import mvc.dal.DoctorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import mvc.model.Doctor;
import mvc.model.MedicalRecord;
import mvc.model.Patient;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PatientProfile", value = "/patient_profile")
public class PatientProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1){
            String bid = req.getParameter("bid");
            String status = req.getParameter("status");
            // Kiểm tra xem các tham số có tồn tại hay không
            if (bid != null && status != null) {
                // Cập nhật trạng thái của đặt lịch
                doctorDBContext.updateBookingStatus(bid, status);
            }
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            String id = req.getParameter("id");
            Patient patient = doctorDBContext.getPatientByDoctor(id);
            List<MedicalRecord> medicalRecordList = doctorDBContext.getInforMyPatients(doctor, id);
            req.setAttribute("patient", patient);
            req.setAttribute("medicalRecordList", medicalRecordList);
            String bill = req.getParameter("bill");
            String medical = req.getParameter("medical");
            if(bill != null && bill.equals("true")){
                req.getRequestDispatcher("view/doctor/patient-bill.jsp").forward(req,resp);
            } else if (medical != null && medical.equals("true")){
                req.getRequestDispatcher("view/doctor/patient-medical-record.jsp").forward(req,resp);
            }
            req.getRequestDispatcher("view/doctor/patient-profile.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}