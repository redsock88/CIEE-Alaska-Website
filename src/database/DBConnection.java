package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection connection = getConnection();
	
	 //Connect to database
    public static Connection getConnection()  {
    	Connection conn = null;
        try {
			Class.forName("com.mysql.jdbc.Driver");
	        String url="jdbc:mysql://127.0.0.1:3306/daniels_website";
	        String username="root";
	        String password="root";
	        conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
