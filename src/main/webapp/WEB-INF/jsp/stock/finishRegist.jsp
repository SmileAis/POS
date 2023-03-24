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
		<h2> Item Finish Regist </h2>
		<%=code%>(<%= name%>)이(가) 등록되었습니다.
		<br/><br/>
		<button type="button" onclick="location='/menu/stockMenu'">재고 관리로 돌아가기</button> <br/><br/>
		<button type="button" onclick="location='/pos_main'">처음으로 돌아가기</button>
		
		
		
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/stock/regist'">이전으로</button>
	</body>
</html>