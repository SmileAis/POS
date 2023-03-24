<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="system.pos.member.Member" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<%
			Member member = (Member)session.getAttribute("member");
			if(!member.getRank().equals("점장") && !member.getRank().equals("매니저")){
		%>
				<script>
					alert("직원은 접근 불가능합니다.");
					history.back();
				</script>	
		<%	
			}
		%>
		<h2> Stock Delete </h2>
	
		<form action="/stock/finishDelete" method="GET">
			<코드>: <input type="text" name="code" /><br/><br/>
			<input type="submit" value="삭제"/>
		</form>
		

		<br/><br/><br/><br/>
		<button type="button" onclick="location='/menu/stockMenu'">이전으로</button>
	</body>
</html>