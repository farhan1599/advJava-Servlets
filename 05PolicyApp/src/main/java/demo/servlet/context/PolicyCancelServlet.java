package demo.servlet.context;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PolicyCancelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		int policyId = Integer.parseInt(req.getParameter("policyId"));

		// get the ServletContext object
		ServletContext context = getServletContext();

		// read the map from context associated with the key policies
		Map<Integer, Policy> policiesMap = (Map) context.getAttribute("policies");

		// read the policyCount from the context
		int policyCount = (Integer) context.getAttribute("policyCount");

		if (policiesMap.remove(policyId) != null) {
			policyCount--;

			// store the back to the context
			context.setAttribute("policies", policiesMap);
			context.setAttribute("policyCount", policyCount);

			out.println("<html> <body>");
			out.println("<h2>");
			out.println(" Your policy with id :" + policyId + " is cancelled ");
			out.println("<br>");
			out.println("<a href='Index.html'> click here </a> to goto home page");
			out.println("</body> </html>");
		} else {
			out.println("<h2> policy with id :" + policyId + " doesn't exist !! </h2>");
			out.println("<br>");
			out.println("<a href='Index.html'> click here </a> to goto home page");
			out.println("</h2> </body> </html>");
		}
		out.close();

	}

}
