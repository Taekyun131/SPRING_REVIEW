package servlet.db01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private Statement stmt;
	private Connection con;
	private String user="system";
	private String pwd="1111";
	private String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private String driver="oracle.jdbc.driver.OracleDriver";
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list=new ArrayList<MemberVO>();
		try {
			connDB();	// 데이터 베이스 연결
			String query="select * from t_member";
			System.out.println(query);
			ResultSet rs=stmt.executeQuery(query);	// SQL문으로 회원 정보를 조회
			while(rs.next()) {	// 조회한 레코드의 각 컬럼 값을 받아 옴
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				Date joinDate=rs.getDate("joinDate");
				
				//각 커럼 값을 다시 MemberVO 객체의 속성에 설정
				MemberVO vo=new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);	// 설정된 MemberVO 객체를 다시 ArrayList에 저장
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	// 조회한 레코드의 개수만큼 MemberVO객체를 저장한 ArrayList 반환
	}
	
	// 네 가지 정보로 데이터베이스와 연결하는 메서드
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con=DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
			stmt=con.createStatement();
			System.out.println("Statement 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
