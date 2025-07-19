package Board02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	BoardService boardService;
	ArticleVO articleVO;
	private static String ARTICLE_IMAGE_REPO="D:\\board\\article_image";	// 글에 첨부한 이미지 저장 위치를 상수로 선언
	
	public void init() throws ServletException{
		boardService=new BoardService();	// 서블릿 초기화 시 BoardService 객체를 생성
		articleVO=new ArticleVO();
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
				nextPage="/Board02/listArticles.jsp";
			}else if(action.equals("/articleForm.do")) {
				nextPage="/Board02/articleForm.jsp";
			}else if(action.equals("/addArticle.do")) {
				Map<String, String> articleMap=upload(request, response);
				String title=articleMap.get("title");
				String content=articleMap.get("content");
				String imageFileName=articleMap.get("imageFileName");
				
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.addArticle(articleVO);
				nextPage="/board/listArticles.do";
			}
			RequestDispatcher dispatch=request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap=new HashMap<String, String>();
		String encoding="utf-8";
		File currentDirPath=new File(ARTICLE_IMAGE_REPO);	// 글 이미지 저장 폴더에 대해 파일 객체를 생성
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			List items=upload.parseRequest(request);
			for(int i=0;i<items.size();i++) {
				FileItem fileItem=(FileItem) items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));	
					// 파일 업로드로 같이 전송된 새 글 관련 매개변수를 Map에 (key, value)로 저장한 후,
					// 새 글과 관련된 title, content를 Map에 저장
				}else {
					System.out.println("파라미터이름: "+fileItem.getFieldName());
					System.out.println("파일이름: "+fileItem.getName());
					System.out.println("파일크기: "+fileItem.getSize()+"bytes");
					if(fileItem.getSize()>0) {	// 업로드된 파일이 존재하는 경우 업로드한 파일의 파일 이름으로 저장소에 업로드
						int idx=fileItem.getName().lastIndexOf("\\");
						if(idx==-1) {
							idx=fileItem.getName().lastIndexOf("/");
						}
						
						String fileName=fileItem.getName().substring(idx+1);
						articleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile=new File(currentDirPath+"\\"+fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}
}
