package demo.servlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// read the username and password from the request
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(authenticate(username, password)) {
			//create session object
			HttpSession session= req.getSession();
			//add the username in session object
			session.setAttribute("username ", username);
			res.sendRedirect("product.html");
		}
		else {
			res.sendRedirect("index.html");
		}
	}

	private boolean authenticate(String username, String password) {
		return username != null && !username.isEmpty() && password != null && !password.isEmpty();
	}
}
