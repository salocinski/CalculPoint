package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Joueur;
import model.Partie;

class PartieTest
{
	@Test
	void testSetScoreLimite()
	{
		Partie partieTest = new Partie();
		partieTest.setScoreLimite(700);
		
		int scoreLimite = partieTest.getScoreLimite();
		
		if(scoreLimite == 500)
			fail("Erreur cas de test 'set score limite'");
		else
			System.out.println("Cas de test 'set score limite' - OK");
	}

	@Test
	void testSetScoreLimiteDefaut()
	{
		Partie partieTest = new Partie();
		
		int scoreLimite = partieTest.getScoreLimite();
		
		if(scoreLimite != 500)
			fail("Erreur cas de test 'score limite par défaut'");
		else
			System.out.println("Cas de test 'score limite par défaut' - OK");
	}
	
	@Test
	void testNbToursDefaut()
	{
		Partie partieTest = new Partie();
		int nbTours = partieTest.getNombreTours();
		
		if(nbTours != 1)
			fail("Erreur cas de test 'nb tours par défaut'");
		else
			System.out.println("Cas de test 'nb tours par défaut' - OK");
	}
	
	@Test
	void testSetNbTours()
	{
		Partie partieTest = new Partie();
		partieTest.ajouterTour();
		
		if(partieTest.getNombreTours() == 1)
			fail("Erreur cas de test 'set nombre tours'");
		else
			System.out.println("Cas de test 'set nb tours' - OK");
	}
	
	@Test
	void testFinDePartieOk()
	{
		Partie partieTest = new Partie();
		ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
		Joueur joueur_un = new Joueur("Nicolas");
		joueur_un.ajouterPoints(600);
		
		Joueur joueur_deux = new Joueur("Elodie");
		joueur_deux.ajouterPoints(300);
		
		listeJoueurs.add(joueur_un);
		listeJoueurs.add(joueur_deux);
		
		Joueur perdant = partieTest.finDePartie(listeJoueurs);
		
		if(perdant == null)
			fail("Erreur cas de test 'fin de partie OK'");
		else
			System.out.println("Cas de test 'fin de partie' - OK");
	}
	
	@Test
	void testPasDeFinDePartie()
	{
		Partie partieTest = new Partie();
		ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
		Joueur joueur_un = new Joueur("Nicolas");
		joueur_un.ajouterPoints(200);
		
		Joueur joueur_deux = new Joueur("Elodie");
		joueur_deux.ajouterPoints(300);
		
		listeJoueurs.add(joueur_un);
		listeJoueurs.add(joueur_deux);
		
		Joueur perdant = partieTest.finDePartie(listeJoueurs);
		
		if(perdant != null)
			fail("Erreur cas de test 'pas de fin de partie'");
		else
			System.out.println("Cas de test 'pas de fin de partie' - OK");
	}
	
	@Test
	void testAjouterJoueur()
	{
		Partie partieTest = new Partie();
		ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
		Joueur joueur_un = new Joueur("Nicolas");
		partieTest.ajouterJoueur(joueur_un);
		
		int tailleListeJoueur = listeJoueurs.size();
		
		if(tailleListeJoueur != 1)
			fail("Erreur cas de test 'ajouter un joueur à la liste joueur'");
		else
			System.out.println("Cas de test 'ajouter joueur à la liste' - OK");
	}
	
	@Test
	void testInitialiserListeJoueur()
	{
		Partie partieTest = new Partie();
		
		if (partieTest.getListeJoueurs().size() != 0)
			fail("Erreur cas de test 'initialiser liste joueur");
		else
			System.out.println("Cas de test 'initialiser liste joueur' - OK");
	}
}
