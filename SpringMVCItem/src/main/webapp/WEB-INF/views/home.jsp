<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jsp를 이용해서 데이터를 출력할 때는 이 코드는 거의 필수 -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ITEM</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
	<div align="center" class="body">
		<h2>상품 목록</h2>
		<table border ="1">
			<tr class="header">
				<th width="80">상품 ID</th>
				<th width="300">상품 이름</th>
				<th width="100">상품 가격</th>
			</tr>
			<c:forEach var="item" items="${list}">
				<tr class="record">
					<td width="80">${item.itemid}</td>
					<td width="80"><a href="detail/${item.itemid}">${item.itemname}</a></td>
					<td width="80" align="right">${item.price}원</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>