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
            // Add data to the staff table
            String staffSql = "INSERT INTO doctor (username, url, name, gender, dob) VALUES (?, ?, ?,?, ?)";
            PreparedStatement staffStm = connection.prepareStatement(staffSql);
            staffStm.setString(1, staff.getUserName());
            staffStm.setString(2, staff.getUrl());
            staffStm.setString(3, staff.getName());
            staffStm.setString(4, staff.getGender());
            staffStm.setDate(5, staff.getDob());
            staffStm.executeUpdate();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
