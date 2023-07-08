package model;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    @Test
    public void testGetPatientId() {
        Booking booking = new Booking();
        booking.setPatient_id(123);
        assertEquals(123, booking.getPatient_id());
    }
    @Test
    public void testSetPatientId() {
        Booking booking = new Booking();
        booking.setPatient_id(123);
        assertEquals(123, booking.getPatient_id());
    }
    @Test
    public void testGetId() {
        Booking booking = new Booking();
        booking.setId(1);
        assertEquals(1, booking.getId());
    }
    @Test
    public void testSetId() {
        Booking booking = new Booking();
        booking.setId(1);
        assertEquals(1, booking.getId());
    }
    @Test
    public void testGetDoctorId() {
        Booking booking = new Booking();
        booking.setDoctor_id(456);
        assertEquals(456, booking.getDoctor_id());
    }
    @Test
    public void testSetDoctorId() {
        Booking booking = new Booking();
        booking.setDoctor_id(456);
        assertEquals(456, booking.getDoctor_id());
    }
    @Test
    public void testGetBookingReason() {
        Booking booking = new Booking();
        booking.setBooking_reason("Checkup");
        assertEquals("Checkup", booking.getBooking_reason());
    }
    @Test
    public void testSetBookingReason() {
        Booking booking = new Booking();
        booking.setBooking_reason("Checkup");
        assertEquals("Checkup", booking.getBooking_reason());
    }
    @Test
    public void testGetDate() {
        Booking booking = new Booking();
        Date date = new Date(System.currentTimeMillis());
        booking.setDate(date);
        assertEquals(date, booking.getDate());
    }
    @Test
    public void testSetDate() {
        Booking booking = new Booking();
        Date date = new Date(System.currentTimeMillis());
        booking.setDate(date);
        assertEquals(date, booking.getDate());
    }
    @Test
    public void testGetSlotId() {
        Booking booking = new Booking();
        booking.setSlot_id(789);
        assertEquals(789, booking.getSlot_id());
    }
    @Test
    public void testSetSlotId() {
        Booking booking = new Booking();
        booking.setSlot_id(789);
        assertEquals(789, booking.getSlot_id());
    }
    @Test
    public void testGetStatus() {
        Booking booking = new Booking();
        booking.setStatus("Confirmed");
        assertEquals("Confirmed", booking.getStatus());
    }
    @Test
    public void testSetStatus() {
        Booking booking = new Booking();
        booking.setStatus("Confirmed");
        assertEquals("Confirmed", booking.getStatus());
    }
}