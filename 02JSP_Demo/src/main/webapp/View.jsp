<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,demo.jsp.example.Employees" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"  prefix = "c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Employees</title>
</head>
<body>

	<!-- creating EmployeesBean object -->
	<jsp:useBean id="eBean" class="demo.jsp.example.EmployeesBean" />

	<%
	List<Employees> lst = eBean.fetchEmployees();
	request.setAttribute("employees", lst);
	%>

	<table border="1" style="background: yellow">
		<tr>
			<th><b>EMPNO</b></th>
			<th><b>ENAME</b></th>
			<th><b>SAL</b></th>
		</tr>

		<c:forEach items="${employees}" var="e">
			<tr>
				<td><c:out value="${e.empNo}" /></td>
				<td><c:out value="${e.eName}" /></td>
				<td><c:out value="${e.sal}" /></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>