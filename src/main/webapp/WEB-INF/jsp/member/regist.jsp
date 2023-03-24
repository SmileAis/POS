<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<h2> Member Regist </h2>
		
		<form action="/member/finishRegist" method="GET">
			<직급>: <input type="text" name="rank"/><br/><br/>
			<이름>: <input type="text" name="name" /><br/><br/>
			<아이디>: <input type="text" name="id" /><br/><br/>
			<비밀번호>: <input type="password" name="pwd"/><br/>
			<input type="submit" value="등록"/>
		</form>




		<br/><br/><br/><br/>
		<button type="button" onclick="location='/menu/memberMenu'">이전으로</button>
	</body>
</html>