package mvc.dal;

import mvc.dal.DBContext;
import mvc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDBContext extends DBContext {
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public Staff getStaff(Account account){
        try {
            String sql = " SELECT s.id, s.username, s.url, s.name, s.gender, s.dob \n " +
                    " FROM staff s \n " +
                    " WHERE s.username = ?; ";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            rs = stm.executeQuery();
            if (rs.next()){
                Staff s = new Staff();
                s.setId(rs.getInt("id"));
                s.setUserName(rs.getString("username"));
                s.setUrl(rs.getString("url"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setDob(rs.getDate("dob"));
                return s;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<MedicalRecord> getTop5Doctor(){
        List<MedicalRecord> doctorListTop5 = new ArrayList<>();
        try {
            String sql = "SELECT D.id, D.url, D.name, D.gender, D.dob, D.specialty, RD.name AS rank_name, SUM(B.totalPrice) AS total_revenue\n" +
                    "FROM doctor D\n" +
                    "JOIN booking BK ON D.id = BK.doctor_id\n" +
                    "JOIN medical_record MR ON BK.id = MR.booking_id\n" +
                    "JOIN bill B ON MR.id = B.medical_record_id\n" +
                    "JOIN rank_doctor RD ON D.rank_id = RD.id\n" +
                    "WHERE B.payment_status = 'Paid'\n" +
                    "GROUP BY D.id\n" +
                    "ORDER BY total_revenue DESC\n" +
                    "LIMIT 5;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Doctor doctors = new Doctor();
                doctors.setId(rs.getInt("id"));
                doctors.setUrl(rs.getString("url"));
                doctors.setName(rs.getString("name"));
                doctors.setDob(rs.getDate("dob"));
                doctors.setGender(rs.getString("gender"));
                doctors.setSpecialty(rs.getString("specialty"));
                Rank rank = new Rank();
                rank.setName(rs.getString("rank_name"));
                doctors.setRanks(rank);
                Bill bill = new Bill();
                bill.setTotalPrice(rs.getFloat("total_revenue"));
                Booking booking = new Booking();
                booking.setDoctor(doctors);
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setBooking(booking);
                medicalRecord.setBill(bill);
                doctorListTop5.add(medicalRecord);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return doctorListTop5;
    }
    public List<MedicalRecord> getTop5Patient(){
        List<MedicalRecord> patientListTop5 = new ArrayList<>();
        try {
            String sql = "SELECT P.id, P.url, P.name, P.gender, P.dob, RP.name AS rank_name, SUM(B.totalPrice) AS total_revenue, MAX(BK.date) AS latest_booking_date \n" +
                    "FROM patient P \n" +
                    "JOIN booking BK ON P.id = BK.patient_id \n" +
                    "JOIN medical_record MR ON BK.id = MR.booking_id \n" +
                    "JOIN bill B ON MR.id = B.medical_record_id \n" +
                    "JOIN rank_patient RP ON P.rank_id = RP.id \n" +
                    "WHERE B.payment_status = 'Paid' AND BK.status = 'Completed' \n" +
                    "GROUP BY P.id\n" +
                    "ORDER BY total_revenue DESC \n" +
                    "LIMIT 5;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setUrl(rs.getString("url"));
                patient.setName(rs.getString("name"));
                patient.setDob(rs.getDate("dob"));
                patient.setGender(rs.getString("gender"));
                Rank rank = new Rank();
                rank.setName(rs.getString("rank_name"));
                patient.setRanks(rank);
                Bill bill = new Bill();
                bill.setTotalPrice(rs.getFloat("total_revenue"));
                Booking booking = new Booking();
                booking.setDate(rs.getDate("latest_booking_date"));
                booking.setPatient(patient);
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setBooking(booking);
                medicalRecord.setBill(bill);
                patientListTop5.add(medicalRecord);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return patientListTop5;
    }
    public int getTotalDoctor(){
        try {
            String sql = "SELECT COUNT(*) AS total_doctors FROM doctor;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()){
                int count = rs.getInt("total_doctors");
                return count;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int getTotalPatient(){
        try {
            String sql = "SELECT COUNT(*) AS total_patients FROM patient;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()){
                int count = rs.getInt("total_patients");
                return count;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int getTotalStaff(){
        try {
            String sql = "SELECT COUNT(*) AS total_staffs FROM staff;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()){
                int count = rs.getInt("total_staffs");
                return count;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }
    public float getTotalPrice(){
        try {
            String sql = "SELECT SUM(totalPrice) AS total_price\n" +
                    "FROM bill\n" +
                    "WHERE payment_status = 'Paid';";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()){
                float count = rs.getFloat("total_price");
                return count;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int getTotalAppointment(){
        try {
            String sql = "SELECT COUNT(*) AS total_appointments\n" +
                    "FROM booking;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()){
                int count = rs.getInt("total_appointments");
                return count;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }
    public List<MedicalRecord> getInforTotalAppoinment(){
        List<MedicalRecord> getAppoinmentList = new ArrayList<>();
        try {
            String sql = "SELECT b.*, d.name AS doctor_name, d.specialty AS doctor_specialty, d.url AS doctor_url, p.name AS patient_name, p.url AS patient_url, MAX(m.totalPrice) AS max_total_bill, s.name AS slot_name, m.payment_status\n" +
                    "FROM booking b\n" +
                    "LEFT JOIN doctor d ON b.doctor_id = d.id\n" +
                    "LEFT JOIN patient p ON b.patient_id = p.id\n" +
                    "LEFT JOIN medical_record mr ON b.id = mr.booking_id\n" +
                    "LEFT JOIN bill m ON mr.id = m.medical_record_id\n" +
                    "LEFT JOIN slot s ON b.slot_id = s.id\n" +
                    "GROUP BY b.id, d.name, p.name, s.name, m.payment_status\n" +
                    "ORDER BY max_total_bill DESC;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Doctor doctors = new Doctor();
                doctors.setId(rs.getInt("doctor_id"));
                doctors.setUrl(rs.getString("doctor_url"));
                doctors.setName(rs.getString("doctor_name"));
                doctors.setSpecialty(rs.getString("doctor_specialty"));
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setUrl(rs.getString("patient_url"));
                patient.setName(rs.getString("patient_name"));
                Slot slot = new Slot();
                slot.setName(rs.getString("slot_name"));
                Bill bill = new Bill();
                bill.setPayment_status(rs.getString("payment_status"));
                bill.setTotalPrice(rs.getFloat("max_total_bill"));
                Booking booking = new Booking();
                booking.setDate(rs.getDate("date"));
                booking.setStatus(rs.getString("status"));
                booking.setSlots(slot);
                booking.setDoctor(doctors);
                booking.setPatient(patient);
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setBooking(booking);
                medicalRecord.setBill(bill);
                getAppoinmentList.add(medicalRecord);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return getAppoinmentList;
    }
    public  List<MedicalRecord> doctorList (){
        List<MedicalRecord> getDoctorList = new ArrayList<>();
        try {
            String sql = "SELECT d.*, a.username AS account_username, a.password, a.phone, a.email, a.isAdmin, a.status, \n" +
                    "       SUM(bi.totalPrice) AS total_bill_price\n" +
                    "FROM doctor d\n" +
                    "LEFT JOIN account a ON d.username = a.username\n" +
                    "LEFT JOIN booking b ON d.id = b.doctor_id\n" +
                    "LEFT JOIN medical_record m ON b.id = m.booking_id\n" +
                    "LEFT JOIN bill bi ON m.id = bi.medical_record_id AND bi.payment_status = 'Paid'\n" +
                    "GROUP BY d.id";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Account account = new Account();
                account.setUsername(rs.getString("account_username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setIsAdmin(rs.getInt("isAdmin"));
                account.setStatus(rs.getBoolean("status"));
                Doctor doctors = new Doctor();
                doctors.setId(rs.getInt("id"));
                doctors.setUrl(rs.getString("url"));
                doctors.setName(rs.getString("name"));
                doctors.setGender(rs.getString("gender"));
                doctors.setDob(rs.getDate("dob"));
                doctors.setRankId(rs.getInt("rank_id"));
                doctors.setSpecialty(rs.getString("specialty"));
                doctors.setAccount(account);
                Bill bill = new Bill();
                bill.setTotalPrice(rs.getFloat("total_bill_price"));
                Booking booking = new Booking();
                booking.setDoctor(doctors);
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setBooking(booking);
                medicalRecord.setBill(bill);
                getDoctorList.add(medicalRecord);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return getDoctorList;
    }
    public  List<MedicalRecord> patientList (){
        List<MedicalRecord> getPatientList = new ArrayList<>();
        try {
            String sql = "SELECT p.*, a.password, a.phone, a.email, a.isAdmin, a.status, MAX(b.date) AS latest_booking_date, SUM(bi.totalPrice) AS total_bill_price\n" +
                    "FROM patient p\n" +
                    "LEFT JOIN account a ON p.username = a.username\n" +
                    "LEFT JOIN booking b ON p.id = b.patient_id\n" +
                    "LEFT JOIN medical_record m ON b.id = m.booking_id\n" +
                    "LEFT JOIN bill bi ON m.id = bi.medical_record_id AND bi.payment_status = 'Paid'\n" +
                    "GROUP BY p.id;";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setIsAdmin(rs.getInt("isAdmin"));
                account.setStatus(rs.getBoolean("status"));
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setUrl(rs.getString("url"));
                patient.setName(rs.getString("name"));
                patient.setGender(rs.getString("gender"));
                patient.setDob(rs.getDate("dob"));
                patient.setRankId(rs.getInt("rank_id"));
                patient.setAccount(account);
                Bill bill = new Bill();
                bill.setTotalPrice(rs.getFloat("total_bill_price"));
                Booking booking = new Booking();
                booking.setDate(rs.getDate("latest_booking_date"));
                booking.setPatient(patient);
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setBooking(booking);
                medicalRecord.setBill(bill);
                getPatientList.add(medicalRecord);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return getPatientList;
    }
}