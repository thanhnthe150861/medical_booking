package mvc.model;

import java.sql.Date;

public class NgayNghi {
    private int id;
    private int slot_id;
    private int doctor_id;
    private Date date;
    private boolean status;
    private Slot slot = new Slot();
    private Doctor doctor = new Doctor();

    public NgayNghi() {
    }

    public NgayNghi(int id, int slot_id, int doctor_id, Date date, boolean status, Slot slot, Doctor doctor) {
        this.id = id;
        this.slot_id = slot_id;
        this.doctor_id = doctor_id;
        this.date = date;
        this.status = status;
        this.slot = slot;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
