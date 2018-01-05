package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import database.DBConnection;

public class Users {

	public long userID;
	public String type;
	public String status;
	public String username;
	public String password;
	public Date joinDate;
	public Date lastLogin;
	private Connection connection = DBConnection.connection;
	
	public Users() {
		userID = 0;
		type = null;
		status = null;
		username = null;
		password = null;
		joinDate = null;
		lastLogin = null;
	}
	
	public Users searchByUsername(String name){
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users where username = '" + name + "'");
			while(rs.next()) {
				userID = rs.getLong("userID");
				type = rs.getString("type");
				status = rs.getString("status");
				username = rs.getString("username");
				password = rs.getString("password");
				joinDate = rs.getDate("joinDate");
				lastLogin = rs.getDate("lastLogin");
			}
			return this;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public void editUser(Users user) {
		
	}
	
	public boolean checkLogin(String name, String pwd) {
		Users user = new Users();
		user = searchByUsername(name);
		if(user != null && user.username.equalsIgnoreCase(name) && user.password.equals(pwd)) {
			return true;
		}else {
			return false;
		}
	}
}
