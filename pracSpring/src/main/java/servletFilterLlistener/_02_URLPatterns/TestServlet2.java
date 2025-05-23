package servletFilterLlistener._02_URLPatterns;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 2. 디렉터리 이름만 일치하는 URL 패턴

@WebServlet("/first/*")	// /first/ 디렉터리 이름으로 시작되는 요청에 대해 실행
public class TestServlet2 extends HttpServlet {
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
		out.println("<title>TestServlet2 입니다</title>");
		out.println("</head>");
		out.println("<body bgcolor='yellow'>");
		out.println("<b>TestServlet2입니다.</b><br>");
		out.println("<b>컨텍스트 이름: "+context+"</b><br>");
		out.println("<b>전체 경로: "+url+"</b><br>");
		out.println("<b>매핑 이름: "+mapping+"</b><br>");
		out.println("<b>URI: "+uri+"</b><br>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
