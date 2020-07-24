package fr.diginamic.jdbc.entites;

/**
 * @author antoinethebault
 *Article : represente un article avec un id, une reference, une designation, un prix 
 *et un fournisseur
 */
public class Article {
	/**id : int*/
	int id;
	/**ref : String*/
	String ref;
	/**designation : String*/
	String designation;
	/**prix : double*/
	double prix;
	/**fournisseur : Fournisseur*/
	Fournisseur fournisseur;
	
	/**Constructor
	 * @param id
	 * @param ref
	 * @param designation
	 * @param prix
	 * @param fournisseur
	 */
	public Article(int id, String ref, String designation, double prix, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.prix = prix;
		this.fournisseur = fournisseur;
	}

	/**Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**Getter
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**Getter
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**Getter
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**Getter
	 * @return the fournisseur
	 */
	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	/**Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**Setter
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**Setter
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**Setter
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**Setter
	 * @param fournisseur the fournisseur to set
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	
}
