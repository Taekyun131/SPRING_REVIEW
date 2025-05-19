package servletFilterLlistener._04_ListenerAPI;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginImpl implements HttpSessionBindingListener{	// HttpSessionBindingListener를 구현해 세션에 바인딩 시 이벤트 처리
	String user_id;
	String user_pw;
	static int total_user=0;	// 세션에 바인딩 시 1씩 증가
	
	public LoginImpl(String user_id, String user_pw) {
		this.user_id=user_id;
		this.user_pw=user_pw;
	}

	@Override
	// 세션에 저장 시 접속자 수를 증가
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("사용자 접속");
		++total_user;
	}

	@Override
	// 세션에서 소멸 시 접속자 수를 감소
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("사용자 접속 해제");
		total_user--;
	}
	
	
	
}
