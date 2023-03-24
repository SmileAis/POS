<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<h2> Member Delete </h2>
	
		<form action="/member/finishDelete" method="GET">
			<아이디>: <input type="text" name="id" /><br/><br/>
			<input type="submit" value="삭제"/>
		</form>
		

		<br/><br/><br/><br/>
		<button type="button" onclick="location='/menu/memberMenu'">이전으로</button>
	</body>
</html>