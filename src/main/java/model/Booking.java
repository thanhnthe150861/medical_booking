package model;

import javax.print.Doc;
import java.sql.Date;

public class Booking {
    private int id;
    private int doctor_id;
    private int client_id;
    private String booking_reason;
    private Date date;
    private int slot_id;
    private String status;
    private Doctor doctor = new Doctor();
    private Client client = new Client();
    private Slot slots = new Slot();

    public Booking() {
    }

    public Booking(int id, int doctor_id, int client_id, String booking_reason, Date date, int slot_id, String status, Doctor doctor, Client client, Slot slots) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.client_id = client_id;
        this.booking_reason = booking_reason;
        this.date = date;
        this.slot_id = slot_id;
        this.status = status;
        this.doctor = doctor;
        this.client = client;
        this.slots = slots;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Slot getSlots() {
        return slots;
    }

    public void setSlots(Slot slots) {
        this.slots = slots;
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

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
