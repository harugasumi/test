<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="outflow.web.OutflowMonitor" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
</head>
<body>
	<h1>更新しますか？</h1>
	
	<!-- 今日の日付が自動で表示 -->
	<h2>日付</h2>
	<%
	OutflowMonitor dto = (OutflowMonitor)request.getAttribute("SelectedId");
	pageContext.setAttribute("dto", dto);
	%>
	
	<form action="DeleteServlet" method="post">
		<input type="hidden" name="id" value="${dto.id}">
		<input type="submit" value="削除する">
	</form>
	
	<br>
	
	<form action="RegisterServlet" method="post">
	<table>
		<tr>
			<th>番号</th>
			<td>${ dto.id }</td>
		</tr>
		
		<tr>
			<th>購入日</th>
			<!-- typeをdateとして、カレンダーからの選択入力としている -->
			<td><input type="date" name="paymetnDate" value="${dto.outputPaymentdate}"></td>
		</tr>
		<tr>
			<th>分類</th>
			<td>
				<input type="radio" name="categorynum" value="1"<% if(dto.getCategorynum()==1){%> checked <% } %>>食品
				<input type="radio" name="categorynum" value="2"<% if(dto.getCategorynum()==2){%> checked <% } %>>消耗品
				<input type="radio" name="categorynum" value="3"<% if(dto.getCategorynum()==3){%> checked <% } %>>通信
				<input type="radio" name="categorynum" value="4"<% if(dto.getCategorynum()==4){%> checked <% } %>>水道光熱
				<input type="radio" name="categorynum" value="5"<% if(dto.getCategorynum()==5){%> checked <% } %>>家賃
				<input type="radio" name="categorynum" value="6"<% if(dto.getCategorynum()==6){%> checked <% } %>>保険
				<input type="radio" name="categorynum" value="7"<% if(dto.getCategorynum()==7){%> checked <% } %>>その他
			</td>
		</tr>
		
		<tr>
			<th>内容</th>
			<td><textarea name="note" rows="4" cols="40">${dto.note}</textarea></td>
		</tr>
		
		<tr>
			<th>金額</th>
			<!-- 正の整数(1~100万)のみ受け付ける -->
			<td><input type="number" name="paid" value="${dto.paid}" step="1" min="1" max="1000000"></td>
		</tr>
	</table>
		<br>
		<input type="hidden" name="id" value="${dto.id}">
		<input type="submit" value="更新する">
	</form>
	
	<br>
	
	<form action="SearchServlet" method="post">
		<input type="submit" value="履歴を見る">
	</form>
	
	<br>
	
	<form action="CategorySummaryServlet" method="post">
		<input type="submit" value="集計を見る">
	</form>
	
	
	
</body>
</html>