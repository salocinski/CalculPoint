package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vue.Classement;
import model.Joueur;
import model.Partie;

public class CreerPartie extends JFrame
{
	private static String TITRE = "Créer une nouvelle partie";
	private static int L_FENETRE = 450;
	private static int H_FENETRE = 200;
	
	public CreerPartie()
	{
		//Init de la frame
		this.setSize(L_FENETRE, H_FENETRE);
		this.setTitle(TITRE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		Partie partie = new Partie();
		
		//Définition du panel
		JPanel contenu = new JPanel();
		contenu.setLayout(new BorderLayout());
		
		//Création PanelBouton + Bouton
		JPanel panelBouton = new JPanel();
		panelBouton.setSize(450, 150);
		JButton ajouterJoueur = new JButton("Ajouter un joueur");
		JButton changerScoreLimite = new JButton("Changer score limite");
		JButton lancerPartie = new JButton("Lancer la partie");
		JButton quitter = new JButton("Quitter");
		panelBouton.add(ajouterJoueur);
		panelBouton.add(changerScoreLimite);
		
		if(!partie.getListeJoueurs().isEmpty())
			panelBouton.add(lancerPartie);
		
		panelBouton.add(quitter);
		
		JPanel panelTitreTableauJoueur = new JPanel();
		JLabel titreTableauScore = new JLabel("Joueur(s) de la partie");
		panelTitreTableauJoueur.add(titreTableauScore);
		
		JPanel panelTableauJoueur =  new JPanel();
		ArrayList<Joueur> listeJoueur = partie.getListeJoueurs();

		if(listeJoueur != null)
		{
			for (Joueur joueur : listeJoueur)
			{
				JLabel nomJoueur = new JLabel(joueur.getPrenom());
				panelTableauJoueur.add(nomJoueur);
			}
		}
		else
		{
			JLabel messageParDefaut = new JLabel("Aucun joueur enregistré pour le moment.");
			panelTableauJoueur.add(messageParDefaut);
		}
		
		//Ajout des éléments
		contenu.add(panelBouton, BorderLayout.NORTH);
		contenu.add(panelTitreTableauJoueur, BorderLayout.CENTER);
		contenu.add(panelTableauJoueur, BorderLayout.SOUTH);
		this.add(contenu);
		
		changerScoreLimite.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String saisieScoreLimite = JOptionPane.showInputDialog(null, "Quel sera le score limite de la partie ?","Initialisation score limite",JOptionPane.PLAIN_MESSAGE);
				System.out.println("AVANT - le score limite est de " + partie.getScoreLimite());
				partie.setScoreLimite(Integer.parseInt(saisieScoreLimite));
				System.out.println("APRES - le score limite est de " + partie.getScoreLimite());
			}
		});
		
		ajouterJoueur.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				AjouterJoueur nouveauJoueur = new AjouterJoueur(partie);
				nouveauJoueur.setVisible(true);
				dispose();
			}
		});
		
		lancerPartie.addActionListener(new ActionListener()
		{
			private Partie partieEnCours = partie;
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Classement classement = new Classement(partieEnCours);
				classement.setVisible(true);
				dispose();
			}
		});
		
		quitter.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JOptionPane d = new JOptionPane();
				int retour = d.showConfirmDialog(CreerPartie.this, "Voulez-vous vraiment quitter l'application ?", 
				      "Quitter", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if(retour == 0)
					System.exit(0);
				else
					JOptionPane.getRootFrame().dispose(); 
			}
		});
	}
	
	public CreerPartie(Partie partieEnCours)
	{
		//Init de la frame
		this.setSize(L_FENETRE, H_FENETRE);
		this.setTitle(TITRE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		Partie partie = partieEnCours;
		
		//Définition du panel
		JPanel contenu = new JPanel();
		contenu.setLayout(new BorderLayout());
		
		//Création PanelBouton + Bouton
		JPanel panelBouton = new JPanel();
		panelBouton.setSize(450, 150);
		JButton ajouterJoueur = new JButton("Ajouter un joueur");
		JButton lancerPartie = new JButton("Lancer la partie");
		JButton quitter = new JButton("Quitter");
		panelBouton.add(ajouterJoueur);
		panelBouton.add(lancerPartie);
		panelBouton.add(quitter);
		
		JPanel panelTitreTableauJoueur = new JPanel();
		JLabel titreTableauScore = new JLabel("Joueur(s) de la partie", SwingConstants.CENTER);
		Dimension dimensionLabel = new Dimension(400, 25);
		titreTableauScore.setPreferredSize(dimensionLabel);
		panelTitreTableauJoueur.add(titreTableauScore);
		
		JPanel panelTableauJoueur =  new JPanel();
		ArrayList<Joueur> listeJoueur = partie.getListeJoueurs();

		if(listeJoueur != null)
		{
			System.out.println(partie.toString());
			for (Joueur joueur : listeJoueur)
			{
				JLabel nomJoueur = new JLabel(joueur.getPrenom() + " - ", SwingConstants.CENTER);
//				Dimension dimensionLabelNomJoueur = new Dimension(400, 25);
//				nomJoueur.setPreferredSize(dimensionLabelNomJoueur);
//				JLabel scoreJoueur = new JLabel(Integer.toString(joueur.getScore()));
				
				panelTableauJoueur.add(nomJoueur);
//				panelTableauJoueur.add(scoreJoueur);
			}
		}
		else
		{
			JLabel messageParDefaut = new JLabel("Aucun joueur enregistré pour le moment.");
			panelTableauJoueur.add(messageParDefaut);
		}
		
		//Ajout des éléments
		contenu.add(panelBouton, BorderLayout.NORTH);
		contenu.add(panelTitreTableauJoueur, BorderLayout.CENTER);
		contenu.add(panelTableauJoueur, BorderLayout.SOUTH);
		this.add(contenu);
		
		ajouterJoueur.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				AjouterJoueur nouveauJoueur = new AjouterJoueur(partie);
				nouveauJoueur.setVisible(true);
				dispose();
			}
		});
		
		lancerPartie.addActionListener(new ActionListener()
		{
			private Partie partieEnCours = partie;
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Classement classement = new Classement(partieEnCours);
				classement.setVisible(true);
				dispose();
			}
		});
		
		quitter.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JOptionPane d = new JOptionPane();
				int retour = d.showConfirmDialog(CreerPartie.this, "Voulez-vous vraiment quitter l'application ?", 
				      "Quitter", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if(retour == 0)
					System.exit(0);
				else
					JOptionPane.getRootFrame().dispose(); 
			}
		});
	}
}