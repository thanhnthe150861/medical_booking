package mvc.controller.doctor;

import mvc.dal.DoctorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.model.Account;
import mvc.model.Booking;
import mvc.model.Doctor;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DoctorDashboard", value = "/doctor_dashboard")
public class DoctorDashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("account");
            DoctorDBContext doctorDBContext = new DoctorDBContext();
            if (account != null && account.getIsAdmin() == 1) {
                Doctor doctor = doctorDBContext.getDoctor(account);
                session.setAttribute("doctor", doctor);
                List<Booking> bookingList = doctorDBContext.getBooking(doctor, "Pending");
                req.setAttribute("bookingList", bookingList);
                req.getRequestDispatcher("view/doctor/doctor-dashboard.jsp").forward(req, resp);
            }
            resp.sendRedirect("login");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
