package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *Classe du jeux Mastermind avec ses 3 modes
 */
public class Mastermind {


    private static final Logger logger = LogManager.getLogger();

    private int longNbAleaConf; //Dans le fichier de conf
    private int nombreTourConf; //Dans le fichier de conf
    private int nombreChiffresUtilisables; //Dans le fichier de conf
    private int devMode; //Dans le fichier de conf
    private  String devModeArgs;

    /**
     * Constructeur du jeux Mastermind
     *
     * @param longNbAleaConf            longeur du nombre à trouver, passer dans config.properties
     * @param nombreTourConf            nombre de tour possible, passer dans config.properties
     * @param nombreChiffresUtilisables nombre de pion utilisable, de 4 à 9, passer dans config.properties
     * @param devMode                   Mode developpeur activé ou non, passer dans config.properties
     */
    public Mastermind(int longNbAleaConf, int nombreTourConf, int nombreChiffresUtilisables, int devMode, String devModeArgs) {
        this.longNbAleaConf = longNbAleaConf;
        this.nombreTourConf = nombreTourConf;
        this.nombreChiffresUtilisables = nombreChiffresUtilisables - 1;
        this.devMode = devMode;
        this.devModeArgs = devModeArgs;
    }

    Menu menu = new Menu(devModeArgs);

    /**
     * Cette méthode permet de comparer la réponse de l'utilisateur à la combinaison de l'ordinateur
     *
     * @param tentativeCombinaison la tentative saisie par l'utilisateur
     * @param combinaisonSecrete   la combinaison généré par l'ordinateur que l'utilisateur doit trouver
     * @return La valeur du nombre de pion en bonne position
     */
    private int mainGameChal(StringBuilder tentativeCombinaison, StringBuilder combinaisonSecrete) {

        int nombrePositionOk = 0;
        int nombrePositionMauvaise = 0;
        int nombreMauvais = 0;

        String recupSaisieUtilisateur;

        //Demande au joueur de saisir sa tentative pour trouver la combinaison secrète
        recupSaisieUtilisateur = Tools.saisieNumeroMastermind(longNbAleaConf, 0, nombreChiffresUtilisables);
        logger.info("La tentative est: "+recupSaisieUtilisateur);
        tentativeCombinaison.append(recupSaisieUtilisateur);

        //Comparer la réponse de l'utilisateur à la solutions
        //Générer la réponse de l'ordinateur

        for (int i = 0; i < longNbAleaConf; i++) {
            if (combinaisonSecrete.toString().charAt(i) == tentativeCombinaison.charAt(i)) {
                nombrePositionOk++;
                tentativeCombinaison.setCharAt(i, 'z');
            }
        }

        for (int i = 0; i < longNbAleaConf; i++) {
            for (int k = 0; k < longNbAleaConf; k++) {
                char j = combinaisonSecrete.charAt(i);
                if (tentativeCombinaison.charAt(k) == j) {
                    nombrePositionMauvaise++;
                    tentativeCombinaison.setCharAt(k, 'z');
                }
            }
        }

        nombreMauvais = longNbAleaConf - nombrePositionMauvaise - nombrePositionOk;

        System.out.println(tentativeCombinaison);
        System.out.println("Vous avez " + nombrePositionOk + " pion en bonne position");
        logger.info("Il y a "+nombrePositionOk+" pion en bonne position");
        System.out.println("Vous avez " + nombrePositionMauvaise + " pion en mauvaise position");
        logger.info("Il y a "+nombrePositionMauvaise+" pion en mauvaise position");
        System.out.println("Vous avez " + nombreMauvais + " mauvais pion");
        logger.info("Il y a "+nombreMauvais+" mauvais pion");

        if (nombrePositionOk == longNbAleaConf){
            logger.info("La tentative de combinaison est la bonne");
            System.out.println("La tentative de combinaison du joueur est la bonne !");
        }else{
            logger.info("La tentative de combinaison n'est pas la bonne");
            System.out.println("La tentative de combinaison du joueur n'est pas la bonne !");
        }

        tentativeCombinaison.delete(0, longNbAleaConf);
        return nombrePositionOk;
    }

    /**
     * Méthode du jeux Mastermind dans son mode Challenger, l'utilisateur doit trouver la solution de l'ordinateur
     */
    public void mastermindChallenger() {

        boolean replay = true;
        while (replay == true) {
            replay = false;

            int numeroTour = 0;//Compteur de nombre de tour
            int nombrePositionOk;

            StringBuilder tentativeCombinaison = new StringBuilder();
            StringBuilder combinaisonSecrete;

            //L'ordinateur doit générer la solution
            combinaisonSecrete = Tools.geneNbAlea(longNbAleaConf, 0, nombreChiffresUtilisables);
            logger.info("L'ordinateur a généré la combinaison secrète: "+combinaisonSecrete);

            if (devMode == 1)
                Tools.devMode(combinaisonSecrete);

            do {
                numeroTour++;
                Tools.affichageTour(numeroTour, nombreTourConf);
                logger.info("Le numéro du tour est: "+numeroTour);
                nombrePositionOk = mainGameChal(tentativeCombinaison, combinaisonSecrete);

            } while (nombrePositionOk != longNbAleaConf && numeroTour < nombreTourConf);

            if (nombrePositionOk == longNbAleaConf) {
                System.out.println("Bravo, vous avez gagné");
                logger.info("Le joueur a gagné, l'ordinateur a perdu");
            }else {
                System.out.println("Vous avez perdu !");
                logger.info("L'ordinateur a gagné, le joueur a perdu");
            }
            replay = menu.finDePArtie();
        }
    }

    /**
     * Cette méthode permet de générer une nouvelle combinaison en fonction de la combinaison secrète décidé par l'utilisateur
     *
     * @param tentativeCombinaison la tentative généré par l'ordinateur
     * @param combinaisonSecrete   la combinaison secrète décidé par l'utilisateur
     * @return la nouvelle tentative généré par l'ordinateur
     */
    private StringBuilder mainGameDef(StringBuilder tentativeCombinaison, StringBuilder combinaisonSecrete) {

        for (int i = 0; i < longNbAleaConf; i++) {
            if (tentativeCombinaison.charAt(i) != combinaisonSecrete.charAt(i)) {
                tentativeCombinaison.deleteCharAt(i);
                StringBuilder nouveauChiffre = Tools.geneNbAlea(1, 0, longNbAleaConf);
                tentativeCombinaison.insert(i, nouveauChiffre);
            }
        }

        if (tentativeCombinaison != combinaisonSecrete){
            logger.info("La tentative de combinaison de l'ordinateur n'est pas la bonne");
            System.out.println("La tentative de combinaison de l'ordinateur n'est pas la bonne !");
        }else{
            logger.info("La tentative de combinaison de l'ordinateur est la bonne");
            System.out.println("La tentative de combinaison de l'ordinateur est la bonne !");
        }

        return tentativeCombinaison;
    }

    /**
     * Méthode du jeux Mastermind dans son mode défensseur, l'ordinateur doit trouver la combinaison décidé par l'utilisateur
     */
    public void mastermindDefenseur() {

        boolean replay = true;

        while (replay == true) {

            replay = false;

            int numeroTour = 0;//Compteur de nombre de tour

            String recupSaisieUtilisateur;

            StringBuilder tentativeCombinaison = new StringBuilder();
            StringBuilder combinaisonSecrete = new StringBuilder();

            //L'utilisateur décide de la combinaison secrète
            System.out.println("Veuillez saisir la combinaison secrète que l'ordinateur doit deviner");
            recupSaisieUtilisateur = Tools.saisieNumeroMastermind(longNbAleaConf, 0, nombreChiffresUtilisables);
            logger.info("L'utilisateur décide de la combinaison que l'ordinateur doit deviner: "+recupSaisieUtilisateur);
            combinaisonSecrete.append(recupSaisieUtilisateur);

            //L'ordinateur génère sa première réponse
            tentativeCombinaison = Tools.geneNbAlea(longNbAleaConf, 0, nombreChiffresUtilisables);

            //On compare la combinaison a la réponse de l'ordi
            do {
                numeroTour++;
                Tools.affichageTour(numeroTour, nombreTourConf);
                logger.info("Le numéro du tour est: "+numeroTour);

                tentativeCombinaison = mainGameDef(tentativeCombinaison, combinaisonSecrete);
                logger.info("La tentative de l'ordinateur est: "+tentativeCombinaison);

                System.out.println(tentativeCombinaison);
            } while (!tentativeCombinaison.toString().equals(combinaisonSecrete.toString()) && numeroTour < nombreTourConf);

            if (!tentativeCombinaison.toString().equals(combinaisonSecrete.toString())) {
                logger.info("Le joueur a gagné, l'ordinateur a perdu");
                System.out.println("L'ordinateur à perdu !");
            }else {
                logger.info("L'ordinateur a gagné, le joueur a perdu");
                System.out.println("L'ordinateur à gagné en " + numeroTour + " tours !");
            }

            replay = menu.finDePArtie();
        }
    }

    /**
     * Méthode du jeux Mastermind dans son mode duel, utilisateur vs ordinateur, chacun doit trouver la combinaison de l'autre
     */
    public void mastermindDuel() {

        boolean replay = true;

        while (replay == true) {

            replay = false;

            String recupSaisieUtilisateur;
            int numeroTour = 0;
            int gagnant = 0;

            //Variable pour l'ordinateur
            StringBuilder tentativeCombinaisonOrdi = new StringBuilder();
            StringBuilder combinaisonSecreteOrdi = new StringBuilder();

            //Variable pour le joueur
            StringBuilder tentativeCombinaisonJoueur = new StringBuilder();
            StringBuilder combinaisonSecreteJoueur = new StringBuilder();
            int nombrePositionOk;

            System.out.println("L'ordinateur commence !");

            //L'ordinateur génère sa première réponse
            tentativeCombinaisonOrdi = Tools.geneNbAlea(longNbAleaConf, 0, nombreChiffresUtilisables);
            //L'ordinateur génère la combinaison secrète que le joueur doit trouver
            combinaisonSecreteJoueur = Tools.geneNbAlea(longNbAleaConf, 0, nombreChiffresUtilisables);
            if (devMode == 1)
                Tools.devMode(combinaisonSecreteJoueur);

            System.out.println("Veuillez saisir la combinaison secrète que l'ordinateur doit deviner:");
            recupSaisieUtilisateur = Tools.saisieNumeroMastermind(longNbAleaConf,0, nombreChiffresUtilisables);
            logger.info("L'utilisateur décide de la combinaison que l'ordinateur doit deviner: "+recupSaisieUtilisateur);
            combinaisonSecreteOrdi.append(recupSaisieUtilisateur);

            do {
                numeroTour++;
                logger.info("Le numéro du tour est: "+numeroTour);
                Tools.affichageTour(numeroTour, nombreTourConf);
                logger.info("Au tour de l'ordinateur");
                tentativeCombinaisonOrdi = mainGameDef(tentativeCombinaisonOrdi, combinaisonSecreteOrdi);
                System.out.println("L'ordinateur donne : " + tentativeCombinaisonOrdi);
                logger.info("L'ordinateur donne comme combinaison: "+tentativeCombinaisonOrdi);
                if (tentativeCombinaisonOrdi.toString().equals(combinaisonSecreteOrdi.toString())) {
                    gagnant = 1;
                    break;
                } else {
                    System.out.println("A votre tour, à vous de trouver le nombre de l'ordinateur");
                    logger.info("AU tour du joueur");
                    nombrePositionOk = mainGameChal(tentativeCombinaisonJoueur, combinaisonSecreteJoueur);
                    if (nombrePositionOk == longNbAleaConf) {
                        gagnant = 2;
                        break;
                    }
                }
            } while (numeroTour < nombreTourConf);
            Tools.gagnant(gagnant);
            replay = menu.finDePArtie();
        }
    }
}