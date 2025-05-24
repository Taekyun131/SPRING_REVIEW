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

@WebServlet("/setAttr")
public class SetAttribute extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String ctxMesg="context에 바인딩됩니다.";
		String sesMesg="session에 바인딩됩니다.";
		String reqMesg="requst에 바인딩됩니다.";
		
		// HttpServletContext 객체, HttpSession객체, HttpServletRequest객체를 얻은 후 속성을 바인딩
		ServletContext ctx=getServletContext();
		HttpSession session=request.getSession();
		ctx.setAttribute("context", ctxMesg);
		session.setAttribute("session", sesMesg);
		request.setAttribute("request", reqMesg);
		out.print("바인딩을 수행합니다");
		
		
	}
	
}
