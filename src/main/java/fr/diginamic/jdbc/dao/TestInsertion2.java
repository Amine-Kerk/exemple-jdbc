package fr.diginamic.jdbc.dao;


import fr.diginamic.jdbc.entites.Fournisseur;

public class TestInsertion2 {
	public static void main(String[] args){
		FournisseurDaoJdbc2 dao = new FournisseurDaoJdbc2();
		dao.insert(new Fournisseur(4,"La Maison de la Peinture"));
		dao.insert(new Fournisseur(5,"L’Espace Création"));
	}
}
