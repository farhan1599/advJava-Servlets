package demo.servlet.context;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PolicyViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		int policyId = Integer.parseInt(req.getParameter("PolicyId"));

		// get the ServletContext object
		ServletContext context = getServletContext();

		// read the map from context associated with the key policies
		Map<Integer, Policy> policiesMap = (Map) context.getAttribute("policies");

		// read the policyCount from the context
		int policyCount = (Integer)context.getAttribute("policyCount");

		Policy policy = policiesMap.get(policyId);
		if (policy != null) {
			out.println("<html> <body>");
			out.println("<h2>");
			out.println("policy Id :" + policy.getPolicyId());
			out.println("<br>");
			out.println("Name :" + policy.getName());
			out.println("<br>");
			out.println("Age :" + policy.getAge());
			out.println("<br>");
			out.println("Expires On :" + policy.getExppiresOn());
			out.println("<hr>");
			out.println("Total number of policies :" + policyCount);
			out.println("<br>");
			out.println("<a href='Index.html'>click here </a> to goto home page");
			out.println("</h2> </body> </html>");
		} else {
			out.println("<html> <body>");
			out.println("<h2>");
			out.println("Policy with id :" + policyId + " doesn't exist !!");
			out.println("<br>");
			out.println("<a href='Index.html'>click here </a> to goto home page");
			out.println("</h2> </body> </html>");

		}
		out.close();

	}

}
