<%@ page import="system.pos.item.Item" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
	<%
			String code = (String)request.getAttribute("code");
			String name = (String)request.getAttribute("name");
			int price = (Integer)request.getAttribute("price");
			int stock = (Integer)request.getAttribute("stock");
			String receiveDate = (String)request.getAttribute("receiveDate");
			
	%>
		<h2> Item Finish Search </h2>
		<table border=1>
			<tr>
				<td>코드</td> 
				<td><%=code%></td> 
			</tr>
			<tr>
				<td>제품명</td>
				<td><%=name%></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><%=price%></td>
			</tr>
			<tr>
				<td>재고</td>
				<td><%=stock%></td>
			</tr>
			<tr>
				<td>입고날짜</td>
				<td><%=receiveDate%></td>
			</tr>
		</table>
		<br/>
		<button type="button" onclick="location='/menu/stockMenu'">재고 관리로 돌아가기</button> <br/><br/>
		<button type="button" onclick="location='/pos_main'">처음으로 돌아가기</button>
		
		
		
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/stock/search'">이전으로</button>
	</body>
</html>