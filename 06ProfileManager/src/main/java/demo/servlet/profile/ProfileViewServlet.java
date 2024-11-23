package demo.servlet.profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/viewSrv")
public class ProfileViewServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	
	String query="select * from USERS_PROFILE where EMAIL=?";
	out.println("<html> <body style = text-align:center;>");
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	   String url="jdbc:mysql://localhost:3306/test";
	   String username="root";
	   String password="farhanmySQL";
	   
	   Connection connection= DriverManager.getConnection(url,username,password);
	   PreparedStatement pstmt=connection.prepareStatement(query);
	   String email= req.getParameter("email");
	   pstmt.setString(1, email);
	   ResultSet rs=pstmt.executeQuery();
	   rs.next();
	   
	   RequestDispatcher dispatcher1= req.getRequestDispatcher("headerSrv");
	   dispatcher1.include(req, res);
	   out.println("<hr>");
	   out.println("First Name :"+rs.getString(1));
	   out.println("<br>");
	   out.println("Last Name :"+rs.getString(2));
	   out.println("<br>");
	   out.println("Email :"+rs.getString(3));
	   out.println("<br>");
	   out.println("DOB :"+rs.getString(4));
	   out.println("<br>");
	   out.println("Contact :"+rs.getString(5));
	   out.println("<hr>");
	   
	   RequestDispatcher dispatcher2= req.getRequestDispatcher("footerSrv");
	   dispatcher2.include(req, res);
	   rs.close();
	   pstmt.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
	out.println("</body> </html>");
	out.close();
	}

}
