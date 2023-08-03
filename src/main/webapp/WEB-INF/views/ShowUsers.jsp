<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>you are now currently logged as ${sessionScope.name}</h1>
	<table border="1">
	<tr>
		<th>UserId</th>
		<th>Name</th>
		<th>Email</th>
		<th>ProfilePic</th>
		<th></th>
	</tr>
	<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.userId}</td>
				<td>${user.firstName }</td>
				<td>${user.email }</td>
				<td><embed src="${user.fileName}" width="150px" height="150px"/></td>
				<td>
					<a href="deleteUser?userId=${user.userId }"><button type="button">Delete</button></a>
				</td>
			</tr>
	</c:forEach>
	</table>	
	<a href="/logout">logout</a>
</body>
</html>