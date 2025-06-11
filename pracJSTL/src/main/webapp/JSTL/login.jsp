<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 창</title>
</head>
<body>
	<form action="result.jsp">
		아이디: <input type="text" size=20><br>
		비밀번호: <input type="password" size=20><br>
		<input type="submit" value="로그인">
		<input type="reset" value="다시 입력">
	</form>
	<br><br>
	<a href="/pracJSTL/JSTL/memberForm.jsp">회원가입 하기1</a><br>
	<a href="<%=request.getContextPath() %>/JSTL/memberForm.jsp">회원가입 하기2</a><br>
	<a href="${pageContext.request.contextPath }/JSTL/memberForm.jsp">회원가입 하기3</a><br>
</body>
</html>