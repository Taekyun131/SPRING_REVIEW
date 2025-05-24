package servlet._04_memberBinding;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memberBinding")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}

	
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		MemberDAO dao=new MemberDAO();	
		List<MemberVO>memberList=dao.listMembers();
		request.setAttribute("memberList", memberList);		// 조회된 회원정보를 ArrayList 객체에 저장 후 request에 바인딩
		RequestDispatcher dispatch=request.getRequestDispatcher("viewMembers");
		dispatch.forward(request, response);	// 바인딩한 request를 viewMembers 서블릿으로 포워딩
		
//		String command=request.getParameter("command");
//		
//		
//		if(command!=null && command.equals("addMember")) {	// 회원가입창에서 전송된 command가 addMember이면 전송된 값들을 받아옴
//			String _id=request.getParameter("id");
//			String _pwd=request.getParameter("pwd");
//			String _name=request.getParameter("name");
//			String _email=request.getParameter("email");
//			MemberVO vo=new MemberVO();
//			vo.setId(_id);
//			vo.setPwd(_pwd);
//			vo.setName(_name);
//			vo.setEmail(_email);
//			dao.addMember(vo);
//		}else if(command!=null && command.equals("delMember")) {
//			String id=request.getParameter("id");
//			dao.delMember(id);
//		}
//		
//		
//		out.print("<html><body>");
//		out.print("<table border=1><tr align=;center' bgcolor='lightgreen'>");
//		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");
//		
//		for(int i=0;i<list.size();i++) {
//			MemberVO memberVO=(MemberVO) list.get(i);
//			String id=memberVO.getId();
//			String pwd=memberVO.getPwd();
//			String name=memberVO.getName();
//			String email=memberVO.getEmail();
//			Date joinDate=memberVO.getJoinDate();
//			out.print("<tr><td>"+id+"</td><td>"+pwd+"</td><td>"+name+"</td><td>"
//						+email+"</td><td>"+joinDate+"</td><td>"+"<a href='/pracSpring/member4?command=delMember&id="+id+"'>삭제</a></td></tr>");
//			
//		}
//		out.print("</table></body></html>");
//		out.print("<a href='/pracSpring/servletdb/memberForm.html'>새 회원 등록하기</a>"); // 클릭 시 다시 회원가입 창으로 이동
	}
}
