<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="system.pos.member.Member" %>
<!DOCTYPE html>
<html>
	<head> <meta charset="utf-8"> </head>
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
		<h2> Member Menu </h2>
		<button type="button" onclick="location='/member/view'">계정 확인</button>
		<button type="button" onclick="location='/member/regist'">계정 등록</button>
		<button type="button" onclick="location='/member/delete'">계정 삭제</button>
		
		<p>${member.rank}: ${member.name}님 <p/>
		
		
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/pos_main'">이전으로</button>
	</body>
</html>