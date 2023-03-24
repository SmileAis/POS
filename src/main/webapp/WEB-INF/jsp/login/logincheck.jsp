<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head> <meta charset="utf-8"> </head>
	<body>
		<h2> LOGIN CHECK </h2>
		<%
			String id = (String)request.getAttribute("id");

			if(id == null)
				out.println("<p> 아이디/비밀번호 잘못입력 </p>");
		%>
		<button type="button" onclick="location='/'">돌아가기</button>
		
	</body>
</html>