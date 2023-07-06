package mvc.dal;


import mvc.model.Account;
import mvc.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDB extends DBContext{
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public Role getRole(Account account){
        try {
            String sql = "SELECT * FROM role WHERE id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, account.getIsAdmin());
            rs = stm.executeQuery();
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
        stm = connection.prepareStatement(sql);
        stm.setString(1, user);
        stm.setString(2, pass);
        rs = stm.executeQuery();
            if(rs.next()){
                Account account = new Account();
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

    public List<Account> getAllAccount(){
        List<Account> accountList = new ArrayList<>();
        Account account = null;
        try {
            String sql = "SELECT * FROM account";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
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
            stm = connection.prepareStatement(sql);
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
                stm = connection.prepareStatement(sql);
                stm.setString(1, user);
                rs = stm.executeQuery();
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


    public void insertPatient(Account account, String name){
        try {
            //Insert Account
            String sql = "INSERT account (username, password, email, isAdmin) " +
                    "VALUES (?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
//            stm.setString(3, account.getPhone());
            stm.setString(3, account.getEmail());
            stm.setInt(4, account.getIsAdmin());
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
}
