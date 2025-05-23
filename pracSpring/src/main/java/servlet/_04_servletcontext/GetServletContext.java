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

@WebServlet("/cget")
public class GetServletContext extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		ServletContext context=getServletContext();	// ServletContext 객체를 가져옴
		List member=(ArrayList) context.getAttribute("member");	// member로 이전에 바인딩돈 회원 정보를 가져옴
		String name=(String) member.get(0);
		int age=(Integer) member.get(1);
		out.print("<html><body>");
		out.print(name+"<br>");
		out.print(age+"<br>");
		out.print("</body></html>");
		
		// ServletContext에 바인딩된 데이터는 모든 서블릿들이 접근 가능
	}
}
