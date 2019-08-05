<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/lux/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="/styles.css" />
</head>
<body>
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
      <li class="linav"><a class="lianav" href="/">Home</a></li>
    </ul>
<div class="container">
		<h1>Products</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Price</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${product}">
					<tr>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td>${product.description}</td>
						<c:if test="${ not empty user  && user.admin == 1}">
						<td>
						<a href="/edit?id=${ product.id }" type="button" class="btn btn-primary">Edit</a>
						<a href="/delete?id=${ product.id }" type="button" class="btn btn-danger">Delete</a>
						</td>
						</c:if>
						<c:if test="${ not empty user  && user.admin == 0}">
						<td>
						<a href="/add-to-cart?productid=${ product.id }&userid=${ user.id }" type="button" class="btn btn-primary">Add to cart</a>
						</td>
						</c:if>
					</tr>
				</c:forEach>
				<c:if test="${ not empty user && user.admin == 1}">
				<tr><td><a  href="/add" type="button" class="btn btn-success">Add</a></td></tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>