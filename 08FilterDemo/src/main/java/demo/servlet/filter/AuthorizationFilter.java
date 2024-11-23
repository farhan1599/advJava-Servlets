package demo.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(value = "/viewSrv")
public class AuthorizationFilter implements Filter {
	List<User> userList;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		userList = new ArrayList<>();
		userList.add(new User("John", "John@123", "ADMIN"));
		userList.add(new User("Manoj", "Manoj@123", "EMPLOYEE"));
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");

		User user = userList.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)
				&& u.getRole().equals("ADMIN")).findFirst().orElse(null);
		if (user != null) {
			chain.doFilter(req, res);
		} else {
			PrintWriter out = res.getWriter();
			out.println("<html><body style=\"text-align:center\" >");
			out.println("<h3> <font color='red'>");
			out.println("Username/Password is invalid or the role is not ADMIN !!");
			out.println("</font></h3>");
			out.println("<br>");
			out.println("<a href='index.html'> click here </a>");
			out.println("</body> </html>");
			out.close();
		}
	}

	@Override
	public void destroy() {
		userList = null;
	}

}
