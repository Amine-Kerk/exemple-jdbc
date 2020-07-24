package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class ArticleDaoJdbc implements ArticleDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public List<Article> extraire() throws SQLException {
		loadBdd();
		// fait un SELECT dans la base de compta sur la table fournisseur
		ArrayList<Article> articles = new ArrayList<Article>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM fournisseur");
			resultSet = preparedStatement.executeQuery();
			List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
			FournisseurDaoJdbc2 dao = new FournisseurDaoJdbc2();
			fournisseurs = dao.extraire();
			while (resultSet.next()) {
				int idFournisseur = resultSet.getInt("fk_fournisseur");
				String nomFournisseur = "";
				for (Fournisseur fournisseur : fournisseurs) {
					if (fournisseur.getId()==idFournisseur)
						nomFournisseur = fournisseur.getNom();
				}
				articles.add(new Article(resultSet.getInt("id"), resultSet.getString("ref"), resultSet.getString("designation"), resultSet.getDouble("prix"), new Fournisseur(idFournisseur, nomFournisseur)));
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			closeBdd();
		}
		closeBdd();
		
		return articles;
	}

	public void insert(Article article) throws SQLException {
		loadBdd();
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO article (id, ref, designation, prix, fk_fournisseur) VALUES ("+article.getId()+", '"+article.getRef()+", "+article.getDesignation()+","+article.getPrix()+","+article.getFournisseur().getId()+"') ");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			closeBdd();
		}
	}

	public int update(String ancienNom, String nouveauNom) throws SQLException {
		loadBdd();
		int nombreLignes = 0;
		try {
			preparedStatement = connection.prepareStatement("UPDATE article SET nom='"+nouveauNom+"' WHERE nom='"+ancienNom+"'");
			nombreLignes = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			closeBdd();
		}
		closeBdd();
		return nombreLignes;
	}

	public boolean delete(Article article) throws SQLException {
		loadBdd();
		int nombreLignes=0;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM article where id="+article.getId());
			nombreLignes = preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			closeBdd();
		}
		closeBdd();
		if (nombreLignes==0)
			return false;
		else
			return true;
	}
	
	public void loadBdd() throws SQLException {
		// recupere le fichier properties
		ResourceBundle db = ResourceBundle.getBundle("database");

		// enregistre le pilote
		try {
			Class.forName(db.getString("db.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// creer la connection
		connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));

		// affiche la connexion
		boolean valid = false;
		valid = connection.isValid(500);
		if (valid)
			System.out.println("La connection est ok");
		else
			System.out.println("Il y a une erreur de connection");
		

	}

	void closeBdd() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
