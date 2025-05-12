package sessionTracking._01_hiddenTag;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/stLogin")
public class LoginServlet extends HttpServlet{
	
	// 웹 페이지 사이의 상태나 정보를 공유하기 위해 세션 트래킹 사용
	// <hidden> 태그는 브라우저에는 표시되지 않지만 미리 저장된 정보를 서블릿으로 전송가능
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
		// <hidden> 태그로 전송된 값을 getParameter() 메서드를 이용해 가져옴
		String user_address=request.getParameter("user_address");
		String user_email=request.getParameter("user_email");
		String user_hp=request.getParameter("user_hp");
		
		String data="안녕하세요!<br> 로그인하셨습니다.<br><br>";
		data+="<html><body>";
		data+="아이디: "+user_id;
		data+="<br>";
		data+="패스워드: "+user_pw;
		data+="<br>";
		data+="주소: "+user_address;
		data+="<br>";
		data+="email: "+user_email;
		data+="<br>";
		data+="휴대전화: "+user_hp;
		data+="</body></html>";
		out.print(data);
	}


	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}


	
}
