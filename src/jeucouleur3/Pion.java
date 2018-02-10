package jeucouleur3;

import java.awt.Color;

public class Pion {
    private Color couleur;
    private int x;
    private int y;
    
    // - Constructors
    public Pion(){
        
    }
    public Pion(int x, int y, Color couleur) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
    }

    // - Getters n' Setters
    public Color getCouleur() {
        return couleur;
    }
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    private void setCoordonnees(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String s;
        s = "Je suis une case de couleur " + this.couleur;
        return s;
    }
}
