/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeucouleur3;

/**
 *
 * @author Loris
 */
public class Joueur {
    private String nom;
    private String couleurAttribuee;
    
    public Joueur(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getCouleurAttribuee() {
        return couleurAttribuee;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
