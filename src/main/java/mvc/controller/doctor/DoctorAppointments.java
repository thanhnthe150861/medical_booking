package mvc.controller.doctor;

import mvc.dal.DoctorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import mvc.model.Doctor;
import mvc.model.MedicalRecord;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DoctorAppointments", value = "/doctor_appointments")
public class DoctorAppointments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        if (account != null && account.getIsAdmin() == 1) {
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
            List<MedicalRecord> bookingList = doctorDBContext.bookingList(doctor);
            req.setAttribute("bookingList", bookingList);
            req.getRequestDispatcher("view/doctor/doctor-appointments.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
