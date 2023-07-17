
package mvc.dal;

import mvc.model.*;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DoctorDBContextTest {
    @Test
    public void testGetDoctorTrue() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Account account = new Account();
        account.setUsername("doctor1");
        Doctor doctor = dbContext.getDoctor(account);
        assertEquals(2, doctor.getId());
        assertEquals("doctor1", doctor.getUserName());
    }
    @Test
    public void testGetDoctorFasle() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Account account = new Account();
        account.setUsername("doctor1");
        Doctor doctor = dbContext.getDoctor(account);
        assertFalse(doctor.getId()==1);
        assertFalse(doctor.getUserName().equals("doctor@@"));
    }
    @Test
    public void testGetPatientByDoctorTrue() {
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        Patient patient = doctorDBContext.getPatientByDoctor("1");
        assertNotNull(patient);
        assertTrue(patient.getId()==1);
        assertEquals("patient", patient.getUserName());
        assertEquals(null, patient.getUrl());
        assertEquals("John Doe", patient.getName());
        assertEquals("Male", patient.getGender());
    }
    @Test
    public void testGetPatientByDoctorFalse() {
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        Patient patient = doctorDBContext.getPatientByDoctor("1");
        assertNotNull(patient);
        assertFalse(patient.getId()==2);
        assertFalse(patient.getUserName().equals("patient@@"));
        assertEquals(null, patient.getUrl());
        assertEquals("John Doe", patient.getName());
        assertEquals("Male", patient.getGender());
    }
    @Test
    public void testGetPatientByDoctorNull() {
        DoctorDBContext doctorDBContext = new DoctorDBContext();
        Patient patient = doctorDBContext.getPatientByDoctor("0");
        assertNull(patient);
    }
    @Test
    public void testGetInforMyPatientsFlase() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Doctor doctor = new Doctor();
        doctor.setId(1);
        String patientId = "123";
        // Perform the test
        List<MedicalRecord> result = dbContext.getInforMyPatients(doctor, patientId);
        // Assert the result
        assertNotNull(result);
        assertTrue(result.isEmpty());
        // Add more assertions to validate the result
    }
    @Test
    public void testGetInforMyPatientsTrue() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Doctor doctor = new Doctor();
        doctor.setId(1);
        String patientId = "1";
        // Perform the test
        List<MedicalRecord> result = dbContext.getInforMyPatients(doctor, patientId);
        // Assert the result
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // Add more assertions to validate the result
    }
    @Test
    public void testGetBookingTrue() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Doctor doctor = new Doctor();
        doctor.setId(1);
        String status = "Pending";
        List<Booking> bookings = dbContext.getBooking(doctor,status);
        assertTrue(bookings.size()==1);
    }
    @Test
    public void testGetBookingFasle() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Doctor doctor = new Doctor();
        doctor.setId(1);
        String status = "confirmed";
        List<Booking> bookings = dbContext.getBooking(doctor,status);
        assertFalse(bookings.size()==2);
    }


    @Test
    public void testUpdateDoctorTrue() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Account account = new Account();
        Doctor doctor = new Doctor();
        account.setUsername("doctor1");
        account.setPassword("1234");
        account.setEmail("quyet@gmail.com");
        account.setPhone("0868746275");
        doctor.setName("doctor1");
        dbContext.updateDoctor( doctor);
        Doctor doctor23 = dbContext.getDoctor(account);
        assertNotNull(doctor23);
        assertEquals("doctor1", doctor23.getUserName());
    }
    @Test
    public void testUpdateDoctorFlase() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Account account = new Account();
        Doctor doctor = new Doctor();
        account.setUsername("doctor1");
        account.setPassword("1234");
        account.setEmail("quyet@gmail.com");
        account.setPhone("0868746275");
        doctor.setName("doctor1");
        // Update the doctor's information
        dbContext.updateDoctor( doctor);
        Doctor doctor23 = dbContext.getDoctor(account);
        assertNotNull(doctor23);
        assertFalse(doctor23.getUserName().equals("doctor1@"));
    }
    @Test
    public void testGetMyPatientTrue(){
        DoctorDBContext dbContext = new DoctorDBContext();
        Booking booking = new Booking();
        Doctor doctor = new Doctor();
        booking.setStatus("Completed");
        doctor.setId(1);
        List<Patient> patient = dbContext.getMyPatient(doctor,booking.getStatus());
        assertNotNull(patient);
        assertEquals(2, patient.size());
        assertEquals("John Doe",patient.get(0).getName());
    }
    @Test
    public void testGetMyPatientFalse(){
        DoctorDBContext dbContext = new DoctorDBContext();
        Booking booking = new Booking();
        Doctor doctor = new Doctor();
        booking.setStatus("Completed");
        doctor.setId(1);
        List<Patient> patient = dbContext.getMyPatient(doctor,booking.getStatus());
        assertNotNull(patient);
        assertEquals(2, patient.size());
        assertEquals("John Doe",patient.get(0).getName());
        assertFalse(patient.get(0).getAccount().equals("patient!@"));
    }
}