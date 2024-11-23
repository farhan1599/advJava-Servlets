package demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServlet extends GenericServlet {
	Connection conn = null;
	PreparedStatement pstmt = null;

	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/test";
			String uname = "root";
			String pwd = "farhanmySQL";
			conn = DriverManager.getConnection(url, uname, pwd);

			String query = "select count(*) from USERS where USER_NAME=? and PASS_WORD=?";
			pstmt = conn.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		// read the input from req
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");

		// setting the username and password to the PreparedStatement

		try {
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			if (count == 1) {
				out.println("<html>");
				out.println("<body>");
				out.println("<h1>");
				out.println("<font color = 'green'>");
				out.println("Welcome " + username + " <br>");
				out.println("Your login success !!");
				out.println("</font> </h1> </body> </html>");

			} else {
				out.println("<html>");
				out.println("<body>");
				out.println("<h1>");
				out.println("<font color = 'red'>");
				out.println("Your username or password is invalid !!");
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
