package dal;

import static org.junit.jupiter.api.Assertions.*;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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
    public void testGetAllDoctortrue() {
        PatientDBContext patientDBContext = new PatientDBContext();
        List<Doctor> doctorList = patientDBContext.getAllDoctor();
        assertNotNull(doctorList);
        assertTrue(doctorList.size() > 0);
        // Add more assertions to validate the returned doctorList
    }
    @Test
    public void testGetAllDoctorFasle() {
        PatientDBContext patientDBContext = new PatientDBContext();
        List<Doctor> doctorList = patientDBContext.getAllDoctor();
        assertNotNull(doctorList);
        assertFalse(doctorList.size()==123);
        // Add more assertions to validate the returned doctorList
    }
    @Test
    public void testGetInforMyPatientsTrue(){
        Patient patient = new Patient();
        patient.setId(1);
        PatientDBContext patientDBContext = new PatientDBContext();
        List<MedicalRecord> medicalRecords = patientDBContext.getInforMyPatients(patient);
        assertEquals(1, medicalRecords.size());

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
        List<Booking> bookings = patientDBContext.getBooking(patient, "CONFIRMED");
        assertEquals(1, bookings.size());
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
        Account account = new Account("patient", "123", "0982783817", "olika@gmail.com",2);
        Patient patient = new Patient();
        patient.setName("John Doe");
        PatientDBContext patientDBContext = new PatientDBContext();
        patientDBContext.UpdatePatient(account, patient);
        Patient patient23 = patientDBContext.getPatient(account);
        assertNotNull(patient23);
        assertEquals("patient", patient23.getUserName());
    }
    @Test
    public void testUpdatePatientFlase() {
        Account account = new Account("patient", "123", "0982783817", "olika@gmail.com",2);
        Patient patient = new Patient();
        patient.setName("John Doe");
        PatientDBContext patientDBContext = new PatientDBContext();
        patientDBContext.UpdatePatient(account, patient);
        Patient patient23 = patientDBContext.getPatient(account);
        assertNotNull(patient23);
        assertFalse(patient23.getUserName().equals("patient@@"));
    }
    @Test
    public void testCheckDateSlotEmpty_PositiveCaseTrue() {
        PatientDBContext patientDBContext = new PatientDBContext();
        String date = "2023-06-22";
        String slot = "1";
        List<Doctor> result = patientDBContext.checkDateSlotEmpty(date, slot);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    public void testCheckDateSlotEmptyFlase() {
        PatientDBContext patientDBContext = new PatientDBContext();
        String date = "2029-06-01";
        String slot = "7";
        List<Doctor> result = patientDBContext.checkDateSlotEmpty(date, slot);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}