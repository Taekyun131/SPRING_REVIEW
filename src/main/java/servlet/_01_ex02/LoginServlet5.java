package servlet._01_ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}

	
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("user_id");
		String pw=request.getParameter("uesr_pw");
		String address=request.getParameter("user_address");	// <hidden> 태그로 전송된 값으 받아옴
		System.out.println("아이디: "+id);
		System.out.println("비밀번호: "+pw);
		
		String data="<html>";
		data+="<body>";
		data+="아이디: "+id;
		data+="<br>";
		data+="비밀번호: "+pw;
		data+="<br>";
		data+="주소: "+address;
		data+="</body>";
		data+="</html>";
		out.print(data);
	}
	
}
