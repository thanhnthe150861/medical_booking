package controller.doctor;

import dal.DoctorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Doctor;
import model.MedicalRecord;
import model.Patient;

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
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            // Lấy giá trị của các tham số id từ liên kết
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