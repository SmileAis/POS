<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="system.pos.member.Member" %>
<!DOCTYPE html>
<html>
	<head> <meta charset="utf-8"> </head>
	<body>
		<h2> POS_main </h2>
		<button type="button" onclick="location='/menu/sell'">판매</button>
		<button type="submit" onclick="location='/menu/stockMenu'">재고관리</button>
		<button type="button" onclick="location='/menu/statMenu'">통계</button>
		<button type="submit" onclick="location='/menu/memberMenu'">회원관리</button>
		
		<%
			Object object = session.getAttribute("member");
			String id;
			Member member;
			
			if(object == null){
				id = (String)request.getAttribute("id");
				member = (Member)request.getAttribute("member");
				session.setAttribute("id", id);
				session.setAttribute("member", member);
			}
			else 
				member = (Member)session.getAttribute("member");	
		%>
		<p>${member.rank}: ${member.name}님 <p/>


		<br/><br/><br/><br/>
		<button type="submit" onclick="location='/'">로그아웃</button>
	</body>
</html>