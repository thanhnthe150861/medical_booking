package mvc.dal;


import mvc.model.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDBContext extends  DBContext{
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public List<Slot> getAllSlots(){
        List<Slot> slotList = new ArrayList<>();
        try {
            String sql = "SELECT  * FROM slot";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Slot slot = new Slot();
                slot.setId(rs.getInt("id"));
                slot.setName(rs.getString("name"));
                slotList.add(slot);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return slotList;
    }
    public Patient getPatient(Account account){
        try {
            String sql = "SELECT p.id, p.username, p.url, p.name, p.gender, p.dob, p.rank_id, rp.name as rank_name\n" +
                    "FROM patient p\n" +
                    "LEFT JOIN rank_patient rp ON p.rank_id = rp.id\n" +
                    "WHERE username = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            rs = stm.executeQuery();
            if (rs.next()){
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setUserName(rs.getString("username"));
                patient.setUrl(rs.getString("url"));
                patient.setName(rs.getString("name"));
                patient.setGender(rs.getString("gender"));
                patient.setDob(rs.getDate("dob"));
                Rank rank = new Rank();
                rank.setId(rs.getInt("rank_id"));
                rank.setName(rs.getString("rank_name"));
                patient.setRanks(rank);
                return patient;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Doctor getDoctorByPatient(String id){
        try {
            String sql = "SELECT doctor.id, doctor.url, doctor.name, doctor.gender, doctor.dob, doctor.specialty, doctor.rank_id, rank_doctor.name AS rank_name\n" +
                    "FROM doctor\n" +
                    "LEFT JOIN rank_doctor ON doctor.rank_id = rank_doctor.id\n" +
                    "WHERE doctor.id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id));
            rs = stm.executeQuery();
            if (rs.next()){
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setUrl(rs.getString("url"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setDob(rs.getDate("dob"));
                doctor.setSpecialty(rs.getString("specialty"));
                Rank rank = new Rank();
                rank.setId(rs.getInt("rank_id"));
                rank.setName(rs.getString("rank_name"));
                doctor.setRanks(rank);
                return doctor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<MedicalRecord> getInforMyPatients(Patient patients){
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        try {
            String sql = "SELECT booking.id, booking.doctor_id, booking.patient_id, booking.slot_id, booking.booking_reason, booking.date, booking.status,  \n" +
                    "       doctor.url AS doctor_url, doctor.name AS doctor_name, doctor.gender AS doctor_gender,   \n" +
                    "       doctor.dob AS doctor_dob, doctor.specialty, doctor.rank_id AS doctor_rank_id,  \n" +
                    "       patient.url AS patient_url, patient.name AS patient_name, patient.gender AS patient_gender,  \n" +
                    "       patient.dob AS patient_dob, patient.rank_id AS patient_rank_id,  \n" +
                    "       slot.name AS slot_name,  \n" +
                    "       medical_record.id AS medical_record_id, medical_record.diagnosis, medical_record.prescription,  \n" +
                    "       bill.id AS bill_id, bill.price, bill.payment_status  \n" +
                    "FROM booking  \n" +
                    "LEFT JOIN doctor ON booking.doctor_id = doctor.id  \n" +
                    "LEFT JOIN patient ON booking.patient_id = patient.id  \n" +
                    "LEFT JOIN slot ON booking.slot_id = slot.id  \n" +
                    "LEFT JOIN medical_record ON booking.id = medical_record.booking_id  \n" +
                    "LEFT JOIN bill ON medical_record.id = bill.medical_record_id  \n" +
                    "WHERE patient.id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, patients.getId());
            rs = stm.executeQuery();
            while (rs.next()){
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setBooking_reason(rs.getString("booking_reason"));
                booking.setDate(rs.getDate("date"));
                booking.setStatus(rs.getString("status"));
                Doctor doctors = new Doctor();
                doctors.setId(rs.getInt("doctor_id"));
                doctors.setUrl(rs.getString("doctor_url"));
                doctors.setName(rs.getString("doctor_name"));
                doctors.setDob(rs.getDate("doctor_dob"));
                doctors.setGender(rs.getString("doctor_gender"));
                doctors.setSpecialty(rs.getString("specialty"));
                booking.setDoctor(doctors);
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setUrl(rs.getString("patient_url"));
                patient.setName(rs.getString("patient_name"));
                patient.setDob(rs.getDate("patient_dob"));
                patient.setGender(rs.getString("patient_gender"));
                booking.setPatient(patient);
                Slot slot = new Slot();
                slot.setId(rs.getInt("slot_id"));
                slot.setName(rs.getString("slot_name"));
                booking.setSlots(slot);
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setPrice(rs.getFloat("price"));
                bill.setPayment_status(rs.getString("payment_status"));
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setId(rs.getInt("medical_record_id"));
                medicalRecord.setDiagnosis(rs.getString("diagnosis"));
                medicalRecord.setPrescription(rs.getString("prescription"));
                medicalRecord.setBooking(booking);
                medicalRecord.setBill(bill);
                medicalRecordList.add(medicalRecord);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return medicalRecordList;
    }
    public List<Booking> getBooking(Patient patient, String status){
        List<Booking> bookingList = new ArrayList<>();
        try {
            String sql = "SELECT \n" +
                    "    booking.id,\n" +
                    "    booking.booking_reason,\n" +
                    "    booking.date,\n" +
                    "    booking.status,\n" +
                    "\tdoctor.id AS doctor_id,\n" +
                    "    doctor.name AS doctor_name,\n" +
                    "    doctor.url AS doctor_url,\n" +
                    "    doctor.gender AS doctor_gender,\n" +
                    "    doctor.dob AS doctor_dob,\n" +
                    "    doctor.specialty AS doctor_specialty,\n" +
                    "    rank_doctor.id AS doctor_rank_id,\n" +
                    "    rank_doctor.name AS doctor_rank,\n" +
                    "    slot.id AS slot_id,\n" +
                    "    slot.name AS slot_name\n" +
                    "FROM booking\n" +
                    "LEFT JOIN doctor ON booking.doctor_id = doctor.id\n" +
                    "LEFT JOIN rank_doctor ON doctor.rank_id = rank_doctor.id\n" +
                    "LEFT JOIN slot ON booking.slot_id = slot.id\n" +
                    "WHERE booking.status = ? and booking.patient_id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, patient.getId());
            rs = stm.executeQuery();
            while (rs.next()){
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setBooking_reason(rs.getString("booking_reason"));
                booking.setDate(rs.getDate("date"));
                booking.setStatus(rs.getString("status"));
               Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("doctor_id"));
                doctor.setName(rs.getString("doctor_name"));
                doctor.setUrl(rs.getString("doctor_url"));
                doctor.setDob(rs.getDate("doctor_dob"));
                doctor.setGender(rs.getString("doctor_gender"));
                doctor.setSpecialty(rs.getString("doctor_specialty"));
                Rank rank = new Rank();
                rank.setId(rs.getInt("doctor_rank_id"));
                rank.setName(rs.getString("doctor_rank"));
                doctor.setRanks(rank);
                booking.setDoctor(doctor);
                Slot slot = new Slot();
                slot.setId(rs.getInt("slot_id"));
                slot.setName(rs.getString("slot_name"));
                booking.setSlots(slot);
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingList;
    }
    public void UpdatePatient(Patient patient) {
        try {
            // Update the account's information
            String accountSql = "UPDATE account SET password = ?, email = ?, phone = ? WHERE username = ?";
            stm = connection.prepareStatement(accountSql);
            stm.setString(1, patient.getAccount().getPassword());
            stm.setString(2, patient.getAccount().getEmail());
            stm.setString(3, patient.getAccount().getPhone());
            stm.setString(4, patient.getAccount().getUsername());
            stm.executeUpdate();
            // Update the patient's information
            String patientSql = "UPDATE patient SET url = ?, name = ?, gender = ?, dob = ?, rank_id = ? WHERE username = ?";
            stm = connection.prepareStatement(patientSql);
            stm.setString(1, patient.getUrl());
            stm.setString(2, patient.getName());
            stm.setString(3, patient.getGender());
            stm.setDate(4, patient.getDob());
            stm.setInt(5, patient.getRanks().getId());
            stm.setString(6, patient.getUserName());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Doctor> getListDoctorEmpty(String date, String slot, String status){// Kiểm tra xem có bác sĩ nào có lịch đặt vào ngày và slot đó chưa
        List<Doctor> doctorList = new ArrayList<>();
        try {
            String sql = "SELECT *\n" +
                    "FROM doctor\n" +
                    "WHERE id NOT IN (\n" +
                    "    SELECT doctor_id\n" +
                    "    FROM booking\n" +
                    "    WHERE status <> ?\n" +
                    "    AND date = ?\n" +
                    "    AND slot_id = ?\n" +
                    ");";
            stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setDate(2, Date.valueOf(date));
            stm.setInt(3, Integer.parseInt(slot));
            rs = stm.executeQuery();
            while (rs.next()){
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setUrl(rs.getString("url"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setDob(rs.getDate("dob"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setRankId(rs.getInt("rank_id"));
                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctorList;
    }
    public  void addNewBooking(Booking booking){
        try {
            String sql = "INSERT INTO booking (doctor_id, patient_id, slot_id, booking_reason, date, status) VALUES (?, ?, ?, ?, ?, ?);";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, booking.getDoctor_id());
            stm.setInt(2, booking.getPatient_id());
            stm.setInt(3, booking.getSlot_id());
            stm.setString(4, booking.getBooking_reason());
            stm.setDate(5, booking.getDate());
            stm.setString(6, booking.getStatus());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  Booking checkBookingExsit(Patient patient, String date, String slot){
        try {
            String sql = "SELECT *\n" +
                    "FROM booking\n" +
                    "WHERE slot_id = ?\n" +
                    "AND date = ?\n" +
                    "AND patient_id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(slot));
            stm.setDate(2, Date.valueOf(date));
            stm.setInt(3, patient.getId());
            rs = stm.executeQuery();
            if (rs.next()){
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                return booking;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Doctor> getMydoctor(Patient patient, String status){
        List<Doctor> doctorList = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT d.id, d.username, d.url, d.name, d.gender, d.dob, d.specialty , rd.name AS rank_name\n" +
                    "FROM doctor d\n" +
                    "LEFT JOIN rank_doctor rd ON d.rank_id = rd.id\n" +
                    "INNER JOIN booking b ON d.id = b.doctor_id\n" +
                    "WHERE b.status = ? AND b.patient_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, patient.getId());
            rs = stm.executeQuery();
            while (rs.next()){
               Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setUrl(rs.getString("url"));
                doctor.setDob(rs.getDate("dob"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setGender(rs.getString("gender"));
                Rank rank = new Rank();
                rank.setName(rs.getString("rank_name"));
                doctor.setRanks(rank);
                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctorList;
    }
    }

