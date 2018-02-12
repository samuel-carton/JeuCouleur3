package jeucouleur3;

public class Grille {
    private Pion[][] plateau;

    // - Constructeur
    public Grille() {
        plateau = new Pion[6][6];
        for (int i = 0; i < 6; i++) {
            plateau[i] = new Pion[6];
        }
    }
    
    // - Getters & Setters
    public Pion getPion(int x, int y){
        return this.plateau[x][y];
    }
    
    public void setPion(int x, int y, Pion p){
        this.plateau[x][y] = p;
    }
    
}