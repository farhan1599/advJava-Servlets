package demo.servlet.profile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/entry")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String choice = req.getParameter("option");

		if ("build".equals(choice)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("BuildProfile.html");
			dispatcher.forward(req, res);
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("UpdateProfile.html");
			dispatcher.forward(req, res);
		}

	}

}
