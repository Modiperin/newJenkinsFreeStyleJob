<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- <link src="SignUp.css"> -->
</head>
<body>
<h1>SignUp</h1>
<h2>${welcome}${firstName} </h2>
<h2 style="color: red;">${error}</h2>
<form enctype="multipart/form-data" method="POST" action="signup">
	First Name:<input type="text" name="firstName"><br>
	Email:<input type="text" name="email"><br>
	Password:<input type="text" name="password"><br>
	Uploads:<input type="file" name="uploads"><br>
	<input type="submit" value="submitForm">
</form>
</body>
<!-- <script type="text/javascript" src="SignUp.js"></script> -->
</html>