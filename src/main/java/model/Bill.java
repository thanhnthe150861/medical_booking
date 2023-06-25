package model;

public class Bill {
    private int id;
    private int medical_record_id;
    private String payment_status;

    public Bill() {
    }

    public Bill(int id, int medical_record_id, String payment_status) {
        this.id = id;
        this.medical_record_id = medical_record_id;
        this.payment_status = payment_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedical_record_id() {
        return medical_record_id;
    }

    public void setMedical_record_id(int medical_record_id) {
        this.medical_record_id = medical_record_id;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
}
