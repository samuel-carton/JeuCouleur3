/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeucouleur3;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Loris
 */
public class Jeu {

    // Grille contenant les pions
    private Grille plateau_de_jeu;
    
    // Différent joueurs - turn est le joueur qui est en train de jouer
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur turn;
    private Joueur winner;

    private boolean joueursInitialized; // - Pour savoir si les joueurs ont étés alloués

    private Fenetre fen; // - L'interface graphique est directement intégrée à la partie, pour un accès plus facile. Cela évite de passer par la fonction main, et "d'attendre" un évènement

    private Pion dernierPion; // - C'est le dernier coup joué, il sert à détecter la victoire
    private int nbCoupsJoues;
    private boolean partieFinie;

    public Jeu() {
        this.fen = new Fenetre(this);
        plateau_de_jeu = new Grille();
        this.dernierPion = new Pion();
        this.nbCoupsJoues = 0;
        this.partieFinie = false;
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

    public Grille getPlateau_de_jeu() {
        return plateau_de_jeu;
    }

    public Joueur getTurn() {
        return turn;
    }

    public boolean isPartieFinie() {
        return partieFinie;
    }
    
    //</editor-fold>
    
    public void initJoueurs(boolean isThereAnyAI) {
        if (isThereAnyAI) {
            String fileName = "src\\jeucouleur3\\Noms IA.txt";
            /*
             *
             * Cette partie sert à aller lire dans le fichier
             *
             */
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                String line;
                ArrayList<String> noms = new ArrayList<String>();
                // - On compte ici le nombre d elignes du fichier
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    noms.add(line);
                }
                int randomNumber = (int) Math.floor(Math.random() * noms.size()); // - On crée un nombre aléatoire entre 0 et le nombre de lignes du fichier
                String nomduBot = noms.get(randomNumber);
                this.joueur1 = new Joueur(nomduBot, false);
            } catch (Exception ex) {
                // - Si l'on n'arrive pas à ouvrir le fichier, on catch l'erreur
                System.out.println("ERREUR: Le fichier " + fileName + " n'a pas été trouvé. Le bot n'aura donc pas d'originalité.");
                System.out.println(ex.getMessage());
                this.joueur1 = new Joueur("Bot Peu Original", false);
            }

            this.joueur2 = new Joueur(true);
        } else {
            this.joueur1 = new Joueur(true);
            this.joueur2 = new Joueur(true);
        }

        fen.rentrerNomJoueurs();
    }
    
    // - Cette fonction n'est appelée que par l'appui du bouton de validation dans la classe fenêtre, lors de la sélection du nom des joueurs
    public void initNomJoueurs(String nomJoueur1, Color couleurJoueur1, String nomJoueur2, Color couleurJoueur2) {
        this.joueur1.setNom(nomJoueur1);
        this.joueur1.setCouleurAttribuee(couleurJoueur1);
        this.joueur2.setCouleurAttribuee(couleurJoueur2);
        this.joueur2.setNom(nomJoueur2);
        this.joueursInitialized = true;
        start();
    }

    public void start() {
        fen.AffichagePartie();
        affichePartieConsole();
        boolean continuer = true;
        if (Math.floor((Math.random() * 2)) == 1) {
            turn = joueur1;
        } else {
            turn = joueur2;
        }
        if (turn.isHuman() == false) {
            jouerAI();
        }
    }

    public void jouerUnCoup(int NbColonne) {
        // - Si le coup n'est pas possible, ou si la partie est finie, l'appui sur les boutons colonnes ne produit rien, et on saute les lignes de code suivantes
        if (verifCoup(NbColonne) && !partieFinie) {
            for (int i = 5; i >= 0; i--) {
                if (this.plateau_de_jeu.getPion(NbColonne, i) == null) {
                    dernierPion = new Pion(NbColonne, i, this.turn.getCouleurAttribuee()); // - On enregistre le dernier coup joué
                    this.plateau_de_jeu.setPion(NbColonne, i, new Pion(NbColonne, i, this.turn.getCouleurAttribuee())); // - On crée un pion dans la grille, à l'emplacement souhaité
                    nbCoupsJoues++;
                    break;
                }
            }
            String detect;
            detect = chercheCouleur3(); // - La détection de victoire s'effectue ici
            if (detect == "victoire") {
                afficherVictoire("Victoire du joueur " + turn.getNom());
            } else if (detect == "matchnul") {
                afficherMatchNul();
            } else { // - Si ni le match nul, ni la victoire ne sont détectés, on joue le coup, on change de joueur et on rafraichit la partie
                turn = (this.turn == joueur1) ? joueur2 : joueur1;
                fen.AffichagePartie();
                affichePartieConsole();
                if (turn.isHuman() == false) { // - Si le prochain joueur est l'IA, on appelle la fonction pour jouer son coup
                    jouerAI();
                }
            }

        }
    }

    public void jouerAI() {
        int tmpCol = 0;
        while (!verifCoup(tmpCol)) {
            tmpCol++;
        }
        for (int i = 5; i >= 0; i--) {
            if (this.plateau_de_jeu.getPion(tmpCol, i) == null) {
                dernierPion = new Pion(tmpCol, i, this.turn.getCouleurAttribuee());
                this.plateau_de_jeu.setPion(tmpCol, i, new Pion(tmpCol, i, this.turn.getCouleurAttribuee()));
                nbCoupsJoues++;
                break;
            }
        }
        
        // - Les lignes suivantes fonctionnent de la même manière que dans la fonction jouerCoup
        String detect;
        detect = chercheCouleur3();
        if (detect == "victoire") {
            afficherVictoire("Victoire du joueur " + turn.getNom());
        } else if (detect == "matchnul") {
            afficherMatchNul();
        } else {
            turn = (this.turn == joueur1) ? joueur2 : joueur1;
            fen.AffichagePartie();
            affichePartieConsole();
        }
    }

    public boolean verifCoup(int NbColonne) {
        for (int i = 0; i < 6; i++) {
            if (this.plateau_de_jeu.getPion(NbColonne, i) == null) {
                return true;

            }
        }
        return false;
    }

    public void affichePartieConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (this.plateau_de_jeu.getPion(j, i) == null) {
                    System.out.print("| ");
                } else if (this.plateau_de_jeu.getPion(j, i).getCouleur().equals(joueur1.getCouleurAttribuee())) {
                    System.out.print("|X");
                } else {
                    System.out.print("|O");
                }
            }
            System.out.println("|");
        }
    }

    public String chercheCouleur3() {
        
        
        // - On garde en mémoire les coordonnées du dernier coup joué
        int x = dernierPion.getX();
        int y = dernierPion.getY();
        String messageVictoire = "Victoire du joueur ";

        //Détermination de celui qui vient de jouer
        if (joueur1.joueurFromCouleur(dernierPion.getCouleur()) != null) {
            winner = joueur1.joueurFromCouleur(dernierPion.getCouleur());
        } else {
            winner = joueur2.joueurFromCouleur(dernierPion.getCouleur());
        }

        String annonce = messageVictoire + winner.getNom();

        //Vérification de victoire
        // Lignes horizontales
        if (x < 4 && plateau_de_jeu.getPion(x + 1, y) != null && plateau_de_jeu.getPion(x + 2, y) != null
                && (plateau_de_jeu.getPion(x + 1, y).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x + 2, y).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        if (x >= 2 && plateau_de_jeu.getPion(x - 1, y) != null && plateau_de_jeu.getPion(x - 2, y) != null
                && (plateau_de_jeu.getPion(x - 1, y).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x - 2, y).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        if (x < 5 && x >= 1 && plateau_de_jeu.getPion(x + 1, y) != null && plateau_de_jeu.getPion(x - 1, y) != null
                && (plateau_de_jeu.getPion(x + 1, y).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x - 1, y).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        // Lignes verticales
        if (y < 4 && plateau_de_jeu.getPion(x, y + 1) != null && plateau_de_jeu.getPion(x, y + 2) != null
                && (plateau_de_jeu.getPion(x, y + 1).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x, y + 2).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        if (y >= 2 && plateau_de_jeu.getPion(x, y - 1) != null && plateau_de_jeu.getPion(x, y - 2) != null
                && (plateau_de_jeu.getPion(x, y - 1).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x, y - 2).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        if (y < 5 && y >= 1 && plateau_de_jeu.getPion(x, y + 1) != null && plateau_de_jeu.getPion(x, y - 1) != null
                && (plateau_de_jeu.getPion(x, y + 1).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x, y - 1).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }

        //Lignes diagonales #1
        if (x < 4 && y < 4 && plateau_de_jeu.getPion(x + 1, y + 1) != null && plateau_de_jeu.getPion(x + 2, y + 2) != null
                && (plateau_de_jeu.getPion(x + 1, y + 1).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x + 2, y + 2).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        if (x >= 2 && y >= 2 && plateau_de_jeu.getPion(x - 1, y - 1) != null && plateau_de_jeu.getPion(x - 2, y - 2) != null
                && (plateau_de_jeu.getPion(x - 1, y - 1).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x - 2, y - 2).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        if (x < 5 && y < 5 && x >= 1 && y >= 1 && plateau_de_jeu.getPion(x + 1, y + 1) != null && plateau_de_jeu.getPion(x - 1, y - 1) != null
                && (plateau_de_jeu.getPion(x + 1, y + 1).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x - 1, y - 1).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        //Lignes diagonales #2
        if (x < 4 && y >= 2 && plateau_de_jeu.getPion(x + 1, y - 1) != null && plateau_de_jeu.getPion(x + 2, y - 2) != null
                && (plateau_de_jeu.getPion(x + 1, y - 1).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x + 2, y - 2).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        if (x >= 2 && y < 4 && plateau_de_jeu.getPion(x - 1, y + 1) != null && plateau_de_jeu.getPion(x - 2, y + 2) != null
                && (plateau_de_jeu.getPion(x - 1, y + 1).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x - 2, y + 2).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";
        }
        if (x < 5 && y < 5 && x >= 1 && y >= 1 && plateau_de_jeu.getPion(x + 1, y - 1) != null && plateau_de_jeu.getPion(x - 1, y + 1) != null
                && (plateau_de_jeu.getPion(x + 1, y - 1).getCouleur() == dernierPion.getCouleur()) && (plateau_de_jeu.getPion(x - 1, y + 1).getCouleur() == dernierPion.getCouleur())) {
            return "victoire";

        }

        if (nbCoupsJoues == 36) {
            return "matchnul";
        }

        return "nothing";
    }

    public void afficherVictoire(String annonce) {
        partieFinie = true;
        fen.AffichagePartie();
        //fin de la partie
        JOptionPane jop1 = new JOptionPane();
        ImageIcon victory = new ImageIcon("src\\jeucouleur3\\victoire.png");
        jop1.showMessageDialog(null, annonce, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, victory);
    }

    public void afficherMatchNul() {
        partieFinie = true;
        fen.AffichagePartie();
        //fin de la partie
        JOptionPane jop1 = new JOptionPane();
        ImageIcon tieGame = new ImageIcon("src\\jeucouleur3\\egalite.png");
        jop1.showMessageDialog(null, "Match Nul! Fin de la partie", "Fin de la partie", JOptionPane.INFORMATION_MESSAGE, tieGame);
    }
}
