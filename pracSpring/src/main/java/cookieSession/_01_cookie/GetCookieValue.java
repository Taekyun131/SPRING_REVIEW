package cookieSession._01_cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get")
public class GetCookieValue extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		// request의 getCookie() 메서드를 호출해 브라우저에게 쿠키 정보를 요청한 후 쿠키 정보를 배열로 가져옴
		Cookie[] allValues=request.getCookies();	
		
		// 배열에서 저장할 때 사용한 쿠키 이름인 cookieTest로 검색해 쿠키값을 가져옴
		for(int i=0;i<allValues.length;i++) {
			if(allValues[i].getName().equals("cookieTest")) {
				out.println("<h2>Cookie 값 가져오기:"
						+ URLDecoder.decode(allValues[i].getValue(),"utf-8"));
			}
		}
	}
	
}
