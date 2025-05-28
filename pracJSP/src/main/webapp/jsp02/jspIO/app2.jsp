<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name=(String) session.getAttribute("name");
	String address=(String) application.getAttribute("address");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 스코프 테스트2</title>
</head>
<body>
	<h1>이름은 <%=name %>입니다.</h1>
	<h1>주소는 <%=address %>입니다.</h1>
	<!-- 같은 브라우저에서 app1.jsp 요청 후 app2.jsp 요청 시 이름과 주소 모두 접근 가능
		다른 브라우저에서 app2.jsp 요청 시 주소만 접근가능
		(다른 브라우저에서는 session 스코프가 적용되지 않음)-->
</body>
</html>