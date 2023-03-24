<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<h2> Item Receive </h2>
		<form action="/stock/finishReceive" method="GET">
			<코드>: <input type="text" name="code"/><br/><br/>
			<제품 개수>: <input type="number" name="stock"/><br/>
			<input type="submit" value="입고 완료"/>
		</form>




		<br/><br/><br/><br/>
		<button type="button" onclick="location='/menu/stockMenu'">이전으로</button>
	</body>
</html>