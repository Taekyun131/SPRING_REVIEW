package Board07;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public BoardDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context) ctx.lookup("java:/comp/env");
			dataFactory=(DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ArticleVO> selectAllArticles(){
		List<ArticleVO> articlesList=new ArrayList<ArticleVO>();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT level, articleNO, parentNO, title, content, id, writedate"
					+ " FROM t_board"
					+ " START WITH parentNO=0"
					+ " CONNECT BY PRIOR articleNO=parentNO"
					+ " ORDER SIBLINGS BY articleNO DESC";	// 오라클의 계층형 SQL문을 실행
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				int level=rs.getInt("level");
				int articleNO=rs.getInt("articleNO");
				int parentNO=rs.getInt("parentNO");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String id=rs.getString("id");
				Date writeDate=rs.getDate("writedate");
				ArticleVO article=new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	public List<ArticleVO> selectAllArticles(Map<String, Integer> pagingMap){
		List<ArticleVO> articlesList=new ArrayList<ArticleVO>();
		int section=(Integer) pagingMap.get("section");
		int pageNum=(Integer) pagingMap.get("pageNum");
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * FROM ("
					+ "SELECT ROWNUM as recNum,"
					+ "LVL, articleNO, "
					+ "parentNO, title,"
					+ "id, writeDate"
					+ " FROM (SELECT LEVEL as LVL, "
					+ "articleNO, parentNO, title, id, writeDate"
					+ " FROM t_board"
					+ " START WITH parentNO=0 CONNECT BY PRIOR articleNO=parentNO"
					+ " ORDER SIBLINGS BY articleNO DESC))"
					+ " WHERE recNum between(?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int level=rs.getInt("lvl");
				int articleNO=rs.getInt("articleNO");
				int parentNO=rs.getInt("parentNO");
				String title=rs.getString("title");
				String id=rs.getString("id");
				Date writeDate=rs.getDate("writeDate");
				ArticleVO article=new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	private int getNewArticleNO() {
		try { 
			conn=dataFactory.getConnection();
			String query="SELECT max(articleNO) FROM t_board";	// 기본 글 번호 중 가장 큰 번호를 조회
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				return (rs.getInt(1)+1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertNewArticle(ArticleVO article) {
		int articleNO=getNewArticleNO();	
		try {
			conn=dataFactory.getConnection();
			int parentNO=article.getParentNO();
			String title=article.getTitle();
			String content=article.getContent();
			String id=article.getId();
			String imageFielName=article.getImageFileName();
			String query="INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES(?,?,?,?,?,?)";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFielName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	}

	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article=new ArticleVO();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT articleNO, parentNO, title, content, imageFileName, id, writeDate"
					+ " FROM t_board"
					+ " WHERE articleNO=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			int _articleNO=rs.getInt("articleNO");
			int parentNO=rs.getInt("parentNO");
			String title=rs.getString("title");
			String content=rs.getString("content");
			String imageFileName=rs.getString("imageFileName");
			String id= rs.getString("id");
			Date wrtieDate=rs.getDate("writeDate");
			
			article.setArticleNO(_articleNO);
			article.setParentNO(parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(wrtieDate);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	public void updateArticle(ArticleVO article) {
		int articleNO=article.getArticleNO();
		String title=article.getTitle();
		String content=article.getContent();
		String imageFileName=article.getImageFileName();
		try {
			conn=dataFactory.getConnection();
			String query="UPDATE t_board SET title=?, content=?";
			if(imageFileName!=null && imageFileName.length()!=0) {
				query+=", imageFileName=?";
			}
			query+=" WHERE articleNO=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			if(imageFileName!=null && imageFileName.length()!=0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			}else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeQuery();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteArticle(int articleNO) {
		try {
			conn=dataFactory.getConnection();
			String query="DELETE FROM t_board";
			// 오라클의 계층형 sql문을 이용해 삭제 글과 관련된 자식 글까지 모두 삭제
			query+=" WHERE articleNO in (";
			query+="SELECT articleNO from t_board";
			query+=" START WITH articleNO=?";
			query+=" CONNECT BY PRIOR articleNO=parentNO)";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Integer> selectRemovedArticles(int articleNO) {
		List<Integer> articleNOList=new ArrayList<Integer>();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT articleNO FROM t_board ";
			query+="START WITH articleNO=?";
			query+=" CONNECT BY PRIOR articleNO=parentNO";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				articleNO=rs.getInt("articleNO");
				articleNOList.add(articleNO);
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNOList;
	}

	public int selectTotArticles() {
		try {
			conn=dataFactory.getConnection();
			String query="SELECT count(articleNO) FROM t_board";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				return (rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
