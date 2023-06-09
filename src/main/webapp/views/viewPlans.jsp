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
	<title>DataTables examples - Examples index</title>
<script >
$(document).ready(function() {
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
<h1 >View Plans</h1>
<table border="1">
   <thead>
      <tr>
      	<th>S.NO</th>
      	<th>Plan Name</th>
      	<th>Plan Description</th>
      	<th>Plan Start Date</th>
      	<th>Plan End Date</th>
      	<th>Action</th>
      </tr>
   </thead>
   <tbody>
   <c:forEach items="${plans}" var="plan" varStatus="index" >
     <tr>
     	<td>${index.count}</td>
     	<td>${plan.planName}</td>
     	<td>${plan.planDesc}</td>
     	<td>${plan.startDate}</td>
     	<td>${plan.endDate}</td>
     		 <td align="center"><a href="editSWPlan?pid=${plan.planId}">Edit</a> 
				 <c:if test="${plan.activeSW =='Y'}">
					     <a href ="deletePlan?pid=${plan.planId}" onclick="deleteConfirm()">Delete </a>
				
					</c:if>
					<c:if test="${plan.activeSW =='N'}">
					     <a href ="activatePlan?pid=${plan.planId}" onclick="activateConfirm()">Activate </a>
					</c:if>
				</td>
     </tr>
   
   </c:forEach>
   
   </tbody>

</table>

</body>
</html>