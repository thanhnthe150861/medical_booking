package mvc.model;

public class MedicalRecord {
    private  int id;
    private  int booking_id;
    private  String diagnosis;
    private  String prescription;
    private  String url;
    private Booking booking = new Booking();
    private Bill bill = new Bill();

    public MedicalRecord() {
    }

    public MedicalRecord(int id, int booking_id, String diagnosis, String prescription, String url, Booking booking, Bill bill) {
        this.id = id;
        this.booking_id = booking_id;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.url = url;
        this.booking = booking;
        this.bill = bill;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
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

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}
