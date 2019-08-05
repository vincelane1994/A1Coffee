<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/lux/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="/styles.css" />
</head>
<body>
	<div class="container">
		<h2>Edit ${ product.name} Coffee</h2>
		<form action="/edit" method="post">
		<input type="text" name="id" value="${ product.id }" hidden="true"/>
		<table class="table">
			<tr>
				<th scope="row">Name</th>
				<td><input type="text" name="name" value="${ product.name }" autofocus/></td>
			</tr>
			<tr>
				<th scope="row">Price</th>
				<td><input type="number" name="price" value="${ product.price }" step="0.01" min="0"/></td>
			</tr>
			<tr>
				<th scope="row">Description</th>
				<td>
					<input type="text" name="description" value="${ product.description }"/>
				</td>
			</tr>
		</table>
	<button type="submit" class="btn btn-primary">Submit</button>
		<a class="btn btn-danger" href="/list">Cancel</a>
		</form>
	</div>
</body>
</html>