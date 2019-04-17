package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Character.isDigit;

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
     * Méthode qui permet de creer une chaine de caractere de type StringBuilder correspondant a la longeur du nbAlea
     * Elle permet de tester et d'arréter la boucle for du jeu si le joueur trouve la bonne réponse
     * <p>
     * On peut aussi creer une fonction qui regarde le nombre de = dans reponse et le compare a la longueur de nbAlea, si ! continue alors ... (moins gourmand en mémoire si très grand nombre?)
     */
    public static StringBuilder geneTesterEquals(int longNbAleaConf) {

        StringBuilder testerEquals = new StringBuilder();

        for (int i = 0; i < longNbAleaConf; i++) {
            testerEquals.insert(i, "=");
        }
        return testerEquals;
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

    //todo javadoc
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
     * Cette méthode permet de récuper la saisie de l'utilisateur, elle récupère un int qui ensuite retourner en String
     *
     * @param longNbAleaConf longueur du nombre aléatoire
     * @return la saisie de l'utilisateur de longueur longNbAleaConf en String
     */
    public static String saisieNumero(int longNbAleaConf){

        String saisieUtil;
        Scanner sc = new Scanner(System.in);
        boolean saisieCorrect = false;

        do{
            do {
                System.out.println("Veuillez saisir un nombre (" + longNbAleaConf + " chiffres)");
                logger.info("Demande de saisie d'une combinaison secrète au joueur");
                saisieUtil = sc.next();

                if (saisieUtil.length() == longNbAleaConf) {
                    logger.info("Le joueur saisi comme combinaison: " + saisieUtil);
                }else {
                    logger.info("La saisie du joueur ne respecte pas la bonne longueur");
                }

            }while (saisieUtil.length() != longNbAleaConf);
            for (int i = 0; i < longNbAleaConf; i++) {
                saisieCorrect = isDigit(saisieUtil.charAt(i));
                if (!saisieCorrect){
                    logger.info("La saisie du joueur est incorecte");
                    break;
                }
            }
        }while (!saisieCorrect);
        return saisieUtil;
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
            do {
                System.out.println("Merci de saisir votre réponse ("+longNbAleaConf+" signes)");
                saisie = sc.next();
            } while (saisie.length() != longNbAleaConf);
            for (int i = 0; i < longNbAleaConf; i++)
                if (saisie.charAt(i) != '+' && saisie.charAt(i) != '-' && saisie.charAt(i) != '='){
                    test = false;
                    break;
        }else{
                    test = true;
                }
        }while (!test);
        return saisie;
    }

    public static String saisieNumeroMastermind(int longNbAleaConf, int lowBound, int highBound){

        String saisieUtil;
        Scanner sc = new Scanner(System.in);
        boolean saisieCorrect = false;
        boolean entreBornes = false;

        do {
            do {
                do {
                    System.out.println("Veuillez saisir un nombre (" + longNbAleaConf + " chiffres)");
                    saisieUtil = sc.next();
                } while (saisieUtil.length() != longNbAleaConf);
                for (int i = 0; i < longNbAleaConf; i++) {
                    saisieCorrect = isDigit(saisieUtil.charAt(i));
                    if (!saisieCorrect) {
                        break;
                    }
                }
            } while (!saisieCorrect);
            for (int i = 0; i < longNbAleaConf; i++){
                int y = Character.getNumericValue(saisieUtil.charAt(i));
                if (y >= lowBound && y <= highBound){
                    entreBornes = true ;

                }else{
                    entreBornes = false;
                    break;
                }
            }
        }while (!entreBornes);
        return saisieUtil;
    }
}
