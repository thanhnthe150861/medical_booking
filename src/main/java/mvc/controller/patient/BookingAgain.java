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
import mvc.model.Booking;
import mvc.model.Doctor;
import mvc.model.Slot;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "BookingAgain", value = "/booking_again")
public class BookingAgain extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        PatientDBContext patientDBContext = new PatientDBContext();
//        if (account != null && account.getIsAdmin() == 2) {
            // lấy slot trong db ra
            List<Slot> slotList = patientDBContext.getAllSlots();
            req.setAttribute("slotList", slotList);
            //Lấy ngày hôm nay
            LocalDate today = LocalDate.now();
            String date = today.toString();
            session.setAttribute("date", date);
            //ngày chọn
            String selectedDate = req.getParameter("datePicker");
            if (selectedDate != null){//check ngày chọn xem nếu không null thì set lại date bằng ngày chọn
                session.setAttribute("date", selectedDate);
            } else {
                selectedDate = date;
            }
            //chọn slots
            String selectedSlot = req.getParameter("selectedSlot");
            req.setAttribute("selectedSlot", selectedSlot);
            //lấy doctor
            String did = req.getParameter("did");
            if (did == null){//check session xem nếu không null thì set lại vào did
                did = session.getAttribute("did").toString();
            }

            if (did != null) {
                session.setAttribute("did", did);
                DoctorDBContext dbContext = new DoctorDBContext();
                Doctor doctor = patientDBContext.getDoctorByPatient(did);
                req.setAttribute("doctor", doctor);
                List<Slot> slotExist = dbContext.checkSlotExist(did, selectedDate);
                req.setAttribute("slotExist", slotExist);
            }
            req.getRequestDispatcher("view/patient/booking-again.jsp").forward(req, resp);
//        }
//        resp.sendRedirect("login");
    }

}
