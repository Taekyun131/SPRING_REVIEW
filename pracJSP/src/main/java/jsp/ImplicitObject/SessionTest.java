package jsp.ImplicitObject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sess")
public class SessionTest extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		HttpSession session=request.getSession();
		session.setAttribute("name", "이순신");	// 세션 객체에 name을 바인딩
		
		pw.println("<html><body>");
		pw.println("<h1>세션에 이름을 바인딩합니다.</h1>");
		pw.println("<a href='/pracJSP/jsp02/jspIO/session1.jsp'>첫번째 페이지로 이동하기</a>");
		pw.println("</body></html>");
	}
	
}
