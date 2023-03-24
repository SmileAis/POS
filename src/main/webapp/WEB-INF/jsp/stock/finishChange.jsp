<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<%
			String code = (String)request.getAttribute("code");
			String name = (String)request.getAttribute("name");
			int stock = (Integer)request.getAttribute("stock");
		%>
		<h2> Item Finish Receive </h2>
		<%= code%> (<%= name%>):<%=stock%>개로 수정완료
		<br/><br/>
		<button type="button" onclick="location='/menu/stockMenu'">재고 관리로 돌아가기</button> <br/><br/>
		<button type="button" onclick="location='/pos_main'">처음으로 돌아가기</button>
		
		
		
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/stock/change'">이전으로</button>
	</body>
</html>