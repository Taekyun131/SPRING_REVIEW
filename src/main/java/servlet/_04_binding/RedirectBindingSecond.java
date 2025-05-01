package servlet._04_binding;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirectBindingSecond")
public class RedirectBindingSecond extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String address=(String) request.getAttribute("address");	// 전달된 request에서 getAttribute()를 이용해 address의 값을 가져옴
		out.println("<html><body>");
		out.println("주소: "+address);
		out.println("<br>");
		out.println("redirect를 이용한 바인딩 실습입니다");
		out.println("</body></html>");
		
		// 주소: null
		// 이유: 첫번째 request는 웹 브라우저를 통해 재요청되는 3단계의 두번째 request와 다른 요청이기 때문
		// redirect 방식으로는 서블릿에서 바인딩한 데이터를 다른 서블릿으로 전송 불가
	}

}
