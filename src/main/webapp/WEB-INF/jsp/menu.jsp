<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>menu</title>
</head>
<body>
	<form:form id="loginForm" modelAttribute="user" action="loginProcess"
		method="post">
		<table align="center">
			<tr>
			<td>Menu</td>
			</tr>
			<tr>
				<td><a href="transaction">Show Transactions</a></td>
			</tr>
			<tr>
				<td><a href="withdraw">Withdraw</a></td>
			</tr>
			<tr>
				<td><a href="deposit">Deposit</a></td>
			</tr>
			<tr>
				<td><a href="showbalance">Show balance</a></td>
			</tr>
			<tr>
				<td><a href="logout">logout</a></td>
			</tr>
		</table>
	</form:form>
	 <table align="center">
                <tr>
                    <td style="font-style: italic; color: red;">${message}</td>
                </tr>
            </table>
</body>
</html>