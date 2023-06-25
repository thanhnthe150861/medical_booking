package dal;

import model.*;

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
            String sql = "SELECT d.id, d.username, d.picture, d.name, d.gender, d.dob, d.specialty, rd.id AS rank_id, rd.name AS 'rank'\n" +
                    "FROM doctor d\n" +
                    "LEFT JOIN rank_doctor rd ON rd.id = d.rank_id\n" +
                    "WHERE d.username = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            rs = stm.executeQuery();
            if (rs.next()){
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setUserName(rs.getString("username"));
                doctor.setUrl(rs.getString("picture"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setDob(rs.getDate("dob"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setRankId(rs.getInt("rank_id"));
                Rank rank = new Rank();
                rank.setId(rs.getInt("rank_id"));
                rank.setName(rs.getString("rank"));
                doctor.setRanks(rank);
                return doctor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Client> getMyPatients(Doctor doctor, String status){
        List<Client> clientList = new ArrayList<>();
        try {
            String sql = "SELECT c.id, c.username, c.picture, c.name, c.gender, c.dob, rc.id as rank_id\n" +
                    "FROM client c\n" +
                    "INNER JOIN booking b ON b.client_id = c.id\n" +
                    "INNER JOIN doctor d ON d.id = b.doctor_id\n" +
                    "LEFT JOIN rank_client rc ON rc.id = c.rank_id\n" +
                    "WHERE b.status = ? AND d.username = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, doctor.getUserName());
            rs = stm.executeQuery();
//            while (rs.next()){
//                Client client = new Client();
//                client.setId();
//                client.setUserName();
//                client.setUrl();
//                client.setName();
//                client.setGender();
//                client.setDob();
//                client.setRankId();
//            }
    }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return clientList;
    }

    public List<Booking> getBooking(Doctor doctor, String status){
        List<Booking> bookingList = new ArrayList<>();
        try {
            String sql = "SELECT b.id AS booking_id, b.doctor_id, b.client_id, b.slot_id, b.booking_reason, b.date, b.status, c.name AS client_name, c.gender, c.dob, s.name AS slot_name\n" +
                    "FROM booking b\n" +
                    "LEFT JOIN client c ON c.id = b.client_id\n" +
                    "LEFT JOIN slot s ON s.id = b.slot_id\n" +
                    "WHERE b.status = ? and b.doctor_id =  ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, doctor.getId());
            rs = stm.executeQuery();
            while (rs.next()){
                Booking booking = new Booking();
                booking.setId(rs.getInt("booking_id"));
                booking.setDoctor_id(rs.getInt("doctor_id"));
                booking.setClient_id(rs.getInt("client_id"));
                booking.setSlot_id(rs.getInt("slot_id"));
                booking.setBooking_reason(rs.getString("booking_reason"));
                booking.setDate(rs.getDate("date"));
                booking.setStatus(rs.getString("status"));
                Client client = new Client();
                client.setId(rs.getInt("client_id"));
                client.setName(rs.getString("client_name"));
                client.setDob(rs.getDate("dob"));
                client.setGender(rs.getString("gender"));
                booking.setClient(client);
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

    public void updateBookingStatus(String id, String status) {
        try {
            String sql = "UPDATE booking SET status = ? WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, Integer.parseInt(id));
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDoctor(Account account, Doctor doctor) {
        try {
            // Update the account's information
            String accountSql = "UPDATE account SET password = ?, email = ?, phone = ? WHERE username = ?";
            stm = connection.prepareStatement(accountSql);
            stm.setString(1, account.getPassword());
            stm.setString(2, account.getEmail());
            stm.setString(3, account.getPhone());
            stm.setString(4, account.getUsername());
            stm.executeUpdate();
            // Update the doctor's information
            String doctorSql = "UPDATE doctor SET picture = ?, name = ?, gender = ?, dob = ?, specialty = ?, rank_id = ? WHERE username = ?";
            stm = connection.prepareStatement(doctorSql);
            stm.setString(1, doctor.getUrl());
            stm.setString(2, doctor.getName());
            stm.setString(3, doctor.getGender());
            stm.setDate(4, doctor.getDob());
            stm.setString(5, doctor.getSpecialty());
            stm.setInt(6, doctor.getRankId());
            stm.setString(7, doctor.getUserName());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
