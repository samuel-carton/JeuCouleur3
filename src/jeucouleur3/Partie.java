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
    
    public Partie(){
        plateau_de_jeu = new Grille();
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
    //</editor-fold>
    
    public boolean isNomJoueurInitialized(int NbJoueur){
        if ( NbJoueur == 1){
            if ( joueur1 == null ){
                return false;
            }
            if ( joueur1.getNom() == null ){
                return false;
            }
        }
        if ( NbJoueur == 2){
            if ( joueur1 == null ){
                return false;
            }
            if ( joueur1.getNom() == null ){
                return false;
            }
        }
        return true;
    }
}
