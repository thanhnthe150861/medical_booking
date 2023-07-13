package mvc.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordTest {
    @Test
    public void testGetBill() {
        MedicalRecord medicalRecord = new MedicalRecord();
        Bill bill = new Bill();
        medicalRecord.setBill(bill);
        assertEquals(bill, medicalRecord.getBill());
    }
    @Test
    public void testGetBooking() {
        MedicalRecord medicalRecord = new MedicalRecord();
        Booking booking = new Booking();
        medicalRecord.setBooking(booking);
        assertEquals(booking, medicalRecord.getBooking());
    }
    @Test
    public void testGetId() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(1);
        assertEquals(1, medicalRecord.getId());
    }
    @Test
    public void testGetBookingId() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBooking_id(2);
        assertEquals(2, medicalRecord.getBooking_id());
    }
    @Test
    public void testGetDiagnosis() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDiagnosis("Headache");
        assertEquals("Headache", medicalRecord.getDiagnosis());
    }
    @Test
    public void testGetPrescription() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPrescription("Painkillers");
        assertEquals("Painkillers", medicalRecord.getPrescription());
    }

}