package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO="D:\\board\\article_image";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String imageFileName=(String) request.getParameter("imageFileName");
		String articleNO=request.getParameter("articleNO");
		System.out.println("imageFileName: "+imageFileName);
		OutputStream out=response.getOutputStream();
		String path=ARTICLE_IMAGE_REPO+"\\"+articleNO+"\\"+imageFileName;
		File imageFile=new File(path);
		
		response.setHeader("Cache-Controller", "no-cache");
		response.addHeader("COntent-disposition", "attachment;filName="+imageFileName);	// 이미지 파일을 내려받는 데 필요한 response에 헤더 정보를 설정
		FileInputStream in=new FileInputStream(imageFile);
		
		// 버퍼를 이용해 한번에 8kb씩 전송
		byte[] buffer=new byte[1024*8];
		while(true) {
			int count=in.read(buffer);
			if(count==-1) {
				break;
			}
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}
	
}
