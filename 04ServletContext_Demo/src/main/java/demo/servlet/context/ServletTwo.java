package demo.servlet.context;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTwo extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	res.setContentType("text/html");
	PrintWriter out= res.getWriter();
	ServletContext context= getServletContext();
	String value=context.getInitParameter("apiKey");
	 out.println("<html> <body>");
	   out.println("<font color='red' size=10>");
	   out.println("apiKey :"+value);
	   out.println("</font> </body> </html>");
	   out.close();
	}

}
