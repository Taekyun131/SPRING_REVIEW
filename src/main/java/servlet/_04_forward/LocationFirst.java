package servlet._04_forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/locationFirst")
public class LocationFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 3) location을 사용한 포워딩
	// 자바스크립트 location 객체의 href 속성을 사용
	// 자바스크립트에서 재요청하는 방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("<script type='text/javascript'>");	// 자바스크립트 location의 href 속성에 서블릿 locationSecond를 설정해 재요청
		out.print("location.href='locationSecond'");
		out.print("</script>");
	}

}
