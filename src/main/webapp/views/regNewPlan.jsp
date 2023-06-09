<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<font color="red"><h1>${msg}</h1></font>
<h1>Register New Plan</h1>
<form:form action="addPlan" modelAttribute="plan" method="Post">
	<table>
	       <tr>
	       <form:hidden path="planId"/>
	       		<td>Plan Name :</td>
	       		<td><form:input path="planName"/> </td>
	       </tr>
	       <tr>
	       		<td>Plan Description :</td>
	       		<td><form:input path="planDesc"/> </td>
	       </tr>
	       <tr>
	       		<td>Plan Start Date</td>
	       		<td><form:input path="startDate"/> </td>
	       </tr>
	       <tr>
	       		<td>Plan End Date</td>
	       		<td><form:input path="endDate"/> </td>
	       </tr>
	       <tr>
	       		<td><input type="submit" value="Register"> </td>
	       		<td></td>
	       </tr>
	
	</table>

</form:form>
</body>
</html>