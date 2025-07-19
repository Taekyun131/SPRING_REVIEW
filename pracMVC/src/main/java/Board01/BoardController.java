package Board01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	BoardService boardService;
	ArticleVO articleVO;
	
	public void init() throws ServletException{
		boardService=new BoardService();	// 서블릿 초기화 시 BoardService 객체를 생성
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage="";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getPathInfo();	// 요청명을 가져옴
		System.out.println("action: "+action);
		try {
			List<ArticleVO> articlesList=new ArrayList<ArticleVO>();
			if(action==null || action.equals("/listArticles.do")) {
				articlesList=boardService.listArticles();	// 전체 글 조회
				request.setAttribute("articlesList", articlesList);	// 조회된 글 목록을 articlesList로 바인딩한 후 listArticles.jsp로 포워딩
				nextPage="/Board01/listArticles.jsp";
			}
			RequestDispatcher dispatch=request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
