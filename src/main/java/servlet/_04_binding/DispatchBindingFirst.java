package servlet._04_binding;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatchBindingFirst")
public class DispatchBindingFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 1) dispatch 포워딩 시 바인딩
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.setAttribute("address", "서울시 성북구");	// 웹 브라우저의 최초 요청 request에 바인딩
		RequestDispatcher dispatch=request.getRequestDispatcher("dispatchBindingSecond");
		dispatch.forward(request, response);	// 바인딩된 request를 두번째 서블릿으로 포워드
		
	}

}
