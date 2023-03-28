<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head> 
		<meta charset="utf-8"> 
	</head>
	<body>
		<%
			session.invalidate();
		%>
		
		<h2> LOGIN </h2>
		
		<form action="login/logincheck" method="POST">
			<p>id: <input type="text" name="id"/></p>
			<p>password: <input type="password" name="pwd"/></p>
			<input type="submit" value="로그인"/>
		</form>
	</body>
</html>