package json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/json2")
public class JsonServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		
		JSONObject totalObject=new JSONObject();	// 배열을 저장할 totalObject 선언
		JSONArray membersArray=new JSONArray();		// memberInfo JSON 객체를 저장할 membersArray 배열 선
		JSONObject memberInfo=new JSONObject();		// 회원 한 명의 정보가 들어갈 memberInfo JSON 객체 선언
		
		// 회원정보를 name, value 쌍으로 저장
		memberInfo.put("name", "박지성");
		memberInfo.put("age", "25");
		memberInfo.put("gender", "남자");
		memberInfo.put("nickname", "날센돌이");
		membersArray.add(memberInfo);
		
		// totalObject에 members라는 name으로 membersArray를 value로 저장
		totalObject.put("members", membersArray);
		
		String jsonInfo=totalObject.toJSONString();	// JSONObject를 문자열로 변환
		writer.print(jsonInfo);	// JSON 데이터를 브라우저로 전송
		
	}
}
