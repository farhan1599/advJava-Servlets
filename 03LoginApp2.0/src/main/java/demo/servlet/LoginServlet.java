package demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
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
			String query = "select count(*) from USERS where USER_NAME=? and PASS_WORD=?";
			pstmt = conn.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		// reading input from req
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");

		// setting the username and password to the PreparedStatement
		try {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			if (count == 1) {
				out.println("<html>");
				out.println("<body>");
				out.println(" <h1>");
				out.println("<font color ='green'>");
				out.println("Welcome " + username + " <br>");
				out.println("Your Login Success !");
				out.println("</font> </h1> </body> </html>");

			} else {
				out.println("<html>");
				out.println("<body>");
				out.println(" <h1>");
				out.println("<font color ='red'>");
				out.println("Username or password is invalid !");
				out.println("</font> </h1> </body> </html>");

			}
		} catch (Exception e) {
			e.printStackTrace();
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
