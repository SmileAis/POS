<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<%
			String code = (String)request.getAttribute("code");
			String name = (String)request.getAttribute("name");
		%>
		<h2> Item Finish Delete </h2>
		<%=code%>(<%= name%>)가 삭제되었습니다.
		<br/><br/>
		<button type="button" onclick="location='/menu/stockMenu'">재고 관리로 돌아가기</button> <br/><br/>
		<button type="button" onclick="location='/pos_main'">처음으로 돌아가기</button>
		
		
		
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/stock/delete'">이전으로</button>
	</body>
</html>