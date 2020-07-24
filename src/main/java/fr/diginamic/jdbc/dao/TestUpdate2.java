package fr.diginamic.jdbc.dao;


public class TestUpdate2 {
	public static void main(String[] args) {
		FournisseurDaoJdbc2 dao = new FournisseurDaoJdbc2();
		dao.update("La Maison de la Peinture", "La Maison des Peintures");
	}
}
