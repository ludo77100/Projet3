package com.main;

import java.util.Scanner;

public class Menu {

    public static void gameChoice() {

        PlusOuMoins plusOuMoins = new PlusOuMoins(4, 10);
        Mastermind mastermind = new Mastermind(4, 10, 4);

        int choixJeu;
        int choixMode;

        System.out.println("Veuillez choisir votre jeux");
        System.out.println("1: Recherche +/-");
        System.out.println("2: Mastermind");
        Scanner sc = new Scanner(System.in);
        choixJeu = sc.nextInt();
        switch (choixJeu) {
            case 1:
                System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
                choixMode = sc.nextInt();
                switch (choixMode) {
                    case 1:
                        System.out.println("Bienvenue dans le +/-, mode Challenger !");
                        plusOuMoins.plusOuMoinsChallenger();
                        break;
                    case 2:
                        System.out.println("Bienvenue dans le +/-, mode Défenseur !");
                        plusOuMoins.plusOuMoinsDefenseur();
                        break;
                    case 3:
                        System.out.println("Bievenue dans le +/-, mode duel !");
                        plusOuMoins.plusOuMoinsDuel();
                        break;
                    default:
                        gameChoice();
                }
                break;
            case 2:
                System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
                choixMode = sc.nextInt();
                switch (choixMode) {
                    case 1:
                        System.out.println("Bienvenue dans le Mastermind, mode Challenger !");
                        mastermind.mastermindChallenger();
                        break;
                    case 2:
                        System.out.println("Bienvenue dans le Mastermind, mode Défenseur !");
                        mastermind.mastermindDefenseur();
                        break;
                    case 3:
                        System.out.println("Bievenue dans le Mastermind, mode duel !");
                        mastermind.mastermindDuel();
                        break;
                    default:
                        Menu.gameChoice();
                }
                break;
            default:
                gameChoice();
                break;
        }
    }

    public static boolean finDePArtie() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Que souhaitez vous faire ? 1: Rejouer -- 2: Choisir un autre jeu --3: Quitter");
        int replay = sc.nextInt();
        switch (replay) {
            case 1:
                return true ;
            case 2:
                gameChoice();
                break;
            case 3:
                break;
        }
        return false ;
    }
}