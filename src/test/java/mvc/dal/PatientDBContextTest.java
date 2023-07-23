
package mvc.dal;

import mvc.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class PatientDBContextTest {
    @Mock
    private PatientDBContext patientDBContext;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getPatientTrue() {
        PatientDBContext patientDBContext = new PatientDBContext();
        Account account = new Account();
        account.setUsername("patient");
        Patient patient = patientDBContext.getPatient(account);
        assertNotNull(patient);
        assertEquals(account.getUsername(), patient.getUserName());
    }
    @Test
    void getPatientFlase() {
        PatientDBContext patientDBContext = new PatientDBContext();
        Account account = new Account();
        account.setUsername("patient");
        Patient patient = patientDBContext.getPatient(account);
        assertNotNull(patient);
        assertFalse(account.getUsername().equals("patient@"));
    }
    @Test
    public void testGetDoctorByPatientTrue() {
        PatientDBContext patientDBContext = new PatientDBContext();
        String id = "1";
        Doctor doctor = patientDBContext.getDoctorByPatient(id);
        assertNotNull(doctor);
        assertEquals(1, doctor.getId());

    }
    @Test
    public void testGetDoctorByPatientFasle() {
        PatientDBContext patientDBContext = new PatientDBContext();
        String id = "1";
        Doctor doctor = patientDBContext.getDoctorByPatient(id);
        assertNotNull(doctor);
        assertFalse(doctor.getId()==12);
    }
    @Test
    public void testGetDoctorByPatientNull() {
        PatientDBContext patientDBContext = new PatientDBContext();
        String id = "999";
        Doctor doctor = patientDBContext.getDoctorByPatient(id);
        assertNull(doctor);
    }

    @Test
    public void testGetInforMyPatientsTrue(){
        Patient patient = new Patient();
        patient.setId(1);
        PatientDBContext patientDBContext = new PatientDBContext();
        List<MedicalRecord> medicalRecords = patientDBContext.getInforMyPatients(patient);
        assertEquals(14, medicalRecords.size());

    }
    @Test
    public void testGetInforMyPatientsFlase(){
        Patient patient = new Patient();
        patient.setId(1);
        PatientDBContext patientDBContext = new PatientDBContext();
        List<MedicalRecord> medicalRecords = patientDBContext.getInforMyPatients(patient);
        assertFalse(medicalRecords.size()==2);
    }
    @Test
    public void testGetBookingTrue() throws SQLException {
        Patient patient = new Patient();
        patient.setId(1);
        PatientDBContext patientDBContext = new PatientDBContext();
        // Mock other ResultSet methods
        List<Booking> bookings = patientDBContext.getBooking(patient, "Completed");
        assertEquals(4, bookings.size());
    }
    @Test
    public void testGetBookingFasle() throws SQLException {
        Patient patient = new Patient();
        patient.setId(1);
        PatientDBContext patientDBContext = new PatientDBContext();
        // Mock other ResultSet methods
        List<Booking> bookings = patientDBContext.getBooking(patient, "CONFIRMED");
        assertFalse(bookings.size()==2);
    }
    @Test
    public void testUpdatePatientTrue() {
        Account account = new Account();
        account.setUsername("patient");
        account.setPassword("12345");
        account.setEmail("quyetlbche16025@fpt.edu.vn");
        account.setPhone("123124124");
        Patient patient = new Patient();
        patient.setName("John Doe");
        PatientDBContext patientDBContext = new PatientDBContext();
        patientDBContext.UpdatePatient( patient);
        Patient patient23 = patientDBContext.getPatient(account);
        assertNotNull(patient23);
        assertEquals("patient", patient23.getUserName());
    }
    @Test
    public void testUpdatePatientFlase() {
        Account account = new Account();
        account.setUsername("patient");
        account.setPassword("12345");
        account.setEmail("quyetlbche16025@fpt.edu.vn");
        account.setPhone("123124124");
        Patient patient = new Patient();
        patient.setName("John Doe");
        PatientDBContext patientDBContext = new PatientDBContext();
        patientDBContext.UpdatePatient(patient);
        Patient patient23 = patientDBContext.getPatient(account);
        assertNotNull(patient23);
        assertFalse(patient23.getUserName().equals("patient@@"));
    }
    @Test
    public void testgetListDoctorEmptyTrue() {
        PatientDBContext patientDBContext = new PatientDBContext();
        String date = "2023-06-22";
        String slot = "1";
        String status = "Completed";
        List<Doctor> doctorList = patientDBContext.getListDoctorEmpty(date, slot, status);
        assertNotNull(doctorList);
        assertFalse(doctorList.isEmpty());
        assertEquals(14, doctorList.size());

    }
    @Test
    public void testgetListDoctorEmptyFalse() {
        PatientDBContext patientDBContext = new PatientDBContext();
        String date = "2023-06-22";
        String slot = "1";
        String status = "Completed";
        List<Doctor> doctorList = patientDBContext.getListDoctorEmpty(date, slot, status);
        assertNotNull(doctorList);
        assertFalse(doctorList.isEmpty());
        assertFalse(doctorList.size()==2);

    }
    @Test
    public void testaddNewBookingTrue(){
        Booking booking = new Booking();
        // Set up the booking object with test data
        booking.setDoctor_id(1);
        booking.setPatient_id(2);
        booking.setSlot_id(3);
        booking.setBooking_reason("Test booking");
        booking.setDate(new Date(2023-06-22));
        booking.setStatus("Completed");
        PatientDBContext patientDBContext = new PatientDBContext();
        patientDBContext.addNewBooking(booking);
        assertEquals(booking.getBooking_reason(),"Test booking");
    }

    @Test
    public void testaddNewBookingFalse(){
        Booking booking = new Booking();
        // Set up the booking object with test data
        booking.setDoctor_id(1);
        booking.setPatient_id(2);
        booking.setSlot_id(3);
        booking.setBooking_reason("Test booking");
        booking.setDate(new Date(2023-06-22));
        booking.setStatus("Completed");
        PatientDBContext patientDBContext = new PatientDBContext();
        patientDBContext.addNewBooking(booking);
        assertFalse(booking.getBooking_reason().equals("testbooking"));
    }
    @Test
    public void testCheckBookingnotNull() {
        Patient patient  = new Patient();
        patient.setId(1);
        patient.setUserName("patient");
        patient.setUserName("John Doe");
        patient.setId(1);
        patient.setUrl("");
        patient.setGender("Male");
        patient.setDob(new Date(1990-01-01));
        String date = "2023-06-22";
        String slot = "1";
        PatientDBContext patientDBContext = new PatientDBContext();
        Booking result = patientDBContext.checkBookingExist(patient, date, slot);
        assertNotNull(result);

    }

    @Test
    public void testCheckBookingNull() {
        Patient patient  = new Patient();
        patient.setId(1);
        String date = "2023-09-22";
        String slot = "1";
        PatientDBContext patientDBContext = new PatientDBContext();
        Booking result = patientDBContext.checkBookingExist(patient, date, slot);
        assertNull(result);
    }
    @Test
    public void testGetMyDoctorTrue() {
        PatientDBContext patientDBContext = new PatientDBContext();
        Patient patient = new Patient();
        patient.setUserName("patient");
        patient.setName("John Doe");
        patient.setId(1);
        patient.setUrl("");
        patient.setGender("Male");
        patient.setDob(new Date(1990-01-01));
        patient.setRankId(1);
        List<Doctor> doctors = patientDBContext.getMyDoctor(patient, "Completed");
        assertNotNull(doctors);
        assertEquals(2,doctors.size());
        assertEquals(doctors.get(0).getName(),"Nguyễn Văn X");
    }
    @Test
    public void testGetMyDoctorFalse() {
        PatientDBContext patientDBContext = new PatientDBContext();
        Patient patient = new Patient();
        patient.setUserName("patient");
        patient.setName("John Doe");
        patient.setId(1);
        patient.setUrl("");
        patient.setGender("Male");
        patient.setDob(new Date(1990-01-01));
        patient.setRankId(1);
        List<Doctor> doctors = patientDBContext.getMyDoctor(patient, "Completed");
        assertNotNull(doctors);
        assertEquals(2,doctors.size());
        assertEquals(doctors.get(0).getName(),"Nguyễn Văn X");
        assertFalse(doctors.get(0).getGender().equals("Female"));
    }
}