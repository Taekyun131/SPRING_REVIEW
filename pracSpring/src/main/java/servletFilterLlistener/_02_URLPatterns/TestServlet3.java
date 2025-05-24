package servletFilterLlistener._02_URLPatterns;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 3. 확장자만 일치하는 URL패턴
@WebServlet("*.do")	// 매핑이름 상관없이 확장자가 .do면 실행
					// do는 일반적으로 MVC나 프레임워크에서 자주 사용한는 확장자
public class TestServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String context=request.getContextPath();
		String url=request.getRequestURL().toString();
		String mapping=request.getServletPath();
		String uri=request.getRequestURI();
		
		out.println("<html");
		out.println("<head>");
		out.println("<title>TestServlet3 입니다</title>");
		out.println("</head>");
		out.println("<body bgcolor='red'>");
		out.println("<b>TestServlet3입니다.</b><br>");
		out.println("<b>컨텍스트 이름: "+context+"</b><br>");
		out.println("<b>전체 경로: "+url+"</b><br>");
		out.println("<b>매핑 이름: "+mapping+"</b><br>");
		out.println("<b>URI: "+uri+"</b><br>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
