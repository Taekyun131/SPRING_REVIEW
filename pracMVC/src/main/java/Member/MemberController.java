package Member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mem.do")
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
		List<MemberVO> membersList=memberDAO.listMembers();
		request.setAttribute("membersList", membersList);
		RequestDispatcher dispatch=request.getRequestDispatcher("/Member/listMembers.jsp");
		dispatch.forward(request, response);
	}
	
	
}
