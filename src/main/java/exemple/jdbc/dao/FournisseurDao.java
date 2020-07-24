package exemple.jdbc.dao;

import java.util.List;

import exemple.jdbc.entity.Fournisseur;

/**CRUD
 * @author Amine-PC
 *create :insert
 *read :extraire 
 *update 
 *
 */
public interface FournisseurDao {

	List<Fournisseur> extraire();
	void insert (Fournisseur fournisseur);
	int update (String ancienNom , String nouveauNom);
	boolean delete (Fournisseur fournisseur);
}
