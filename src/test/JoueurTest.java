package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Joueur;

class JoueurTest
{

	@Test
	void testConstructeurCreationJoueurGetPrenom()
	{
		Joueur nouveauJoueur = new Joueur("Nicolas");
		
		if(!nouveauJoueur.getPrenom().equals("Nicolas"))
			fail("Création joueur - Erreur");
		else
			System.out.println("Création nouveau joueur - OK");
	}
	
	@Test
	void testGetNbTours()
	{
		Joueur nouveauJoueur = new Joueur("Nicolas");
		
		if(nouveauJoueur.getNbTours() != 1)
			fail("Get nb tours - Erreur");
		else
			System.out.println("Get nb tours - OK");
	}

	@Test
	void testSetNbTours()
	{
		Joueur nouveauJoueur = new Joueur("Nicolas");
		nouveauJoueur.setNbTours(5);
		
		if(nouveauJoueur.getNbTours() != 5)
			fail("Set nb tours - Erreur");
		else
			System.out.println("Set nb tours - OK");
	}
	
	@Test
	void testAjouterPoints()
	{
		Joueur nouveauJoueur = new Joueur("Nicolas");
		nouveauJoueur.ajouterPoints(300);
		
		if(nouveauJoueur.getScore() != 300)
			fail("Ajouter points - Erreur");
		else
			System.out.println("Ajouter points - OK");
	}
	
	@Test
	void testAjouterTours()
	{
		Joueur nouveauJoueur = new Joueur("Nicolas");
		nouveauJoueur.ajouterTours();
		
		if(nouveauJoueur.getNbTours() != 2)
			fail("Ajouter tours - Erreur");
		else
			System.out.println("Ajouter tours - OK");
	}
}
