package az.hrmodule.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {

    private Connection conn = null;

    public void closeConn() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnect() {

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521/xe";
            String user = "hr";
            String password = "hr";
            conn = DriverManager.getConnection(url, user, password);



            ///   conn = DriverManager.getConnection(url, user,password);

            if (conn == null) {
                System.out.println("Not connected");
            } else {
                System.out.println("Connected");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException se) {
            se.printStackTrace();
        }

        return conn;
    }


}
