<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdraw</title>
</head>
<body>
 <form:form id="withdrawForm" modelAttribute="withdrawtransaction" action="withdrawtransactionProcess" method="post">

                <table align="center">
                    
                    <tr>
                        <td>
                            <form:label path="type">type</form:label>
                        </td>
                        <td>
                            <form:input path="type" name="type" id="type" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="amount">amount</form:label>
                        </td>
                        <td>
                            <form:input path="amount" name="amount" id="amount" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="reason">reason</form:label>
                        </td>
                        <td>
                            <form:input path="reason" name="reason" id="reason" />
                        </td>
                    </tr>
                  
                    <tr>
                        <td></td>
                        <td>
                            <form:button id="deposit" name="deposit">Do Withdraw</form:button>
                        </td>
                    </tr>
                    <tr></tr>
                   <tr>
                        <td></td>
                        <td><a href="${pageContext.request.contextPath}/menu">Go to menu</a>
                        </td>
                    </tr> 
                </table>
            </form:form>
            <table align="center">
                <tr>
                    <td style="font-style: italic; color: red;">${message}</td>
                </tr>
            </table>

        </body>

</body>
</html>