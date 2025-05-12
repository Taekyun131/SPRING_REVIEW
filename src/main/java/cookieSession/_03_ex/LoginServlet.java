package cookieSession._03_ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/smLogin")
public class LoginServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandel(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandel(request, response);
	}
	
	private void doHandel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		// 로그인 창에서 전송된 ID와 비밀번호를 가져옴
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
		// MemberVO 객체를 생성하고 속성에 ID와 비밀번호를 설정
		MemberVO memberVO=new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pw);
		
		MemberDAO dao=new MemberDAO();
		boolean result=dao.isExisted(memberVO);	// MemberDAO의 isExisted()메서드를 호출하면서 memberVO를 전달
		
		if(result) {
			HttpSession session=request.getSession();
			session.setAttribute("isLogon", true);	// 조회한 결과가 true이면 isLogOn속석을 true로 세션에 저장
			session.setAttribute("login.id", user_id);	// ID와 비밀번호를 세션에 저장
			session.setAttribute("login.pwd", user_pw);
			out.print("<html><body>");
			out.print("안녕하세요 "+user_id+" 님!!!<br>");
			out.print("<a href='showMember'>회원정보 보기</a>");
			out.print("</body></html>");
		}else {
			out.print("<html><body>");
			out.print("<a href='cookie/login3.html'>다시 로그인하기</a>");
			out.print("</body></html>");
		}
	}
}
