<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="outflow.web.OutflowMonitor,java.util.List,java.sql.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>支払い履歴一覧</title>
</head>
<body>
	<h1>履歴一覧</h1>
	<table border="1">
		<tr>
			<th>番号</th>
			<th>購入日</th>
			<th>分類名</th>
			<th>品目名</th>
			<th>金額</th>
			<th>更新</th>
		</tr>
	
	<%
	//DBより取得した支払い履歴をList
	List<OutflowMonitor> paymentLists = (List<OutflowMonitor>) request.getAttribute("paymentList");
	if(paymentLists != null){
		OutflowMonitor paymentList;
		for(int i = 0; i < paymentLists.size(); i++){
			paymentList = paymentLists.get(i);
	%>
	
		<tr>
			<td><%=paymentList.getId()%></td>
			<td><%=paymentList.getPaymentdate()%></td>
			<td><%=paymentList.getCategory()%></td>
			<td><%=paymentList.getNote()%></td>
			<td><%=paymentList.getPaid()%></td>
			<td><a href="DetailServlet?id=<%=paymentList.getId()%>">更新</a></td>
		</tr>
		
	<%
		}
	}
	 %>
	 
	</table>
	
	<br>
	<form action="entry_home.jsp" method="get">
		<input type="submit" value="戻る"/>
	</form>
	
	<br>
	<form action="AllDeleteServlet" method="get">
		<input type="submit" value="一括削除"/>
	</form>
</body>
</html>