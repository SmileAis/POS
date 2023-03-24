<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<h2> SellList All </h2>
		<table border=1>
			<th>번호</th>
			<th>제품코드</th>
			<th>총 판매 개수</th>
			<th>총 판매 가격</th>
			<th>판매날짜</th>
			<c:forEach var="sellList" items="${sellList}" varStatus="status">
				<tr>
					<td>${sellList.num}</td>
					<td>${sellList.code}</td>
					<td>${sellList.count}</td>
					<td>${sellList.totalPrice}</td>
					<td>${sellList.saleDate}</td>
				</tr>
			</c:forEach>
		</table>
		
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/menu/statMenu'">이전으로</button>
	</body>
</html>