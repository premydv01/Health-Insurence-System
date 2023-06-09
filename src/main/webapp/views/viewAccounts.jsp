<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
	<title>view Account</title>
<script >
$(document).ready(function() {
	
	$('#roleId').on('change', function() {
		  this.form.submit();
		});
	
	$('#contactTbl').DataTable({
		"pagingType" : "full_numbers"
	});
});
   function deleteConfirm() {
	   return confirm("Are you sure,You want to delete?");
	
}
   function activateConfirm() {
	   return confirm("Are you sure,You want to Activate?");
	
}

</script>
</head>
<body>
<h1>View Accounts</h1>
<form action="getCaseWorkers">
	<table>
		<tr>
			<td>Select Role :</td>
			<td>
			   <select name="role" id="roleId">
			   		<option value="">-select-</option>
			   		<option value="CaseWorker">Case Worker</option>
			   		<option value="Admin">Admin</option>
			   </select>
			</td>
		</tr>
	</table>
</form>
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

				 <td align="center"><a href="editSWAccount?cid=${c.caseWorkerId}">Edit</a> 
				 <c:if test="${c.activeSW =='Y'}">
					     <a href ="deleteAccount?cwid=${c.caseWorkerId}" onclick="deleteConfirm()">Delete </a>
				
					</c:if>
					<c:if test="${c.activeSW =='N'}">
					     <a href ="activateAccount?cwid=${c.caseWorkerId}" onclick="activateConfirm()">Activate </a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	
	</tbody>
</table>

</body>
</html>