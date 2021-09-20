package model;

import java.util.ArrayList;

public class Partie
{
	public ArrayList<Joueur> listeJoueurs = null;
	private int scoreLimite = 500;
	private int nombreTours = 1;
	private boolean partieFinie = false;
	
	public void setScoreLimite(int scoreLimite)
	{
		this.scoreLimite = scoreLimite;
	}

	public Partie()
	{
		this.nombreTours = nombreTours;
		this.scoreLimite = scoreLimite;
		listeJoueurs = new ArrayList<Joueur>();
	}

	public int getScoreLimite()
	{
		return scoreLimite;
	}

	public int getNombreTours()
	{
		return this.nombreTours;
	}
	
	public ArrayList<Joueur> getListeJoueurs()
	{
		return listeJoueurs;
	}

	public void ajouterJoueur(Joueur joueur)
	{
		listeJoueurs.add(joueur);
	}
	
	public void ajouterTour()
	{
		this.nombreTours += 1;
	}
	
	public Joueur finDePartie(ArrayList<Joueur> listeJoueurs)
	{
		Joueur perdant = null;
		for(Joueur joueur : listeJoueurs)
		{
			if(joueur.getScore() >= scoreLimite)
			{
				partieFinie = true;
				perdant = joueur;
			}	
		}
		
		return perdant;
	}

	@Override
	public String toString()
	{
		return "Partie : [listeJoueurs=" + listeJoueurs + ", scoreLimite=" + scoreLimite + ", nombreTours=" + nombreTours
				+ ", partieFinie=" + partieFinie + "]";
	}
	
}
