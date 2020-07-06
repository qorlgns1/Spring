<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- action은 생략하면 이 페이지에 오게된 요청 : form
	method는 생략하면 get
	enctype은 file이 있을 때만 multipart/form-data를 설정 -->
	<form id="paramformm" method="post">
		이름:<input type="text" name="name" /><br/>
		전화번호:<input type="text" name="phone" id="phone" /><br/>
		나이:<input type="text" name="age" id="age" /><br/>
		<input type="submit" value="전송"/>
		<input type= "button" value="전송" id="ajaxbtn"/>
	</form>
</body>
</html>