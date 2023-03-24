<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head> <meta charset="EUC-KR"> </head>
	<body>
		<h2> Member List </h2>
		
		<table border=1>
			<th>직급</th>
			<th>이름</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>등록 날짜</th>
			<c:forEach var="member" items="${members}" varStatus="status">
				<tr></tr>
					<td>${member.rank}</td>
					<td>${member.name}</td>
					<td>${member.id}</td>
					<td>${member.password}</td>
					<td>${member.regDate}</td>
				<tr></tr>
			</c:forEach>
		</table>
		
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/menu/memberMenu'">이전으로</button>
	</body>
</html>