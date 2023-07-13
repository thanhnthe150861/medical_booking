package mvc.dal;

import mvc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDBContext extends DBContext{

    private static PreparedStatement stm = null;

    private static ResultSet rs = null;

    public Staff getStaff(Account account){
        try {
            String sql = "SELECT s.id, s.username, s.url, s.name, s.gender, s.dob \n" +
                    "FROM staff s \n" +
                    "WHERE username = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            rs = stm.executeQuery();
            if (rs.next()){
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setUserName(rs.getString("username"));
                staff.setUrl(rs.getString("url"));
                staff.setName(rs.getString("name"));
                staff.setGender(rs.getString("gender"));
                staff.setDob(rs.getDate("dob"));
                return staff;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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

    public List<MedicalRecord> doctorList (){
        List<MedicalRecord> getDoctorList = new ArrayList<>();
        try {
            String sql = "SELECT d.*, a.username AS account_username, a.password, a.phone, a.email, a.isAdmin, a.status, \n" +
                    "       SUM(bi.price) AS total_bill_price\n" +
                    "FROM doctor d\n" +
                    "LEFT JOIN account a ON d.username = a.username\n" +
                    "LEFT JOIN booking b ON d.id = b.doctor_id AND b.status = 'Completed'\n" +
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
                bill.setPrice(rs.getFloat("total_bill_price"));
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
}
