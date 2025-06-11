<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<jsp:useBean id="m1" class="jstl.MemberBean" scope="page" />
<jsp:setProperty name="m1" property="name" value="이순신" />
<jsp:useBean id="m2" class="java.util.ArrayList" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empty 연산자</title>
</head>
<body>
	<h2>empty 연산자</h2>
		<!-- empty 연산자: 자바의 빈의 속성이 값으로 설정되었는지 or 저장 객체에 값이 존재하는지를 판단-->
	<h1>
		\${empty m1 }: ${empty m1 }<br>	<!-- m1의 name 속성에 값이 설정되어 있으므로 false 반환 -->
		\${not empty m1 }: ${not empty m1 }<br><br>
		
		\${empty m2 }: ${empty m2 }<br>	<!-- ArrayList 객체인 m2는 비어있으므로 true 반환 -->
		\${not empty m2 }: ${not empty m2 }<br><br>
		
		\${empty "hello" }: ${empty "hello" }<br>	<!-- 문자열에 대해 false 반환 -->
		\${empty null }: ${empty null }<br>	<!-- null에 대해 true 반환 -->
		\${empty "" }: ${empty "" }<br>	<!-- 빈 문자열에 대해 true 반환 -->
		
	</h1>
</body>
</html>