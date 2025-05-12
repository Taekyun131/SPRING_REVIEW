package cookieSession._03_ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/showMember")
public class ShowMember extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String id="", pwd="";
		Boolean isLogon=false;
		
		HttpSession session=request.getSession(false);	// 세션이 존재하면 세션을 반환하고 없으면 null을 반환
		if(session != null) {	// 세션 생성 여부 확인
			isLogon=(Boolean) session.getAttribute("isLogon");
			if(isLogon==true) {	// isLogon이 true면 로그인 상태이므로 회원정보를 브라우저에 표시
				id=(String)session.getAttribute("login.id");
				pwd=(String)session.getAttribute("login.pwd");
				out.print("<html><body>");
				out.print("아이디: "+id+"<br>");
				out.print("비밀번호: "+pwd+"<br>");
				out.print("</body></html>");
			}else {
				response.sendRedirect("cookie/login.html");	// 로그인 상태가 아니면 로그인 창으로 이동
			}
		}else {
			response.sendRedirect("cookie/login.html");	// 세션이 생성되지 않았으면 로그인 창으로 이동
		}
	}
	
}
