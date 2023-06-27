package controller.patient;

import dal.PatientDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Doctor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@WebServlet(name = "Booking", value = "/booking")
public class Booking extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        PatientDBContext patientDBContext = new PatientDBContext();
        if (account != null && account.getIsAdmin() == 2){
            // Lấy giá trị của các tham số id từ liên kết
            String id = req.getParameter("id");
            if(id != null){
                Doctor doctor = patientDBContext.getDoctorByPatient(id);
                session.setAttribute("doctor", doctor);
                req.getRequestDispatcher("view/patient/booking.jsp").forward(req,resp);
            }
            String date = req.getParameter("date");
            String slot = req.getParameter("slot");
            if(date != null && slot != null){
                List<Doctor> doctorList = patientDBContext.getAllDoctor();
                List<Doctor> doctorListNotEmpty = patientDBContext.checkDateSlotEmpty(date, slot);
                for (Doctor doctor : doctorListNotEmpty) {
                    int idToRemove = doctor.getId();
                    Iterator<Doctor> iterator = doctorList.iterator();
                    while (iterator.hasNext()) {
                        Doctor d = iterator.next();
                        if (d.getId() == idToRemove) {
                            iterator.remove();
                        }
                    }
                }
                Random rand = new Random();
                Doctor randomDoctor = doctorList.get(rand.nextInt(doctorList.size()));
                session.setAttribute("randomDoctor", randomDoctor);
                req.getRequestDispatcher("view/patient/booking.jsp").forward(req,resp);
            }
            req.getRequestDispatcher("view/patient/booking.jsp").forward(req,resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("check_out");
    }
}
