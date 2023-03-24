<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head> <meta charset="utf-8"> </head>
	<body>
		<h2> POS - stat </h2>
		<button type="button" onclick="location='/stat/all'">전체 매출</button>
		<button type="button" onclick="location='/stat/today'">당일 매출</button>
		<button type="button" onclick="location='/stat/week'">일주일 매출</button>
		<button type="button" onclick="location='/stat/month'">한달 매출</button>	
		<button type="button" onclick="location='/stat/quantity5'">판매 개수 Top5</button>
		<button type="button" onclick="location='/stat/earning5'">판매액 Top5</button>
		
		
		
		
		
		
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/pos_main'">이전으로</button>
	</body>
</html>