package mvc.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {
    private Bill bill;
    @Test
    public void testGetPriceMedical() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");
        assertEquals(100.0f, bill.getPriceMedical(), 0.0f);
    }
    @Test
    public void testSetPriceMedical() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");
        bill.setPriceMedical(200.0f);
        assertEquals(200.0f, bill.getPriceMedical(), 0.0f);
    }
    @Test
    public void testGetPricePrescription() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        assertEquals(50.0f, bill.getPricePrescription(), 0.0f);
    }
    @Test
    public void testSetPricePrescription() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        bill.setPricePrescription(75.0f);
        assertEquals(75.0f, bill.getPricePrescription(), 0.0f);
    }
    @Test
    public void testGetTotalPrice() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        assertEquals(150.0f, bill.getTotalPrice(), 0.0f);
    }
    @Test
    public void testSetTotalPrice() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        bill.setTotalPrice(200.0f);
        assertEquals(200.0f, bill.getTotalPrice(), 0.0f);
    }
    @Test
    public void testGetId() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        assertEquals(1, bill.getId());
    }
    @Test
    public void testSetId() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        bill.setId(2);
        assertEquals(2, bill.getId());
    }
    @Test
    public void testGetMedical_record_id() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        assertEquals(1, bill.getMedical_record_id());
    }
    @Test
    public void testSetMedical_record_id() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        bill.setMedical_record_id(2);
        assertEquals(2, bill.getMedical_record_id());
    }
    @Test
    public void testGetPayment_status() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        assertEquals("Paid", bill.getPayment_status());
    }
    @Test
    public void testSetPayment_status() {
        bill = new Bill(1, 1, 100.0f, 50.0f, 150.0f, "Paid");

        bill.setPayment_status("Unpaid");
        assertEquals("Unpaid", bill.getPayment_status());
    }
}