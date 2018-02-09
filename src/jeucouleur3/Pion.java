package jeucouleur3;

import java.awt.Color;

public class Pion {
    private Color couleur;

    // - Constructors
    public Pion(){
        
    }
    public Pion(Color couleur) {
        this.couleur = couleur;
    }

    // - Getters n' Setters
    public Color getCouleur() {
        return couleur;
    }
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        String s;
        s = "Je suis une case de couleur " + this.couleur;
        return s;
    }
}
