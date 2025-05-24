package servlet._01_ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}

	@Override						// 응답은 HttpServletResponse 객체를 사용
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	// 웹 브라우저에서 전송된 데이터의 인코딩 설정
		resp.setContentType("text/html;charset=utf-8");		// 응답할 데이터 종류가 HTML임을 설정
		PrintWriter out=resp.getWriter();		// HttpServletResponse 객체의 getWriter()를 이용해 출력스트림 PrintWriter 객체를 받아옴
		String id=req.getParameter("user_id");
		String pw=req.getParameter("user_pw");
		
		String data="<html>";	// 브라우저로 출력할 데이터를 문자열로 연결해서 HTML 태그로 만든다
		data+="<body>";
		data+="아이디: "+id;
		data+="<br>";
		data+="비밀번호: "+pw;
		data+="</body>";
		data+="</html>";
		out.print(data);		// PrintWriter의 print()를 이용해 HTML 태그 문자열을 웹 브라우저로 출력
				
				
	}

	@Override
	public void destroy() {
		System.out.println("destroy 함수 호출");
	}

	
}
