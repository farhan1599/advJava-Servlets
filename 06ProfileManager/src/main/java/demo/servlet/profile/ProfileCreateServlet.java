package demo.servlet.profile;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/buildSrv")
public class ProfileCreateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// read the input
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String email = req.getParameter("email");
		String dob = req.getParameter("dob");
		String contact = req.getParameter("contact");

		String query = "insert into USERS_PROFILE values (?,?,?,?,?)";

		try {
			// to convert a date from string to date format
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateOfBirth = sdf.parse(dob);

			// convert contact from string to long
			long phone = Long.parseLong(contact);

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/test";
			String username = "root";
			String password = "farhanmySQL";
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
			java.sql.Date sqlDate = new java.sql.Date(dateOfBirth.getTime());
			pstmt.setDate(4, sqlDate);
			pstmt.setLong(5, phone);
			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("viewSrv");
		dispatcher.forward(req, res);
	}

}
