package cookieSession._02_session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessLogin2")
// 쿠키를 사용할 수 없을 경우 encodeURL() 메세ㄷ를 이용해 세션 기능 사용가능
public class SessionTest5 extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
	
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
		
		if(session.isNew()) {	
			if(user_id!=null) {	
				session.setAttribute("user_id", user_id);
				String url=response.encodeURL("sessLogin2"); // 변수에 encodeURL()을 이용해 응답 시 미리 jsessionId를 저장
				out.print("<a href="+url+">로그인 상태 확인</a>"); // 로그인 상태 확인 클릭 시 jsessionId를 서블릿으로 다시 전송
			}else {
				out.println("<a href='cookie/login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate();
			}
		}else {	
			user_id=(String) session.getAttribute("user_id");
			if(user_id!=null && user_id.length()!=0) {
				out.print("안녕하세요 "+user_id+"님!!!");
			}else {
				out.print("<a href='cookie/login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate();
			}
		}
		
		
		

	}
	
}
