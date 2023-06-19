package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    protected Connection connection;

    public DBContext() {
        try {

            String dbName = "medical_booking";
            String portNumber = "3306";


//            String serverName = "database.cvuua3k5atka.ap-southeast-1.rds.amazonaws.com"; //kết nối với database cloud
//            //Acc AWS DB
//            String user = "admin";
//            String pass = "12345678";


            String serverName = "localhost";//đang kết nối với database localhost
            //Acc localhost
            String user = "root";           //Account mySQL
            String pass = "Tuan@3006";      //Password mySQL


            String url = "jdbc:mysql://"+serverName+":"+portNumber+"/"+dbName;

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
