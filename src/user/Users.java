package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import database.DBConnection;
import person.Person;

public class Users {

	public long userID;
	public long personID;
	public String type;
	public String status;
	public String username;
	public String password;
	public Date joinDate;
	public Date lastLogin;
	private Connection connection = DBConnection.connection;
	
	public Users() {
		userID = 0;
		personID = 0;
		type = null;
		status = null;
		username = null;
		password = null;
		joinDate = null;
		lastLogin = null;
	}
	
	public void insertUser(Users user) {
		try {
			user.userID = getNextUnique();
			user.status = "inactive";
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(
					"INSERT INTO user (userID, personID, type, status, username, password, joinDate, lastLogin) "
							+ "VALUES (" + user.userID + ", " + user.personID + ", '" + user.type + "', '" + user.status + "', '"
							+ user.username + "', '" + user.password + "', current_date(), current_date())");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Users searchByUsername(String name){
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users where username = '" + name + "'");
			while(rs.next()) {
				userID = rs.getLong("userID");
				personID = rs.getLong("personID");
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

	
	public void activateUser(String usrID) {
		try {
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate("UPDATE users SET status = 'active' WHERE userID = " + usrID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deactivateUser(String usrID) {
		try {
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate("UPDATE users SET status = 'inactive' WHERE userID = " + usrID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getUserType(String usrID) {
		String userType = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT type FROM users where userID = " + usrID);
			while(rs.next()) {
				userType = rs.getString("type");
			}
			return userType;
		} catch (SQLException e) {
			e.printStackTrace();
			return userType;
		}
	}
	
	public String getUserStatus(String usrID) {
		String userStatus = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT status FROM users where userid = " + usrID);
			while(rs.next()) {
				userStatus = rs.getString("status");
			}
			return userStatus;
		} catch (SQLException e) {
			e.printStackTrace();
			return userStatus;
		}
	}
	
	public Users checkLoginAndGetUser(String name, String pwd) {
		Users user = new Users();
		user = searchByUsername(name);
		if(user != null && user.username.equalsIgnoreCase(name) && user.password.equals(pwd)) {
			return user;
		}else {
			return null;
		}
	}
	
	
	//a return of false means the username is not used
	public boolean checkUsername(String name) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT count(*) FROM users where username = '" + name + "'");
			return rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Get the next unique primary key
	private int getNextUnique() {
		try {
			int nextUnique = 0;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT MAX(userID) userID FROM users");
			while (rs.next()) {
				nextUnique = rs.getInt("userID") + 1;
			}
			return nextUnique;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
