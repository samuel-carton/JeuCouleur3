/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeucouleur3;

import java.util.Scanner;

/**
 *
 * @author Loris
 */
public class Partie {
    private Joueur joueur1;
    private Joueur joueur2;
    private Grille plateau_de_jeu;
    
    private Joueur starter, turn;
    private Joueur winner;
    
    private boolean joueursInitialized;
    
    private Fenetre fen;
    
    public Partie(){
        this.fen = new Fenetre(this);
        plateau_de_jeu = new Grille();
        
        // Initialisation des joueurs
        this.joueursInitialized = false;
        fen.initJoueurs();
    }
    
    //<editor-fold desc="Getters n' Setters">
    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }
    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }
    public Joueur getJoueur1() {
        return joueur1;
    }
    public Joueur getJoueur2() {
        return joueur2;
    }

    public boolean isJoueursInitialized() {
        return joueursInitialized;
    }
    //</editor-fold>
    
    public void jouerUnCoup(Joueur celuiQuiJoue){
        
    }
    
    public void initJoueurs(boolean isThereAnyAI){
        if ( isThereAnyAI ){
            this.joueur1 = new Joueur("Bot McBottyFace", false);
            this.joueur2 = new Joueur(true);
        }
        else {
            this.joueur1 = new Joueur(true);
            this.joueur2 = new Joueur(true);
        }
        
        fen.rentrerNomJoueurs();
    }
    
    public void initNomJoueurs(String nomJoueur1, String nomJoueur2){
        this.joueur1.setNom(nomJoueur1);
        this.joueur2.setNom(nomJoueur2);
        this.joueursInitialized = true;
    }
    
    public void start(){
        fen.AffichagePartie();
    }
}
