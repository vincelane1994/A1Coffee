<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="/styles.css" />
</head>
<body>
	<div id="bannerimage"></div>
	<ul class="ulnav">
		<c:if test="${ empty user }">
		<li class="linav"><a class="lianav"  href="/login">Log in</a></li>
		<li class="linav"><a class="lianav"  href="/signup">Sign up!</a></li>
		</c:if>
		<c:if test="${ not empty user }">
		<li class="linav">
		<a class="lianav"  href="/logout">Log out</a>
		</li>
		</c:if>
      <li class="linav"><a class="lianav" href="/list">Products</a></li>
    </ul>
   		<c:if test="${ empty user }">
		<h1 class="welcome">If you already have an account<a href="/login"> Log in </a>to A1Coffee if not please, 
		<a href="/signup">Sign up!</a></h1>
		</c:if>
		<c:if test="${ not empty user }">
		<h1 class="welcome">Welcome To A1Coffee ${ user.name }</h1>
		</c:if>

</body>
</html>