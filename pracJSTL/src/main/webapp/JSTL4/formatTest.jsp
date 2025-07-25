<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포매팅 태그 라이브러리 예제</title>
</head>
<body>
	<h2>fmt의 number 태그를 이용한 숫자 포매팅 예제</h2>
	<c:set var="price" value="100000000"/>
	
	통화로 표현 시: <fmt:formatNumber type="currency" value="${price }" groupingUsed="true" /><br>
	퍼센트로 표현 시: <fmt:formatNumber type="percent" value="${price }" groupingUsed="false"/><br>
	일반 숫자로 표현 시: ${price }<br>
	
	<h2>formatDate 예제</h2>
	<c:set var="now" value="<%=new Date() %>"/>
	<fmt:formatDate value="${now }" type="date" dateStyle="full"/><br>
	<fmt:formatDate value="${now }" type="date" dateStyle="short"/><br>
	<fmt:formatDate value="${now }" type="time"/><br>
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br>
	<fmt:formatDate value="${now }" pattern="YYYY-MM-dd :hh:mm:ss"/><br>
	
	<br><br>
	한국 현재 시간: 
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br><br>
	
	<fmt:timeZone value="America/New York">
	뉴욕 현재 시간:
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full" /><br>
	</fmt:timeZone>
</body>
</html>