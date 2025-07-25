package Board07;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO=new BoardDAO();	// 생성자 호출 시 BoardDAO 객체 생성
	}
	
	public Map listArticles(Map pagingMap){
		Map articlesMap=new HashMap();
		List<ArticleVO> articlesList=boardDAO.selectAllArticles(pagingMap);	// 전달된 pagingMap을 사용해 글 목록을 조회
		int totArticles=boardDAO.selectTotArticles();	// 테이블에 존재하는 전체 글 수를 조회
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("totArticles", totArticles);
		return articlesMap;
	}
	
	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}
	public ArticleVO viewArticle(int articleNO) {
		ArticleVO article=null;
		article=boardDAO.selectArticle(articleNO);
		return article;
	}
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}

	public List<Integer> removeArticle(int articleNO) {
		List<Integer> articleNOList=boardDAO.selectRemovedArticles(articleNO);	// 글 삭제 전 글 번호들을 ArrayList객체에 저장
		boardDAO.deleteArticle(articleNO);
		return articleNOList;
	}

	public int addReply(ArticleVO articleVO) {
		return boardDAO.insertNewArticle(articleVO);
	}
}
