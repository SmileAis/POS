<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<%
			String name = (String)request.getAttribute("name");
			String id = (String)request.getAttribute("id");
		%>
		<h2> Member Finish Regist </h2>
		<%= name%>님 <%= id%>로 등록되었습니다.
		<br/><br/>
		<button type="button" onclick="location='/menu/memberMenu'">멤버 관리로 돌아가기</button> <br/><br/>
		<button type="button" onclick="location='/pos_main'">처음으로 돌아가기</button>
		
		
		
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/pos_main'">이전으로</button>
	</body>
</html>