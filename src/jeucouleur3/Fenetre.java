/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeucouleur3;

import java.awt.Container;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
    private Partie partie;
    
    // Grille de Label
    private JLabel[][] grilleGraphique;
    
    // Pour rentrer le nom
    private JLabel labelRentrerNomJoueur1;
    private JLabel labelRentrerNomJoueur2;
    private JTextField rentrerNomJoueur1Field;
    private JTextField rentrerNomJoueur2Field;
    private JButton rentrerNomJoueursSubmit;
    
    // Dans le hub
    private JLabel labelNomJoueur1;
    private JLabel labelNomJoueur2;
    
    // Type de parties
    private JButton partieContreAI;
    private JButton partieEntre2Joueurs;
    
    public Fenetre(Partie p){
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
        labelRentrerNomJoueur1 = new JLabel("Player One");
        labelRentrerNomJoueur2 = new JLabel("Player Two");
        rentrerNomJoueur1Field = new JTextField(20);
        rentrerNomJoueur2Field = new JTextField(20);
        rentrerNomJoueursSubmit = new JButton("Valider");
        
        labelNomJoueur1 = new JLabel();
        labelNomJoueur2 = new JLabel();
        
        // Choix de partie
        partieContreAI = new JButton("Partie avec AI");
        partieEntre2Joueurs = new JButton("Partie avec Joueurs");
        
        
        // Layout
        GridBagLayout gbd = new GridBagLayout();
        cst = new GridBagConstraints();
        c.setLayout(gbd);
        
        // Placement
        
        // Action Listeners
        partieContreAI.addActionListener(this);
        partieEntre2Joueurs.addActionListener(this);
        rentrerNomJoueursSubmit.addActionListener(this);
        
        // Finalisation
        setContentPane(c);
    }
    
    public void SetupGrilleGraphique(){
        for( int i = 0; i < 6; i ++){
            for ( int j = 0; j < 6; i ++){
                grilleGraphique[i][j] = new JLabel(this.grille.getPion(j, j).getCouleur());
            }
        }
    }
    
    public void AffichageGrilleGraphique(){
        for( int i = 0; i < 6; i ++){
            for ( int j = 0; j < 6; i ++){
                cst.gridx = i;
                cst.gridy = j;
                c.add(this.grilleGraphique[i][j], cst);
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
        cst.gridx = 0;
        cst.gridy = 2;
        c.add(this.rentrerNomJoueursSubmit, cst);
        this.setContentPane(c);
        this.revalidate();
        this.repaint();
    }
    
    public void setPartie(Partie partie){
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
    }
    
 
    public void AffichagePartie() {
    	c.removeAll();
    	
    	this.labelNomJoueur1.setText(this.partie.getJoueur1().getNom());
        cst.gridx = 0;
        cst.gridy = 0;
    	c.add(labelNomJoueur1, cst);
    	
    	this.labelNomJoueur2.setText(this.partie.getJoueur2().getNom());
    	cst.gridx = 1;
        c.add(labelNomJoueur2, cst);
    	this.setContentPane(c);
    	this.revalidate();
        this.repaint();
    }
}
