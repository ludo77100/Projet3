package com.main;

import java.util.Scanner;

public class Mastermind {

    public static void choixMode(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
        int choixMode = sc.nextInt();
        switch (choixMode) {
            case 1:
                System.out.println("Bienvenue dans le Mastermind, mode Challenger !");
                mastermindChallenger();
                break;
            case 2:
                System.out.println("Bienvenue dans le Mastermind, mode Défenseur !");
                break;
            case 3:
                System.out.println("Bievenue dans le Mastermind, mode duel !");
                break;
            default:
                Tools.gameChoice();
        }
    }

    public static void mastermindChallenger(){

        int longNbAleaConf = 4; //todo A coder dans le fichier de conf //Longueur de la combinaison
        int nombreTourConf = 10; //todo A coder dans le fichier de conf//nombre de tour possible
        int nombreChiffresUtilisables = 4; //todo A coder dans le fichier de conf//nombre de chiffres utilisables
        int i = 0;//Compteur de nombre de tour
        int tentativeCombinaison ;

        StringBuilder combinaisonSecrete = new StringBuilder();//Combinaison secrète généré par l'ordinateur

        Scanner sc = new Scanner(System.in);

        //L'ordinateur doit générer la solution
        combinaisonSecrete = Tools.geneNbAlea(longNbAleaConf, 1, nombreChiffresUtilisables);
        System.out.println(combinaisonSecrete);
        //Demande au joueur de saisir sa tentative pour trouver la combinaison secrète
        i++;
        Tools.affichageTour(i, nombreTourConf);
        System.out.println("Veuillez saisir une combinaison (Chiffre de 1 à " +nombreChiffresUtilisables+ ")");
        tentativeCombinaison = sc.nextInt();

        //Comparer la réponse de l'utilisateur à la solutions
        //Générer la réponse de l'ordinateur
        //Afficher la réponse de l'ordinateur
        //En fonction de la réponse, nouveau tour ou fin de jeu
        //Message pour rejouer, changer de jeu ou quitter
    }

}
