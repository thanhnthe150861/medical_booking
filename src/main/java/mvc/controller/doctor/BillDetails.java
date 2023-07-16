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
            if(mid != null){
                Patient patient = (Patient) session.getAttribute("patient");
                MedicalRecord medicalRecord = doctorDBContext.getMedicalRecord(mid);
                req.setAttribute("patient", patient);
                session.setAttribute("medicalRecord", medicalRecord);
            }
            String bid = req.getParameter("bid");
            if(bid != null){
                Patient patient = (Patient) session.getAttribute("patient");
                MedicalRecord bills = doctorDBContext.getBill(bid);
                req.setAttribute("patient", patient);
                session.setAttribute("bills", bills);
            }
            req.getRequestDispatcher("view/doctor/add-billing.jsp").forward(req, resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String mid = req.getParameter("mid");
        if(!mid.isEmpty()){
//            String diagnosis = req.getParameter("priceMedical");
//            String prescription = req.getParameter("pricePrescription");
//        String totalPrice = req.getParameter("totalPrice");
            float totalPrice = Float.parseFloat(req.getParameter("totalPrice"));
            String status = req.getParameter("status");
            Bill bill = new Bill();
            bill.setMedical_record_id(Integer.parseInt(mid));
            bill.setPrice(totalPrice);
            bill.setPayment_status(status);
            DoctorDBContext dbContext = new DoctorDBContext();
            dbContext.addBill(bill);
        }
        String bid = req.getParameter("bid");
        if(!bid.isEmpty()){
//            String diagnosis = req.getParameter("priceMedical");
//            String prescription = req.getParameter("pricePrescription");
//        String totalPrice = req.getParameter("totalPrice");
            float totalPrice = Float.parseFloat(req.getParameter("totalPrice"));
            String status = req.getParameter("status");
            Bill bill = new Bill();
            bill.setId(Integer.parseInt(bid));
            bill.setPrice(totalPrice);
            bill.setPayment_status(status);
            DoctorDBContext dbContext = new DoctorDBContext();
            dbContext.UpdateBill(bill);

            MedicalRecord bills = dbContext.getBill(bid);
            session.setAttribute("bills", bills);
        }
        req.setAttribute("messSuccess", "Cập nhật thành công");
        req.getRequestDispatcher("view/doctor/add-billing.jsp").forward(req, resp);
    }
}
