package dal;


import model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDB extends DBContext{
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;
    public Account getAccount(String user, String pass){
        try {
        String sql = "select * from account where username = ? and password = ?";
        stm = connection.prepareStatement(sql);
        stm.setString(1, user);
        stm.setString(2, pass);
        rs = stm.executeQuery();
            if(rs.next()){
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setPhone(rs.getString("phone_number"));
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
            String sql = "select * from account";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setPhone(rs.getString("phone_number"));
                account.setEmail(rs.getString("email"));
                account.setIsAdmin(rs.getInt("isAdmin"));
                accountList.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    public Account checkAccountExist(String user){
        Account account = null;
            try {
                String sql = "select * from account where username = ?";
                stm = connection.prepareStatement(sql);
                stm.setString(1, user);
                rs = stm.executeQuery();
                if(rs.next()){
                    account = new Account();
                    account.setUsername(rs.getString("username"));
                    account.setPassword(rs.getString("password"));
                    account.setPhone(rs.getString("phone_number"));
                    account.setEmail(rs.getString("email"));
                    account.setIsAdmin(rs.getInt("isAdmin"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return account;
        }

    public void insertClient(Account account, String name){
        try {
            //Insert Account
            String sql = "INSERT account (username, password, phone_number, email, isAdmin) " +
                    "VALUES (?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
            stm.setString(3, account.getPhone());
            stm.setString(4, account.getEmail());
            stm.setInt(5, account.getIsAdmin());
            stm.executeUpdate();
            //Insert Client
            String sql1 = "INSERT client (username_account, name, rank_id)" +
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
