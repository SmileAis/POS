<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<%
			List codeTop5 = (List)request.getAttribute("codeTop5");
			List countTop5 = (List)request.getAttribute("countTop5");
			List nameTop5 = (List)request.getAttribute("nameTop5");
			List priceTop5 = (List)request.getAttribute("priceTop5");
		%>
		<h2> SellList Earning Top5 </h2>
		<table border=1>
			<th>번호</th>
			<th>제품 코드</th>
			<th>제품 이름</th>
			<th>총 판매 개수</th>
			<th>총 판매 금액</th>
			<%
				for(int i=0; i<5; i++){
					out.println("<tr>");
					out.println("<td>"+(i+1)+"</td>");
					out.println("<td>"+codeTop5.get(i)+"</td>");
					out.println("<td>"+nameTop5.get(i)+"</td>");
					out.println("<td>"+countTop5.get(i)+"</td>");
					out.println("<td>"+priceTop5.get(i)+"</td>");
					out.println("</tr>");
				}
			%>
		</table>
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/menu/statMenu'">이전으로</button>
	</body>
</html>