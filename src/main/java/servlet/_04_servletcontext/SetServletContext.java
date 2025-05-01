package servlet._04_servletcontext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cset")
// ServletContext 
// - 서블릿과 컨테이너 간의 연동을 위해 사용
// - 웹 어플리케이션마다 하나씩 생성
// - 서블릿끼리 자원을 공유하는 데 사용
// - 컨테이너 실행 시 생성되고 종료 시 소멸

public class SetServletContext extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		ServletContext context=getServletContext();	// ServletContext 객체를 가져옴
		List member=new ArrayList();
		member.add("이순신");
		member.add(30);
		context.setAttribute("member", member);	// ServletContext 객체에 데이터를 바인딩
		out.print("<html><body>");
		out.print("이순신과 30 설정");
		out.print("</body></html>");
		
	}
	
}
