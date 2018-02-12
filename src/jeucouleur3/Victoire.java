import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public bool victoire (Pion dernierPion){
	
	//Vérification d'égalité
	
	if (nbCoupsJoues == 36){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon tieGame = new ImageIcon("java/egalite.png");
		jop1.showMessageDialog(null, "Match Nul! Fin de la partie", "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, tieGame);
	}
	int x = dernierPion.getX;
	int y = dernierPion.getY;
	String joueurVictorieux;
	String messageVictoire = "Victoire du joueur ";
	String annonce = messageVictoire + joueurVictorieux;
	
	//Détermination de celui qui vient de jouer
	
	if (joueur1.joueurFromCouleur(dernierPion.getCouleur) != null){
		joueurVictorieux = joueur1.joueurFromCouleur(dernierPion.getCouleur);
	}
	else {joueurVictorieux = joueur2.joueurFromCouleur(dernierPion.getCouleur)};
	
	//Vérification de victoire
	
	if ((plateau_de_jeu.getPion(x+1, y).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x+2, y).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x-1, y).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x-2, y).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x+1, y).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x-1, y).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x, y+1).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x, y+2).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x, y-1).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x, y-2).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x, y+1).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x, y-1).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x+1, y+1).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x+2, y+2).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x-1, y-1).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x-2, y-2).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x+1, y+1).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x-1, y-1).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x+1, y-1).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x+2, y-2).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x-1, y+1).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x-2, y+2).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
	if ((plateau_de_jeu.getPion(x+1, y-1).getCouleur == dernierPion.getCouleur) && (plateau_de_jeu.getPion(x-1, y+1).getCouleur == dernierPion.getCouleur)){
		//fin de la partie
		JOptionPane jop1 = new JOptionPane();
		ImageIcon victory = new ImageIcon("java/victoire.png");
		jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
	}
}