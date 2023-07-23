
        package mvc.controller.doctor;

        import jakarta.servlet.RequestDispatcher;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        import jakarta.servlet.http.HttpSession;
        import mvc.controller.admin.AdminDashboard;
        import mvc.dal.DoctorDBContext;
        import mvc.model.Account;
        import mvc.model.Booking;
        import mvc.model.Doctor;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

        import java.util.List;

        import static org.hamcrest.CoreMatchers.any;
        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class DoctorAppointmentsTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void testDoGetTrue() throws Exception {
        MockitoAnnotations.initMocks(this);
        DoctorAppointments doctorAppointments = new DoctorAppointments();
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        Account account = (Account) session.getAttribute("account");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(account);
        if(account != null && account.getIsAdmin()==1){
            Doctor doctor = doctorDBContext.getDoctor(account);
            when(session.getAttribute("doctor")).thenReturn(doctor);
            String id = request.getParameter("1");
            String status = request.getParameter("Pending");
            when(request.getParameter(id)).thenReturn("1");
            when(request.getParameter(status)).thenReturn("Pending");
            if(id != null && status!= null){
                doctorDBContext.updateBookingStatus(id,status);
            }
            List<Booking> bookingList = doctorDBContext.getBooking(doctor, "Pending");
            request.setAttribute("bookingList",bookingList);
            when(request.getRequestDispatcher("view/doctor/doctor-appointments.jsp")).thenReturn(requestDispatcher);
            doctorAppointments.doGet(request,response);
            verify(doctorDBContext).updateBookingStatus("1", "Pending");
            verify(doctorDBContext).getBooking(doctor, "Pending");
            verify(request).setAttribute("bookingList", bookingList);
            verify(request).getRequestDispatcher("view/doctor/doctor-appointments.jsp");
            verify(response).sendRedirect("login");
        }
    }
}