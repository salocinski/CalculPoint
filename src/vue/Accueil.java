package vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vue.Accueil;

public class Accueil extends JFrame
{
	private static String TITRE_FENETRE = "Calculatrice de points";
	private static int L_FENETRE = 450;
	private static int H_FENETRE = 150;
	
	public static void main(String[] args)
	{
		// Déclaration et configuration de la frame
		Accueil main = new Accueil();
		main.setSize(L_FENETRE, H_FENETRE);
		main.setTitle(TITRE_FENETRE);
		main.setResizable(false);
		main.setLocationRelativeTo(null);
		
		//Déclaration du panel de la fenetre et du Layout
		JPanel contenu = new JPanel();
		contenu.setLayout(new BorderLayout());
		
		//Définition du panel TITRE et configuration de ses éléments
		JLabel titre = new JLabel("Calculatrice de points");
		JPanel panelTitre = new JPanel();
//		panelTitre.setSize(450, 150);
		titre.setFont(new Font ("Arial", Font.BOLD, 16));
		titre.setHorizontalAlignment(JLabel.CENTER);
		
		//Ajout des élément au panel TITRE
		panelTitre.add(titre);
		
		JButton lancer = new JButton("Lancer une partie");
		JButton quitter = new JButton("Quitter");
		JLabel signature = new JLabel("Développé par Nicolas STRYJEWSKI");
		
		//Définition du panel bouton et configuration de ses éléments
		JPanel panelBouton = new JPanel();
//		lancer.setSize(150,50);
//		quitter.setSize(150,50);
		
		//Ajout des éléments au panel BOUTON
		panelBouton.add(lancer);
		panelBouton.add(quitter);
		
		//Définition du panel SIGNATURE et configuration de ses éléments
		JPanel panelSignature = new JPanel();
		signature.setHorizontalAlignment(JLabel.CENTER);
		
		//Ajout des éléments au panel SIGNATURE
		panelSignature.add(signature);
		
		//Ajout des éléments au panel CONTENU
		contenu.add(panelTitre, BorderLayout.NORTH);
		contenu.add(panelBouton, BorderLayout.CENTER);
		contenu.add(panelSignature, BorderLayout.SOUTH);
		
		//Ajout du panel sur la frame
		main.add(contenu);
		
		/*Affichage de la frame*/
		main.setVisible(true);
		
		lancer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				CreerPartie nouvellePartie = new CreerPartie();
				nouvellePartie.setVisible(true);
				main.dispose();
			}
		});
		
		quitter.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JOptionPane d = new JOptionPane();
				int retour = d.showConfirmDialog(main, "Voulez-vous vraiment quitter l'application ?", 
				      "Quitter", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if(retour == 0)
					System.exit(0);
				else
					JOptionPane.getRootFrame().dispose(); 
			}
		});
	}
}
