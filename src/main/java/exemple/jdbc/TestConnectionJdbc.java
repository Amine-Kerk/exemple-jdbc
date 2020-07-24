package exemple.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import exemple.jdbc.TestConnectionJdbc;

/**
 * @author Amine-PC  TP01 
 *
 */
public class TestConnectionJdbc {

	public static void main(String[] args) {

		// recupere le fichier properties7
		ResourceBundle db = ResourceBundle.getBundle("database2");
		Connection connection = null;

		try {

			// enregistre le pilote
			Class.forName(db.getString("db.driver"));
			// creer la connection
			connection = DriverManager.getConnection(
					db.getString("db.url"), 
					db.getString("db.user"),
					db.getString("db.pass"));

			// affiche la connexion
			boolean valid = connection.isValid(500);
			if (valid) {

				System.out.println("La connection est ok");

			} else {
				System.err.println("Il y a une erreur de connection !");
			}

		} catch (SQLException | ClassNotFoundException e) {
			// Handle errors for JDBC
			System.err.println("Erreur de communication avec la base :" + e.getMessage());
		}

		finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {

				System.err.println("erreur de connexion:" + e.getMessage());
			}
			System.err.println("base déconnectée");
		}
	}
}
