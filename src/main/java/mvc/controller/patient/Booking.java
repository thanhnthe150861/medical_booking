package mvc.controller.patient;

import mvc.dal.PatientDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import mvc.model.Doctor;
import mvc.model.Patient;
import mvc.model.Slot;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@WebServlet(name = "Booking", value = "/booking")
public class Booking extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        PatientDBContext patientDBContext = new PatientDBContext();
        if (account != null && account.getIsAdmin() == 2) {
//            // Lấy giá trị của các tham số id từ liên kết
//            String id = req.getParameter("id");
//            if(id != null){
//                Doctor doctor = patientDBContext.getDoctorByPatient(id);
//                //Lấy ngày hôm nay
//                LocalDate today = LocalDate.now();
//                String date = today.toString();
//                session.setAttribute("date", date);
//                session.setAttribute("doctor", doctor);
//                req.getRequestDispatcher("view/patient/booking.jsp").forward(req,resp);
//            }
//            }


            //Lấy ngày hôm nay
            LocalDate today = LocalDate.now();
            String date = today.toString();
            session.setAttribute("date", date);
            List<Slot> slotList = patientDBContext.getAllSlots();
            session.setAttribute("slotList", slotList);
            String selectedSlot = req.getParameter("selectedSlot");
            session.setAttribute("selectedSlot", selectedSlot);
            req.getRequestDispatcher("view/patient/booking.jsp").forward(req, resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PatientDBContext patientDBContext = new PatientDBContext();
        Account account = (Account) session.getAttribute("account");
        Patient patient = patientDBContext.getPatient(account);
        //Ngày và Slot được chọn
        String selectedDate = req.getParameter("datePicker");
        String selectedSlot = (String) session.getAttribute("selectedSlot");
        String textReason = req.getParameter("textReason");
        String status = "Cancelled";
        mvc.model.Booking booking = patientDBContext.checkBookingExsit(patient, selectedDate, selectedSlot);
        if(booking != null){
            req.setAttribute("messError", "Bạn đã lịch đặt vào ca này");
            req.getRequestDispatcher("view/patient/booking.jsp").forward(req, resp);
        }else {
            if (selectedDate != null && selectedSlot != null) {
                List<Doctor> doctorList = patientDBContext.getListDoctorEmpty(selectedDate, selectedSlot, status);
                Random random = new Random();
                int size = doctorList.size();
                if (size > 0) {
                    int randomIndex = random.nextInt(size);
                    Doctor randomDoctor = doctorList.get(randomIndex);
                    mvc.model.Booking bookings = new mvc.model.Booking();
                    bookings.setDoctor_id(randomDoctor.getId());
                    bookings.setPatient_id(patient.getId());
                    bookings.setSlot_id(Integer.parseInt(selectedSlot));
                    bookings.setDate(Date.valueOf(selectedDate));
                    bookings.setBooking_reason(textReason);
                    bookings.setStatus("Pending");
                    patientDBContext.addNewBooking(bookings);
                    resp.sendRedirect("patient_dashboard");
                } else {
                    req.setAttribute("messError", "Không có bác sĩ nào trống lịch khám vào ca này");
                    req.getRequestDispatcher("view/patient/booking.jsp").forward(req, resp);
                }
            }
        }
    }
}
