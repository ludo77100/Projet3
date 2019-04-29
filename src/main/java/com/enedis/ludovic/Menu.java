package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe pour l'affichage des menus et les selections de jeu, mode et choix de fin de partie
 */
public class Menu {

    private static final Logger logger = LogManager.getLogger();
    private String devModeArgs;

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

        if (devModeArgs.equals("dm"))
            devMode = 1;

        PlusOuMoins plusOuMoins = new PlusOuMoins(longNbAlea, nombreDeTour, devMode);
        plusOuMoins.setMenu(this);
        Mastermind mastermind = new Mastermind(longNbAlea, nombreDeTour, nombreChiffreUtilisables, devMode);
        mastermind.setMenu(this);

        String choixJeu ;
        String choixMode = new String() ;

            System.out.println("Veuillez choisir votre jeux");
            System.out.println("1: Recherche +/-");
            System.out.println("2: Mastermind");
            choixJeu = Tools.saisieNumero(1, 1,3);
            logger.info("Demande du choix du jeu");

        switch (choixJeu) {
            case "1":
                logger.info("L'utilisateur choisit le Plus ou Moins");
                Messages.plusOuMoins();
                    System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
                    choixMode = Tools.saisieNumero(1, 1,3);
                    logger.info("Demande du choix du mode (Plus ou Moins)");
                switch (choixMode) {
                    case "1":
                        logger.info("L'utilisateur choisit le mode challenger");
                        System.out.println("Bienvenue dans le +/-, mode Challenger !");
                        plusOuMoins.plusOuMoinsChallenger();
                        break;
                    case "2":
                        logger.info("L'utilisateur choisit le mode défenseur");
                        System.out.println("Bienvenue dans le +/-, mode Défenseur !");
                        plusOuMoins.plusOuMoinsDefenseur();
                        break;
                    case "3":
                        logger.info("L'utilisateur choisit le mode duel");
                        System.out.println("Bievenue dans le +/-, mode duel !");
                        plusOuMoins.plusOuMoinsDuel();
                        break;
                }
                break;
            case "2":
                logger.info("L'utilisateur choisi le Mastermind");
                Messages.mastermind();
                    System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
                    choixMode = Tools.saisieNumero(1, 1,3);
                    logger.info("Choix du mode (Mastermind)");

                switch (choixMode) {
                    case "1":
                        logger.info("L'utilisateur choisit le mode challenger");
                        System.out.println("Bienvenue dans le Mastermind, mode Challenger !");
                        mastermind.mastermindChallenger();
                        break;
                    case "2":
                        logger.info("L'utilisateur choisit le mode défenseur");
                        System.out.println("Bienvenue dans le Mastermind, mode Défenseur !");
                        mastermind.mastermindDefenseur();
                        break;
                    case "3":
                        logger.info("L'utilisateur choisit le mode duel");
                        System.out.println("Bievenue dans le Mastermind, mode duel !");
                        mastermind.mastermindDuel();
                        break;
                }
                break;
        }
    }

    /**
     * Cette méthode affiche le menu de fin de partie
     *
     * @return retourne le boolean qui permet de refaire une nouvelle partie
     */
    public boolean finDePArtie() {
        String replay ;
            System.out.println("Que souhaitez vous faire ? 1: Rejouer -- 2: Choisir un autre jeu --3: Quitter");
            logger.info("Choix de fin de partie");
            replay = Tools.saisieNumero(1, 1,3);
            switch (replay) {
                case "1":
                    logger.info("L'utilisateur choisi de rejouer");
                    return true;
                case "2":
                    logger.info("L'utilisateur choisi de jouer un autre jeu");
                    gameChoice();
                    break;
                case "3":
                    logger.info("L'utilisateur choisi de quitter");
                    System.out.println("Merci d'avoir joué, à bientot !");
                    break;
            }
            return false;
        }
    }