<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>File Uploading</h1>
<form enctype="multipart/form-data" method="POST" action="savefile">
	Uploads:<input type="file" name="uploads">
	<br>
	<button type="submit">Add File</button>
</form>
</body>
</html>