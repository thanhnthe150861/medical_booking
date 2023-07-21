package mvc.controller.staff;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import mvc.dal.AccountDB;
import mvc.model.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "EditDetail", value = "/edit_detail")
public class EditDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDB adb = new AccountDB();
        if (account != null && account.getIsAdmin() == 3) {
            List<Rank> rankListDoctor = adb.getRankDoctor();
            List<Rank> rankListPatient = adb.getRankPatient();
            session.setAttribute("rankListDoctor", rankListDoctor);
            session.setAttribute("rankListPatient", rankListPatient);

            //lấy id để update
            String did = request.getParameter("did");
            String pid = request.getParameter("pid");

            //sau khi cập nhật xong thì xóa session đi để ko bị dính vào add
            session.removeAttribute("doctor");
            session.removeAttribute("patient");

            //Update
            if (did != null) {
                session.setAttribute("did", did);
                Doctor doctor = adb.getDoctorByID(did);
                session.setAttribute("doctor", doctor);
                request.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(request, response);
            } else if (pid != null) {
                session.setAttribute("pid", pid);
                Patient patient = adb.getPatientByID(pid);
                session.setAttribute("patient", patient);
                request.getRequestDispatcher("view/staff/patient-detail.jsp").forward(request, response);
            }

            //Add
            String str = request.getParameter("str");
            if (str == null) {
                str = "";
            }
            if (str.equals("doctor")) {
                session.setAttribute("str", str);
                request.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(request, response);
            } else if (str.equals("patient")) {
                session.setAttribute("str", str);
                request.getRequestDispatcher("view/staff/patient-detail.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("staff_dashboard");
            }
        } else {
            request.getRequestDispatcher("login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Update
        String did = (String) session.getAttribute("did");
        String pid = (String) session.getAttribute("pid");
        if (did != null && pid == null) {
            String pass = request.getParameter("password");
            String url = request.getParameter("file");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");
            String specialty = request.getParameter("speciality");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String rank = request.getParameter("rank");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));

            // Validate name: should not contain special characters
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                request.setAttribute("messError", "Tên không được chứa ký tự đặc biệt");
                request.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(request, response);
                return;
            }
            // Validate specialty: should not contain special characters
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                request.setAttribute("messError", "Chuyên môn không được chứa ký tự đặc biệt");
                request.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(request, response);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                request.setAttribute("messError", "Số điện thoại sai định dạng");
                request.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(request, response);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                request.setAttribute("messError", "Email sai định dạng");
                request.getRequestDispatcher("view/staff/doctor-detail.jsp").forward(request, response);
                return;
            }
            AccountDB adb = new AccountDB();
            Doctor doctor = (Doctor) session.getAttribute("doctor");
            doctor.getAccount().setStatus(status);
            doctor.getAccount().setPhone(phone);
            doctor.getAccount().setEmail(email);
            doctor.getAccount().setPassword(pass);
            doctor.getAccount().setIsAdmin(1);
            doctor.setUrl(url);
            doctor.setName(name);
            doctor.setGender(gender);
            doctor.setDob(Date.valueOf(dob));
            doctor.setSpecialty(specialty);
            doctor.setRankId(Integer.parseInt(rank));
            adb.UpdateDoctor(doctor);
            request.setAttribute("messSuccess", "Cập nhật thành công");
            session.removeAttribute("did");
            response.sendRedirect("view/staff/doctor-detail.jsp");

        } else if (did != null && pid != null) {
            String pass = request.getParameter("password");
            String url = request.getParameter("file");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String rank = request.getParameter("rank");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));

            // Validate name: should not contain special characters
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                request.setAttribute("messError", "Tên không được chứa ký tự đặc biệt");
                request.getRequestDispatcher("view/staff/patient-detail.jsp").forward(request, response);
                return;
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                request.setAttribute("messError", "Số điện thoại sai định dạng");
                request.getRequestDispatcher("view/staff/patient-detail.jsp").forward(request, response);
                return;
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                request.setAttribute("messError", "Email sai định dạng");
                request.getRequestDispatcher("view/staff/patient-detail.jsp").forward(request, response);
                return;
            }
            AccountDB adb = new AccountDB();
            Patient patient = (Patient) session.getAttribute("patient");
            patient.getAccount().setStatus(status);
            patient.getAccount().setPhone(phone);
            patient.getAccount().setEmail(email);
            patient.getAccount().setPassword(pass);
            patient.getAccount().setIsAdmin(1);
            patient.setUrl(url);
            patient.setName(name);
            patient.setGender(gender);
            patient.setDob(Date.valueOf(dob));
            patient.setRankId(Integer.parseInt(rank));
            adb.UpdatePatient(patient);
            request.setAttribute("messSuccess", "Cập nhật thành công");
            session.removeAttribute("pid");
            response.sendRedirect("view/staff/patient-detail.jsp");
        }

    }

}
