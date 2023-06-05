<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>View Accounts</h1>
<table border="1">
	<thead>
		<tr>
			<th>S.No</th>
			<th>FirsT Name</th>
			<th>LasT Name</th>
			<th>Email</th>
			<th>Role</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ListCW}" var="c" varStatus="index">
			<tr>
				<td>${index.count}</td>
				<td>${c.fname}</td>
				<td>${c.lname}</td>
				<td>${c.email}</td>
				<td>${c.role}</td>
<!-- 				<td>
					<a href="editContact?cid=${c.caseWorkerId}">Edit</a>
					<a href="deleteContact?cid=${c.caseWorkerId}" onclick="deleteConfirm()">Delete</a>
				</td> 
-->
				 <td align="center"><a href="editSWAccount?cid=${c.caseWorkerId}">Edit</a> 
				 <c:if test="${c.activeSW =='Y'}">
					     <a href ="activateAccnt">Delete </a>
				
					</c:if>
					<c:if test="${c.activeSW =='N'}">
					     <a href ="deleteAccnt">Activate </a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	
	</tbody>
</table>

</body>
</html>