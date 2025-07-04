<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, jsp.ActionTag.*"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="m" class="jsp.ActionTag.MemberBean" scope="page" />
<!-- useBean액션태그로 id가 m인 MemberBean 객체 생성 -->

<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	
	// MemberBean객체를 생성하지 않고 userBean에 전송된 회원정보를 설정 
	m.setId(id);
	m.setPwd(pwd);
	m.setName(name);
	m.setEmail(email);
	
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
			<td width="5%">가입일</td>
		</tr>
		<%
			if(memberList.size()==0){
		%>
		<tr>
			<td colspan="5">
				<p align="center"><b><span style="font-size:9pt;">등록된 회원이 없습니다.</span></b></p>
			</td>
		</tr>
		<%
			}else{
				for(int i=0;i<memberList.size();i++){
					MemberBean bean=(MemberBean) memberList.get(i);
		%>
		<tr align="center">
			<td>
				<%=bean.getId() %>
			</td>
			<td>
				<%=bean.getPwd() %>
			</td>
			<td>
				<%=bean.getName() %>
			</td>
			<td>
				<%=bean.getEmail() %>
			</td>
			<td>
				<%=bean.getJoinDate() %>
			</td>
		</tr>
		<%
				}	// end for
			}	// end if
		%>
		<tr height="1" bgcolor="#99ccff">
			<td colspan="5"></td>
		</tr> 
	</table>
</body>
</html>