<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String name=(String) request.getAttribute("name");
	String address=(String) request.getAttribute("address");
	%><!-- 포둬딩된 request 객체에서 getAttribute()를 이용해 정보를 가져옴 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이름은 <%=name %>입니다.</h1>
	<h1>주소는 <%=address %>입니다.</h1>
</body>
</html>