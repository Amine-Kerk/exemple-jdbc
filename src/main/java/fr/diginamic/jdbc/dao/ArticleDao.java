package fr.diginamic.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import fr.diginamic.jdbc.entites.Article;

public interface ArticleDao {
	List<Article> extraire() throws SQLException;
    void insert(Article article) throws SQLException;
    int update(String ancienNom, String nouveauNom) throws SQLException;
    boolean delete(Article article) throws SQLException;
}
