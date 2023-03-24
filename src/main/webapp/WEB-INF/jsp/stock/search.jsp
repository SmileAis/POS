<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<h2> Item Search </h2>
		<form action="/stock/finishSearch" method="GET">
			<코드>: <input type="text" name="code"/><br/><br/>
			<input type="submit" value="검색"/>
		</form>




		<br/><br/><br/><br/>
		<button type="button" onclick="location='/menu/stockMenu'">이전으로</button>
	</body>
</html>