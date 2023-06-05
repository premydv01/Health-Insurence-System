<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js">
</script>

<script>
     $(document).ready(function(e){
    	 $("#email").blur(function(event){
    		$("#dupEmail").html("");
    		var emailId =$("#email").val();
    		$.ajax({
    			url : 'validateEmail?email=' + emailId,
    			success : function(response){
    				if(response=='Duplicate'){
    					$("#dupEmail").html("Email alredy registered");
    					$("#email").focus();
    				}
    			}		
    		});
    	 });
     });
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Create Account</h1>

<form:form action="saveCaseWorker" modelAttribute="caseWorker" method="Post">
<table>
		<tr>
		<form:hidden path="caseWorkerId"/>
			<td>First Name :</td>
		    <td><form:input path="fname"/></td>
		</tr>
		<tr>
			<td>Last Name :</td>
		    <td><form:input path="lname"/></td>
		</tr>
        <tr>
			<td>Email :</td>
		    <td><form:input path="email"/>
		      <font color="red">
 		        <div id="dupEmail"></div>
 		    </font>
		    </td>
		</tr>
		<tr>
			<td>Data Of Birth :</td>
		    <td><form:input path="dob"/></td>
		</tr>
		<tr>
			<td>Gender :</td>
          <td><form:radiobutton path="gender" value="M"/>Male
              <form:radiobutton path="gender" value="F"/>Female
          </td>
		</tr>
		<tr>
			<td>SSN No :</td>
		    <td><form:input path="ssnNo"/></td>
		</tr>
		<tr>
			<td>Phone Number :</td>
		    <td><form:input path="contatNo"/></td>
		</tr>
		<tr>
			<td>Role :</td>
		    <td><form:select path="role">
		         <form:option value="">-select-</form:option>
              <form:options items="${roleMap}" />
		       </form:select></td>
		</tr>
		
		  <tr>
         <td><input type="reset" value="Reset"></td>
         <td><input type="submit" value="Create">
         </tr>
   </table>
   <a href="getCaseWorkers">View Accounts</a>
</form:form>

</body>
</html>