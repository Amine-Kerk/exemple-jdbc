package fr.diginamic.jdbc.dao;

import java.util.Iterator;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect2 {
	public static void main(String[] args) {
		FournisseurDaoJdbc2 dao = new FournisseurDaoJdbc2();
		List<Fournisseur> fournisseurs = dao.extraire();
		Iterator<Fournisseur> iterator = fournisseurs.iterator();
		while(iterator.hasNext()) {
			Fournisseur fournisseur = iterator.next();
			System.out.println(fournisseur.getId()+" "+fournisseur.getNom());
		}
	}
}
