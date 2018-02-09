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
		this.joueur1.setCouleurAttribuee("Bleu");
		this.joueur2.setCouleurAttribuee("Rouge");
		this.joueur2.setNom(nomJoueur2);
		this.joueursInitialized = true;
	}

	public void start(){
		fen.AffichagePartie();
		affichePartieConsole();
		boolean continuer = true;
		if ( Math.floor((Math.random()*2)) == 1 ){
			starter = joueur1;
		} else {
			starter = joueur2;
		}
		turn = starter;
	}

	public void jouerUnCoup(int NbColonne){
		if ( verifCoup(NbColonne) ){
			for( int i = 5; i >= 0; i --){
				if ( this.plateau_de_jeu.getPion(NbColonne, i) == null){
					this.plateau_de_jeu.setPion(NbColonne, i, new Pion(this.turn.getCouleurAttribuee()));
					break;
				}
			}
			// chercheCouleur3(); et vérification de la victoire
			turn = (this.turn == joueur1)? joueur2 : joueur1;
			fen.AffichagePartie();
			affichePartieConsole();
			if ( turn.isHuman() == false ){
				jouerAI();
			}
		}
	}

	public void jouerAI(){

		// Jouer le tour de l'AI
		turn = (this.turn == joueur1)? joueur2 : joueur1;
	}

	public boolean verifCoup(int NbColonne){
		for ( int i = 0; i < 6; i++){
			if ( this.plateau_de_jeu.getPion(NbColonne, i) == null ){
				return true;

			}
		}
		return false;
	}

	public void affichePartieConsole(){
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if ( this.plateau_de_jeu.getPion(j, i) == null){
					System.out.print("| ");
				}else if (this.plateau_de_jeu.getPion(j, i).getCouleur().equals(joueur1.getCouleurAttribuee())){
					System.out.print("|X");
				}else{
					System.out.print("|O");
				}
			}
			System.out.println("|");
		}
	}

	public void affichePartie() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if ( this.plateau_de_jeu.getPion(j, i) == null){
					//rien
				}
			}else if (this.plateau_de_jeu.getPion(j, i).getCouleur().equals(joueur1.getCouleurAttribuee())){
				Jeton pionJ1 = new Jeton(j*10,i*10,plateau_de_jeu.getPion(j, i).getCouleur());
			}else{
				
			}
		}
		
	}


}

