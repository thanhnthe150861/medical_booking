package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {
    @Test
    public void testGetPrice() {
        Bill bill = new Bill(1, 1, 100.0f, "Paid");
        assertEquals(100.0f, bill.getPrice(), 0.0f);
    }
    @Test
    public void testSetPrice() {
        Bill bill = new Bill(1, 1, 100.0f, "Paid");
        bill.setPrice(200.0f);
        assertEquals(200.0f, bill.getPrice(), 0.0f);
    }
    @Test
    public void testGetId() {
        Bill bill = new Bill(1, 1, 100.0f, "Paid");
        assertEquals(1, bill.getId());
    }
    @Test
    public void testSetId() {
        Bill bill = new Bill(1, 1, 100.0f, "Paid");
        bill.setId(2);
        assertEquals(2, bill.getId());
    }
    @Test
    public void testGetMedical_record_id() {
        Bill bill = new Bill(1, 1, 100.0f, "Paid");
        assertEquals(1, bill.getMedical_record_id());
    }
    @Test
    public void testSetMedical_record_id() {
        Bill bill = new Bill(1, 1, 100.0f, "Paid");
        bill.setMedical_record_id(2);
        assertEquals(2, bill.getMedical_record_id());
    }
    @Test
    public void testGetPayment_status() {
        Bill bill = new Bill(1, 1, 100.0f, "Paid");
        assertEquals("Paid", bill.getPayment_status());
    }
    @Test
    public void testSetPayment_status() {
        Bill bill = new Bill(1, 1, 100.0f, "Paid");
        bill.setPayment_status("Unpaid");
        assertEquals("Unpaid", bill.getPayment_status());
    }
}