package servlet._04_binding;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatchBindingSecond")
public class DispatchBindingSecond extends HttpServlet {
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
		
		// 주소: 서울시 성북구
		// 이유: 첫번째 서블릿에서 두번째 서블릿으로 전달되는 request가 브라우저를 거치지 않고, request에 바인딩된 데이터가 그대로 전달
	}

}
