package exemple.jdbc.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import exemple.jdbc.entity.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	public static void main(String[] a) {

		FournisseurDaoJdbc ofo = new FournisseurDaoJdbc();
		List<Fournisseur> listeFour = ofo.extraire();
		for (Fournisseur fo : listeFour) {
			System.out.println(fo);
		}
	}

	public List<Fournisseur> extraire() {

		Connection connection = null;
		List<Fournisseur> listefour = new ArrayList<Fournisseur>();

		try {
			connection = getConnection();// jeton de permission et d'accés à la base
			/**
			 * récupérer un statement = accés aux données à partir de l'objet connection
			 * récupérer le resultat de la requéte ajouter ligne par ligne dans la liste des
			 * founisseurs
			 */
			// recupérer un buffer d'échange avec la base
			// un tuyau de communication por echanger avec la 	bdd pour faire des requétes
       System.err.println(connection);
			Statement monCanal = connection.createStatement();
			ResultSet monResultat = monCanal.executeQuery("select * from fournisseur;");

			while (monResultat.next()) {
				listefour.add(new Fournisseur(monResultat.getInt("id"), monResultat.getString("nom")));
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

		return listefour;
	}

	public void insert(Fournisseur fournisseur) {

	}

	public Connection getConnection() {
		ResourceBundle db = ResourceBundle.getBundle("database");

		try {
			Class.forName(db.getString("db.driver"));
			System.err.println("classe chargée");
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
	public boolean delete(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		return false;
	}
}
