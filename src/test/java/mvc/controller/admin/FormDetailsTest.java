
        package mvc.controller.admin;

        import jakarta.servlet.RequestDispatcher;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        import jakarta.servlet.http.HttpSession;
        import mvc.dal.AccountDB;
        import mvc.model.*;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

        import java.io.IOException;
        import java.sql.Date;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class FormDetailsTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void testDoGet() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        FormDetails formDetails = new FormDetails();
        Account account = new Account();
        AccountDB accountDB = new AccountDB();
        account.setIsAdmin(0);
        account.setUsername("Admin");
        List<Rank> rankslistdoctor = accountDB.getRankDoctor();
        List<Rank> rankListpatient = accountDB.getRankPatient();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("rankslistdoctor")).thenReturn(rankslistdoctor);
        when(session.getAttribute("rankListpatient")).thenReturn(rankListpatient);
        String did = request.getParameter("did");
        String pid = request.getParameter("pid");
        String sid = request.getParameter("sid");
        when(session.getAttribute("doctor")).thenReturn(null);
        when(session.getAttribute("patient")).thenReturn(null);
        when(session.getAttribute("staff")).thenReturn(null);
        if (did == "doctor") {
            when(request.getParameter("did")).thenReturn(did);
            Doctor doctor = new Doctor();
            when(accountDB.getDoctorByID(did)).thenReturn(doctor);
            // Act
            formDetails.doGet(request, response);
            // Assert
            verify(session).setAttribute("did", did);
            verify(session).setAttribute("doctor", doctor);
            verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(request, response);
        } else if (pid == "patient") {
            when(request.getParameter("pid")).thenReturn(pid);
            Patient patient = new Patient();
            when(accountDB.getPatientByID(pid)).thenReturn(patient);
            // Act
            formDetails.doGet(request, response);
            // Assert
            verify(session).setAttribute("pid", pid);
            verify(session).setAttribute("patient", patient);
            verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp").forward(request, response);
        } else if (sid == "staff") {
            when(request.getParameter("sid")).thenReturn(sid);
            Staff staff = new Staff();
            // Act
            formDetails.doGet(request, response);
            // Assert
            verify(session).setAttribute("sid", sid);
            verify(session).setAttribute("staff", staff);
            verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp").forward(request, response);
        }

        String str = request.getParameter("str");
        if (str == null) {
            str = "";
        }
        if (str.equals("doctor")) {
            when(request.getParameter("str")).thenReturn(str);
            when(request.getSession()).thenReturn(session);
            // Call the function
            formDetails.doGet(request, response);
            // Verify the behavior
            verify(session).setAttribute("str", "doctor");
            verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
            verify(requestDispatcher).forward(request, response);
        } else if (str.equals("patient")) {
            when(request.getParameter("str")).thenReturn(str);
            when(request.getSession()).thenReturn(session);
            // Call the function
            formDetails.doGet(request, response);
            // Verify the behavior
            verify(session).setAttribute("patient", str);
            verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
            verify(requestDispatcher).forward(request, response);
        } else if (str.equals("staff")) {
            when(request.getParameter("str")).thenReturn(str);
            when(request.getSession()).thenReturn(session);
            // Call the function
            formDetails.doGet(request, response);
            // Verify the behavior
            verify(session).setAttribute("str", "staff");
            verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp");
            verify(requestDispatcher).forward(request, response);
        } else {

            // Call the function
            formDetails.doGet(request, response);
            // Verify the behavior

            verify(request).getRequestDispatcher("login");
        }
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        FormDetails formDetails = new FormDetails();
        String did = (String) session.getAttribute("did");
        String pid = (String) session.getAttribute("pid");
        String sid = (String) session.getAttribute("sid");
        String user = request.getParameter("username");
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
        AccountDB accountDB = null;
        if (did == "doctor") {
            when(request.getParameter(pass)).thenReturn("newPassword");
            when(request.getParameter(url)).thenReturn("newUrl");
            when(request.getParameter(name)).thenReturn("newName");
            when(request.getParameter(gender)).thenReturn("newMale");
            when(request.getParameter(dob)).thenReturn("1990-01-01");
            when(request.getParameter(specialty)).thenReturn("newCardiology");
            when(request.getParameter(phone)).thenReturn("newphone");
            when(request.getParameter(email)).thenReturn("newemail");
            when(request.getParameter(rank)).thenReturn("1");
            when(request.getParameter(String.valueOf(status))).thenReturn("1");

            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Speciality không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Phone sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Email sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }

            accountDB = new AccountDB();
            Doctor doctor = (Doctor) session.getAttribute("doctor");
            doctor.getAccount().setStatus(status);
            doctor.getAccount().setPhone(phone);
            doctor.getAccount().setEmail(email);
            doctor.getAccount().setPassword(pass);
            doctor.getAccount().setIsAdmin(1);
            //
            doctor.setUrl(url);
            doctor.setName(name);
            doctor.setGender(gender);
            doctor.setDob(Date.valueOf(dob));
            doctor.setSpecialty(specialty);
            doctor.setRankId(Integer.parseInt(rank));
            accountDB.UpdateDoctor(doctor);
            formDetails.doGet(request, response);
            verify(request).setAttribute("messSuccess", "Cập nhật thành công");
            verify(session).removeAttribute(pid);
            verify(session).removeAttribute(sid);
            verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp").forward(request, response);
        } else if (pid == "patient") {

            when(request.getParameter(pass)).thenReturn("newPassword");
            when(request.getParameter(url)).thenReturn("newUrl");
            when(request.getParameter(name)).thenReturn("newName");
            when(request.getParameter(gender)).thenReturn("newMale");
            when(request.getParameter(dob)).thenReturn("1990-01-01");
            when(request.getParameter(specialty)).thenReturn("newCardiology");
            when(request.getParameter(phone)).thenReturn("newphone");
            when(request.getParameter(email)).thenReturn("newemail");
            when(request.getParameter(rank)).thenReturn("1");
            when(request.getParameter(String.valueOf(status))).thenReturn("1");

            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Speciality không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Phone sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Email sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }

            Patient patient = (Patient) session.getAttribute("patient");
            patient.getAccount().setStatus(status);
            patient.getAccount().setPhone(phone);
            patient.getAccount().setEmail(email);
            patient.getAccount().setPassword(pass);
            patient.getAccount().setIsAdmin(1);
            //
            patient.setUrl(url);
            patient.setName(name);
            patient.setGender(gender);
            patient.setDob(Date.valueOf(dob));
            patient.setRankId(Integer.parseInt(rank));
            accountDB.UpdatePatient(patient);
            formDetails.doGet(request, response);
            verify(request).setAttribute("messSuccess", "Cập nhật thành công");
            verify(session).removeAttribute(did);
            verify(session).removeAttribute(sid);
            verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp").forward(request, response);
        } else if (sid == "staff") {
            when(request.getParameter(pass)).thenReturn("newPassword");
            when(request.getParameter(url)).thenReturn("newUrl");
            when(request.getParameter(name)).thenReturn("newName");
            when(request.getParameter(gender)).thenReturn("newMale");
            when(request.getParameter(dob)).thenReturn("1990-01-01");
            when(request.getParameter(specialty)).thenReturn("newCardiology");
            when(request.getParameter(phone)).thenReturn("newphone");
            when(request.getParameter(email)).thenReturn("newemail");
            when(request.getParameter(rank)).thenReturn("1");
            when(request.getParameter(String.valueOf(status))).thenReturn("1");

            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Speciality không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Phone sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Email sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }

            Staff staff = (Staff) session.getAttribute("staff");
            staff.getAccount().setStatus(status);
            staff.getAccount().setPhone(phone);
            staff.getAccount().setEmail(email);
            staff.getAccount().setPassword(pass);
            staff.getAccount().setIsAdmin(1);
            //
            staff.setUrl(url);
            staff.setName(name);
            staff.setGender(gender);
            staff.setDob(Date.valueOf(dob));
            accountDB.UpdateStaff(staff);
            formDetails.doGet(request, response);
            verify(request).setAttribute("messSuccess", "Cập nhật thành công");
            verify(session).removeAttribute(did);
            verify(session).removeAttribute(pid);
            verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp").forward(request, response);
        }

        String str = (String) session.getAttribute("str");
        if (str == null) {
            str = "";
        }
        if (str.equals("doctor")) {
            when(request.getParameter("str")).thenReturn(str);
            when(request.getSession()).thenReturn(session);
            when(request.getParameter(user)).thenReturn("newuser");
            when(request.getParameter(pass)).thenReturn("newPassword");
            when(request.getParameter(url)).thenReturn("newUrl");
            when(request.getParameter(name)).thenReturn("newName");
            when(request.getParameter(gender)).thenReturn("newMale");
            when(request.getParameter(dob)).thenReturn("1990-01-01");
            when(request.getParameter(specialty)).thenReturn("newCardiology");
            when(request.getParameter(phone)).thenReturn("newphone");
            when(request.getParameter(email)).thenReturn("newemail");
            when(request.getParameter(rank)).thenReturn("1");
            when(request.getParameter(String.valueOf(status))).thenReturn("1");
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Speciality không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Phone sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Email sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            if (accountDB.checkAccountExist(user) == null) {
                Account account = new Account();
                account.setStatus(true);
                account.setPhone(phone);
                account.setEmail(email);
                account.setUsername(user);
                account.setPassword(pass);
                account.setIsAdmin(1);
                Doctor doctor = new Doctor();
                doctor.setUserName(user);
                doctor.setUrl(url);
                doctor.setName(name);
                doctor.setGender(gender);
                doctor.setDob(Date.valueOf(dob));
                doctor.setSpecialty(specialty);
                doctor.setRankId(1);
                doctor.setAccount(account);
                accountDB.addNewDoctor(doctor);
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Tạo tài khoản thành công");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            } else {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "User đã tồn tại");
                verify(request).getRequestDispatcher("view/admin/form-doctor-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }

        } else if (str.equals("patient")) {
            when(request.getParameter("str")).thenReturn(str);
            when(request.getSession()).thenReturn(session);
            when(request.getParameter(user)).thenReturn("newuser");
            when(request.getParameter(pass)).thenReturn("newPassword");
            when(request.getParameter(url)).thenReturn("newUrl");
            when(request.getParameter(name)).thenReturn("newName");
            when(request.getParameter(gender)).thenReturn("newMale");
            when(request.getParameter(dob)).thenReturn("1990-01-01");
            when(request.getParameter(specialty)).thenReturn("newCardiology");
            when(request.getParameter(phone)).thenReturn("newphone");
            when(request.getParameter(email)).thenReturn("newemail");
            when(request.getParameter(rank)).thenReturn("1");
            when(request.getParameter(String.valueOf(status))).thenReturn("1");
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Speciality không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Phone sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Email sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            if (accountDB.checkAccountExist(user) == null) {
                Account account = new Account();
                account.setStatus(true);
                account.setPhone(phone);
                account.setEmail(email);
                account.setUsername(user);
                account.setPassword(pass);
                account.setIsAdmin(2);
                Patient patient = new Patient();
                patient.setUserName(user);
                patient.setUrl(url);
                patient.setName(name);
                patient.setGender(gender);
                patient.setDob(Date.valueOf(dob));
                patient.setRankId(1);
                patient.setAccount(account);
                accountDB.addNewPatient(patient);
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Tạo tài khoản thành công");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            } else {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "User đã tồn tại");
                verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
        } else if (str.equals("staff")) {
            when(request.getParameter("str")).thenReturn(str);
            when(request.getSession()).thenReturn(session);
            when(request.getParameter(user)).thenReturn("newuser");
            when(request.getParameter(pass)).thenReturn("newPassword");
            when(request.getParameter(url)).thenReturn("newUrl");
            when(request.getParameter(name)).thenReturn("newName");
            when(request.getParameter(gender)).thenReturn("newMale");
            when(request.getParameter(dob)).thenReturn("1990-01-01");
            when(request.getParameter(specialty)).thenReturn("newCardiology");
            when(request.getParameter(phone)).thenReturn("newphone");
            when(request.getParameter(email)).thenReturn("newemail");
            when(request.getParameter(rank)).thenReturn("1");
            when(request.getParameter(String.valueOf(status))).thenReturn("1");
            if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Name không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            if (!specialty.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Speciality không được chứa ký tự đặc biệt");
                verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate phone: should only contain numbers and not exceed 10 digits
            if (!phone.matches("^[0-9]{10}$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Phone sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            // Validate email: should be in the correct email format
            if (!email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                formDetails.doPost(request, response);
                verify(request).setAttribute("messError", "Email sai định dạng");
                verify(request).getRequestDispatcher("view/admin/form-staff-details.jsp");
                verify(requestDispatcher).forward(request, response);
            }
            if (accountDB.checkAccountExist(user) == null) {
                if (accountDB.checkAccountExist(user) == null) {
                    Account account = new Account();
                    account.setStatus(true);
                    account.setPhone(phone);
                    account.setEmail(email);
                    account.setUsername(user);
                    account.setPassword(pass);
                    account.setIsAdmin(3);
                    Staff staff = new Staff();
                    staff.setUserName(user);
                    staff.setUrl(url);
                    staff.setName(name);
                    staff.setGender(gender);
                    staff.setDob(Date.valueOf(dob));
                    staff.setAccount(account);
                    accountDB.addNewStaff(staff);
                    formDetails.doPost(request, response);
                    verify(request).setAttribute("messError", "Tạo tài khoản thành công");
                    verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                    verify(requestDispatcher).forward(request, response);
                } else {
                    formDetails.doPost(request, response);
                    verify(request).setAttribute("messError", "User đã tồn tại");
                    verify(request).getRequestDispatcher("view/admin/form-patient-details.jsp");
                    verify(requestDispatcher).forward(request, response);
                }
            }
        }else if (str == null) {
            formDetails.doGet(request,response);
            verify(session, never()).setAttribute(anyString(), anyString());
            verify(request, never()).getRequestDispatcher(anyString());
            verify(response).sendRedirect("admin_dashboard");
        }
    }
}