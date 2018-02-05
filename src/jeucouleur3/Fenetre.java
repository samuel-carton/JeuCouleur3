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
    private JLabel labelRentrerNomJoueur;
    private JTextField rentrerNomJoueurField;
    private JButton rentrerNomJoueurButton1;
    private JButton rentrerNomJoueurButton2;
    
    // Dans le hub
    private JLabel labelNomJoueur1;
    private JLabel labelNomJoueur2;
    
    // Type de parties
    private JButton partieAvecAI;
    private JButton partieAvecJoueurs;
    
    public Fenetre(Partie p){
        setSize(500,500);
        setTitle("Puissance 3 !! Alignez-en trois, et c'est gagné !");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        instanciation();
        this.partie = p;
    }
    
    private void instanciation(){
        
        // Allocation
        placeholdingy = new JLabel("Je ne sers à rien !");
        c = getContentPane(); // Container
        labelRentrerNomJoueur = new JLabel();
        rentrerNomJoueurField = new JTextField(20);
        rentrerNomJoueurButton1 = new JButton("GO !");
        rentrerNomJoueurButton2 = new JButton("GO !");
        
        labelNomJoueur1 = new JLabel();
        labelNomJoueur2 = new JLabel();
        
        // Layout
        GridBagLayout gbd = new GridBagLayout();
        cst = new GridBagConstraints();
        c.setLayout(gbd);
        
        // Placement
        
        // Action Listeners
        rentrerNomJoueurButton1.addActionListener(this);
        rentrerNomJoueurButton2.addActionListener(this);
        
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
        
        partieAvecAI = new JButton("Partie avec AI");
        partieAvecJoueurs = new JButton("Partie avec Joueurs");
        cst.gridx = 0;
        cst.gridy = 0;
        c.add(partieAvecAI, cst);
        cst.gridy = 2;
        c.add(partieAvecJoueurs, cst);
        this.setContentPane(c);
        this.revalidate();
        this.repaint();
    }
    
    
    public void setPartie(Partie partie){
        this.partie = partie;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( ae.getSource() == rentrerNomJoueurButton1 ){
            partie.setJoueur1(new Joueur(rentrerNomJoueurField.getText()));
        }
        if ( ae.getSource() == rentrerNomJoueurButton2 ){
            partie.setJoueur2(new Joueur(rentrerNomJoueurField.getText()));
        }
    }
    
    public void AffichageHub(){
        c.removeAll();
        
        this.labelNomJoueur1.setText(this.partie.getJoueur1().getNom());
        this.labelNomJoueur2.setText(this.partie.getJoueur2().getNom());
        cst.gridx = 0;
        cst.gridy = 0;
        c.add(labelNomJoueur1, cst);
        cst.gridy = 1;
        c.add(labelNomJoueur2, cst);
        this.revalidate();
        this.repaint();
    }
}
