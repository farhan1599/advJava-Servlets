package demo.servlet.filter;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = "/viewSrv")
public class ViewEmployeesServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out= res.getWriter();
    
    String url="jdbc:mysql://localhost:3306/test";
    String username="root";
    String password="farhanmySQL";
    
    try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection =DriverManager.getConnection(url,username,password);
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("select *from emp");
		
		out.println("<html><body>");
		out.println("<table border=1>");
		while(resultSet.next()) {
			out.println("<tr>");
			out.println("<td>" +resultSet.getString(1)+"</td>");
			out.println("<td>" +resultSet.getString(2)+"</td>");
			out.println("<td>" +resultSet.getString(3)+"</td>");
			out.println("<td>" +resultSet.getString(4)+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body></html>");
		resultSet.close();
		statement.close();
		connection.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
    out.close();
	}
}
