<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>今日、なに買った？</title>
</head>
<body>
	<h1>今日、なに買った？</h1>
	
	<!-- 今日の日付が自動で表示 -->
	<!-- RegisterServletから戻ってきたら、「ｉｄ番に登録しました」と表示する -->
	<h2>日付</h2>
	
	
	<form action="RegisterServlet" method="post">
	<table>	
		
		<tr>
			<th>購入日</th>
			<!-- typeをdateとして、カレンダーからの選択入力としている -->
			<td><input type="date" name="paymetnDate"></td>
		</tr>
		<tr>
			<th>分類</th>
			<td>
				<input type="radio" name="categorynum" value="1" checked>食品
				<input type="radio" name="categorynum" value="2">消耗品
				<input type="radio" name="categorynum" value="3">通信
				<input type="radio" name="categorynum" value="4">水道光熱
				<input type="radio" name="categorynum" value="5">家賃
				<input type="radio" name="categorynum" value="6">保険
				<input type="radio" name="categorynum" value="7">その他
			</td>
		</tr>
		
		<tr>
			<th>内容</th>
			<td><textarea name="note" rows="4" cols="40"></textarea></td>
		</tr>
		
		<tr>
			<th>金額</th>
			<!-- 正の整数(1~100万)のみ受け付ける -->
			<td><input type="number" name="paid" step="1" min="1" max="1000000"></td>
		</tr>
		
		<tr>
			<th></th>
			<td>
				<input type="submit" value="登録">
				<input type="hidden" name="id" value="0"><!-- 新規登録のｉｄは０ -->
			</td>
		</tr>
	</table>
	</form>
	
	<br>
	
	<form action="SearchServlet" method="post">
		<input type="submit" value="履歴を見る">
	</form>
	
	<br>
	
	<form action="setting.jsp" method="post">
		<input type="submit" value="集計を見る">
	</form>
	
	
	
</body>
</html>