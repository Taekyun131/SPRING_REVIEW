<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, jstl2.*"
    isELIgnored="false"%>

<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="m" class="jstl2.MemberBean"/>
<jsp:setProperty name="m" property="*"/>
<%
	MemberDAO memDAO=new MemberDAO();
	memDAO.addMember(m);
	List membersList=memDAO.listMembers();
	request.setAttribute("membersList", membersList);
	
%>
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="memberList.jsp"/>
</body>
</html>