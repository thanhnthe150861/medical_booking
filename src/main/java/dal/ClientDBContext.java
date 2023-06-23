package dal;

import model.Account;
import model.Booking;
import model.Client;
import model.Doctor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDBContext  extends  DBContext{
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public Client  getClient(Account account){
        try {
            String sql = "SELECT * FROM doctor WHERE username_account = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            rs = stm.executeQuery();
            if (rs.next()){
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setUserName(rs.getString("username_account"));
                client.setDob(rs.getDate("dob"));
                client.setGender(rs.getString("gender"));
                client.setUrl(rs.getString("picture"));
                client.setRankId(rs.getInt("rank_id"));
                return client;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Booking> getSlotsTime(Client client){
        List<Booking> bookingList = new ArrayList<>();
        try {
            String sql = "SELECT b.id, b.doctor_id, b.client_id, b.booking_reason, s.date, sl.name AS slot, b.status\n" +
                    "FROM booking b\n" +
                    "LEFT JOIN schedule s ON b.id_schedule = s.id\n" +
                    "LEFT JOIN slot_booking sb ON s.id = sb.schedule_id\n" +
                    "LEFT JOIN slot sl ON sb.slot_id = sl.id\n" +
                    "WHERE doctor_id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, client.getId());
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
}
