package servlet._04_servletcontext;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// load-on-startup 기능: 서블릿이 메모리에 로드되어 기능을 수행하는데, 최초 요청에 대해서 실행 시간이 길어지는 단점을 보완하는 기능
// - 톰캣 컨테이너가 실행되면서 미리 서블릿 실행
// - 지정한 숫자가 0보다 크면 톰캣 컨테이너가 실행되면서 서블릿이 초기화
// - 지정한 숫자는 우선순위를 의미하며, 작은 숫자부터 초기화 진행

@WebServlet(name="loadConfig",
			urlPatterns={
					"/loadConfig"
					},
			loadOnStartup = 1)		// loadOnStartup 속성을 추가하고 우선순위를 1로 설정
public class LoadAppConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;		// 변수 context를 멤버변수로 선언
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LoadAppConfig의 init 메서드 호출");
		context=config.getServletContext();	// init() 메서드에서 ServletContext 객체를 얻음
		
		// getInitParameter() 메서드로 web.xml의 메뉴 정보를 읽어옴
		String menu_member=context.getInitParameter("menu_member");
		String menu_order=context.getInitParameter("menu_order");
		String menu_goods=context.getInitParameter("menu_goods");
		
		// 메뉴 정보를 ServletContext 객체에 바인딩
		context.setAttribute("menu_member", menu_member);
		context.setAttribute("menu_order", menu_order);
		context.setAttribute("menu_goods", menu_goods);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		// ServletContext context=getServletContext();	// doGet() 메서드 호출 시 ServletContext 객체를 얻는 부분은 주석처리
		
		// 브라우저에서 요청 시 ServletContext 객체의 바인딩된 메뉴 항목을 가져옴
		String menu_member=(String) context.getAttribute("menu_member");
		String menu_order=(String) context.getAttribute("menu_order");
		String menu_goods=(String) context.getAttribute("menu_goods");
		
		out.print("<html><body>");
		out.print("<table border=1 cellspacing=0><tr>메뉴 이름</tr>");
		out.print("<tr><td>"+menu_member+"</td></tr>");
		out.print("<tr><td>"+menu_order+"</td></tr>");
		out.print("<tr><td>"+menu_goods+"</td></tr>");
		out.print("</table></body></html>");
		
		// 톰캣 실행시 init() 메서드를 호출하면 web.xml의 메뉴 정보를 읽어 들인 후 ServletContext 객체에 바인딩
		// 브라우저에서 요청 시 web.xml이 아니라 ServletContext 객체에서 메뉴 항목을 가져온 후 출력하기 때문에 속도 향상
	}

}
