package servletFilterLlistener._03_FilterAPI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filterLogin")
public class LoginTest extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 request.setCharacterEncoding("utf-8");		// post 방식으로 한글 전송 시 인코딩 작업을 생략
		 response.setContentType("text/html;charset=utf-8");
		 PrintWriter out=response.getWriter();
		 
		 String user_name=request.getParameter("user_id");
		 String user_pw=request.getParameter("user_pw");
		 
		 out.print("<html><body>");
		 out.print("이름은 "+user_name+"<br>");
		 out.print("비밀번호는 "+user_pw+"<br>");
		 out.print("</body></html>");
	}
	
}
