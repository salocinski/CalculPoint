package model;

public class Joueur
{
	private String prenom;
	private int score;
	private int nbTours;
	
	public Joueur(String prenom)
	{
		this.prenom = prenom;
		this.score = 0;
		this.nbTours = 1;
	}

	public int getNbTours()
	{
		return nbTours;
	}

	public void setNbTours(int nbTours)
	{
		this.nbTours = nbTours;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public int getScore()
	{
		return score;
	}

	public int ajouterPoints(int points)
	{
		return this.score += points;
	}
	
	public int ajouterTours()
	{
		return this.nbTours += 1;
	}

	@Override
	public String toString()
	{
		return "Le joueur '" + prenom + "' a un score de " + score + "pts pour " + nbTours + " tours.";
	}
		
}
