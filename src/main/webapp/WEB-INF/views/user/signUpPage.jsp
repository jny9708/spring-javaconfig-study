<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sign up</title>
</head>
<body>
	<c:url value="/signup" var="signupUrl"/>
	<form action="${signupUrl}" method="post">
		<p>
        <label for="username">Username(ID)</label>
        <input type="text" id="username" name="username"/>  
    	</p>
	    <p>
	        <label for="password">Password</label>
	        <input type="password" id="password" name="password"/>  
	    </p>
	    <p>
	        <label for="name">Name</label>
	        <input type="text" id="name" name="name"/>  
	    </p>
	    <input type="hidden"                        
        name="${_csrf.parameterName}"
        value="${_csrf.token}"/>
	    <button type="submit" class="btn">sign up</button>
	</form>
</body>
</html>