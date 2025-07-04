package Member2;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet{
	private static final long serialVersionUID=1L;
	MemberDAO memberDAO;
	
	public void init() throws ServletException{
		memberDAO=new MemberDAO();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String nextPage=null;
		String action=request.getPathInfo();	// URL에서 요청명을 가져옴
		System.out.println("action: "+action);
		
		if(action==null || action.equals("/listMembers.do")) {	// 최초요청이거나 action 값이 /memberList.do면 회원 목록 출력
			List<MemberVO> membersList=memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage="/Member/listMembers.jsp";
		}else if(action.equals("/addMember.do")) {		// action 값이 /addMember.do면 전송된 회원 정보를 가져와서 테이블에 추가
			String id=request.getParameter("id");
			String pwd=request.getParameter("pwd");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			MemberVO memberVO=new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			nextPage="/member/listMembers.do";	// 회원등록 이후 다시 회원목록 출력
		}else if(action.equals("/memberForm.do")) {		// action값이 /memberForm.do면 회원가입창을 화면에 출력
			nextPage="/Member/memberForm.jsp";	
		}else {		// 그 외 다른 action값은 회원목록을 출력
			List<MemberVO>membersList=memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage="/Member/listMembers.jsp";
		}
		RequestDispatcher dispatch=request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
	
	
}
