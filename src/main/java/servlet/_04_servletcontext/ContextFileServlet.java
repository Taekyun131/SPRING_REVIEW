package servlet._04_servletcontext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		ServletContext context=getServletContext();
		InputStream is=context.getResourceAsStream("/WEB-INF/bin/init.txt");	// 해당위치의 파일을 읽어옴
		BufferedReader buffer=new BufferedReader(new InputStreamReader(is));
		
		String menu=null;
		String menu_member=null;
		String menu_order=null;
		String menu_goods=null;
		// ","를 구분자로 하여 메뉴 항목을 분리
		while((menu=buffer.readLine())!=null) {
			StringTokenizer tokens=new StringTokenizer(menu, ",");	
			menu_member=tokens.nextToken();
			menu_order=tokens.nextToken();
			menu_goods=tokens.nextToken();
		}
		
		
		out.print("<html><body>");
		out.print("<table border=1 cellspcing=0><tr>메뉴 이름</tr>");
		out.print("<tr><td>"+menu_member+"</td></tr>");
		out.print("<tr><td>"+menu_order+"</td></tr>");
		out.print("<tr><td>"+menu_goods+"</td></tr>");
		out.print("</table></body></html>");
	}
	

}
