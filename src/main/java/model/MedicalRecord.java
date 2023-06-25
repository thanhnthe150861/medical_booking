package model;

public class MedicalRecord {
    private  int id;
    private  int booking_id;
    private  String diagnosis;
    private  String medication;

    public MedicalRecord() {
    }

    public MedicalRecord(int id, int booking_id, String diagnosis, String medication) {
        this.id = id;
        this.booking_id = booking_id;
        this.diagnosis = diagnosis;
        this.medication = medication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}
