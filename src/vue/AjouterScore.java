package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Joueur;
import model.Partie;

public class AjouterScore extends JFrame
{
	private static String TITRE = "Ajouter les points";
	private static int L_FENETRE = 450;
	private static int H_FENETRE = 200;
	private Partie partie;
	
	public AjouterScore(Partie partie)
	{
		//Init de la frame
		this.setSize(L_FENETRE, H_FENETRE);
		this.setTitle(TITRE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.partie = partie;
		
		JPanel contenu = new JPanel();
		contenu.setLayout(new BorderLayout());
		ArrayList<String> listeNomJoueur = new ArrayList<String>();
		
		System.out.println("Informations sur la partie : " + partie.toString());
		
		for(Joueur joueur : partie.getListeJoueurs())
			if(joueur.getNbTours() == partie.getNombreTours())
				listeNomJoueur.add(joueur.getPrenom());
		
		JComboBox<Joueur> listeDeroulante = new JComboBox(listeNomJoueur.toArray());
		
		JPanel panelListeDeroulante = new JPanel();
		//panelListeDeroulante.add(listeDeroulante);
		
		JPanel panelSaisie = new JPanel();
		JLabel messageSaisie = new JLabel("Combien de points s'ajoute au score ?");
		JTextField saisie = new JTextField();
		saisie.setPreferredSize(new Dimension(250, 35));
		//panelSaisie.add(messageSaisie);
		//panelSaisie.add(saisie);
		
		JPanel panelBouton = new JPanel();
		JButton valider = new JButton("Ajouter les points");
		JButton finTour = new JButton("Finir le tour");
		JButton annuler = new JButton("Annuler");
		
		if(listeDeroulante.getSelectedItem() != null)
		{
			panelBouton.add(valider);
			panelListeDeroulante.add(listeDeroulante);
			panelSaisie.add(messageSaisie);
			panelSaisie.add(saisie);
		}
		else
		{
			JLabel messageDefaut = new JLabel("Tous les scores ont été saisis");
			panelSaisie.add(messageDefaut);
		}
		
		panelBouton.add(finTour);
		panelBouton.add(annuler);
		
		contenu.add(panelListeDeroulante, BorderLayout.NORTH);
		contenu.add(panelSaisie, BorderLayout.CENTER);
		contenu.add(panelBouton, BorderLayout.SOUTH);
				
		this.add(contenu);
		
		//Définir bouton par défaut si on presse sur la touche "ENTER"
		getRootPane().setDefaultButton(valider);
		
		//Mise en place du focus sur le JTextField de saisie
		//this == JFrame
		this.addWindowListener(new WindowAdapter()
			{
				public void windowOpened(WindowEvent event)
				{
					saisie.requestFocus();
				}
			}
		);
		
		valider.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{				
				String nbPoint = saisie.getText();
				String nomJoueur = (String) listeDeroulante.getSelectedItem();
				
				for(Joueur joueur : partie.getListeJoueurs())
				{
					if(joueur.getPrenom().equals(nomJoueur))
					{
						joueur.ajouterPoints(Integer.parseInt(nbPoint));
						joueur.setNbTours(joueur.getNbTours()+1);
					}
				}
				Classement classement = new Classement(partie);
				classement.setVisible(true);
				dispose();
			}
		});
		
		finTour.addActionListener(new ActionListener()
		{
			Partie partieEnCours = partie;
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				partie.ajouterTour();
				JOptionPane.showMessageDialog(null, "Le tour est fini. On passe au tour N° " + partie.getNombreTours(), "Fin de tour", JOptionPane.INFORMATION_MESSAGE);
				Joueur joueur = partie.finDePartie(partie.getListeJoueurs());
				Classement classement = new Classement(partie);
				classement.setVisible(true);
				dispose();
			}
		});
		
		annuler.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Classement classement = new Classement(partie);
				classement.setVisible(true);
				dispose();
			}
		});
	}

}
