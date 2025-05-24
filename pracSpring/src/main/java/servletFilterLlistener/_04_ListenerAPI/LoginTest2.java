package servletFilterLlistener._04_ListenerAPI;

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
import javax.servlet.http.HttpSession;
// HttpSessionListener를 이용해 로그인 접속자 수 표시
@WebServlet("/listenerLogin2")
public class LoginTest2 extends HttpServlet{
	ServletContext context=null;
	List<String> user_list=new ArrayList();	// 로그인한 접속자 ID를 저장하는 ArrayList
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		context=getServletContext();
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
		// 이벤트 핸들러를 생성한 후 세션에 저장
		LoginImpl2 loginUser=new LoginImpl2(user_id, user_pw);	// LoginImpl2 객체를 생성한 후 전송된 ID와 비밀번호를 저장
		
		// 최초 로그인 시 접속자 ID를 ArrayList에 차례로 저장한 후 다시 context객체에 속성으로 저장
		if(session.isNew()) {
			session.setAttribute("loginUser", loginUser);
			user_list.add(user_id);
			context.setAttribute("user_list", user_list);
		}
		
		
	
		out.println("<html><body>");
		out.println("아이디는 "+loginUser.user_id+"<br>");
		out.println("총 접속자 수는 "+LoginImpl2.total_user+"<br>");	//세션에 바인딩 이벤트 처리 후 총 접속자 수를 표시
		out.println("접속 아이디: <br>");
		
		// context 객체의 ArrayList를 가져와 접속자 ID를 차례로 출력
		List<String> list=(ArrayList) context.getAttribute("user_list");	
		for(int i=0;i<list.size();i++) {
			out.println(list.get(i)+"<br>");
		}
		out.println("<a href='listenerLogout?user_id="+user_id+"'>로그아웃</a>");	// 로그아웃 클릭 시 서블릿 logout으로 접속자 ID를 전송해 로그아웃
		out.println("</body></html>");
	}
	
}
