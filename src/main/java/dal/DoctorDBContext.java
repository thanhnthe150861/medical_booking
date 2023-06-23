package dal;

import model.Account;
import model.Booking;
import model.Doctor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDBContext extends  DBContext{
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public Doctor  getDoctor(Account account){
        try {
            String sql = "SELECT * FROM doctor WHERE username_account = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            rs = stm.executeQuery();
            if (rs.next()){
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setUserName(rs.getString("username_account"));
                doctor.setDob(rs.getDate("dob"));
                doctor.setGender(rs.getString("gender"));
                doctor.setUrl(rs.getString("picture"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setRankId(rs.getInt("rank_id"));
                return doctor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Booking> getSlotsTime(Doctor doctor){
        List<Booking> bookingList = new ArrayList<>();
        try {
            String sql = "SELECT b.id, b.doctor_id, b.client_id, b.booking_reason, s.date, sl.name AS slot, b.status\n" +
                    "FROM booking b\n" +
                    "LEFT JOIN schedule s ON b.id_schedule = s.id\n" +
                    "LEFT JOIN slot_booking sb ON s.id = sb.schedule_id\n" +
                    "LEFT JOIN slot sl ON sb.slot_id = sl.id\n" +
                    "WHERE doctor_id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, doctor.getId());
            rs = stm.executeQuery();
            while (rs.next()){
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setDoctor_id(rs.getInt("doctor_id"));
                booking.setClient_id(rs.getInt("client_id"));
                booking.setBooking_reason(rs.getString("booking_reason"));
                booking.setDate(rs.getDate("date"));
                booking.setSlot(rs.getString("slot"));
                booking.setStatus(rs.getString("status"));
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingList;
    }

    public void updateDoctor(Account account, Doctor doctor) {
        try {
            // Update the account's information
            String updateAccountSql = "UPDATE account SET password = ?, phone_number = ?, email = ?, isAdmin = ? WHERE username = ?";
            stm = connection.prepareStatement(updateAccountSql);
            stm.setString(1, account.getPassword());
            stm.setString(2, account.getPhone());
            stm.setString(3, account.getEmail());
            stm.setInt(4, account.getIsAdmin());
            stm.setString(5, account.getUsername());
            stm.executeUpdate();
            // Update the doctor's information
            String updateDoctorSql = "UPDATE doctor SET name = ?, dob = ?, gender = ?, picture = ?, specialty = ?, rank_id = ? WHERE username_account = ?";
            stm = connection.prepareStatement(updateDoctorSql);
            stm.setString(1, doctor.getName());
            stm.setDate(2, doctor.getDob());
            stm.setString(3, doctor.getGender());
            stm.setString(4, doctor.getUrl());
            stm.setString(5, doctor.getSpecialty());
            stm.setInt(6, doctor.getRankId());
            stm.setString(7, account.getUsername());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
