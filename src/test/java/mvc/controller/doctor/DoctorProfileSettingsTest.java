
        package mvc.controller.doctor;

        import jakarta.servlet.RequestDispatcher;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        import jakarta.servlet.http.HttpSession;
        import mvc.dal.DoctorDBContext;
        import mvc.model.Account;
        import mvc.model.Doctor;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

        import java.io.IOException;
        import java.sql.Date;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class DoctorProfileSettingsTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void testdoGet() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        DoctorProfileSettings doctorProfileSettings = new DoctorProfileSettings();
        Account account = (Account) session.getAttribute("account");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        if (account != null && account.getIsAdmin() == 1) {
            Doctor doctor = doctorDBContext.getDoctor(account);
            when(session.getAttribute("doctor")).thenReturn(doctor);
            when(request.getRequestDispatcher("view/doctor/doctor-pr" +
                    "ofile-settings.jsp")).thenReturn(requestDispatcher);
            doctorProfileSettings.doGet(request, response);
            verify(request).getRequestDispatcher("view/doctor/doctor-profile-settings.jsp").forward(request, response);
        }
        doctorProfileSettings.doGet(request, response);
        verify(response).sendRedirect("login");
    }
    @Test
    public void  testDoPost() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        DoctorProfileSettings doctorProfileSettings = new DoctorProfileSettings();
        Account account = (Account) session.getAttribute("account");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("doctor")).thenReturn(doctor);
        //
        String fileName = "file";
        String name = "name";
        when(request.getParameter(fileName)).thenReturn("filename.jpg");
        when(request.getParameter(name)).thenReturn("John@Doe");
        if (!name.matches("^[a-zA-Z0-9_\\p{L} ]*$")) {
            doctorProfileSettings.doPost(request, response);
            verify(request).setAttribute("messError", "Name không được chứa ký tự đặc biệt");
            verify(request).getRequestDispatcher("view/doctor/doctor-profile-settings.jsp");
            verify(requestDispatcher).forward(request, response);
        }
        String phone = "1234567890";
        when(request.getParameter(phone)).thenReturn("1234567890");
        if (!phone.matches("^[0-9]{10}$")) {
            doctorProfileSettings.doPost(request, response);
            verify(request).setAttribute("messError", "Phone sai định dạng");
            verify(request).getRequestDispatcher("view/doctor/doctor-profile-settings.jsp");
            verify(requestDispatcher).forward(request, response);
        }
    }
}