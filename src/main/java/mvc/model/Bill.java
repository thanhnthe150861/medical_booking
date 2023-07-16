package mvc.model;

public class Bill {
    private int id;
    private int medical_record_id;
    private float priceMedical;
    private float pricePrescription;
    private float totalPrice;
    private String payment_status;

    public Bill() {
    }

    public Bill(int id, int medical_record_id, float priceMedical, float pricePrescription, float totalPrice, String payment_status) {
        this.id = id;
        this.medical_record_id = medical_record_id;
        this.priceMedical = priceMedical;
        this.pricePrescription = pricePrescription;
        this.totalPrice = totalPrice;
        this.payment_status = payment_status;
    }

    public float getPriceMedical() {
        return priceMedical;
    }

    public void setPriceMedical(float priceMedical) {
        this.priceMedical = priceMedical;
    }

    public float getPricePrescription() {
        return pricePrescription;
    }

    public void setPricePrescription(float pricePrescription) {
        this.pricePrescription = pricePrescription;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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
