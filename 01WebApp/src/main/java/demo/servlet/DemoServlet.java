package demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DemoServlet extends GenericServlet {

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		// set MIME type(MIME= Multi-Purspose Internet Mail Extension)
		// MIME type has... type/sub-type
		res.setContentType("text/html");

		// create PrintWriter to write the content into res object
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1> Welcome to servlets </h1>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
