package servlet._04_forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatchFirst")
public class DispatchFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 4) dispatch를 사용한 포워딩
	// 일반적인 포워딩 기능을 지칭
	// 세 가지 방법과 달리 웹 브라우저를 거치지 않고 서블릿이 직접 요청하는 방식
	// 웹 브라우저의 URL이 변경되지 않아 클라이언트 측에서는 포워드 진행 여부 알 수 없음 
	// forward() 메서드 이용
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		RequestDispatcher dispatch=request.getRequestDispatcher("dispatchSecond");
		dispatch.forward(request, response);		// dispatch 방법을 이용해 dispatchSecond 서블릿으로 전달
	}

}
