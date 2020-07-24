package fr.diginamic.jdbc.dao;


import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDelete2 {
	public static void main(String[] args) {
		FournisseurDaoJdbc2 dao = new FournisseurDaoJdbc2();
		dao.delete(new Fournisseur(4,"La Maison des Peintures"));
		dao.delete(new Fournisseur(5,"L’Espace Création"));
	}
}
