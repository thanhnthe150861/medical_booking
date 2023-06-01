package dal;


import model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDB extends DBContext{
    public Account getAccount(String user, String pass){
        try {
        String sql = "select * from account where username = ? and password = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, user);
        stm.setString(2, pass);
        ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setPhone(rs.getString("phone_number"));
                account.setEmail(rs.getString("email"));
                account.setIsAdmin(rs.getString("isAdmin"));
                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return null;
    }


    public void insertAccount(Account account){
        try {
            String sql = "";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
            stm.setString(3, account.getPhone());
            stm.setString(4, account.getEmail());
            stm.setString(5, account.getIsAdmin());
            ResultSet rs = stm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
