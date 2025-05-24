package servlet._01_ex02;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input2")
public class InputServlet2 extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println("init 메서드 실행");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Enumeration enu=req.getParameterNames();	// 전송되어 온 name 속성들만 Enumeration 타입으로 저장
		while(enu.hasMoreElements()) {				// 각 name을 하나씩 가져와 대응해서 전송되어 온 값을 출력
			String name=(String) enu.nextElement();
			String[] values=req.getParameterValues(name);
			for(String value: values) {
				System.out.println("name="+name+", value="+value);
			}
		}
	}

	@Override
	public void destroy() {
		System.out.println("destroy 메서드 실행");
	}

	
}
