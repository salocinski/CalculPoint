package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import model.Joueur;
import model.Partie;

public class Classement extends JFrame
{
	private static String TITRE = "Classement de la partie";
	private final String[] EN_TETES = {"Position", "Prénom", "Score"};
	private static int L_FENETRE = 450;
	private static int H_FENETRE = 450;
	private Partie partie;

	public Classement(Partie partie)
	{
		if(partie.finDePartie(partie.getListeJoueurs()) != null)
		{
			Joueur perdant = partie.finDePartie(partie.getListeJoueurs());
			JOptionPane.showMessageDialog(null, "La partie est finie. " + perdant.getPrenom() + " a dépassé le score limite de la partie.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			//Init de la frame
			this.setSize(L_FENETRE, H_FENETRE);
			this.setTitle(TITRE);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			
			this.partie = partie;
			
			JPanel contenu = new JPanel();
			contenu.setLayout(new BorderLayout());
			Dimension dimensionLabel = new Dimension(120, 15);
			
			JPanel panelTableauTitre = new JPanel();
			
			//Générer auto nom colonne selon constante de classe
			for(String nomColonne : EN_TETES)
			{
				JLabel colonne = new JLabel(nomColonne);
				colonne.setPreferredSize(dimensionLabel);
				colonne.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
				panelTableauTitre.add(colonne);
			}
			
			JPanel panelClassement = new JPanel();
			
			if(!partie.getListeJoueurs().isEmpty())
			{
				int i = 1;
				//On trie la liste pour afficher le classement dans l'ordre croissant
				List triListe = (partie.getListeJoueurs());
				Collections.sort(triListe, new Comparator<Joueur>()
					{
						@Override
					    public int compare(Joueur j1, Joueur j2) {
					        if (j1.getScore() > j2.getScore())
					            return 1;
					        if (j1.getScore() < j2.getScore())
					            return -1;
					        return 0;
					    }
					}
				);

				for(Joueur joueur : partie.getListeJoueurs())
				{
					JLabel valeurJoueurPosition = new JLabel("#" + Integer.toString(i));
					valeurJoueurPosition.setPreferredSize(dimensionLabel);
					
					JLabel valeurJoueurNom = new JLabel(joueur.getPrenom());
					valeurJoueurNom.setPreferredSize(dimensionLabel);
					
					JLabel valeurJoueurScore = new JLabel(Integer.toString(joueur.getScore()));
					valeurJoueurScore.setPreferredSize(dimensionLabel);
					
					panelClassement.add(valeurJoueurPosition);
					panelClassement.add(valeurJoueurNom);
					panelClassement.add(valeurJoueurScore);
					
					i++;
				}
			}
			else
			{
				JLabel messageDefaut = new JLabel("Aucun joueur dans la partie");
				panelClassement.add(messageDefaut);
			}
			
			JPanel panelBouton = new JPanel();
			JButton finirLeTour = new JButton("Ajouter les points");
			JButton quitter = new JButton("Quitter la partie");
			panelBouton.add(finirLeTour);
			panelBouton.add(quitter);
	
			contenu.add(panelTableauTitre, BorderLayout.NORTH);
			contenu.add(panelClassement, BorderLayout.CENTER);
			contenu.add(panelBouton, BorderLayout.SOUTH);
			
			this.add(contenu);
			
			finirLeTour.addActionListener(new ActionListener()
			{
				Partie partieEnCours = partie;
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					AjouterScore ajouterScore = new AjouterScore(partieEnCours);
					ajouterScore.setVisible(true);
					dispose();
				}
			});
			
			quitter.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					JOptionPane d = new JOptionPane();
					int retour = d.showConfirmDialog(Classement.this, "Voulez-vous vraiment quitter l'application ?", 
					      "Quitter", JOptionPane.YES_NO_CANCEL_OPTION);
					
					if(retour == 0)
						System.exit(0);
					else
						JOptionPane.getRootFrame().dispose(); 
				}
			});
		}
	}
}
