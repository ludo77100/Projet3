package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Classe pour l'affichage des menus et les selections de jeu, mode et choix de fin de partie
 */
public class Menu {

    private static final Logger logger = LogManager.getLogger();
    private String devModeArgs ;

    public Menu(String devModeArgs) {
        this.devModeArgs = devModeArgs;
    }
    /**
     * Cette méthode est utilisé afin d'afficher les menus de selection, pour le choix du jeu ainsi que du mode
     */
    public void gameChoice() {

        Config config = new Config("config.properties");

        int longNbAlea = config.getIntPropertiesByName("longNbAlea");
        int nombreDeTour = config.getIntPropertiesByName("nombreDeTour");
        int nombreChiffreUtilisables = config.getIntPropertiesByName("nombrePionPossible");
        int devMode = config.getIntPropertiesByName("devMode");

        PlusOuMoins plusOuMoins = new PlusOuMoins(longNbAlea, nombreDeTour, devMode, devModeArgs);
        Mastermind mastermind = new Mastermind(longNbAlea, nombreDeTour, nombreChiffreUtilisables, devMode, devModeArgs);

        int choixJeu;
        int choixMode;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez choisir votre jeux");
        System.out.println("1: Recherche +/-");
        System.out.println("2: Mastermind");
        choixJeu = sc.nextInt();
        logger.info("Demande du choix du jeu");
        switch (choixJeu) {
            case 1:
                logger.info("L'utilisateur choisit le Plus ou Mois");
                Messages.plusOuMoins();
                System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
                choixMode = sc.nextInt();
                logger.info("Demande du choix du mode (Plus ou Moins)");
                switch (choixMode) {
                    case 1:
                        logger.info("L'utilisateur choisit le mode challenger");
                        System.out.println("Bienvenue dans le +/-, mode Challenger !");
                        plusOuMoins.plusOuMoinsChallenger();
                        break;
                    case 2:
                        logger.info("L'utilisateur choisit le mode défenseur");
                        System.out.println("Bienvenue dans le +/-, mode Défenseur !");
                        plusOuMoins.plusOuMoinsDefenseur();
                        break;
                    case 3:
                        logger.info("L'utilisateur choisit le mode duel");
                        System.out.println("Bievenue dans le +/-, mode duel !");
                        plusOuMoins.plusOuMoinsDuel();
                        break;
                    default:
                        logger.info("Mauvaise saisie dans le choix du mode (Plus ou Moins)");
                        System.out.println("Merci de saisir un nombre compris entre 1 et 3");
                        gameChoice();
                        break;
                }
                break;
            case 2:
                logger.info("L'utilisateur choisi le Mastermind");
                Messages.mastermind();
                System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
                choixMode = sc.nextInt();
                logger.info("Choix du mode (Mastermind)");
                switch (choixMode) {
                    case 1:
                        logger.info("L'utilisateur choisit le mode challenger");
                        System.out.println("Bienvenue dans le Mastermind, mode Challenger !");
                        mastermind.mastermindChallenger();
                        break;
                    case 2:
                        logger.info("L'utilisateur choisit le mode défenseur");
                        System.out.println("Bienvenue dans le Mastermind, mode Défenseur !");
                        mastermind.mastermindDefenseur();
                        break;
                    case 3:
                        logger.info("L'utilisateur choisit le mode duel");
                        System.out.println("Bievenue dans le Mastermind, mode duel !");
                        mastermind.mastermindDuel();
                        break;
                    default:
                        logger.info("L'utilisateur saisi un choix non possible");
                        System.out.println("Mauvaise saisie dans le choix du mode (Mastermind)");
                        gameChoice();
                        break;
                }
                break;
            default:
                logger.info("Mauvaise saisie dans le choix du jeu");
                System.out.println("Merci de saisir un nombre compris entre 1 et 3");
                gameChoice();
                break;
        }
    }

    /**
     * Cette méthode affiche le menu de fin de partie
     * @return retourne le boolean qui permet de refaire une nouvelle partie
     */
    public boolean finDePArtie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que souhaitez vous faire ? 1: Rejouer -- 2: Choisir un autre jeu --3: Quitter");
        logger.info("Choix de fin de partie");
        int replay = sc.nextInt();
        switch (replay) {
            case 1:
                logger.info("L'utilisateur choisi de rejouer");
                return true ;
            case 2:
                logger.info("L'utilisateur choisi de jouer un autre jeu");
                gameChoice();
                break;
            case 3:
                logger.info("L'utilisateur choisi de quitter");
                System.out.println("Merci d'avoir joué, à bientot !");
                break;
            default:
                logger.info("Mauvaise saisi dans le choix de l'action de fin de partie");
                System.out.println("Merci de saisir un nombre compris entre 1 et 3");
                gameChoice();
                break;
        }
        return false ;
    }
}