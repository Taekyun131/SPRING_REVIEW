<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 출력창</title>
</head>
<body>
	<h1>결과 출력</h1>
	<!-- getParameter()메서드를 이용해 입력 정보를 가져옴 -->
	<%
		request.setCharacterEncoding("utf-8");
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		%>
	<!-- ID와 비밀번호를 표현식으로 출력 -->
	<h1>아이디: <%=user_id %></h1>
	<h1>비밀번호: <%=user_pw %></h1>
</body>
</html>