<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js">
</script>
<script>
     function validatePwds(){
    		 var newPwd = $('#newPwd').val();
    		 var confirmPwd= $('#confirmPwd').val();
    		 if(newPwd != confirmPwd){
        		 $('#errId').text('New Pasword & Confirm Password should be same');
                  return false;
    		 }
    		 
    		 return true;
    	 }
   
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<font color="red">${errMsg}</font>
        <h1>Unlock Account Here</h1>
<form:form action="unlockAcc" modelAttribute="unlock" method="Post">
<font color="red"><span id="errId"></span></font>
	<table>
		<tr>
				<td>Email :</td>
				<td><form:input path="email" readonly="true"/> </td>
		</tr>
	   <tr>
				<td>Temprary Pwd :</td>
				<td><form:password path="tempPwd"/> </td>
		</tr>
		 <tr>
				<td>New Pwd :</td>
				<td><form:password path="newPwd"/> </td>
		</tr>
	    <tr>
				<td>Confirm Pwd:</td>
				<td><form:password path="confirmPwd"/> </td>
		</tr>
		 <tr>
				
				<td><input type="submit" value="Proceed"
				 onclick="javascript:return validatePwds()"> </td>
		</tr>
		
	</table>
</form:form>
</body>
</html>