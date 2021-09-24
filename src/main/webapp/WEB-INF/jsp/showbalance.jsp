<%-- <%@page import="Trail.model.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="Trail.Dao.UserDao"%>
<%@page import="Trail.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
    
    <%
    String driver = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/example";
    String userid = "root";
    String password = "Saiadmin";
HttpSession session1 = request.getSession();
User user = (User) session.getAttribute("user");
String username=user.getUsername();

try {
	Class.forName(driver);
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}
Connection connection = null;
Statement stmt = null;
ResultSet resultSet12 = null;
//out.println(user);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
<link rel="stylesheet" type="text/css">
</head>
<body>
	<h2>Display Balance</h2>
	<br>
	<div class="login">
		<form id="login" method="post" action="statement">

	<h2>your balance is</h2>
			<table border="1" id="customers">
				<tr>
				
					<td>Balance</td>

				</tr>
				<%
					try {
						connection = DriverManager.getConnection(connectionUrl, userid, password);
						String sql = "SELECT * FROM userstable WHERE username = '"+username+"' ;";
						stmt = connection.prepareStatement(sql);
						
						resultSet12 = stmt.executeQuery(sql);
						while (resultSet12.next()) {
				%>
				 <tr>
					<td><%=resultSet12.getString("accountnumber")%></td>
					<td><%=resultSet12.getString("balance")%></td>
					
				</tr> 
				<%
				 } 
					
				
				} catch (Exception e) {
				System.out.println(e);
				}
				%>
			</table>
			</form>
	</div>
	

</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
<link rel="stylesheet" type="text/css">
</head>
<body>
	<h2>Display Balance</h2>
	<br>
	<div class="login">
		<form id="login" method="post" action="statement">

			<h2>your balance is</h2>
			<table border="1" id="customers">
				<tr>

					<td>Balance</td>

				</tr>
					
				<tr>
					<td>${balance}</td>

				</tr>
				<tr>
                       
                        <td><a href="${pageContext.request.contextPath}/menu">Go to menu</a>
                        </td>
			</table>
		</form>
	</div>


</body>
</html>