package person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import database.DBConnection;

public class Person {

	public long personID;
	public String firstName;
	public String lastName;
	public String phone;
	public String address;
	private Connection connection = DBConnection.connection;
	
	public Person() {
		  personID = 0;
		  firstName = null;
		  lastName = null;
		  phone = null;
		  address = null;
	}
	
	public long insertPerson(Person person) {
		try {
			person.personID = getNextUnique();
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(
					"INSERT INTO person (personID, firstName, lastName, phone, address) "
							+ "VALUES (" + person.personID + ", '" + person.firstName + "', '" + person.lastName + "', '"
							+ person.phone + "', '" + person.address + "')");
			return person.personID;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public Person searchByName(String first, String last){
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM person where firstName = '" + first + "' and lastName = '" + last + "' ");
			while(rs.next()) {
				personID = rs.getLong("personID");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				phone = rs.getString("phone");
				address = rs.getString("address");
			}
			return this;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Person searchByPersonID(long id){
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM person where personID = " + id);
			while(rs.next()) {
				personID = rs.getLong("personID");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				phone = rs.getString("phone");
				address = rs.getString("address");
			}
			return this;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Get the next unique primary key
	private int getNextUnique() {
		try {
			int nextUnique = 0;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT MAX(personID) personID FROM person");
			while (rs.next()) {
				nextUnique = rs.getInt("personID") + 1;
			}
			return nextUnique;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
