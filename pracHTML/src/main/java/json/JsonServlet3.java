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

@WebServlet("/json3")
public class JsonServlet3 extends HttpServlet {

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
		
		JSONObject totalObject=new JSONObject();	
		JSONArray membersArray=new JSONArray();		
		JSONObject memberInfo=new JSONObject();		
		
		// 회원정보를 name, value 쌍으로 저장
		memberInfo.put("name", "박지성");
		memberInfo.put("age", "25");
		memberInfo.put("gender", "남자");
		memberInfo.put("nickname", "날센돌이");
		membersArray.add(memberInfo);
		
		memberInfo=new JSONObject();
		memberInfo.put("name", "김연아");
		memberInfo.put("age", "21");
		memberInfo.put("gender", "여자");
		memberInfo.put("nickname", "칼치");
		membersArray.add(memberInfo);
		
		// 회원정보를 저장한 배열을 배열이름 members로 totalObject에 저장
		totalObject.put("members", membersArray);
		
		// totalObject에 members라는 name으로 membersArray를 value로 저장
		totalObject.put("members", membersArray);
		
		
		
		JSONArray bookArray=new JSONArray();
		JSONObject bookInfo=new JSONObject();
		bookInfo.put("title", "초보자를 위한 자바 프로그래밍");
		bookInfo.put("writer", "이병승");
		bookInfo.put("price", "30000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pracHTML/image/image1.jpg");
		bookArray.add(bookInfo);
		
		bookInfo=new JSONObject();
		bookInfo.put("title", "모두의 파이썬");
		bookInfo.put("writer", "이승찬");
		bookInfo.put("price", "12000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pracHTML/image/image2.jpg");
		bookArray.add(bookInfo);
		
		totalObject.put("books", bookArray);
		String jsonInfo=totalObject.toJSONString();	// JSONObject를 문자열로 변환
		System.out.println(jsonInfo);
		writer.print(jsonInfo);	// JSON 데이터를 브라우저로 전송
		
	}
}
