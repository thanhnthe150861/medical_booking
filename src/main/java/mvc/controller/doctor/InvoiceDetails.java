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

@WebServlet(name = "InvoiceDetails", value = "/invoice_details")
public class InvoiceDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        String bid = req.getParameter("bid");
        String mid = req.getParameter("mid");
        if (account != null && account.getIsAdmin() == 0){
            MedicalRecord bill = doctorDBContext.getTTByBillID(bid);
            session.setAttribute("bills", bill);
            req.getRequestDispatcher("view/doctor/add-billing.jsp").forward(req,resp);
        }
        if (account != null && account.getIsAdmin() == 1) {
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            if(mid != null){
                session.removeAttribute("bid");
                session.setAttribute("mid", mid);
                MedicalRecord medicalRecord = doctorDBContext.getTTByMedicalID(mid);
                session.setAttribute("bills", medicalRecord);
                req.getRequestDispatcher("view/doctor/add-billing.jsp").forward(req, resp);
            }
            if(bid != null){
                session.removeAttribute("mid");
                session.setAttribute("bid", bid);
                MedicalRecord bills = doctorDBContext.getTTByBillID(bid);
                session.setAttribute("bills", bills);
                req.getRequestDispatcher("view/doctor/add-billing.jsp").forward(req, resp);
            }
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DoctorDBContext dbContext = new DoctorDBContext();
        String priceMedical = req.getParameter("priceMedical");
        String pricePrescription = req.getParameter("pricePrescription");
        float totalPrice = Float.parseFloat(pricePrescription) + Float.parseFloat(priceMedical);
        String status = req.getParameter("status");
        String mid = req.getParameter("mid");
        if(!mid.isEmpty()){
            MedicalRecord bills = dbContext.getTTByMedicalID(mid);
            Bill bill = new Bill();
            bill.setMedical_record_id(Integer.parseInt(mid));
            bill.setPriceMedical(Float.parseFloat(priceMedical));
            bill.setPricePrescription(Float.parseFloat(pricePrescription));
            bill.setTotalPrice(totalPrice);
            bill.setPayment_status(status);
            if(bills.getBill().getId() == 0){
                dbContext.addBill(bill);
            }else {
                bill.setId(bills.getBill().getId());
                dbContext.UpdateBill(bill);
            }
            bills.getBill().setPriceMedical(Float.parseFloat(priceMedical));
            bills.getBill().setPricePrescription(Float.parseFloat(pricePrescription));
            bills.getBill().setTotalPrice(totalPrice);
            bills.getBill().setPayment_status(status);
            session.setAttribute("bills", bills);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            req.getRequestDispatcher("view/doctor/add-billing.jsp").forward(req, resp);
        }
        String bid = req.getParameter("bid");
        if(!bid.isEmpty()){
            Bill bill = new Bill();
            bill.setId(Integer.parseInt(bid));
            bill.setPriceMedical(Float.parseFloat(priceMedical));
            bill.setPricePrescription(Float.parseFloat(pricePrescription));
            bill.setTotalPrice(totalPrice);
            bill.setPayment_status(status);
            dbContext.UpdateBill(bill);

            MedicalRecord bills = dbContext.getTTByBillID(bid);
            session.setAttribute("bills", bills);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            req.getRequestDispatcher("view/doctor/add-billing.jsp").forward(req, resp);
        }
    }
}
