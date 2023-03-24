<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<h2> Item List </h2>
		
		<table border=1>
			<th>제품코드</th> 
			<th>제품명</th>
			<th>제품가격</th>
			<th>개수</th>
			<th>입고날짜</th>
				<c:forEach var="items" items="${items}" varStatus="status">
				<tr>
					<td>${items.code}</td> 
					<td>${items.name}</td>
					<td>${items.price}</td>
					<td>${items.stock}</td>
					<td>${items.receiveDate}</td>
				</tr>
				</c:forEach>

		</table>
		
		



		<br/><br/><br/><br/>
		<button type="button" onclick="location='/menu/stockMenu'">이전으로</button>
	</body>
</html>