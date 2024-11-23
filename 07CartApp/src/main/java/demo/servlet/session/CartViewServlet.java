package demo.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value = "/view")
public class CartViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String str = req.getParameter("option");
		if (str.equals("continue")) {
			res.sendRedirect("product.html");
		} else {
			HttpSession session = req.getSession(false);
			
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			List<Product> cart = (List<Product>)session.getAttribute("cart");
			String username =(String)session.getAttribute("username");
			out.println("<html> <body>");
			out.println("<h3> Hello " +username+ " ! </h3>");
			out.println("<br>");
			out.println("Your cart items...");
			out.println("<br>");
			out.println("<ol>");

			for (Product p : cart) {
				out.println("<li>");
				out.println(p.getProductId() + " " + p.getProductName() + " " + p.getPrice());
				out.println("</li>");
			}
				out.println("</ol>");
				out.println("<br>");
				out.println("<a href='./logout'> Logout </a>");
				out.println("</body> </html> ");
				out.close();
		
			
		}
	}
}
