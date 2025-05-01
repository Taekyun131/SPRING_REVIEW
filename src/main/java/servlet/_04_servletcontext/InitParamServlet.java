package servlet._04_servletcontext;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ServletConfig - ServletContext 객체를 얻는 기능, 서블릿에 대한 초기화 작업기능
// 서블릿을 초기화 하는 방법에는 @WebServlet 애너테이션을 이용하는 방법, web.xml에 설정하는 방법(거의 사용 안함)이 있다.

// 서블릿 생성 시 설정값대로 표시
@WebServlet(
		// urlPatterns를 이용해 매핑이름을 여러 개 설정가능
		urlPatterns = { 
				"/sInit", 
				"/sInit2"
		}, 
		// @WebInitParam을 이용해 여러 개의 매개변수 설정 가능
		initParams = { 
				@WebInitParam(name = "email", value = "admin@jweb.com"), 
				@WebInitParam(name = "tel", value = "010-1111-2222")
		})
public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		// 설정한 매개변수의 name으로 값을 가져옴
		String email=getInitParameter("email");
		String tel=getInitParameter("tel");
		
		out.print("<html><body>");
		out.print("<table><tr>");
		out.print("<td>email: </td><td>"+email+"</td></tr>");
		out.print("<tr><td>휴대전화: </td><td>"+tel+"</td></tr>");
		out.print("</table></body></html>");
	}

}
