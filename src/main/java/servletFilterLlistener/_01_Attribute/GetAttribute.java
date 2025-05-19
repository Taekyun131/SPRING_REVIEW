package servletFilterLlistener._01_Attribute;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/getAttr")
public class GetAttribute extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		ServletContext ctx=getServletContext();
		HttpSession sess=request.getSession();
		
		// 각 서블릿 API에서 바인딩된 속성의 값을 가져옴
		String ctxMesg=(String) ctx.getAttribute("context");
		String sesMesg=(String) sess.getAttribute("session");
		String reqMesg=(String) request.getAttribute("request");
		
		out.print("context값: "+ctxMesg+"<br>");
		out.print("session값: "+sesMesg+"<br>");
		out.print("request값: "+reqMesg+"<br>");
		
		// Context와 Session 객체에 바인딩된 속성은 같은 브라우저에서 접근할 수 있으므로 값을 출력
		// 그러나 request객체는 /setAttr의 요청으로 생성된 request객체와 다르므로 null이 출력
		
		// 다른 브라우저로 /getAttr 요청시 
		// Context 객체에 바인딩된 데이터는 모든 브라우저에서 같은 결과를 출력
		// session 객체에 바인딩된 데이터는 브라우저가 다르므로 유지되지 않아 null 출력
	}
	
}
