package demo.servlet.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value = "/add-to-cart")
public class CartServlet extends HttpServlet {

	private static List<Product> productList = null;
	static {
		productList = new ArrayList<Product>();
		productList.add(new Product("p-1231", "Samsung QLED TV", 250000.0));
		productList.add(new Product("p-1241", "Macbook Pro", 175000.0));
		productList.add(new Product("p-1251", "Realme 9 Pro", 25000.0));
		productList.add(new Product("p-1261", "LG Micro Owen", 14000.0));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String[] productIds = req.getParameterValues("product");
		HttpSession session = req.getSession(false);

		List<Product> cart = (List<Product>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<Product>();
		}
		for (String pId : productIds) {
			Product selectedProduct = productList.stream().filter(p -> p.getProductId().equals(pId)).findFirst()
					.orElse(null);
			if (selectedProduct != null) {
				cart.add(selectedProduct);
			}
		}
		session.setAttribute("cart", cart);
		res.sendRedirect("next.html");

	}
}
