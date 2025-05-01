package servlet._04_forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/refreshFirst")
public class RefreshFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 2) refresh를 사용한 포워딩
	// addHeader() 메서드 사용
	// 웹 브라우저에 재요청하는 방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		response.addHeader("Refresh", "1;url=refreshSecond");	// 웹 브라우저에 1초 후 서블릿 refreshSecond로 재요청
	}

}
