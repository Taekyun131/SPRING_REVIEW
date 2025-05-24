package servletFilterLlistener._03_FilterAPI;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/filterLogin")	// 모든 요청이 필터를 거쳐 처리
public class EncodingFilter extends HttpFilter implements Filter {	// 사용자 정의 필터는 반드시 Filter 인터페이스 구현해야 함
     ServletContext context;
     
     public void init(FilterConfig fConfig) throws ServletException {
    	 System.out.println("utf-8 인코딩...........");
    	 context=fConfig.getServletContext();
    	 
     }
    
    public EncodingFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		System.out.println("destroy 호출");
	}
	
	// doFilter() 메서드 안에서 실제 필터 기능 구현
	// 브라우저 요청 시 doFilter() 메서드의 매개변수로 request, response가 전달
	// FilterChain 타입인 chain을 세번쨰 매개변수로 가짐
	// 전달된 request를 이용해 한글 인코딩 작업
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter 호출");
		request.setCharacterEncoding("utf-8");	// 한글 인코딩 설정
		
		String context=((HttpServletRequest) request).getContextPath();		// 웹 애플리케이션의 컨텍스트 이름을 가져옴
		String pathInfo=((HttpServletRequest) request).getRequestURI(); 	// 웹 브라우저에서 요청한 요청 URI를 가져옴
		String realPath=request.getRealPath(pathInfo);						// 요청 URI의 실제 경로를 가져옴
		String mesg="Context 정보: "+context
				+"\n URI 정보: "+pathInfo
				+"\n 물리적 경로: "+realPath;
		System.out.println(mesg); 
		long begin=System.currentTimeMillis();	// 요청 필터에서 요청 처리 전의 시각 저장
		
		
		// pass the request along the filter chain
		// chain.doFilter() 기준으로 위쪽 코드틑 요청필터, 아래쪽 코드는 응답필터로 구분
		chain.doFilter(request, response);		// 다음 필터로 넘기는 작업을 수행
		
		long end=System.currentTimeMillis();	// 응답필터에서 요청 처리 후의 시각 저장
		System.out.println("작업시간: "+(end-begin)+"ms");	// 작업 요청 전과 후의 사각 차를 구해 작업 수행 시간을 출력
	}

	

}
