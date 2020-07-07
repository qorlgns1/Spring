<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">
</head>
<body>
	<div align="center" class="body">
		<h2>상품 상세 화면</h2>
		<table border="1">
			<tr class="header">
				<td><img src="${pageContext.request.contextPath}/img/${item.pictureurl}" />
				<td>
					<table>
						<tr height="50">
							<td width="80">상품명</td>
							<td width="160">${item.itemname}</td>
						<tr/>
						<tr height="50">
							<td width="80">상품명</td>
							<td width="160">${item.price}원</td>
						<tr/>	
						<tr height="50">
							<td width="80">비고</td>
							<td width="160">${item.description}</td>
						<tr/>
						<tr>
							<td colspan="2" align="center" width="240">
								<a href="${pageContext.request.contextPath}/">■목록으로 돌아가기</a>
							</td>
					</table>
				</td>	
			</tr>
		</table>
	</div>
</body>
</html>