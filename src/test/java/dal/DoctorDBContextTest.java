package dal;

import model.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DoctorDBContextTest {

    @Test
    public void testGetDoctorTrue() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Account account = new Account();
        account.setUsername("doctor1");
        Doctor doctor = dbContext.getDoctor(account);
        assertEquals( 2, doctor.getId());
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
        assertEquals(1, patient.getId());
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
        String status = "confirmed";
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
        Account account = new Account("doctor1", "123", "0982783817", "olika@gmail.com",1);
        Doctor doctor = new Doctor(2,"doctor1"	,null,"Dr. Park","Female", new Date(1975-07-07), "Bác Sĩ Chuyên Khoa Mũi",2);
        // Update the doctor's information
        dbContext.updateDoctor(account, doctor);
        Doctor doctor23 = dbContext.getDoctor(account);
        assertNotNull(doctor23);
        assertEquals("doctor1", doctor23.getUserName());
    }
    @Test
    public void testUpdateDoctorFlase() {
        DoctorDBContext dbContext = new DoctorDBContext();
        Account account = new Account("doctor1", "123", "0982783817", "olika@gmail.com",1);
        Doctor doctor = new Doctor(2,"doctor1"	,null,"Dr. Park","Female", new Date(1975-07-07), "Bác Sĩ Chuyên Khoa Mũi",2);
        // Update the doctor's information
        dbContext.updateDoctor(account, doctor);
        Doctor doctor23 = dbContext.getDoctor(account);
        assertNotNull(doctor23);
        assertFalse(doctor23.getUserName().equals("doctor1@"));
    }

}