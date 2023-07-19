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

        if (selectedDate == null || selectedSlot == null) {
            resp.sendRedirect("patient_dashboard");
            return;
        }

        String status = "Cancelled";

        Booking booking = patientDBContext.checkBookingExist(patient, selectedDate, selectedSlot);

        if (booking != null) {
            req.setAttribute("messError", "Bạn đã lịch đặt vào ca này");
        } else {
            List<Doctor> doctorList = patientDBContext.getListDoctorEmpty(selectedDate, selectedSlot, status);
            if (!doctorList.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(doctorList.size());
                Doctor randomDoctor = doctorList.get(randomIndex);
                Booking bookings = new Booking();
                bookings.setDoctor_id(randomDoctor.getId());
                bookings.setPatient_id(patient.getId());
                bookings.setSlot_id(Integer.parseInt(selectedSlot));
                bookings.setDate(Date.valueOf(selectedDate));
                bookings.setBooking_reason(textReason);
                bookings.setStatus("Pending");
                patientDBContext.addNewBooking(bookings);
                resp.sendRedirect("patient_dashboard");
                return;
            }
            req.setAttribute("messError", "Không có bác sĩ nào trống lịch khám vào ca này");
        }

        req.getRequestDispatcher("view/patient/booking.jsp").forward(req, resp);
    }
}
