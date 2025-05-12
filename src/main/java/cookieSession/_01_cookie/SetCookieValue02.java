package cookieSession._01_cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/set2")
public class SetCookieValue02 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Date d=new Date();
		

		Cookie c=new Cookie("cookieTest", URLEncoder.encode("JSP 프로그래밍입니다.", "utf-8"));
//		c.setMaxAge(24*60*60);	
		
		c.setMaxAge(-1);	// 유효시간을 음수로 지정하여 Session 쿠키 생성
		// persistnce 쿠키 - 파일로 생성되어 유효기간 설정
		// session 쿠키 - 브라우저 메모리에 생성되어 브라우저 종료 시 소멸
		
		response.addCookie(c);	
		out.println("현재시간: "+d);
		out.println("문자열을 Cookie에 저장합니다.");
	}
	
}
