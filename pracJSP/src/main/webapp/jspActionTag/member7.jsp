<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, jsp.ActionTag.*"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="m" class="jsp.ActionTag.MemberBean" scope="page" />
<!-- useBean액션태그로 id가 m인 MemberBean 객체 생성 -->

<jsp:setProperty name="m" property="*" />
<!-- 회원가입 창에서 전송된 동일한 이름의 매개변수에 해당되는 useBean속성에 전송된 값을 설정 -->
<%
	MemberDAO memberDAO=new MemberDAO();
	memberDAO.addMember(m);
	List memberList=memberDAO.listMembers();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록 창</title>
</head>
<body>
<table align="center" width="100%">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>
		</tr>
	
		<tr align="center">
			<td>
				<jsp:getProperty name="m" property="id" />
			</td>
			<td>
				<jsp:getProperty name="m" property="pwd"/>
			</td>
			<td>
				<jsp:getProperty name="m" property="name" />
			</td>
			<td>
				<jsp:getProperty name="m" property="email" />
			</td>
		</tr>
		<tr height="1" bgcolor="#99ccff">
			<td colspan="5"></td>
		</tr> 
	</table>
</body>
</html>