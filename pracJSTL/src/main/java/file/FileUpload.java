package file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String encoding="utf-8";	
		File currentDirPath=new File("D:\\file_repo");		// 업로드할 파일경로 지정
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(currentDirPath);	// 파일경로 설정
		factory.setSizeThreshold(1024*1024); 	// 최대 업로드 가능한 파일 크기 설정
		ServletFileUpload upload=new ServletFileUpload((FileItemFactory) factory);
		try {
			List items=upload.parseRequest(request);	// request 객체에서 매개변수를 List로 가져옴
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem=(FileItem) items.get(i);
				if(fileItem.isFormField()) {	// 폼 필드(텍스트)이면 전송된 매개변수 값을 출력
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString(encoding));
				}else {	// 폼 필드가 아니면 파일 업로드 기능을 수행
					System.out.println("매개변수이름: "+fileItem.getFieldName());
					System.out.println("파일이름: "+fileItem.getName());
					System.out.println("파일크기: "+fileItem.getSize()+"bytes");
					if(fileItem.getSize()>0) {
						int idx=fileItem.getName().lastIndexOf("\\");	// 윈도우 경로 처리
						if(idx==-1) {	// 리눅스 경로 처리
							idx=fileItem.getName().lastIndexOf("/");	
						}
						String fileName=fileItem.getName().substring(idx+1);
						File uploadFile=new File(currentDirPath+"\\"+fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
