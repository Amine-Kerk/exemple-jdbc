package exemple.jdbc.dao;

import java.util.List;

import exemple.jdbc.entity.Article;


public interface ArticleDao {

	List<Article> extraire();
	void insert (Article article);
	int update (String ancienDesignation,String nouveauDesignation);
	boolean delete (Article article);
}
