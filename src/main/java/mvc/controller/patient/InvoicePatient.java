package mvc.controller.patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.dal.PatientDBContext;
import mvc.model.Account;
import mvc.model.Doctor;
import mvc.model.MedicalRecord;
import mvc.model.Patient;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "InvoicePatient", value = "/invoice_patient")
public class InvoicePatient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account != null && account.getIsAdmin() == 2) {
            PatientDBContext pdb = new PatientDBContext();
            Patient patient = pdb.getPatient(account);
            session.setAttribute("patient", patient);
            List<MedicalRecord> invoiceList = pdb.invoiceListByPatient(patient);
            session.setAttribute("invoiceList", invoiceList);
            req.getRequestDispatcher("view/patient/invoices-patient.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("login").forward(req, resp);
        }

    }
}
