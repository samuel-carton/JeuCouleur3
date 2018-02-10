/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeucouleur3;

import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Loris
 */
public class Fenetre extends JFrame implements ActionListener{
    private JLabel placeholdingy;
    private Grille grille;
    private GridBagConstraints cst;
    private Container c;
    private Jeu partie;
    
    
    
    // Grille de Label
    private Jeton[][] grilleGraphique;
    
    // Pour rentrer le nom et choisir la couleur
    private JLabel labelRentrerNomJoueur1;
    private JLabel labelRentrerNomJoueur2;
    private JTextField rentrerNomJoueur1Field;
    private JTextField rentrerNomJoueur2Field;
    private JButton rentrerNomJoueursSubmit;
    private JComboBox colorChoiceJoueur1;
    private JComboBox colorChoiceJoueur2;
    
    // Dans le hub
    private JLabel labelNomJoueur1;
    private JLabel labelNomJoueur2;
    
    // Type de parties
    private JButton partieContreAI;
    private JButton partieEntre2Joueurs;
    
    // Boutons Colonnes
    private JButton[] boutonsColonnes;
    
    public Fenetre(Jeu p){
        setSize(500,500);
        setTitle("Puissance 3 !! Alignez-en trois, et c'est gagné !");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        instanciation();
        this.partie = p;
        this.setVisible(true);
    }
    
    private void instanciation(){
        
        // Allocation
        placeholdingy = new JLabel("Je ne sers à rien !");
        c = getContentPane(); // Container
        
        // Noms des Joueurs
        labelRentrerNomJoueur1 = new JLabel("Player One");
        labelRentrerNomJoueur2 = new JLabel("Player Two");
        rentrerNomJoueur1Field = new JTextField(20);
        rentrerNomJoueur2Field = new JTextField(20);
        rentrerNomJoueursSubmit = new JButton("Valider");
        
        labelNomJoueur1 = new JLabel();
        labelNomJoueur2 = new JLabel();
        
        
        // Jeu [ HUB ]
        SetupGrilleGraphique();
        
        // Choix des couleurs attribuées à chaque joueur
        colorChoiceJoueur1 = new JComboBox();
        colorChoiceJoueur2 = new JComboBox();
        
        // Choix de partie
        partieContreAI = new JButton("Partie avec AI");
        partieEntre2Joueurs = new JButton("Partie avec Joueurs");
        
        
        // Layout
        GridBagLayout gbd = new GridBagLayout();
        cst = new GridBagConstraints();
        c.setLayout(gbd);
        
        // Boutons Colonnes
        ImageIcon ic = new ImageIcon("src\\jeucouleur3\\fleche_bas.png");
        boutonsColonnes = new JButton[6];
        for (int i = 0; i < 6; i++) {
            boutonsColonnes[i] = new JButton(ic);
        }
        
        // Action Listeners
        partieContreAI.addActionListener(this);
        partieEntre2Joueurs.addActionListener(this);
        rentrerNomJoueursSubmit.addActionListener(this);
        for (int i = 0; i < 6; i++) {
            boutonsColonnes[i].addActionListener(this);
        }
        
        // Finalisation
        setContentPane(c);
    }
    
    public void SetupGrilleGraphique(){
        grilleGraphique = new Jeton[6][6];
        for (int i = 0; i < 6; i++) {
            grilleGraphique[i] = new Jeton[6];
        }
        for( int i = 0; i < 6; i ++){
            for ( int j = 0; j < 6; j ++){
                grilleGraphique[i][j] = new Jeton();
            }
        }
    }
    
    public void AffichageGrilleGraphique(){
        // Refresh grille graphique
        for( int i = 0; i < 6; i ++){
            for ( int j = 0; j < 6; j ++){
                if (this.partie.getPlateau_de_jeu().getPion(i, j) != null){
                    grilleGraphique[i][j].setCouleur(this.partie.getPlateau_de_jeu().getPion(i, j).getCouleur());
                }
            }
        }
        // Affichage
        for( int i = 0; i < 6; i ++){
            for ( int j = 3; j < 9; j ++){
                cst.gridx = i;
                cst.gridy = j;
                c.add(this.grilleGraphique[i][j-3], cst);
            }
        }
    }
    
    
    public void initJoueurs(){
        c.removeAll();
        
        
        cst.gridx = 0;
        cst.gridy = 0;
        c.add(partieContreAI, cst);
        cst.gridy = 2;
        c.add(partieEntre2Joueurs, cst);
        this.setContentPane(c);
        this.revalidate();
        this.repaint();
    }
    
    
    // TODO
    public void rentrerNomJoueurs(){
        c.removeAll();
        
        // Noms de joueurs + AI ( Label et TextField non modifiable)
        cst.gridx = 0;
        cst.gridy = 0;
        c.add(this.labelRentrerNomJoueur1, cst);
        cst.gridx = 1;
        c.add(this.labelRentrerNomJoueur2, cst);
        cst.gridx = 0;
        cst.gridy = 1;
        c.add(this.rentrerNomJoueur1Field, cst);
        if ( !this.partie.getJoueur1().isHuman() ){
            rentrerNomJoueur1Field.setText(this.partie.getJoueur1().getNom());
            rentrerNomJoueur1Field.setEditable(false);
        }
        cst.gridx = 1;
        c.add(this.rentrerNomJoueur2Field, cst);
        
        
        
        // Choix des couleurs attribuées
        Color tabColors[] = {Color.BLUE, Color.CYAN, Color.RED, Color.GREEN, Color.ORANGE, Color.PINK, Color.MAGENTA, Color.DARK_GRAY};
        String tabColorsString[] = {"BLUE", "CYAN", "RED", "GREEN", "ORANGE", "PINK", "MAGENTA", "DARK_GRAY"};
        colorChoiceJoueur1 = new JComboBox(tabColorsString);
        colorChoiceJoueur2 = new JComboBox(tabColorsString);
        cst.gridx = 0;
        cst.gridwidth = 1;
        cst.gridy = 2;
        c.add(this.colorChoiceJoueur1, cst);
        
        cst.gridx = 1;
        c.add(this.colorChoiceJoueur2, cst);
        
        
        
        
        
        //if ( colorChoiceJoueur1.getSelectedItem() == colorChoiceJoueur2.getSelectedItem() ){
        //    rentrerNomJoueursSubmit.setEnabled(false);
        //}else{
        //    rentrerNomJoueursSubmit.setEnabled(false);
        //}
        // Bouton de confirmation
        cst.gridx = 0;
        cst.gridy = 3;
        cst.gridwidth = 2;
        c.add(this.rentrerNomJoueursSubmit, cst);
        this.setContentPane(c);
        this.revalidate();
        this.repaint();
    }
    
    public void setPartie(Jeu partie){
        this.partie = partie;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == partieContreAI){
            System.out.println("Vous avez choisi une partie contre un Ordinateur !");
            this.partie.initJoueurs(true);
        }
        if (ae.getSource() == partieEntre2Joueurs){
            System.out.println("Vous avez choisi une partie entre deux joueurs, que le meilleur gagne !");
            this.partie.initJoueurs(false);
        }
        
        if (ae.getSource() == rentrerNomJoueursSubmit){
            this.partie.initNomJoueurs(rentrerNomJoueur1Field.getText(), rentrerNomJoueur2Field.getText());
        }
        
        
        
        
        for (int i = 0; i < 6; i++) {
            if ( ae.getSource() == boutonsColonnes[i] ) partie.jouerUnCoup(i);
        }
    }
    
 
    public void AffichagePartie() {
    	c.removeAll();
    	
    	if(this.partie.getTurn() == this.partie.getJoueur1()) {
    		this.labelNomJoueur1.setOpaque(true);
    		this.labelNomJoueur2.setOpaque(false);
        	this.labelNomJoueur1.setBackground(this.partie.getJoueur1().getCouleurAttribuee());
    	}
    	else {
    		this.labelNomJoueur2.setOpaque(true);
    		this.labelNomJoueur1.setOpaque(false);
        	this.labelNomJoueur2.setBackground(this.partie.getJoueur2().getCouleurAttribuee());
    	}
    	this.labelNomJoueur1.setText(this.partie.getJoueur1().getNom());
    	
        cst.gridx = 0;
        cst.gridy = 0;
        cst.gridwidth = 3;
    	c.add(labelNomJoueur1, cst);
    	
    	this.labelNomJoueur2.setText(this.partie.getJoueur2().getNom());
    	cst.gridx = 4;
        cst.gridwidth = 3;
        c.add(labelNomJoueur2, cst);
        
        cst.gridy = 1;
        cst.gridwidth = 1;
        for (int i = 0; i < 6; i++) {
            cst.gridx = i;
            c.add(boutonsColonnes[i], cst);
        }
        
        AffichageGrilleGraphique();
    	this.setContentPane(c);
        this.validate();
    	this.revalidate();
        this.repaint();
    }
}
