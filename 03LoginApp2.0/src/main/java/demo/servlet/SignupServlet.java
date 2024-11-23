package demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
	Connection conn = null;
	PreparedStatement pstmt = null;

	public void init(ServletConfig config) throws ServletException {
		try {

			String driver = config.getInitParameter("driverClassName");
			Class.forName(driver);
			String url = config.getInitParameter("dbUrl");
			String username = config.getInitParameter("username");
			String password = config.getInitParameter("password");

			/*
			 * Class.forName("com.mysql.cj.jdbc.Driver"); String
			 * url="jdbc:mysql://localhost:3306/test"; String username="root"; String
			 * password="farhanmySQL";
			 */
			conn = DriverManager.getConnection(url, username, password);
			String query = "insert into USERS values( ?, ?)";
			pstmt = conn.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");
		String confirmPassword = req.getParameter("cpwd");

		if (!confirmPassword.equals(password)) {
			out.println("<html>");
			out.println("<body>");
			out.println("<font color='red'>");
			out.println("Confirm password doesn't match with password !");
			out.println("</font> </html> </body>");
		} else {
			try {
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				pstmt.executeUpdate();
				out.println("<html>");
				out.println("<body>");
				out.println("<font color='green'>");
				out.println("Registration success!<br>");
				out.println("<a href='Login.html'> click here </a> to login");
				out.println("</font> </html> </body>");

			} catch (Exception e) {
				out.println("<html>");
				out.println("<body>");
				out.println("<font color='red'>");
				out.println("Username already exist !!<br>");
				out.println("<a href='Signup.html'> click here </a> to try again");
				out.println("</font> </html> </body>");
			}
		}
		out.close();

	}

	public void destroy() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
