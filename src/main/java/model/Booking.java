package model;

import java.sql.Date;

public class Booking {
    private int id;
    private int doctor_id;
    private int patient_id;
    private String booking_reason;
    private Date date;
    private int slot_id;
    private String status;
    private Doctor doctor = new Doctor();
    private Patient patient = new Patient();
    private Slot slots = new Slot();

    public Booking() {
    }

    public Booking(int id, int doctor_id, int patient_id, String booking_reason, Date date, int slot_id, String status, Doctor doctor, Patient patient, Slot slots) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.booking_reason = booking_reason;
        this.date = date;
        this.slot_id = slot_id;
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
        this.slots = slots;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
    

    public String getBooking_reason() {
        return booking_reason;
    }

    public void setBooking_reason(String booking_reason) {
        this.booking_reason = booking_reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Slot getSlots() {
        return slots;
    }

    public void setSlots(Slot slots) {
        this.slots = slots;
    }
}
