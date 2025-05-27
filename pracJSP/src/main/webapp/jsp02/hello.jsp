<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 선언문을 이용해 멤버변수 name과 멤버메서드 getName()을 선언-->
<%!
	String name="듀크";
	public String getName(){return name;}	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언문 연습</title>
</head>
<body>
	<h1>안녕하세요 <%=name %>님</h1><!-- 표현식을 이용해 선언문에서 선언한 name의 값을 출력 -->
</body>
</html>