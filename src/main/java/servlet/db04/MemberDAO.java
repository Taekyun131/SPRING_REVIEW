package servlet.db04;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
//	private String user="system";
//	private String pwd="1111";
//	private String url="jdbc:oracle:thin:@localhost:1521:orcl";
//	private String driver="oracle.jdbc.driver.OracleDriver";
	
	public MemberDAO() {
		try {
			// JNDI에 접근하기 위해 기본경로 (java:/comp/env)를 지정
			Context ctx=new InitialContext();	
			Context envContext=(Context) ctx.lookup("java:/comp/env");
			// 톰캣 context.xml에 설정한 name값인 jdbc/oracle을 이용해 톰캣이 미리 연결한 DataSource를 받아옴
			dataFactory=(DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list=new ArrayList<MemberVO>();
		try {
//			connDB();	// 데이터 베이스 연결
			con=dataFactory.getConnection();	// DataSource를 이용해 데이터베이스에 연결
			String query="select * from t_member";
			System.out.println("prepareStatement: "+query);
			pstmt=con.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery(query);
			while(rs.next()) {	
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				Date joinDate=rs.getDate("joinDate");
				
				MemberVO vo=new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);	
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addMember(MemberVO memberVO) {
		try {
			con=dataFactory.getConnection();	// DataSource를 이용해 데이터베이스와 연결
			String id=memberVO.getId();
			String pwd=memberVO.getPwd();
			String name=memberVO.getName();
			String email=memberVO.getEmail();
			
			String query="insert into t_member";	// insert문을 문자열로 생성
			query+="(id, pwd, name, email)";
			query+=" values (?,?,?,?)";
			System.out.println("prepareStatement: "+query);
			pstmt=con.prepareStatement(query);
			
			// insert문의 각 '?'에 순서대로 회원정보 매핑
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();	// 회원정보를 테이블에 추가
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void delMember(String id) {
		try {
			con=dataFactory.getConnection();
			
			String query="delete from t_member"+" where id=?";	// delete문을 문자열로 생성
			System.out.println("prepareStatment: "+query);
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	// 네 가지 정보로 데이터베이스와 연결하는 메서드
//	private void connDB() {
//		try {
//			Class.forName(driver);
//			System.out.println("Oracle 드라이버 로딩 성공");
//			con=DriverManager.getConnection(url, user, pwd);
//			System.out.println("Connection 생성 성공");
//			stmt=con.createStatement();
//			System.out.println("Statement 생성 성공");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
