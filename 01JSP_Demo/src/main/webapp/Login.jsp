<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form action="./Login.jsp" method="post">
		Username : <input type="text" name="username"> <br>
		Password : <input type="password" name="password"> <br> <input
			type="submit" value="submit">
	</form>

	<!--Scriptlet -->
	<%
	String str1 = request.getParameter("username");
	String str2 = request.getParameter("password");

	boolean flag = isValid(str1, str2);
	String message = " ";

	if (flag == true) {
		message = "Login Success";
	} else {
		message = "Login failed";
	}
	%>
	<!--Declaration  -->
	<%!private boolean isValid(String s1, String s2) {
		if (s1!=null && s1.equals("farhan") 
				&& s2!=null  && s2.equals("farhan")){
			
			return true;
		}
		else
			return false;
		
	}%>
	<!-- Expression -->
	<font color='blue' size=8> <%=message%>
	</font>





</body>
</html>