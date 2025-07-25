package Board02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO=new BoardDAO();	// 생성자 호출 시 BoardDAO 객체 생성
	}
	
	public List<ArticleVO> listArticles(){
		List<ArticleVO> articlesList=boardDAO.selectAllArticles();
		return articlesList;
	}
	
	public void addArticle(ArticleVO article) {
		boardDAO.insertNewArticle(article);
	}
}
