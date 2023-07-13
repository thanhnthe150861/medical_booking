package mvc.dal;

import mvc.model.*;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminDBContextTest {
    @Test
    public void testGetTop5DoctorTrue() {
        AdminDBContext adminDBContext = new AdminDBContext();
        Doctor doctor = new Doctor();
        Rank rank = new Rank();
        rank.setId(1);
        doctor.setId(1);
        doctor.setName("Dr Smith");
        doctor.setUrl("");
        doctor.setGender("Male");
        doctor.setDob(new Date(1980-03-03));
        doctor.setSpecialty("Bác Sĩ Chuyên Khoa Mũi Họng");
        doctor.setRanks(rank);
        Booking booking = new Booking();
        booking.setDoctor(doctor);
        Bill bill = new Bill();
        bill.setPrice(100.5f);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        List<MedicalRecord> doctorListTop5 = adminDBContext.getTop5Doctor();
        // Positive test case
        assertNotNull(doctorListTop5);
        assertEquals(1, doctorListTop5.size());
        // Negative test case
        assertFalse(doctorListTop5.isEmpty());
        assertEquals(doctorListTop5.get(0).getBooking().getDoctor().getName(),"Dr Smith");
    }
    @Test
    public void testGetTop5DoctorFalse() {
        AdminDBContext adminDBContext = new AdminDBContext();
        Doctor doctor = new Doctor();
        Rank rank = new Rank();
        rank.setId(1);
        doctor.setId(1);
        doctor.setName("Dr Smith");
        doctor.setUrl("");
        doctor.setGender("Male");
        doctor.setDob(new Date(1980-03-03));
        doctor.setSpecialty("Bác Sĩ Chuyên Khoa Mũi Họng");
        doctor.setRanks(rank);
        Booking booking = new Booking();
        booking.setDoctor(doctor);
        Bill bill = new Bill();
        bill.setPrice(100.5f);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        List<MedicalRecord> doctorListTop5 = adminDBContext.getTop5Doctor();
        // Positive test case
        assertNotNull(doctorListTop5);
        assertEquals(1, doctorListTop5.size());
        // Negative test case
        assertFalse(doctorListTop5.isEmpty());
        assertFalse(doctorListTop5.get(0).getBooking().getDoctor().getName().equals("Dr Smith@@"));
    }
    @Test
    public void testGetTop5PatientTrue() {
        AdminDBContext adminDBContext = new AdminDBContext();
        Patient patient = new Patient();
        Rank rank = new Rank();
        Bill bill = new Bill();
        rank.setId(1);
        patient.setId(1);
        patient.setUserName("patient");
        patient.setName("John Doe");
        patient.setUrl("");
        patient.setGender("Male");
        patient.setDob(new Date(1990-01-01));
        patient.setRanks(rank);
        bill.setPrice(100.5f);
        Booking booking = new Booking();
        booking.setPatient(patient);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        List<MedicalRecord> patientListTop5 = adminDBContext.getTop5Patient();
        // Positive test case
        assertNotNull(patientListTop5);
        assertEquals(1, patientListTop5.size());
        // Negative test case
        assertFalse(patientListTop5.isEmpty());
        assertEquals(patientListTop5.get(0).getBooking().getPatient().getName(),"John Doe");
    }

    @Test
    public void testGetTop5PatientFalse() {
        AdminDBContext adminDBContext = new AdminDBContext();
        Patient patient = new Patient();
        Rank rank = new Rank();
        Bill bill = new Bill();
        rank.setId(1);
        patient.setId(1);
        patient.setUserName("patient");
        patient.setName("John Doe");
        patient.setUrl("");
        patient.setGender("Male");
        patient.setDob(new Date(1990-01-01));
        patient.setRanks(rank);
        bill.setPrice(100.5f);
        Booking booking = new Booking();
        booking.setPatient(patient);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        List<MedicalRecord> patientListTop5 = adminDBContext.getTop5Patient();
        // Positive test case
        assertNotNull(patientListTop5);
        assertEquals(1, patientListTop5.size());
        // Negative test case
        assertFalse(patientListTop5.isEmpty());
        assertFalse(patientListTop5.get(0).getBooking().getPatient().getName().equals("John Doe12"));
    }

    @Test
    public void testGetTotalDoctorTrue() {
       AdminDBContext adminDBContext = new AdminDBContext();
        int totalDoctors = adminDBContext.getTotalDoctor();
        assertEquals(4, totalDoctors); // Assuming there are 10 doctors in the database
    }
    @Test
    public void testGetTotalDoctorFasle() {
        AdminDBContext adminDBContext = new AdminDBContext();
        int totalDoctors = adminDBContext.getTotalDoctor();
        assertFalse(49==totalDoctors); // Assuming there are 10 doctors in the database
    }

    @Test
    public void testGetTotalPatientTrue() {
        AdminDBContext adminDBContext = new AdminDBContext();
        int totalPatient = adminDBContext.getTotalPatient();
        assertEquals(4, totalPatient); // Assuming there are 10 doctors in the database
    }
    @Test
    public void testGetTotalPatientFasle() {
        AdminDBContext adminDBContext = new AdminDBContext();
        int totalPatient = adminDBContext.getTotalPatient();
        assertFalse(49==totalPatient); // Assuming there are 10 doctors in the database
    }
    @Test
    public void testGetTotalStaffTrue() {
        AdminDBContext adminDBContext = new AdminDBContext();
        int totalStaff = adminDBContext.getTotalStaff();
        assertTrue(5==totalStaff); // Assuming there are 10 doctors in the database
    }
    @Test
    public void testGetTotalStaffFalse() {
        AdminDBContext adminDBContext = new AdminDBContext();
        int totalStaff = adminDBContext.getTotalStaff();
        assertFalse(49==totalStaff); // Assuming there are 10 doctors in the database
    }

    @Test
    public void testGetTotalAppointmentTrue() {
        AdminDBContext adminDBContext = new AdminDBContext();
        int totalAppointment = adminDBContext.getTotalAppointment();
        assertEquals(9,totalAppointment);// Assuming there are 10 doctors in the database
    }
    @Test
    public void testGetTotalAppointmentFalse() {
        AdminDBContext adminDBContext = new AdminDBContext();
        int totalAppointment = adminDBContext.getTotalAppointment();
        assertFalse(totalAppointment==1);// Assuming there are 10 doctors in the database
    }

    @Test
    public void testGetTotalPriceTrue() {
        // Arrange
        AdminDBContext adminDBContext = new AdminDBContext();
        // Act
        float totalPrice = adminDBContext.getTotalPrice();
        // Assert
        assertEquals(181.25f, totalPrice, 0.0f); // Assuming the total price of paid bills is 100.0
    }

    @Test
    public void testGetTotalPriceFalse() {
        // Arrange
        AdminDBContext adminDBContext = new AdminDBContext();
        // Act
        float totalPrice = adminDBContext.getTotalPrice();
        // Assert
        assertFalse(181.24f==totalPrice); // Assuming the total price of paid bills is 100.0
    }
    @Test
    public void testGetInforTotalAppoinmentTrue() {
        Doctor doctor = new Doctor();
        Rank rank = new Rank();
        rank.setId(1);
        doctor.setId(1);
        doctor.setName("Dr Smith");
        doctor.setUrl("");
        doctor.setGender("Male");
        doctor.setDob(new Date(1980-03-03));
        doctor.setSpecialty("Bác Sĩ Chuyên Khoa Mũi Họng");
        doctor.setRanks(rank);
        Patient patient =new Patient();
        patient.setId(1);
        patient.setUserName("patient");
        patient.setName("John Doe");
        patient.setUrl("");
        patient.setGender("Male");
        patient.setDob(new Date(1990-01-01));
        patient.setRanks(rank);
        Slot slot = new Slot();
        slot.setName("9:00 - 9:30");
        Bill bill = new Bill();
        bill.setPayment_status("Paid");
        bill.setPrice(100.5f);
        Booking booking = new Booking();
        booking.setDate(new Date(2023-06-22));
        booking.setStatus("Completed");
        booking.setSlots(slot);
        booking.setDoctor(doctor);
        booking.setPatient(patient);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        AdminDBContext adminDBContext = new AdminDBContext();
        List<MedicalRecord> result = adminDBContext.getInforTotalAppoinment();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // Add more assertions to validate the correctness of the result
    }
}