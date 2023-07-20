package mvc.controller.patient;

import mvc.dal.DoctorDBContext;
import mvc.dal.PatientDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import mvc.model.MedicalRecord;
import mvc.model.Patient;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PatientDashboard", value = "/patient_dashboard")
public class PatientDashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        PatientDBContext patientDBContext = new PatientDBContext();
        if (account != null && account.getIsAdmin() == 2){
            String id = req.getParameter("id");
            String status = req.getParameter("status");
            // Kiểm tra xem các tham số có tồn tại hay không
            if (id != null && status != null) {
                // Cập nhật trạng thái của đặt lịch
                DoctorDBContext doctorDBContext = new DoctorDBContext();
                doctorDBContext.updateBookingStatus(id, status);
            }
            Patient patient = patientDBContext.getPatient(account);
            List<MedicalRecord> medicalRecordList = patientDBContext.getInforMyPatients(patient);
            session.setAttribute("patient", patient);
            session.setAttribute("medicalRecordList", medicalRecordList);
            String bill = req.getParameter("bill");
            String medical = req.getParameter("medical");
            String forwardUrl = "view/patient/patient-dashboard.jsp"; // Trang mặc định

            if (medical != null && medical.equals("true")) {
                forwardUrl = "view/patient/dashboard-medical-record.jsp";
            }
            req.getRequestDispatcher("view/patient/patient-dashboard.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
