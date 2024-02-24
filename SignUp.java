package j2ee_practice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SignUp extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		long number = Long.parseLong(req.getParameter("number"));
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String country = req.getParameter("country");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			PreparedStatement ps = con.prepareStatement("insert into j2ee_practice.info values(?,?,?,?,?)");
			ps.setString(1, name);
			ps.setLong(2, number);
			ps.setString(3, email);
			ps.setString(4, gender);
			ps.setString(5, country);
			
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html><body bgcolor='pink'><h1>Hello MR."+name+" Yours data saved</h1></body></html>");
		
	}

}
