package mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.dal.PatientDBContext;
import mvc.dal.StaffDBContext;
import mvc.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AppointmentDetails", value = "/appointment_details")
public class AppointmentDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        PatientDBContext patientDBContext = new PatientDBContext();
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        StaffDBContext staffDBContext = new StaffDBContext();
        List<Specialty> listSp = patientDBContext.getAllSpecialties();
        session.setAttribute("listSp", listSp);
        String bid = req.getParameter("bid");
        if (account != null) {
            if (bid != null) {
                session.setAttribute("bid", bid);
                MedicalRecord bookingID = doctorDBContext.getTTByBookingID(bid);
                session.setAttribute("bookingID", bookingID);
                if (account.getIsAdmin() == 0) {

                } else if (account.getIsAdmin() == 1) {

                } else if (account.getIsAdmin() == 3) {
                    List<Doctor> doctors = staffDBContext.getDoctorBySpecialty(bookingID.getBooking().getSpecialty().getId());
                    session.setAttribute("doctors", doctors);
                    req.getRequestDispatcher("view/staff/staff-appointment-details.jsp").forward(req, resp);
                }
            }
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        PatientDBContext patientDBContext = new PatientDBContext();
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        String bid = session.getAttribute("bid").toString();
        String did = req.getParameter("did");
        String ghichu = req.getParameter("ghichu");
        String status = req.getParameter("status");
        String date = req.getParameter("date");
        String slot = req.getParameter("slot");
        if (Integer.parseInt(did) == 0 && !status.equalsIgnoreCase("Cancelled")) {
            req.setAttribute("messError", "Bạn chưa chọn bác sĩ khám");
            req.getRequestDispatcher("view/staff/staff-appointment-details.jsp").forward(req, resp);
            return;
        }
        if (account.getIsAdmin() == 3) {
            MedicalRecord bookingIDs = doctorDBContext.CheckBookingByDoctorId(did, date, slot);
            if (bookingIDs != null) {
                req.setAttribute("messError", "Bác sĩ đã bận vào ca này");
                req.getRequestDispatcher("view/staff/staff-appointment-details.jsp").forward(req, resp);
                return;
            }

            if (status.equalsIgnoreCase("Cancelled") && Integer.parseInt(did) == 0) {
                patientDBContext.updateBookingStatusCancel(bid, status);
            } else {
                patientDBContext.updateBookingStatus(bid, status, ghichu, did);
            }
            MedicalRecord bookingID = doctorDBContext.getTTByBookingID(bid);
            session.setAttribute("bookingID", bookingID);
            req.setAttribute("messSuccess", "Cập nhật thành công");
            req.getRequestDispatcher("view/staff/staff-appointment-details.jsp").forward(req, resp);
        }
    }
}