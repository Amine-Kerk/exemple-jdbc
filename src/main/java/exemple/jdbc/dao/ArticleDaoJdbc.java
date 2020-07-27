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
		
		ofo.update("Marteau piqueur","Marteau");
		listeArt = ofo.extraire();
		for (Article ar : listeArt) {
			System.out.println(ar);
	}
		if (ofo.delete(new Article(11,"XX0","Marteau",100.0,1)) ) System.out.println("Article supprimé !");
		listeArt = ofo.extraire();
		for (Article ar : listeArt) {
			System.out.println(ar);
		}
		
//		ofo.insert(new Article(11,"XX0","Marteau piqueur",100.0,1));
//		listeArt = ofo.extraire();
//	    for (Article fo : listeArt) {
//     	System.out.println(fo);
//	}

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
	@Override
	public void insert(Article article) {
		Connection connection = null;
		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			int nb = monCanal.executeUpdate("insert into article (id,ref,designation,prix,id_fou) values " + "(" 
			+ article.getId() + ",'"
			+ article.getRef()+ "','"
			+ article.getDesignation() +"',"
			+ article.getPrix() +","
			+ article.getId_fou()+")");

			if (nb == 1) {
				System.out.println("article ajouté !");
			}

			monCanal.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());

		}

		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e)

			{
				System.err.println("probleme de connection close : " + e.getMessage());
			}
		}
	}

	@Override
	
	public int update(String ancienDesignation,String nouveauDesignation) {
		Connection connection = null;
		int nb = 0;
		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			nb = monCanal
					.executeUpdate("update article SET designation= '" + nouveauDesignation + "'where designation='" + ancienDesignation+ "';");

			monCanal.close();

		} catch (Exception e) {
			System.err.println("erreur d'execution : " + e.getMessage());

		}

		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e)

			{
				System.err.println("probleme de connection close : " + e.getMessage());
			}
		}

		return nb;
	}

	@Override
	public boolean delete(Article article ) {
		Connection connection = null;
		boolean nb = false;
		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			
			if (monCanal.executeUpdate("delete from article where id ="+article.getId()+";")==1)
            nb = true ;
			monCanal.close();
		
		} 
	catch (Exception e) {
			System.err.println("erreur d'execution : " + e.getMessage());

		}

		finally {
			try {
				if (connection != null) connection.close();
			}
			 catch (SQLException e)

			{
				System.err.println("probleme de connection close : " + e.getMessage());
			}
		
		}

		return nb;
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
}
