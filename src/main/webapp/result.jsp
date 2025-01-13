<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="outflow.web.OutflowMonitor,java.util.List" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>集計</title>
</head>
<body>
	<h1>集計結果</h1>
	
	
	<table border="1">
		<tr>
			<th>分類名</th>
			<th>合計件数</th>
			<th>合計金額</th>
		</tr>
	
	<%
	//DBより取得した集計結果を受け取り
	List<OutflowMonitor> summarys = (List<OutflowMonitor>)request.getAttribute("summary");
	if(summarys != null){
		OutflowMonitor summary;
		for(int i = 0; i < summarys.size(); i++){
			summary = summarys.get(i);

	%>
	
		<tr>
			<td><%=summary.getCategory()%></td>
			<td><%=summary.getTotalcount()%></td>
			<td><%=summary.getTotalpaid()%></td>
			
		</tr>
	<%	
		}
	}
	 %>
	 
	</table>
	<br>
	<form action="entry_home.jsp" method="post">  <input type="submit" value="ホームへ戻る"/></form><br>
	<form action="SearchServlet" method="post">	  <input type="submit" value="履歴を見る">	</form><br>

</body>
</html>