<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*, jstl.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	List membersList=new ArrayList();
	MemberBean m1=new MemberBean("son", "1234", "손흥민", "son@test.com");
	MemberBean m2=new MemberBean("ki", "4321", "기성용", "ki@test.com");
	MemberBean m3=new MemberBean("park", "1212", "박지성", "park@test.com");
	membersList.add(m1);
	membersList.add(m2);
	membersList.add(m3);
%>
<c:set var="membersList" value="<%=membersList %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 출력창</title>
</head>
<body>
	<table align="center" border="1">
		<tr align="center" bgcolor="lightgreen" >
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
		</tr>
		<c:forEach var="i" begin="0" end="2" step="1">
			<tr align="center">
				<td>${membersList[i].id }</td>
				<td>${membersList[i].pwd }</td>
				<td>${membersList[i].name }</td>
				<td>${membersList[i].email }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>