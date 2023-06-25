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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "DoctorAppointments", value = "/doctor_appointments")
public class DoctorAppointments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1){
            Doctor doctor = doctorDBContext.getDoctor(account);
            session.setAttribute("doctor", doctor);
            // Lấy giá trị của các tham số id và status từ liên kết
            String id = req.getParameter("id");
            String status = req.getParameter("status");
            // Kiểm tra xem các tham số có tồn tại hay không
            if (id != null && status != null) {
                // Cập nhật trạng thái của đặt lịch
                doctorDBContext.updateBookingStatus(id, status);
            }
            List<Booking> bookingList = doctorDBContext.getBooking(doctor, "Pending");
            req.setAttribute("bookingList", bookingList);
            req.getRequestDispatcher("view/doctor/doctor-appointments.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
