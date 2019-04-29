package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Character.isDigit;

/**
 * Classe avec les méthodes outils pour les deux jeux
 */
public class Tools {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Méthode pour générer un nombre aléatoire
     *
     * @param longNbAleaConf la longueur du nombre devant être gégéré
     * @param lowBound       limite basse pour la génération du chiffre aléatoire
     * @param highBound      limite haute pour la génération du
     * @return le nombre aléatoire dans un type StringBuilder
     */
    public static StringBuilder geneNbAlea(int longNbAleaConf, int lowBound, int highBound) {
        Random r = new Random();
        StringBuilder nbAlea = new StringBuilder();

        for (int i = 0; i < longNbAleaConf; i++) {
            int geneNbAlea = lowBound + r.nextInt(highBound - lowBound);
            nbAlea.insert(i, geneNbAlea);
        }
        return nbAlea;
    }

    /**
     * Méthode permettant de tester la victoire ou non du joueur
     *
     * @param reponse    reponse donné par le défenseur (avec les signes + - =)
     * @param longNbAlea longueur du nombre aléatoire
     * @return false: Mauvaise combianaison, true: Bonne combinaison
     */
    public static boolean combinaisonValide(StringBuilder reponse, int longNbAlea) {
        for (int i = 0; i < longNbAlea; i++) {
            if (reponse.charAt(i) != '=') {
                logger.info("La réponse n'est pas la bonne");
                return false;
            }
        }
        logger.info("La réponse est la bonne");
        return true;
    }

    /**
     * Cette méthode permet d'afficher le tour en cour ainsi que d'aérer un peu la lecture de la console
     *
     * @param numeroTour       numéro du tour en cours
     * @param nombreDeTourConf nombre de tour possible configuré
     */
    public static void affichageTour(int numeroTour, int nombreDeTourConf) {

        int nbTourRestant = nombreDeTourConf - numeroTour;

        System.out.println("\n" + "**********************************************");
        System.out.println("Tour n°" + numeroTour + "(nombre de tour restant: " + nbTourRestant + ")");
        System.out.println("**********************************************" + "\n");
    }

    /**
     * Cette méthode permet d'afficher le gagnant
     * @param winLoose le boolean qui désigne le gagnant
     */
    public static void winLoose(boolean winLoose) {
        if (!winLoose) {
            System.out.println("L'ordinateur à perdu !");
            logger.info("Le joueur a gagné, l'ordinateur a perdu");
        } else {
            System.out.println("L'ordinateur a gagné !");
            logger.info("L'ordinateur a gagné, le joueur a perdu");
        }
    }

    /**
     * Cette méthode permet un affichage en devMode de la combinaison secrète que le joueur doit trouver.
     *
     * @param combinaisonSecrete la valeur que le joueur doit trouver
     */
    public static void devMode(StringBuilder combinaisonSecrete) {
        System.out.println("\n" + "----------------DEV MODE ACTIF------------------");
        System.out.println("La combinaison secrète est : " + combinaisonSecrete);
        System.out.println("--------------------------------------------------" + "\n");
    }

    /**
     * Cette méthode permet d'afficher le gagant en fonction d'un int
     *
     * @param numeroGagnant numéro du gagant
     */
    public static void gagnant(int numeroGagnant) {
        switch (numeroGagnant) {
            case 1:
                System.out.println("L'ordinateur a gagné !");
                logger.info("L'ordinateur a gagné, le joueur a perdu");
                break;
            case 2:
                System.out.println("Vous avez gagné !");
                logger.info("Le joueur a gagné, l'ordinateur a perdu");
                break;
        }
    }

    /**
     * Cette méthode permet de récuperer la saisie de l'utilisateur, elle ne récupère que les signes =, - ou + et les retourne en String
     * Elle vérifie également que la saisie est de la bonne longueur
     *
     * @param longNbAleaConf longueur du nombre aléatoire
     * @return la saisie de l'utilisateur de longueur longNbAleaConf en String
     */
    public static String saisieSignes(int longNbAleaConf) {

        String saisie;
        boolean test = false ;
        Scanner sc = new Scanner(System.in);
        do {
                System.out.println("Merci de saisir votre réponse ("+longNbAleaConf+" signes)");
                saisie = sc.next();
            if (saisie.length() == longNbAleaConf) {
                for (int i = 0; i < longNbAleaConf; i++)
                    if (saisie.charAt(i) != '+' && saisie.charAt(i) != '-' && saisie.charAt(i) != '=') {
                        test = false;
                        break;
                    } else {
                        test = true;
                    }
            }else{
                logger.info("La saisie n'est pas de la bonne longueur");
                System.out.println("Votre saisie n'est pas de la bonne longueur, merci d'essayer à nouveau");
            }
        }while (!test);
        return saisie;
    }

    /**
     * Cette méthode permet de récupérer la saisie de l'utilisateur, d'une bonne longueur, avec que des chiffres et compris dans les bonne bornes
     * @param longNbAleaConf longueur du nombre aléatoire
     * @param lowBound limite basse de saisie
     * @param highBound limite haute de saisie
     * @return la saisie de l'utilisateur
     */
    public static String saisieNumero(int longNbAleaConf, int lowBound, int highBound){

        String saisieUtil;
        Scanner sc = new Scanner(System.in);
        boolean saisieCorrect ;
        boolean saisieTotalOk = false;

        do {
                    logger.info("Demande de saisie au joueur");
                    System.out.println("Merci de saisir " + longNbAleaConf + " chiffres, compris entre "+lowBound+" et "+highBound);
                    saisieUtil = sc.next();
                    if (saisieUtil.length() == longNbAleaConf) {
                        for (int i = 0; i < longNbAleaConf; i++) {
                            int y = Character.getNumericValue(saisieUtil.charAt(i));
                            saisieCorrect = isDigit(saisieUtil.charAt(i));
                            if (y >= lowBound && y <= highBound && saisieCorrect) {
                                saisieTotalOk = true;
                            } else {
                                saisieTotalOk = false;
                                logger.info("La saisie ne comporte pas que des chiffres ou n'est pas compris entre les bornes");
                                break;
                            }
                        }
                    } else {
                        logger.info("La saisie n'est pas de la bonne longueur");
                    }
        }while (!saisieTotalOk);
        return saisieUtil;
    }
}
