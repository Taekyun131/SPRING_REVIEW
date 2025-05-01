package servlet._04_binding;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirectBindingFirst")
public class RedirectBindingFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 1) redirect 포워딩 시 바인딩
	// 바인딩: 서블릿에서 다른 서블릿 또는 JSP로 대량의 데이터를 공유하거나 전달하는 기능
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.setAttribute("address", "서울시 성북구");	// 웹 브라우저에서 요청한 request 객체에 address의 값으로 "서울시 성북구"를 바인딩
		response.sendRedirect("redirectBindingSecond");	// 두번째 서블릿으로 전달하기 위해 sendRedirect() 메서드 호출
		
	}

}
