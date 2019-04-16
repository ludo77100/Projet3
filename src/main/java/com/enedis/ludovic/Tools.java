package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
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

        logger.debug("APPEL de la méthode geneNbAlea avec paramètres longNbAlea: " + longNbAleaConf + " ;lowBound: " + lowBound + " ;highBound: " + highBound);

        Random r = new Random();
        StringBuilder nbAlea = new StringBuilder();

        for (int i = 0; i < longNbAleaConf; i++) {
            int geneNbAlea = lowBound + r.nextInt(highBound - lowBound);
            nbAlea.insert(i, geneNbAlea);
        }

        logger.debug("FIN de la méthode, elle retourne nbAlea: " + nbAlea);

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

        logger.debug("APPEL de la méthode combinaisonValide avec paramètres reponse: " + reponse + " ;longNbAlea: " + longNbAlea);

        for (int i = 0; i < longNbAlea; i++) {
            if (reponse.charAt(i) != '=') {
                logger.debug("FIN de la méthode, elle retourne false");
                return false;
            }
        }
        logger.debug("FIN de la méthode, elle retourne true");
        return true;
    }

    /**
     * Cette méthode permet d'afficher le tour en cour ainsi que d'aérer un peu la lecture de la console
     *
     * @param numeroTour       numéro du tour en cours
     * @param nombreDeTourConf nombre de tour possible configuré
     */
    public static void affichageTour(int numeroTour, int nombreDeTourConf) {

        logger.debug("APPEL de la méthode affichageTour avec paramètres numeroTour: " + numeroTour + " ;nombreDeTourConf:" + nombreDeTourConf);

        int nbTourRestant = nombreDeTourConf - numeroTour;
        System.out.println("\n" + "**********************************************");
        System.out.println("Tour n°" + numeroTour + "(nombre de tour restant: " + nbTourRestant + ")");
        System.out.println("**********************************************" + "\n");

        logger.debug("FIN de la méthode");

    }

    //todo javadoc
    public static void winLoose(boolean winLoose) {

        logger.debug("APPEL de la méthode winLoose avec paramètre winLoose" + winLoose);

        if (!winLoose)
            System.out.println("L'ordinateur à perdu !");
        else
            System.out.println("L'ordinateur a gagné !");

        logger.debug("FIN de la méthode");

    }

    /**
     * Cette méthode permet un affichage en devMode de la combinaison secrète que le joueur doit trouver.
     *
     * @param combinaisonSecrete la valeur que le joueur doit trouver
     */
    public static void devMode(StringBuilder combinaisonSecrete) {

        logger.debug("APPEL de la méthode avec en paramètre combinaisonSecrete: " + combinaisonSecrete);

        System.out.println("\n" + "----------------DEV MODE ACTIF------------------");
        System.out.println("La combinaison secrète est : " + combinaisonSecrete);
        System.out.println("--------------------------------------------------" + "\n");

        logger.debug("FIN de la méthode");

    }

    /**
     * Cette méthode permet d'afficher le gagant en fonction d'un int
     *
     * @param numeroGagnant numéro du gagant
     */
    public static void gagnant(int numeroGagnant) {

        logger.debug("APPEL de la méthode gagnant avec en paramètre numeroGagnant: " + numeroGagnant);

        switch (numeroGagnant) {
            case 1:
                System.out.println("L'ordinateur a gagné !");
                break;
            case 2:
                System.out.println("Vous avez gagné !");
                break;
        }

        logger.debug("FIN de la méthode");
    }

    /**
     * Cette méthode permet de récuper la saisie de l'utilisateur, elle récupère un int qui ensuite retourner en String
     *
     * @param longNbAleaConf longueur du nombre aléatoire
     * @return la saisie de l'utilisateur de longueur longNbAleaConf en String
     */
    public static String saisieNuméros(int longNbAleaConf) {

        logger.debug("APPEL de la méthode saisieNumero avec en paramètre longNbAleaConf: " + longNbAleaConf);

        String choix = new String();
        Integer saisieUtilisateur ;
        Scanner sc = new Scanner(System.in);
        StringBuilder firstCharZero = new StringBuilder();
        boolean bonneSaisie ;
        do{
        try {
            do {
                System.out.println("Veuillez saisir un nombre ! (" + longNbAleaConf + ")");
                saisieUtilisateur = sc.nextInt();
                choix = saisieUtilisateur.toString();

                if (choix.length() <= longNbAleaConf - 1){
                    firstCharZero.delete(0, longNbAleaConf);
                    for (int i = 0; i < longNbAleaConf - choix.length(); i++)
                        firstCharZero.insert(i, 0);
                    firstCharZero.append(choix);
                    choix = firstCharZero.toString();
                    System.out.println(firstCharZero);
                }

                bonneSaisie = true ;
            } while (choix.length() != longNbAleaConf && firstCharZero.length() != longNbAleaConf);
        } catch (InputMismatchException e) {
            sc.next();
            System.out.println("Merci de saisir des chiffres(" + e + ")");
            bonneSaisie = false;
        }
        }while (!bonneSaisie);
        logger.debug("FIN de la méthode, elle retourne choix: " + choix);
        return choix;
    }


    /**
     * Cette méthode permet de récuperer la saisie de l'utilisateur, elle ne récupère que les signes =, - ou + et les retourne en String
     * Elle vérifie également que la saisie est de la bonne longueur
     *
     * @param longNbAleaConf longueur du nombre aléatoire
     * @return la saisie de l'utilisateur de longueur longNbAleaConf en String
     */
    public static String saisieSignes(int longNbAleaConf) {

        logger.debug("APPEL de la méthode saisieSignes avec en paramètre longNbAleaConf: "+longNbAleaConf);

        String saisie;
        Scanner sc = new Scanner(System.in);
        do {
            saisie = sc.next();
        } while (saisie.length() != longNbAleaConf);
        for (int i = 0; i < longNbAleaConf; i++)
            if (saisie.charAt(i) != '+' && saisie.charAt(i) != '-' && saisie.charAt(i) != '=')
                saisieSignes(longNbAleaConf);
            logger.debug("FIN de la méthode, elle retourne saisie: "+saisie);
        return saisie;
    }
}
