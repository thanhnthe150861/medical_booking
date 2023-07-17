
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
        bill.setTotalPrice(100.5f);
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
        bill.setTotalPrice(100.5f);
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
        bill.setTotalPrice(100.5f);
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
        bill.setTotalPrice(100.5f);
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
        assertEquals(5,totalAppointment);// Assuming there are 10 doctors in the database
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
        AdminDBContext adminDBContext = new AdminDBContext();
        List<MedicalRecord> result = adminDBContext.getInforTotalAppoinment();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.get(0).getBooking().getPatient().getName(),"John Doe");
        assertEquals(result.get(0).getBooking().getDoctor().getName(),"Dr Smith");

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
        AdminDBContext adminDBContext = new AdminDBContext();
        List<MedicalRecord> result = adminDBContext.getInforTotalAppoinment();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertFalse(result.get(0).getBooking().getPatient().getName().equals("John Doe@"));
        assertFalse(result.get(0).getBooking().getDoctor().getName().equals("Dr Smith@"));
    }

    @Test
    public void testDoctorListTrue(){
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
        AdminDBContext adminDBContext = new AdminDBContext();
        List<MedicalRecord> medicalRecordList = adminDBContext.doctorList();
        assertNotNull(medicalRecordList);
        assertEquals(4, medicalRecordList.size());
        assertEquals(medicalRecordList.get(0).getBooking().getDoctor().getName(),"Dr Smith");
        assertEquals(medicalRecordList.get(1).getBooking().getDoctor().getName(),"Dr Park");
        assertEquals(medicalRecordList.get(2).getBooking().getDoctor().getName(),"Dr Lee");
        assertEquals(medicalRecordList.get(3).getBooking().getDoctor().getName(),"Thành");
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
        AdminDBContext adminDBContext = new AdminDBContext();
        List<MedicalRecord> medicalRecordList = adminDBContext.doctorList();
        assertNotNull(medicalRecordList);
        assertEquals(4, medicalRecordList.size());
        assertFalse(medicalRecordList.get(0).getBooking().getDoctor().getName().equals("Dr Smith@"));
        assertFalse(medicalRecordList.get(1).getBooking().getDoctor().getName().equals("Dr Park@"));
        assertFalse(medicalRecordList.get(2).getBooking().getDoctor().getName().equals("Dr Le@e"));
        assertFalse(medicalRecordList.get(3).getBooking().getDoctor().getName().equals("Thành@"));

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
        AdminDBContext adminDBContext = new AdminDBContext();
        List<MedicalRecord> medicalRecordList = adminDBContext.patientList();
        assertNotNull(medicalRecordList);
        assertEquals(5, medicalRecordList.size());
        assertEquals(medicalRecordList.get(0).getBooking().getPatient().getName(),"John Doe");
        assertEquals(medicalRecordList.get(1).getBooking().getPatient().getName(),"Sarah Lee");
        assertEquals(medicalRecordList.get(2).getBooking().getPatient().getName(),"Peter Kim");
        assertEquals(medicalRecordList.get(3).getBooking().getPatient().getName(),"Ngân");
        assertEquals(medicalRecordList.get(4).getBooking().getPatient().getName(),"John Doe");

    }
    @Test
    public void testPatientListFalse(){
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
        AdminDBContext adminDBContext = new AdminDBContext();
        List<MedicalRecord> medicalRecordList = adminDBContext.patientList();
        assertNotNull(medicalRecordList);
        assertEquals(5, medicalRecordList.size());
        assertFalse(medicalRecordList.get(0).getBooking().getPatient().getName().equals("John Doe@"));
        assertFalse(medicalRecordList.get(0).getBooking().getPatient().getName().equals("Sarah Lee@"));
        assertFalse(medicalRecordList.get(0).getBooking().getPatient().getName().equals("Peter Kim@"));
        assertFalse(medicalRecordList.get(0).getBooking().getPatient().getName().equals("Ngân@"));
        assertFalse(medicalRecordList.get(0).getBooking().getPatient().getName().equals("John @Doe"));
    }

    @Test
    public void testStafflistTrue(){
        Account account = new Account();
        account.setUsername("staff1");
        account.setPassword("123");
        account.setEmail("user1@example.com");
        account.setPhone("1234567890");
        account.setIsAdmin(3);
        account.setStatus(true);
        Staff staff = new Staff();
        staff.setId(1);
        staff.setUrl("www.example.com/staff1");
        staff.setName("John Doe");
        staff.setGender("Male");
        staff.setDob(new Date(1990-01-01));
        staff.setAccount(account);
        AdminDBContext adminDBContext = new AdminDBContext();
        List<Staff> staffList = adminDBContext.staffList();
        assertNotNull(staffList);
        assertEquals(6, staffList.size());
        assertEquals(staffList.get(0).getName(), "John Doe");
        assertEquals(staffList.get(1).getName(), "Jane Smith");

    }
    @Test
    public void testStafflistFalse(){
        Account account = new Account();
        account.setUsername("staff1");
        account.setPassword("123");
        account.setEmail("user1@example.com");
        account.setPhone("1234567890");
        account.setIsAdmin(3);
        account.setStatus(true);
        Staff staff = new Staff();
        staff.setId(1);
        staff.setUrl("www.example.com/staff1");
        staff.setName("John Doe");
        staff.setGender("Male");
        staff.setDob(new Date(1990-01-01));
        staff.setAccount(account);
        AdminDBContext adminDBContext = new AdminDBContext();
        List<Staff> staffList = adminDBContext.staffList();
        assertNotNull(staffList);
        assertEquals(6, staffList.size());
        assertFalse(staffList.get(0).getName().equals("John Doe@"));
        assertFalse(staffList.get(1).getName().equals("Jane @Smith"));
    }
    @Test
    public  void  testinvoiceListTrue(){
        Patient patient = new Patient();
        patient.setId(1);
        patient.setUrl("");
        patient.setName("John Doe");
        patient.setGender("Male");
        patient.setDob(new Date(1990-01-01));
        patient.setRankId(1);
        Bill bill = new Bill();
        bill.setId(1);
        bill.setTotalPrice(100.5f);
        bill.setPayment_status("Paid");
        Booking booking = new Booking();
        booking.setId(1);
        booking.setDate(new Date(2023-06-22));
        booking.setPatient(patient);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        AdminDBContext adminDBContext = new AdminDBContext();
        List<MedicalRecord> medicalRecordList = adminDBContext.invoiceList();
        assertNotNull(medicalRecordList);
        assertEquals(4,medicalRecordList.size());
        assertEquals(medicalRecordList.get(0).getBooking().getPatient().getName(),"John Doe");
    }

    @Test
    public  void  testinvoiceListFalse(){
        Patient patient = new Patient();
        patient.setId(1);
        patient.setUrl("");
        patient.setName("John Doe");
        patient.setGender("Male");
        patient.setDob(new Date(1990-01-01));
        patient.setRankId(1);
        Bill bill = new Bill();
        bill.setId(1);
        bill.setTotalPrice(100.5f);
        bill.setPayment_status("Paid");
        Booking booking = new Booking();
        booking.setId(1);
        booking.setDate(new Date(2023-06-22));
        booking.setPatient(patient);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking(booking);
        medicalRecord.setBill(bill);
        AdminDBContext adminDBContext = new AdminDBContext();
        List<MedicalRecord> medicalRecordList = adminDBContext.invoiceList();
        assertNotNull(medicalRecordList);
        assertEquals(4,medicalRecordList.size());
        assertFalse(medicalRecordList.get(0).getBooking().getPatient().getName().equals("John Do@e"));
    }

}