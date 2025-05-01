package servlet._04_forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirectFouth")
public class RedirectFourth extends HttpServlet{
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		String name=req.getParameter("name");	// name으로 이전 서블릿에서 전달된 lee를 받음
		out.println("<html><body>");
		out.println("이름: "+name);		
		out.println("</body></html>");
	}
	
}
