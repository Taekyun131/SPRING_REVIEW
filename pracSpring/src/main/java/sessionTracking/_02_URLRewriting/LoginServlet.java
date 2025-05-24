package sessionTracking._02_URLRewriting;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/stLogin2")
public class LoginServlet extends HttpServlet{

	// URL Rewriting을 입력받은 데이터를 다른 서블릿으로 전송
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
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
		data+="<br>";
		out.print(data);
		
		user_address=URLEncoder.encode(user_address, "utf-8");	// GET 방식으로 한글을 전송하기 위해 인코딩 진행
		out.print("<a href='/pracSpring/stSecond?user_id="+user_id+"&user_pw="+user_pw+"&user_address="+user_address+"'> "
				+ "두번째 서블릿으로 보내기</a> ");		// <a>태그를 이용해 링크 클릭 시 두번째 서블릿으로 로그인 정보를 전송
		data="</body></html>";
		out.print(data);
	}


	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

	
	
}
