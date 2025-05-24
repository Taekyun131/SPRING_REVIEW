package servlet._01_ex02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input")
public class InputServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String user_id=req.getParameter("user_id");
		String user_pw=req.getParameter("user_pw");
		System.out.println("아이디: "+user_id);
		System.out.println("비밀번호: "+user_pw);
		String[] subject=req.getParameterValues("subject");	// 하나의 name으로 여러 값을 전송하는 경우 getParameterValues()를 사용
		for(String str:subject) {
			System.out.println("선택한 과목: "+str);
		}
	}

	@Override
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

	
}
