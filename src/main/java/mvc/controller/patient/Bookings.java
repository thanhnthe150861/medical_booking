package mvc.controller.patient;

import mvc.dal.PatientDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@WebServlet(name = "Bookings", value = "/booking")
public class Bookings extends HttpServlet {
    private final PatientDBContext patientDBContext = new PatientDBContext();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        List<Specialty> listSp = patientDBContext.getAllSpecialties();
        session.setAttribute("listSp", listSp);
        if (account != null && account.getIsAdmin() == 2) {
            //lấy date đã trọn, nếu ko có set mặc định ngày hôm nay
            LocalDate date = req.getParameter("datePicker") != null ? LocalDate.parse(req.getParameter("datePicker")) : LocalDate.now();
            session.setAttribute("date", date.toString());
            //lấy full slots ở db
            session.setAttribute("slotList", patientDBContext.getAllSlots());
            // ngày chọn
            session.setAttribute("selectedSlot", req.getParameter("selectedSlot"));
            req.getRequestDispatcher("view/patient/booking.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("login").forward(req, resp);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        Patient patient = patientDBContext.getPatient(account);

        // Ngày và Slot được chọn
        String selectedDate = (String) session.getAttribute("date");
        String selectedSlot = (String) session.getAttribute("selectedSlot");
        String textReason = req.getParameter("textReason");
        String diseaseGroup = req.getParameter("diseaseGroup");
        if (selectedSlot == null) {
            req.setAttribute("messError", "Bạn chưa chọn khung giờ đặt lịch");
            req.getRequestDispatcher("view/patient/booking.jsp").forward(req, resp);
            return;
        }

        Booking booking = patientDBContext.checkBookingExist(patient, selectedDate);

        if (booking != null) {
            req.setAttribute("messError", "Bạn đã đặt lịch vào ngày này");
        } else {
            Booking bookings = new Booking();
            bookings.setPatient_id(patient.getId());
            bookings.setSlot_id(Integer.parseInt(selectedSlot));
            bookings.setDate(Date.valueOf(selectedDate));
            bookings.setSpecialty_id(Integer.parseInt(diseaseGroup));
            bookings.setBooking_reason(textReason);
            bookings.setStatus("Pending");
            patientDBContext.addNewBooking(bookings);
            resp.sendRedirect("patient_dashboard");
            return;
        }
        req.getRequestDispatcher("view/patient/booking.jsp").forward(req, resp);
    }
}
