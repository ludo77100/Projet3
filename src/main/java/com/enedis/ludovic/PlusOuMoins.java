package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Classe du jeux Plus ou Moins avec ses 3 modes
 */
public class PlusOuMoins {

    private static final Logger logger = LogManager.getLogger();

    private int longNbAleaConf; //Dans le fichier de conf
    private int nombreTourConf; //Dans le fichier de conf
    private int devMode; //Dans le fichier de conf
    private String devModeArgs;

    /**
     * Constructeur du jeux Plus ou Moins
     *
     * @param longNbAleaConf longeur du nombre à trouver, passer dans config.properties
     * @param nombreTourConf nombre de tour possible, passer dans config.properties
     * @param devMode        Mode developpeur activé ou non, passer dans config.properties
     */
    public PlusOuMoins(int longNbAleaConf, int nombreTourConf, int devMode, String devModeArgs) {
        this.longNbAleaConf = longNbAleaConf;
        this.nombreTourConf = nombreTourConf;
        this.devMode = devMode;
        this.devModeArgs = devModeArgs;
    }

    /**
     * Cette méthode permet de générer la réponse en fonction de la réponse de l'utilisateur
     *
     * @param longNbAleaConf passé dans le fichier conf, longueur du nombre à trouvé
     * @param choix          c'est la réponse rentré par l'utilisateur
     * @param nbAlea         ce nombre est généré aléatoirement (dans Tools méthode geneNbAlea)
     * @return reponse réponse généré par l'ordinateur (avec les signes =+-)
     */
    private StringBuilder mainGameChal(int longNbAleaConf, String choix, StringBuilder nbAlea) {

        StringBuilder reponse = new StringBuilder();
        reponse.delete(0, reponse.length());//On réinitialise la réponse afin de ne pas mettre bout à bout les réponses

        for (int i = 0; i < longNbAleaConf; i++) {
            int y = Character.getNumericValue(nbAlea.charAt(i)); //On récupère la valeur numérique
            int z = Character.getNumericValue(choix.charAt(i));

            if (y == z)
                reponse.insert(i, "=");

            else if (y < z)
                reponse.insert(i, "-");

            else
                reponse.insert(i, "+");
        }

        //display un message pour la réponse
        System.out.println("Votre proposition est : " + choix + " -> Réponse : " + reponse);
        logger.info("Le joueur propose " + choix + " la réponse de l'ordinateur est " + reponse);
        return reponse;
    }

    /**
     * Méthode du jeux Plus ou Moins dans son mode Challenger, l'utilisateur doit trouver la solution de l'ordinateur
     */
    public void plusOuMoinsChallenger() {

        Menu menu = new Menu(devModeArgs);

        boolean replay ;

        do { //Boucle utilisé afin de pouvoir rejouer en fin de partie

            String choix; //Saisie de l'utlisateur
            StringBuilder reponseEnSigneOrdinateur; //réponse en signe généré par l'ordinateur
            StringBuilder combinaisonSecreteOrdinateur; //combinaison de l'ordinateur que le joueur doit trouver

            int numeroTour = 0;
            boolean winLoose = false;

            combinaisonSecreteOrdinateur = Tools.geneNbAlea(longNbAleaConf, 1, 9); //Génération du nombre aléatoire
            logger.info("L'ordinateur génère sa combinaison secrète: " + combinaisonSecreteOrdinateur);

            if (devMode == 1 || devModeArgs.equals("dm"))
                Tools.devMode(combinaisonSecreteOrdinateur);

            do {

                //Demande de première saisie utillisateur et boucle pour avoir le bon nombre de chiffre saisi par l'utilisateur

                numeroTour++;
                logger.info("Le numéro du Tour est: " + numeroTour);
                Tools.affichageTour(numeroTour, nombreTourConf);

                choix = Tools.saisieNumero(longNbAleaConf);

                reponseEnSigneOrdinateur = mainGameChal(longNbAleaConf, choix, combinaisonSecreteOrdinateur);
                winLoose = Tools.combinaisonValide(reponseEnSigneOrdinateur, longNbAleaConf);

            } while (!winLoose && numeroTour < nombreTourConf);

            if (!winLoose) {
                System.out.println("Vous avez perdu !");
                logger.info("L'ordinateur a gagné, le joueur a perdu");
            } else {
                System.out.println("Bravo ! Vous avez trouvé la combinaison secrète ! (" + choix + ")");
                logger.info("Le joueur a gagné, l'ordinateur a perdu");
            }
            replay = menu.finDePArtie();
        }while (replay);
    }

    /**
     * Cette méthode permet de généré un nouveau nombre aléatoire en fonction de la réponse en signe donné par l'utilisateur
     *
     * @param longNbAleaConf passé dans le fichier de conf, longueur du nombre à trouver
     * @param reponseEnSigne réponse avec les signes +,- ou = rentré par l'utilisateur
     * @param reponseOrdi    la réponse précédement généré par l'ordinateur, le passer en paramètre permet de générer un nombre aléatoire compris dans les bonnes bornes
     * @param r              Méthode Random
     */
    private void mainGameDef(int longNbAleaConf, String reponseEnSigne, StringBuilder reponseOrdi, Random r) {
        for (int i = 0; i < longNbAleaConf; i++) {
            switch (reponseEnSigne.charAt(i)) {
                case ('='):
                    break;
                case ('+'):
                    int z = Character.getNumericValue(reponseOrdi.charAt(i));
                    int geneNbAleaz = z + r.nextInt((9 + 1) - z);
                    reponseOrdi.deleteCharAt(i);
                    reponseOrdi.insert(i, geneNbAleaz);
                    break;
                case ('-'):
                    int y = Character.getNumericValue(reponseOrdi.charAt(i));
                    if (y > 1) {
                        int geneNbAleay = 1 + r.nextInt(y + -1);
                        reponseOrdi.deleteCharAt(i);
                        reponseOrdi.insert(i, geneNbAleay);
                        break;
                    } else {
                        int geneNbAleay = 1 + r.nextInt((y + 1) - 1);
                        reponseOrdi.deleteCharAt(i);
                        reponseOrdi.insert(i, geneNbAleay);
                    }
            }
        }
        ;
    }

    /**
     * Méthode du jeux Plus ou Moins dans son mode défenseur, l'ordinateur doit trouver la solution de l'utilisateur
     */
    public void plusOuMoinsDefenseur() {

        Menu menu = new Menu(devModeArgs);

        boolean replay ;
        do {

            Random r = new Random();
            StringBuilder tentativeOrdinateur = new StringBuilder(); //Nombre généré par l'ordinateur
            StringBuilder reponseEnSigne = new StringBuilder(); //réponse saisie par le joueur avec =, -, +
            String saisieUtilisateur; //Saisie de l'utlisateur, ensuite passé en stringbuilder
            String combinaisonSecreteUtilisateur; //Combinaison secrète que l'ordinateur doit deviner
            int numeroTour = 0;
            boolean winLoose;

            //On récupère le nombre de l'utilisateur que l'ordinateur doit deviner
            System.out.println("L'ordinateur doit deviner votre combinaison !");
            combinaisonSecreteUtilisateur = Tools.saisieNumero(longNbAleaConf);
            logger.info("Le joueur saisi la combinaison secrète que l'ordinateur doit deviner: " + combinaisonSecreteUtilisateur);

            tentativeOrdinateur = Tools.geneNbAlea(longNbAleaConf, 1, 9);

            //Ici l'ordinateur doit générer une nouvelle réponse en fonction de la variable choix
            do {
                numeroTour++;
                logger.info("Le numéro du tour est: " + numeroTour);
                reponseEnSigne.delete(0, reponseEnSigne.length());
                Tools.affichageTour(numeroTour, nombreTourConf);

                //On récupère ici la réponse de l'utilisateur
                System.out.println("L'ordinateur vous donne comme réponse : " + tentativeOrdinateur);
                logger.info("L'ordinateur donne comme réponse : " + tentativeOrdinateur);
                System.out.println("Pour chaque nombre, indiquer + ou - ou = (pour rappel, votre code secret est " + combinaisonSecreteUtilisateur + ")");
                saisieUtilisateur = Tools.saisieSignes(longNbAleaConf);
                logger.info("Le joueur donne comme réponse: " + saisieUtilisateur);
                System.out.println("Vous avez saisi: " + saisieUtilisateur);

                reponseEnSigne.append(saisieUtilisateur); //On passe en StringBuilder afin de pouvoir utiliser la méthode combinaisonValide dans Tools

                mainGameDef(longNbAleaConf, saisieUtilisateur, tentativeOrdinateur, r);

                winLoose = Tools.combinaisonValide(reponseEnSigne, longNbAleaConf);
            } while (!winLoose && numeroTour < nombreTourConf);
            Tools.winLoose(winLoose);
            replay = menu.finDePArtie();
        }while (replay);
    }

    /**
     * Méthode du jeux Plus ou Moins dans son mode duel, utilisateur vs ordinateur, chacun doit trouver la solution de l'autre
     */
    public void plusOuMoinsDuel() {

        Menu menu = new Menu(devModeArgs);

        boolean replay ;

        do {


            Random r = new Random();

            StringBuilder reponseOrdi = new StringBuilder(); //Nombre généré par l'ordinateur
            StringBuilder reponse = new StringBuilder();
            StringBuilder nbAlea = new StringBuilder();
            StringBuilder reponseEnSigne = new StringBuilder();

            String saisieUtilisateur; //Saisie de l'utlisateur
            String codeSecretUtilisateur;
            String choix; //Saisie de l'utlisateur

            int gagnant;
            int numeroTour = 0;

            boolean winLoose;

            reponseOrdi = Tools.geneNbAlea(longNbAleaConf, 1, 9); //L'ordinateur génère sa première réponse
            nbAlea = Tools.geneNbAlea(longNbAleaConf, 1, 9); //Génération du nombre que l'utilisateur doit trouver

            if (devMode == 1 || devModeArgs.equals("dm")) //Condition permettant l'affichage de la solution que l'utilisateur doit trouver en dev mode
                Tools.devMode(nbAlea);

            System.out.println("L'odinateur commence !");

            //On récupère le nombre de l'utilisateur que l'ordinateur doit deviner
            System.out.println("Veuillez saisir le nombre que l'ordinateur doit deviner ! (" + longNbAleaConf + " chiffres)");
            logger.info("Le jouer doit saisir le nombre que l'ordinateur doit trouver");
            codeSecretUtilisateur = Tools.saisieNumero(longNbAleaConf);
            logger.info("La combinaison que l'ordinateur doit deviner est: " + codeSecretUtilisateur);

            do {
                //On incrémente le numéro du tour + display du tour en cours
                numeroTour++;
                logger.info("Le numéro du tour est " + numeroTour);
                Tools.affichageTour(numeroTour, nombreTourConf);

                logger.info("L'ordinateur joue");
                //L'ordinateur affiche sa première tentative, pour chaque chiffre l'utilisateur indique +, = ou -
                System.out.println("L'ordinateur vous donne comme réponse : " + reponseOrdi);
                logger.info("L'ordinateur donne comme réponse : " + reponseOrdi);
                System.out.println("Pour chaque nombre, indiquer + ou - ou = (pour rappel, votre code secret est " + codeSecretUtilisateur + ")");
                saisieUtilisateur = Tools.saisieSignes(longNbAleaConf);
                System.out.println("Vous avez saisi: " + saisieUtilisateur);
                logger.info("L'utilisateur donne comme réponse : " + saisieUtilisateur);

                mainGameDef(longNbAleaConf, saisieUtilisateur, reponseOrdi, r); //On génère une nouvelle réponse en fonction de la réponse de l'utilisateur
                reponseEnSigne.delete(0, reponseEnSigne.length()); //On supprime la réponse de l'utilisateur pour la prochaine tentative afin de ne pas mettre bout à bout ses réponses

                reponseEnSigne.append(saisieUtilisateur); //On passe en Stringbuilder afin de pouvoir utliser la méthode CombinaisonValide dans Tools
                winLoose = Tools.combinaisonValide(reponseEnSigne, longNbAleaConf); //On regarde si l'ordinateur à gagné
                if (winLoose) {
                    gagnant = 1;
                    break;
                } else {

                    logger.info("Le joueur joue");
                    System.out.println("A votre tour, à vous de trouver le nombre de l'ordinateur");
                    reponse.delete(0, longNbAleaConf);//On réinitialise la réponse afin de ne pas mettre bout à bout les réponses

                    //Demande de saisie utillisateur
                    System.out.println("Veuillez saisir un nombre(" + longNbAleaConf + " chiffres)");
                    choix = Tools.saisieNumero(longNbAleaConf);

                    reponse = mainGameChal(longNbAleaConf, choix, nbAlea); //On génère la réponse de l'ordinateur en signe.
                    winLoose = Tools.combinaisonValide(reponse, longNbAleaConf);//On regarde si l'utilisateur à gagné
                    gagnant = 2;
                }
            } while (!winLoose);
            Tools.gagnant(gagnant);
            replay = menu.finDePArtie();
        }while (replay);
    }
}