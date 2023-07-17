package mvc.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillTest {
    @Test
    public void testGetPrice() {
        Bill bill = new Bill();
        assertEquals(100.0f, bill.getTotalPrice(), 0.0f);
    }
    @Test
    public void testSetPrice() {
        Bill bill = new Bill();
        bill.setTotalPrice(200.0f);
        assertEquals(200.0f, bill.getTotalPrice(), 0.0f);
    }
    @Test
    public void testGetId() {
        Bill bill = new Bill();
        assertEquals(1, bill.getId());
    }
    @Test
    public void testSetId() {
        Bill bill = new Bill();
        bill.setId(2);
        assertEquals(2, bill.getId());
    }
    @Test
    public void testGetMedical_record_id() {
        Bill bill = new Bill();
        assertEquals(1, bill.getMedical_record_id());
    }
    @Test
    public void testSetMedical_record_id() {
        Bill bill = new Bill();
        bill.setMedical_record_id(2);
        assertEquals(2, bill.getMedical_record_id());
    }
    @Test
    public void testGetPayment_status() {
        Bill bill = new Bill();
        assertEquals("Paid", bill.getPayment_status());
    }
    @Test
    public void testSetPayment_status() {
        Bill bill = new Bill();
        bill.setPayment_status("Unpaid");
        assertEquals("Unpaid", bill.getPayment_status());
    }
}