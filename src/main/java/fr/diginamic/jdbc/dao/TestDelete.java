package fr.diginamic.jdbc.dao;


import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDelete {
	public static void main(String[] args) {
		FournisseurDaoJdbc dao = new FournisseurDaoJdbc();
		dao.delete(new Fournisseur(4,"La Maison des Peintures"));
		dao.delete(new Fournisseur(5,"L’Espace Création"));
	}
}
