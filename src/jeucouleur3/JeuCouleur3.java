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
public class JeuCouleur3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Is this the real life ?");
        System.out.println("Is this just fantasy ?");
        System.out.println("Caught in the land slide ...");
        System.out.println("C'est pas vrai !");
        
        Jeu game = new Jeu();
        while ( !game.isJoueursInitialized() ){}
        game.start();
    }
    
}
