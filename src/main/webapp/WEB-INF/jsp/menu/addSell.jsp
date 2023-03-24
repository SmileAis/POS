<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="system.pos.item.Item" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
	<head> <meta charset="utf-8"> </head>
	<body>
	<% 
		List sellList = (List)request.getAttribute("sellList"); 
		List countList = (List)request.getAttribute("countList");
		int totalSum;
		
		Object object = session.getAttribute("totalSum");
		if(object == null){
				totalSum = 0;
				session.setAttribute("totalSum", totalSum);
		}
		else
			totalSum = (Integer)session.getAttribute("totalSum");
		
		String blank = "&nbsp&nbsp&nbsp&nbsp";
	%>
	
		<h2> POS - Sell </h2>
		
		<form action="/menu/addSell" method="GET">
			<코드>: <input type="text" name="code"/><br/><br/>
			<제품 개수>: <input type="number" name="count"/><br/><br/>
			<input type="submit" value="추가"/>
		</form>
		

		
		<%
			out.println("코드" + blank + "상품명" + blank + "가격" + blank +"개수" + blank +"총액" + "</br>");
			out.println("------------------------------------------</br>");
			for(int i=0; i<sellList.size(); i++){
				Item item = (Item)sellList.get(i);
				String code = item.getCode();
				String name = item.getName();
				int price = item.getPrice();
				int count = (Integer)countList.get(i);
				int smallSum = price * count;
				totalSum += smallSum;
					
				out.println(code+ blank + name+ blank + price+ blank + count+ blank + smallSum + "<br/>");	
			}
			out.println("</br></br>");
			out.println("------------------------------------------</br>");
			out.println("총액" + blank + blank + totalSum + "</br>");
		%>
		<br/>
		<button type="button" onclick="location='/menu/finishSell'">구입 확정</button>		
		<br/>
				
				
		<br/><br/><br/><br/>
		<button type="button" onclick="location='/pos_main'">이전으로</button>
	</body>
</html>