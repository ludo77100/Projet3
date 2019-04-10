package com.main;

import java.util.Scanner;

public class Menu {

    public static void gameChoice() {

        Config config = new Config("src/ressources/config.properties");

        int longNbAlea = config.getIntPropertiesByName("longNbAlea");
        int nombreDeTour = config.getIntPropertiesByName("nombreDeTour");
        int nombreChiffreUtilisables = config.getIntPropertiesByName("nombrePionPossible");
        int devMode = config.getIntPropertiesByName("devMode");

        PlusOuMoins plusOuMoins = new PlusOuMoins(longNbAlea, nombreDeTour, devMode);
        Mastermind mastermind = new Mastermind(longNbAlea, nombreDeTour, nombreChiffreUtilisables, devMode);

        String choixJeu;
        String choixMode;

        System.out.println("Veuillez choisir votre jeux");
        System.out.println("1: Recherche +/-");
        System.out.println("2: Mastermind");
        choixJeu = Tools.saisieNuméros(1);
        switch (choixJeu) {
            case "1":
                Messages.plusOuMoins();
                System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
                choixMode = Tools.saisieNuméros(1);
                switch (choixMode) {
                    case "1":
                        System.out.println("Bienvenue dans le +/-, mode Challenger !");
                        plusOuMoins.plusOuMoinsChallenger();
                        break;
                    case "2":
                        System.out.println("Bienvenue dans le +/-, mode Défenseur !");
                        plusOuMoins.plusOuMoinsDefenseur();
                        break;
                    case "3":
                        System.out.println("Bievenue dans le +/-, mode duel !");
                        plusOuMoins.plusOuMoinsDuel();
                        break;
                    default:
                        System.out.println("Merci de saisir un nombre compris entre 1 et 3");
                        gameChoice();
                        break;
                }
                break;
            case "2":
                Messages.mastermind();
                System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
                choixMode = Tools.saisieNuméros(1);
                switch (choixMode) {
                    case "1":
                        System.out.println("Bienvenue dans le Mastermind, mode Challenger !");
                        mastermind.mastermindChallenger();
                        break;
                    case "2":
                        System.out.println("Bienvenue dans le Mastermind, mode Défenseur !");
                        mastermind.mastermindDefenseur();
                        break;
                    case "3":
                        System.out.println("Bievenue dans le Mastermind, mode duel !");
                        mastermind.mastermindDuel();
                        break;
                    default:
                        Menu.gameChoice();
                }
                break;
            default:
                System.out.println("Merci de saisir un nombre compris entre 1 et 3");
                gameChoice();
                break;
        }
    }

    public static boolean finDePArtie() {

        System.out.println("Que souhaitez vous faire ? 1: Rejouer -- 2: Choisir un autre jeu --3: Quitter");
        String replay = Tools.saisieNuméros(1);
        switch (replay) {
            case "1":
                return true ;
            case "2":
                gameChoice();
                break;
            case "3":
                System.out.println("Merci d'avoir joué, à bientot !");
                break;
            default:
                System.out.println("Merci de saisir un nombre compris entre 1 et 3");
                gameChoice();
                break;
        }
        return false ;
    }
}