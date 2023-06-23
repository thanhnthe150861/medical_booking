package model;

import java.sql.Date;

public class Booking {
    private int id;
    private int doctor_id;
    private int client_id;
    private String booking_reason;
    private Date date;
    private String slot;
    private String status;

    public Booking() {
    }

    public Booking(int id, int doctor_id, int client_id, String booking_reason, Date date, String slot, String status) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.client_id = client_id;
        this.booking_reason = booking_reason;
        this.date = date;
        this.slot = slot;
        this.status = status;
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

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
