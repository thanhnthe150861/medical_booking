package controller.doctor;

import dal.DoctorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Booking;
import model.Doctor;
import model.Slot;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "DoctorScheduleTimings", value = "/doctor_schedule_timings")
public class DoctorScheduleTimings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1){
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            List<Booking> bookingList = doctorDBContext.getBooking(doctor, "Confirmed");
            req.setAttribute("bookingList", bookingList);
            //Lấy ngày hôm nay
            LocalDate today = LocalDate.now();
            String date = today.toString();
            session.setAttribute("date", date);
            req.getRequestDispatcher("view/doctor/doctor-schedule-timings.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        Doctor doctor = doctorDBContext.getDoctor(account);
        List<Booking> bookingList = doctorDBContext.getBooking(doctor, "Confirmed");
        //
        session.removeAttribute("date");
        //Ngày chọn
        String selectedDate = req.getParameter("dob");
        session.setAttribute("date", selectedDate);
        req.setAttribute("bookingList", bookingList);
        req.getRequestDispatcher("view/doctor/doctor-schedule-timings.jsp").forward(req,resp);
    }
}
