package mvc.dal;


import mvc.model.*;

import javax.print.Doc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDB extends DBContext{

    public Role getRole(Account account){
        try {
            String sql = "SELECT * FROM role WHERE id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, account.getIsAdmin());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                return role;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Account getAccount(String user, String pass){
        try {
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, user);
        stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setPhone(rs.getString("phone"));
                account.setEmail(rs.getString("email"));
                account.setIsAdmin(rs.getInt("isAdmin"));
                account.setStatus(rs.getBoolean("status"));
                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return null;
    }
    public List<Account> getAllAccount(){
        List<Account> accountList = new ArrayList<>();
        Account account = null;
        try {
            String sql = "SELECT * FROM account";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setPhone(rs.getString("phone"));
                account.setEmail(rs.getString("email"));
                account.setIsAdmin(rs.getInt("isAdmin"));
                accountList.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }
    public void UpdateAccount(Account account){
        try {
            String sql = "UPDATE account\n" +
                    "SET password = ?" +
                    "WHERE username = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getPassword());
            stm.setString(2, account.getUsername());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Account checkAccountExist(String user){
            try {
                String sql = "select * from account where username = ?";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, user);
                ResultSet rs = stm.executeQuery();
                if(rs.next()){
                    Account account  = new Account();
                    account.setUsername(rs.getString("username"));
                    account.setPassword(rs.getString("password"));
                    account.setPhone(rs.getString("phone"));
                    account.setEmail(rs.getString("email"));
                    account.setIsAdmin(rs.getInt("isAdmin"));
                    return account;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

    public void Register(Account account, String name){
        try {
            //Insert Account
            String sql = "INSERT account (username, password, email, isAdmin) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
//            stm.setString(3, account.getPhone());
            stm.setString(3, account.getEmail());
            stm.setInt(4, account.getIsAdmin());
//            stm.setBoolean(6, account.getStatus());
            stm.executeUpdate();
            //Insert Patient
            String sql1 = "INSERT patient (username, name, rank_id)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setString(1, account.getUsername());
            stm1.setString(2, name);
            stm1.setInt(3, 1);//rank đồng
            stm1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addNewDoctor(Doctor doctor) {
        try {
            // Add data to the account table
            String accountSql = "INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement accountStm = connection.prepareStatement(accountSql);
            accountStm.setString(1, doctor.getAccount().getUsername());
            accountStm.setString(2, doctor.getAccount().getPassword());
            accountStm.setString(3, doctor.getAccount().getPhone());
            accountStm.setString(4, doctor.getAccount().getEmail());
            accountStm.setInt(5, doctor.getAccount().getIsAdmin());
            accountStm.setBoolean(6, doctor.getAccount().getStatus());
            accountStm.executeUpdate();
            // Add data to the doctor table
            String doctorSql = "INSERT INTO doctor (username, url, name, gender, dob, specialty, rank_id) VALUES (?, ?, ?,?, ?, ?,?)";
            PreparedStatement doctorStm = connection.prepareStatement(doctorSql);
            doctorStm.setString(1, doctor.getUserName());
            doctorStm.setString(2, doctor.getUrl());
            doctorStm.setString(3, doctor.getName());
            doctorStm.setString(4, doctor.getGender());
            doctorStm.setDate(5, doctor.getDob());
            doctorStm.setString(6, doctor.getSpecialty());
            doctorStm.setInt(7, doctor.getRankId());
            doctorStm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addNewPatient(Patient patient) {
        try {
            // Add data to the account table
            String accountSql = "INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES (?,?,?,?,?,?);";
            PreparedStatement stm1 = connection.prepareStatement(accountSql);
            stm1.setString(1, patient.getAccount().getUsername());
            stm1.setString(2, patient.getAccount().getPassword());
            stm1.setString(3, patient.getAccount().getPhone());
            stm1.setString(4, patient.getAccount().getEmail());
            stm1.setInt(5, patient.getAccount().getIsAdmin());
            stm1.setBoolean(6, patient.getAccount().getStatus());
            stm1.executeUpdate();
            // Add data to the patient table
            String patientSql = "INSERT INTO patient (username, url, name, gender, dob, rank_id) VALUES (?, ?, ?,?, ?,?);";
            PreparedStatement stm = connection.prepareStatement(patientSql);
            stm.setString(1, patient.getUserName());
            stm.setString(2, patient.getUrl());
            stm.setString(3, patient.getName());
            stm.setString(4, patient.getGender());
            stm.setDate(5, patient.getDob());
            stm.setInt(6, patient.getRankId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addNewStaff(Staff staff) {
        try {
            // Add data to the account table
            String accountSql = "INSERT INTO account (username, password, phone, email, isAdmin, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement accountStm = connection.prepareStatement(accountSql);
            accountStm.setString(1, staff.getAccount().getUsername());
            accountStm.setString(2, staff.getAccount().getPassword());
            accountStm.setString(3, staff.getAccount().getPhone());
            accountStm.setString(4, staff.getAccount().getEmail());
            accountStm.setInt(5, staff.getAccount().getIsAdmin());
            accountStm.setBoolean(6, staff.getAccount().getStatus());
            accountStm.executeUpdate();
            // Add data to the staff table
            String staffSql = "INSERT INTO staff (username, url, name, gender, dob) VALUES ( ?, ?, ?, ?, ?);";
            PreparedStatement staffStm = connection.prepareStatement(staffSql);
            staffStm.setString(1, staff.getUserName());
            staffStm.setString(2, staff.getUrl());
            staffStm.setString(3, staff.getName());
            staffStm.setString(4, staff.getGender());
            staffStm.setDate(5, staff.getDob());
            staffStm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void UpdateDoctor(Doctor doctor) {
        try {
            // Add data to the account table
            String accountSql = "UPDATE account\n" +
                    "SET password = ?,\n" +
                    "    phone = ?,\n" +
                    "    email = ?,\n" +
                    "    isAdmin = ?,\n" +
                    "    status = ?\n" +
                    "WHERE username = ?;";
            PreparedStatement accountStm = connection.prepareStatement(accountSql);
            accountStm.setString(1, doctor.getAccount().getPassword());
            accountStm.setString(2, doctor.getAccount().getPhone());
            accountStm.setString(3, doctor.getAccount().getEmail());
            accountStm.setInt(4, doctor.getAccount().getIsAdmin());
            accountStm.setBoolean(5, doctor.getAccount().getStatus());
            accountStm.setString(6, doctor.getAccount().getUsername());
            accountStm.executeUpdate();
            // Add data to the doctor table
            String doctorSql = "UPDATE doctor\n" +
                    "SET url = ?,\n" +
                    "    name = ?,\n" +
                    "    gender = ?,\n" +
                    "    dob = ?,\n" +
                    "    specialty = ?,\n" +
                    "    rank_id = ?\n" +
                    "WHERE id = ?;";
            PreparedStatement doctorStm = connection.prepareStatement(doctorSql);
            doctorStm.setString(1, doctor.getUrl());
            doctorStm.setString(2, doctor.getName());
            doctorStm.setString(3, doctor.getGender());
            doctorStm.setDate(4, doctor.getDob());
            doctorStm.setString(5, doctor.getSpecialty());
            doctorStm.setInt(6, doctor.getRankId());
            doctorStm.setInt(7, doctor.getId());
            doctorStm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void UpdatePatient(Patient patient) {
        try {
            // Add data to the account table
            String accountSql = "UPDATE account\n" +
                    "SET password = ?,\n" +
                    "    phone = ?,\n" +
                    "    email = ?,\n" +
                    "    isAdmin = ?,\n" +
                    "    status = ?\n" +
                    "WHERE username = ?;";
            PreparedStatement accountStm = connection.prepareStatement(accountSql);
            accountStm.setString(1, patient.getAccount().getPassword());
            accountStm.setString(2, patient.getAccount().getPhone());
            accountStm.setString(3, patient.getAccount().getEmail());
            accountStm.setInt(4, patient.getAccount().getIsAdmin());
            accountStm.setBoolean(5, patient.getAccount().getStatus());
            accountStm.setString(6, patient.getAccount().getUsername());
            accountStm.executeUpdate();
            // Add data to the patient table
            String patientSql = "UPDATE patient\n" +
                    "SET url = ?,\n" +
                    "    name = ?,\n" +
                    "    gender = ?,\n" +
                    "    dob = ?,\n" +
                    "    rank_id = ?\n" +
                    "WHERE id = ?;";
            PreparedStatement stm = connection.prepareStatement(patientSql);
            stm.setString(1, patient.getUrl());
            stm.setString(2, patient.getName());
            stm.setString(3, patient.getGender());
            stm.setDate(4, patient.getDob());
            stm.setInt(5, patient.getRankId());
            stm.setString(6, patient.getUserName());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void UpdateStaff(Staff staff) {
        try {
            // Add data to the account table
            String accountSql = "UPDATE account\n" +
                    "SET password = ?,\n" +
                    "    phone = ?,\n" +
                    "    email = ?,\n" +
                    "    isAdmin = ?,\n" +
                    "    status = ?\n" +
                    "WHERE username = ?;";
            PreparedStatement accountStm = connection.prepareStatement(accountSql);
            accountStm.setString(1, staff.getAccount().getPassword());
            accountStm.setString(2, staff.getAccount().getPhone());
            accountStm.setString(3, staff.getAccount().getEmail());
            accountStm.setInt(4, staff.getAccount().getIsAdmin());
            accountStm.setBoolean(5, staff.getAccount().getStatus());
            accountStm.setString(6, staff.getAccount().getUsername());
            accountStm.executeUpdate();
            // Add data to the staff table
            String staffSql = "UPDATE staff\n" +
                    "SET url = ?,\n" +
                    "    name = ?,\n" +
                    "    gender = ?,\n" +
                    "    dob = ?\n" +
                    "WHERE id = ?;";
            PreparedStatement staffStm = connection.prepareStatement(staffSql);
            staffStm.setString(1, staff.getUrl());
            staffStm.setString(2, staff.getName());
            staffStm.setString(3, staff.getGender());
            staffStm.setDate(4, staff.getDob());
            staffStm.setInt(5, staff.getId());
            staffStm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Staff getStaffByID(String id){
        try {
            String sql = "SELECT s.*, a.username, a.password, a.phone, a.email, a.isAdmin, a.status\n" +
                    "FROM staff s\n" +
                    "INNER JOIN account a ON s.username = a.username\n" +
                    "WHERE s.id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id));
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setPhone(rs.getString("phone"));
                account.setEmail(rs.getString("email"));
                account.setIsAdmin(rs.getInt("isAdmin"));
                account.setStatus(rs.getBoolean("status"));
                Staff staff = new Staff();
                staff.setAccount(account);
                staff.setId(rs.getInt("id"));
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
    public Patient getPatientByID(String id){
        try {
            String sql = "SELECT p.*, rp.name AS rank_name, a.username, a.password, a.phone, a.email, a.isAdmin, a.status\n" +
                    "FROM patient p\n" +
                    "INNER JOIN rank_patient rp ON p.rank_id = rp.id\n" +
                    "INNER JOIN account a ON p.username = a.username\n" +
                    "WHERE p.id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id));
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setPhone(rs.getString("phone"));
                account.setEmail(rs.getString("email"));
                account.setIsAdmin(rs.getInt("isAdmin"));
                account.setStatus(rs.getBoolean("status"));
                Rank rank = new Rank();
                rank.setId(rs.getInt("rank_id"));
                rank.setName(rs.getString("rank_name"));
                Patient patient = new Patient();
                patient.setAccount(account);
                patient.setRanks(rank);
                patient.setId(rs.getInt("id"));
                patient.setUrl(rs.getString("url"));
                patient.setName(rs.getString("name"));
                patient.setGender(rs.getString("gender"));
                patient.setDob(rs.getDate("dob"));
                return patient;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Doctor getDoctorByID(String id){
        try {
            String sql = "SELECT d.*, rd.name AS rank_name, a.username, a.password, a.phone, a.email, a.isAdmin, a.status\n" +
                    "FROM doctor d\n" +
                    "INNER JOIN rank_doctor rd ON d.rank_id = rd.id\n" +
                    "INNER JOIN account a ON d.username = a.username\n" +
                    "WHERE d.id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id));
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setPhone(rs.getString("phone"));
                account.setEmail(rs.getString("email"));
                account.setIsAdmin(rs.getInt("isAdmin"));
                account.setStatus(rs.getBoolean("status"));
                Rank rank = new Rank();
                rank.setId(rs.getInt("rank_id"));
                rank.setName(rs.getString("rank_name"));
                Doctor doctor = new Doctor();
                doctor.setAccount(account);
                doctor.setRanks(rank);
                doctor.setId(rs.getInt("id"));
                doctor.setUrl(rs.getString("url"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setDob(rs.getDate("dob"));
                doctor.setSpecialty(rs.getString("specialty"));
                return doctor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Rank> getRankDoctor(){
        List<Rank> rankList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM rank_doctor";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Rank rank = new Rank();
                rank.setId(rs.getInt("id"));
                rank.setName(rs.getString("name"));
                rankList.add(rank);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rankList;
    }
    public List<Rank> getRankPatient(){
        List<Rank> rankList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM rank_patient";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Rank rank = new Rank();
                rank.setId(rs.getInt("id"));
                rank.setName(rs.getString("name"));
                rankList.add(rank);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rankList;
    }
}
