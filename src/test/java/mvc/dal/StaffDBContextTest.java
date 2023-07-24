package mvc.dal;

import mvc.model.*;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StaffDBContextTest {

    @Test
    public void testGetStaffTrue() {
        Account account = new Account();
        account.setUsername("staff1");
        StaffDBContext staffDBContext = new StaffDBContext();
        Staff staff = staffDBContext.getStaff(account);
        assertNotNull(staff);
        assertEquals("staff1", staff.getUserName());
        assertEquals(1,staff.getId());
    }
    @Test
    public void testGetStaffFalse() {
        Account account = new Account();
        account.setUsername("staff1");
        StaffDBContext staffDBContext = new StaffDBContext();
        Staff staff = staffDBContext.getStaff(account);
        assertNotNull(staff);
        assertFalse(staff.getUserName().equals("staff1@@"));
        assertEquals(1,staff.getId());
    }
    @Test
    public void testGetStaffNull() {
        Account account = new Account();
        account.setUsername("staff1@@");
        StaffDBContext staffDBContext = new StaffDBContext();
        Staff staff = staffDBContext.getStaff(account);
        assertNull(staff);
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
        bill.setTotalPrice(100.5f);
        Booking booking = new Booking();
        booking.setDate(new Date(2023-06-22));
        booking.setStatus("Completed");
        booking.setSlots(slot);
        booking.setDoctor(doctor);
        booking.setPatient(patient);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        StaffDBContext staffDBContext = new StaffDBContext();
        List<MedicalRecord> result = staffDBContext.getInforTotalAppoinment();
        result.add(medicalRecord);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.get(0).getBooking().getPatient().getName(),"Phạm Thị N");
        assertNotEquals(result.get(0).getBooking().getDoctor().getName(),"Dr Smith");
    }

    @Test
    public void testGetInforTotalAppoinmentFalse() {
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
        bill.setTotalPrice(100.5f);
        Booking booking = new Booking();
        booking.setDate(new Date(2023-06-22));
        booking.setStatus("Completed");
        booking.setSlots(slot);
        booking.setDoctor(doctor);
        booking.setPatient(patient);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        StaffDBContext staffDBContext = new StaffDBContext();
        List<MedicalRecord> result = staffDBContext.getInforTotalAppoinment();
        result.add(medicalRecord);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertFalse(result.get(0).getBooking().getPatient().getName().equals("John Doe@"));
        assertFalse(result.get(0).getBooking().getDoctor().getName().equals("Dr Smith@"));
    }
    @Test
    public void testDoctorListFalse(){
        Account account = new Account();
        account.setUsername("doctor");
        account.setPassword("123");
        account.setEmail("jaod01@gmail.com");
        account.setPhone("0123548293");
        account.setIsAdmin(1);
        account.setStatus(true);
        Doctor doctors = new Doctor();
        doctors.setId(1);
        doctors.setUrl("");
        doctors.setName("Dr Smith");
        doctors.setGender("Male");
        doctors.setDob(new Date(1980-03-03));
        doctors.setRankId(1);
        doctors.setSpecialty("Bác Sĩ Chuyên Khoa Mũi Họng");
        doctors.setAccount(account);
        Bill bill = new Bill();
        bill.setTotalPrice(100.5f);
        Booking booking = new Booking();
        booking.setDoctor(doctors);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        StaffDBContext staffDBContext = new StaffDBContext();
        List<MedicalRecord> result = staffDBContext.doctorList();
        result.add(medicalRecord);
        assertNotNull(result);
        assertEquals(15, result.size());
        assertFalse(result.get(0).getBooking().getDoctor().getName().equals("Dr Smith@"));
        assertFalse(result.get(1).getBooking().getDoctor().getName().equals("Dr Park@"));
        assertFalse(result.get(2).getBooking().getDoctor().getName().equals("Dr Le@e"));
        assertFalse(result.get(3).getBooking().getDoctor().getName().equals("Thành@"));

    }

    @Test
    public void testPatientListTrue(){
        Account account = new Account();
        account.setUsername("patient");
        account.setPassword("123");
        account.setEmail("takassi@gmail.com");
        account.setPhone("0916728374");
        account.setIsAdmin(2);
        account.setStatus(true);
        Patient patient = new Patient();
        patient.setId(1);
        patient.setUrl("");
        patient.setName("John Doe");
        patient.setGender("Male");
        patient.setDob(new Date(1990-01-01));
        patient.setRankId(1);
        patient.setAccount(account);
        Bill bill = new Bill();
        bill.setTotalPrice(100.5f);
        Booking booking = new Booking();
        booking.setDate(new Date(2023-06-22));
        booking.setPatient(patient);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        StaffDBContext staffDBContext = new StaffDBContext();
        List<MedicalRecord> medicalRecordList = staffDBContext.patientList();
        medicalRecordList.add(medicalRecord);
        assertNotNull(medicalRecordList);
        assertEquals(15, medicalRecordList.size());
        assertEquals(medicalRecordList.get(0).getBooking().getPatient().getName(),"Nguyễn Văn K");
        assertEquals(medicalRecordList.get(1).getBooking().getPatient().getName(),"Trần Thị L");
        assertEquals(medicalRecordList.get(2).getBooking().getPatient().getName(),"Lê Văn M");
        assertEquals(medicalRecordList.get(3).getBooking().getPatient().getName(),"Phạm Thị N");
        assertEquals(medicalRecordList.get(4).getBooking().getPatient().getName(),"Hoàng Văn P");
    }


}