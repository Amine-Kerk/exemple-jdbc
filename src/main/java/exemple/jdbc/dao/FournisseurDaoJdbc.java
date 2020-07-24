package exemple.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import exemple.jdbc.entity.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	public List<Fournisseur> extraire(){
		
		Connection connection = null ;
		List<Fournisseur> listefour = new ArrayList<Fournisseur>();
		
		try {
		 connection = getConnection();
		
	} catch (Exception e) {
		System.err.println("Erreur d'écecution" +e.getMessage());
	}
		finally {
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) 
			
			{
				System.err.println("probleme de connection close: "+e.getMessage());
			}
			
		}
		
		return listefour;
	}
		
	public void insert (Fournisseur fournisseur) {
		
	}
	
	public int update (Fournisseur fournisseur ) {
		return false;
	}
	
	public Connection getConnection() {
		ResourceBundle db = ResourceBundle.getBundle("database");
		
		try {
			class.forName(db.getString("db.driver"));
			return DriverManger.getConnection(db.getString("db.url"),db.getString("db.user"),db.getString("db.pass"));
			
		} catch (ClassNotFoundException | SQLException e ) {
			
		}
	}
}
