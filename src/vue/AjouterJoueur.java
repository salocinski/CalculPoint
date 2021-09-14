package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Joueur;
import model.Partie;

public class AjouterJoueur extends JFrame
{
	private static String TITRE = "Ajouter un nouveau joueur";
	private static int L_FENETRE = 450;
	private static int H_FENETRE = 100;
	private Partie partie;
	
	public AjouterJoueur(Partie partie)
	{
		//Init de la frame
		this.setSize(L_FENETRE, H_FENETRE);
		this.setTitle(TITRE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.partie = partie;
		
		//Définition du panel
		JPanel contenu = new JPanel();
		contenu.setLayout(new BorderLayout());
		
		JPanel panelSaisie = new JPanel();
		JLabel message = new JLabel("Nom du joueur : ");
		JTextField saisie = new JTextField();
		saisie.setPreferredSize(new Dimension(85, 25));

		panelSaisie.add(message);
		panelSaisie.add(saisie);
		
		//Création PanelBouton + Bouton
		JPanel panelBouton = new JPanel();
		panelBouton.setSize(100, 150);
		JButton ajouterJoueur = new JButton("Valider");
		JButton annuler = new JButton("Annuler");
		panelBouton.add(ajouterJoueur);
		panelBouton.add(annuler);
		
		contenu.add(panelSaisie, BorderLayout.CENTER);
		contenu.add(panelBouton, BorderLayout.SOUTH);
		
		this.add(contenu);
		
		//Définir bouton par défaut si on presse sur la touche "ENTER"
		getRootPane().setDefaultButton(ajouterJoueur);
		
		ajouterJoueur.addActionListener(new ActionListener()
		{
			private Partie partieEnCours = partie;
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String nomJoueur = saisie.getText();
				Joueur joueur = new Joueur(nomJoueur);
				partieEnCours.ajouterJoueur(joueur);
				CreerPartie partie = new CreerPartie(partieEnCours);
				partie.setVisible(true);
				dispose();
			}
		});
		
		annuler.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
				setVisible(false);
			}
		});
		
	}
}