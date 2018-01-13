package jeucouleur3;

public class Pion {
    private String couleur;

    // - Constructors
    public Pion(){
        
    }
    public Pion(String couleur) {
        this.couleur = couleur;
    }

    // - Getters n' Setters
    public String getCouleur() {
        return couleur;
    }
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        String s;
        s = "Je suis une case de couleur " + this.couleur;
        return s;
    }
}
