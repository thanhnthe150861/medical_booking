package mvc.controller.patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.dal.PatientDBContext;
import mvc.model.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "BookingAgain", value = "/booking_again")
public class BookingAgain extends HttpServlet {
    private final PatientDBContext patientDBContext = new PatientDBContext();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getIsAdmin() == 2) {
            //lấy date đã trọn, nếu ko có set mặc định ngày hôm nay
            LocalDate date = req.getParameter("datePicker") != null ? LocalDate.parse(req.getParameter("datePicker")) : LocalDate.now();
            session.setAttribute("date", date.toString());
            //lấy full slots ở db
            session.setAttribute("slotList", patientDBContext.getAllSlots());
            // ngày chọn
            session.setAttribute("selectedSlot", req.getParameter("selectedSlot"));
            //lấy doctor
            String did = req.getParameter("did");
            if (did == null) {//check session xem nếu không null thì set lại vào did
                did = (String) session.getAttribute("did");
            }

            if (did != null) {
                session.setAttribute("did", did);
                DoctorDBContext dbContext = new DoctorDBContext();
                Doctor doctor = patientDBContext.getDoctorByPatient(did);
                req.setAttribute("doctor", doctor);
                List<Slot> slotExist = dbContext.checkSlotExist(did, date.toString());
                session.setAttribute("slotExist", slotExist);
            }
            req.getRequestDispatcher("view/patient/booking-again.jsp").forward(req, resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        Patient patient = patientDBContext.getPatient(account);

        // Ngày và Slot được chọn
        String did = (String) session.getAttribute("did");
        String selectedDate = (String) session.getAttribute("date");
        String selectedSlot = (String) session.getAttribute("selectedSlot");
        String textReason = req.getParameter("textReason");

        if (selectedDate == null || selectedSlot == null) {
            resp.sendRedirect("patient_dashboard");
            return;
        }

        Booking booking = patientDBContext.checkBookingExist(patient, selectedDate);

        if (booking != null) {
            req.setAttribute("messError", "Bạn đã đặt lịch vào ca này");
        } else {
            Booking bookings = new Booking();
            bookings.setDoctor_id(Integer.parseInt(did));
            bookings.setPatient_id(patient.getId());
            bookings.setSlot_id(Integer.parseInt(selectedSlot));
            bookings.setDate(Date.valueOf(selectedDate));
            bookings.setBooking_reason(textReason);
            bookings.setStatus("Pending");
            patientDBContext.addNewBooking(bookings);
            resp.sendRedirect("patient_dashboard");
            return;
        }
        req.getRequestDispatcher("view/patient/booking-again.jsp").forward(req, resp);
    }
}
