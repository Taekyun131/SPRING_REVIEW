package servletFilterLlistener._04_ListenerAPI;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener	// 애너테이션으로 리스너 등록
public class LoginImpl2 implements HttpSessionListener {
	String user_id;
	String user_pw;
	static int total_user=0;
   
	public LoginImpl2() {
    	
    }
    public LoginImpl2(String user_id, String user_pw) {
    	this.user_id=user_id;
    	this.user_pw=user_pw;
    }

	// 세션 생성 시 이벤트를 처리
    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("세션 생성");
    	++total_user;
    }

	// 세션 소멸 시 이벤트를 처리
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("세션 소멸");
    	--total_user;
    }
	
}
