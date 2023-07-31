package mvc.controller.doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dal.DoctorDBContext;
import mvc.dal.PatientDBContext;
import mvc.model.Account;
import mvc.model.NgayNghi;
import mvc.model.Doctor;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "DoctorNgayNghi", value = "/doctor_ngay_nghi")
public class DoctorNgayNghi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        PatientDBContext pdb = new PatientDBContext();
        if (account != null) {
            Doctor doctor = doctorDBContext.getDoctor(account);
            String id = req.getParameter("id");
            String status = req.getParameter("status");
            if (id != null && status != null) {
                // Cập nhật trạng thái của đặt lịch
                doctorDBContext.updateNgayNghiStatus(id, status);
                req.setAttribute("messSuccess", "Mở khóa thành công");
            }
            session.setAttribute("doctor", doctor);
            List<NgayNghi> ngayNghiList = doctorDBContext.GetAllngaynghi(doctor);
            session.setAttribute("ngayNghiList", ngayNghiList);
            //lấy date đã trọn, nếu ko có set mặc định ngày hôm nay
            LocalDate date = req.getParameter("datePicker") != null ? LocalDate.parse(req.getParameter("datePicker")) : LocalDate.now();
            session.setAttribute("date", date.toString());
            //lấy full slots ở db
            session.setAttribute("slotList", pdb.getAllSlots());
            // ngày chọn
            session.setAttribute("selectedSlot", req.getParameter("selectedSlot"));
            req.getRequestDispatcher("view/doctor/ngay-nghi.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        //Ngày chọn
        String selectedDate = session.getAttribute("date").toString();
        String selectedSlot = session.getAttribute("selectedSlot").toString();
        NgayNghi nghi = doctorDBContext.checkNgayNghi(doctor.getId(), selectedDate, selectedSlot);
        if (nghi != null) {
            req.setAttribute("messError", "Bạn đã khóa ca này");
            req.getRequestDispatcher("view/doctor/ngay-nghi.jsp").forward(req, resp);
            return;
        }
        NgayNghi ngayNghi = new NgayNghi();
        ngayNghi.setDate(Date.valueOf(selectedDate));
        ngayNghi.setSlot_id(Integer.parseInt(selectedSlot));
        ngayNghi.setStatus(true);
        ngayNghi.setDoctor(doctor);
        doctorDBContext.CreateNgayNghi(ngayNghi);
        req.setAttribute("messSuccess", "Khóa ca làm thành công");
        List<NgayNghi> ngayNghiList = doctorDBContext.GetAllngaynghi(doctor);
        session.setAttribute("ngayNghiList", ngayNghiList);
        session.setAttribute("date", selectedDate);
        req.getRequestDispatcher("view/doctor/ngay-nghi.jsp").forward(req, resp);
    }
}
