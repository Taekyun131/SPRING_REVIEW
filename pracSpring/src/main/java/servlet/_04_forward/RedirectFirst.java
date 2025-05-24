package servlet._04_forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirectFirst")
public class RedirectFirst extends HttpServlet{
	
	// 1) redirect를 사용한 포워딩
	// sendRedirect() 메서드 사용
	// 서블릿의 요청이 클라이언트의 웹브라우저를 다시 거쳐 요청되는 방식
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		resp.sendRedirect("redirectSecond");	// sendRedirect() 메서드를 이용해 웹브라우저에게 다른 서블릿인 second로 재요청
	}
	
}
