package student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import database.DBConnection;
import person.Person;

public class Student {

	public long studentID;
	public long personID;
	public String countryOfOrigin;
	public long parentID;
	public long hostID;
	public String school;
	public Date endDate;
	private Connection connection = DBConnection.connection;
	
	public Student() {
		studentID = 0;
		personID = 0;
		countryOfOrigin = null;
		parentID = 0;
		hostID = 0;
		school = null;
		school = null;
	}
	
	public void insertStudent(Student student) {
		try {
			student.studentID = getNextUnique();
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(
					"INSERT INTO student (studentID, personID, countryOfOrigin, parentID, hostID, school, endDate) "
							+ "VALUES (" + student.studentID + ", " + student.personID + ", '" + student.countryOfOrigin + "', " + student.parentID + ", "
							+ student.hostID + ", '" + student.school + "', current_date())");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setParentID(long childID, long parentID) {
		try {
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate("UPDATE student SET parentID = " + parentID + " WHERE personID = " + childID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setSchool(long childID, String schoolName) {
		try {
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate("UPDATE student SET school = '" + schoolName + "' WHERE personID = " + childID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setHost(long childID, String hostID) {
		try {
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate("UPDATE student SET hostID = " + hostID + " WHERE personID = " + childID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// Get the next unique primary key
	private int getNextUnique() {
		try {
			int nextUnique = 0;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT MAX(studentID) studentID FROM student");
			while (rs.next()) {
				nextUnique = rs.getInt("studentID") + 1;
			}
			return nextUnique;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
