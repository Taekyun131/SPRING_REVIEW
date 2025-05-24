package cookieSession._03_ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	public MemberDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context) ctx.lookup("java:/comp/env");
			dataFactory=(DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExisted(MemberVO memberVO) {
		boolean result=false;
		String id=memberVO.getId();
		String pwd=memberVO.getPwd();
		try {
			con=dataFactory.getConnection();
			// 오라클의 decode() 함수를 이용해 조회
			// ID와 비밀번호가 테이블에 존재하면 true를, 존재하지 않으면 false를 조회
			String query="select decode(count(*), 1, 'true', 'false') as result from t_member where id=? and pwd=?";
		
			//	메서드로 전달된 ID와 비밀번호를 이용해 SQL문을 작성한 후 데이터베이스에 조회 
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			result=Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result="+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
