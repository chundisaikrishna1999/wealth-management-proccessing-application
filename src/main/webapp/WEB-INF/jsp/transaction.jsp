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
	<h2>Display Statement</h2>
	<br>
	<div class="login">
		<form id="login" method="post" action="statement">

	<h2>Transactions Are</h2>
			<table border="1" id="customers">
				<tr>
				
					<td>Type</td>
					<td>Amount</td>
					<td>Reason</td>

				</tr>
				<%
					try {
						connection = DriverManager.getConnection(connectionUrl, userid, password);
						String sql = "SELECT * FROM statements WHERE username = '"+username+"' ;";
						stmt = connection.prepareStatement(sql);
						
						resultSet12 = stmt.executeQuery(sql);
						while (resultSet12.next()) {
				%>
				 <tr>
					<td><%=resultSet12.getString("accountnumber")%></td>
					<td><%=resultSet12.getString("type")%></td>
					<td><%=resultSet12.getString("amount")%></td>
					<td><%=resultSet12.getString("reason")%></td>
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
<link rel="stylesheet" type="text/css">
</head>
<body>
	<h2>Display Statement</h2>
	<br>
	<div class="login">
		<form id="login" method="post">

			<h2>Transactions Are</h2>
			<table border="1" id="customers">
				<tr>

					<td>Type</td>
					<td>Amount</td>
					<td>Reason</td>

				</tr>
				<c:forEach items="${transactionobj}" var="user" varStatus="tagStatus">
					<tr>
						<td>${user.type}</td>
						<td>${user.amount}</td>
						<td>${user.reason}</td>
					</tr>
				</c:forEach>
				<tr>
                        <td></td>
                        <td><a href="${pageContext.request.contextPath}/menu">Go to menu</a>
                        </td>
                        <td></td>
			</table>
		</form>
	</div>


</body>
</html>
