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

@WebServlet(name = "BillDetails", value = "/bill_details")
public class BillDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1) {
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            String mid = req.getParameter("mid");
            Patient patient = (Patient) session.getAttribute("patient");
            if(patient == null){
                String pid = req.getParameter("pid");
                patient = doctorDBContext.getPatientByDoctor(pid);
                session.setAttribute("patient", patient);
            }
            if(mid != null){
                MedicalRecord medicalRecord = doctorDBContext.getMedicalRecord(mid);
                req.setAttribute("patient", patient);
                session.removeAttribute("bills");
                session.removeAttribute("medicalRecord");
                session.setAttribute("medicalRecord", medicalRecord);
            }
            String bid = req.getParameter("bid");
            if(bid != null){
                MedicalRecord bills = doctorDBContext.getBillByID(bid);
                req.setAttribute("patient", patient);
                session.removeAttribute("medicalRecord");
                session.removeAttribute("bills");
                session.setAttribute("bills", bills);
            }
            req.getRequestDispatcher("view/doctor/add-billing.jsp").forward(req, resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String priceMedical = req.getParameter("priceMedical");
        String pricePrescription = req.getParameter("pricePrescription");
        float totalPrice = Float.parseFloat(pricePrescription) + Float.parseFloat(priceMedical);
        String status = req.getParameter("status");
        String mid = req.getParameter("mid");
        if(!mid.isEmpty()){
            Bill bill = new Bill();
            bill.setMedical_record_id(Integer.parseInt(mid));
            bill.setPriceMedical(Float.parseFloat(priceMedical));
            bill.setPricePrescription(Float.parseFloat(pricePrescription));
            bill.setTotalPrice(totalPrice);
            bill.setPayment_status(status);
            DoctorDBContext dbContext = new DoctorDBContext();
            dbContext.addBill(bill);

            MedicalRecord bills = dbContext.getBillByMedicalID(mid);
            session.removeAttribute("medicalRecord");
            session.removeAttribute("bills");
            session.setAttribute("bills", bills);
        }
        String bid = req.getParameter("bid");
        if(!bid.isEmpty()){
            Bill bill = new Bill();
            bill.setId(Integer.parseInt(bid));
            bill.setPriceMedical(Float.parseFloat(priceMedical));
            bill.setPricePrescription(Float.parseFloat(pricePrescription));
            bill.setTotalPrice(totalPrice);
            bill.setPayment_status(status);
            DoctorDBContext dbContext = new DoctorDBContext();
            dbContext.UpdateBill(bill);

            MedicalRecord bills = dbContext.getBillByID(bid);
            session.removeAttribute("medicalRecord");
            session.removeAttribute("bills");
            session.setAttribute("bills", bills);
        }
        req.setAttribute("messSuccess", "Cập nhật thành công");
        req.getRequestDispatcher("view/doctor/add-billing.jsp").forward(req, resp);
    }
}
