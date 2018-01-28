package login;

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBConnection;
import person.Person;
import student.Student;
import user.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String requestName = request.getParameter("submit");
		if(requestName.equalsIgnoreCase("Login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Users user = new Users();
			user = user.checkLoginAndGetUser(username, password);
			if(user != null && user.status.equalsIgnoreCase("active")) {
				request.setAttribute("userID", user.userID);
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}else {
				if(user != null && !user.status.equalsIgnoreCase("active")) {
					request.setAttribute("errorMessage", "Your account has been deactivated.");
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				}else{
					request.setAttribute("errorMessage", "Invalid username or password.");
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				}
			}
		}else {
			String username = request.getParameter("username");
			Users user = new Users();
			boolean ind = user.checkUsername(username);
			if(!ind) {
				String userType = request.getParameter("userType");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String password = request.getParameter("password");
				Person person = new Person();
				person.firstName = firstName;
				person.lastName = lastName;
				if(userType.equalsIgnoreCase("host_family")) {
					String phone = request.getParameter("phone");
					String address = request.getParameter("address");
					person.phone = phone;
					person.address = address;
				}
				long personID = person.insertPerson(person);
				if(userType.equalsIgnoreCase("student")) {
					String country = request.getParameter("country");
					Student student = new Student();
					student.personID = personID;
					student.countryOfOrigin = country;
					student.insertStudent(student);
				}
				if(userType.equalsIgnoreCase("student_family")) {
					String childFirstName = request.getParameter("childFirstName");
					String childLastName = request.getParameter("childLastName");
					Person child = new Person();
					child = child.searchByName(childFirstName, childLastName);
					Student student = new Student();
					student.setParentID(child.personID, personID);
				}
				user.personID = personID;
				user.type = userType;
				user.password = password;
				user.insertUser(user);
				request.setAttribute("userID", user.userID);
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("errorMessage", "This email is already in use.");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}
		}
	}

}
