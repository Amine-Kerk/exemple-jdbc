package exemple.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import exemple.jdbc.entity.Article;


public class ArticleDaoJdbc implements ArticleDao {

	public static void main(String[] a) {
	
		ArticleDaoJdbc ofo = new ArticleDaoJdbc();
		List<Article> listeArt = ofo.extraire();
		for (Article art : listeArt) {
			System.out.println(art);
		}

	}
	
	public List<Article> extraire() {

		Connection connection = null;
		List<Article> listeArt = new ArrayList<Article>();

		try {
			connection = getConnection();// jeton de permission et d'accés à la base
			/**
			 * récupérer un statement = accés aux données à partir de l'objet connection
			 * récupérer le resultat de la requéte ajouter ligne par ligne dans la liste des
			 * founisseurs
			 */
			// recupérer un buffer d'échange avec la base
			// un tuyau de communication por echanger avec la 	bdd pour faire des requétes
       
			Statement monCanal = connection.createStatement();
			ResultSet monResultat = monCanal.executeQuery("select * from article;");

			while (monResultat.next()) {
				listeArt.add(new Article(monResultat.getInt("id"), 
						monResultat.getString("ref"),
						monResultat.getString("designation"),
						monResultat.getDouble("prix"),
						monResultat.getInt("id_fou")));
			}

			monResultat.close();
			monCanal.close();

		} catch (Exception e) {
			System.err.println("Erreur d'écecution" + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e)

			{
				System.err.println("probleme de connection close: " + e.getMessage());
			}

		}

		return listeArt;
	}

	public void insert(Article article) {

	}

	public Connection getConnection() {
		ResourceBundle db = ResourceBundle.getBundle("database");

		try {
			Class.forName(db.getString("db.driver"));
			
			return DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));

		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
           throw new RuntimeException();
		}
	
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Article article) {
		// TODO Auto-generated method stub
		return false;
	}
}
