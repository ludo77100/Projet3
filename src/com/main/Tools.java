package com.main;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Tools {

    /**
     * Méthode pour générer un nombre aléatoire
     *
     * @param longNbAleaConf la longueur du nombre devant être gégéré
     * @param lowBound limite basse pour la génération du chiffre aléatoire
     * @param highBound limite haute pour la génération du
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
     *
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
     * @param reponse reponse donné par le défenseur (avec les signes + - =)
     * @param longNbAlea longueur du nombre aléatoire
     * @return false: Mauvaise combianaison, true: Bonne combinaison
     */
    public static boolean combinaisonValide(StringBuilder reponse, int longNbAlea){

        for (int i=0; i < longNbAlea; i++){
            if (reponse.charAt(i) != '=')
                return false;
        }
        return true;
    }

    /**
     * Cette méthode permet d'afficher le tour en cour ainsi que d'aérer un peu la lecture de la console
     *
     * @param numeroTour numéro du tour en cours
     * @param nombreDeTourConf nombre de tour possible configuré
     */
    public static void affichageTour(int numeroTour, int nombreDeTourConf){
        int nbTourRestant = nombreDeTourConf - numeroTour;
        System.out.println("\n"+"**********************************************");
        System.out.println("Tour n°" +numeroTour+ "(nombre de tour restant: "+nbTourRestant+")");
        System.out.println("**********************************************"+"\n");

    }
//todo javadoc
    public static void winLoose (boolean winLoose){
        if (!winLoose)
            System.out.println("L'ordinateur à perdu !");
        else
            System.out.println("L'ordinateur a gagné !");
    }

    /**
     * Cette méthode permet un affichage en devMode de la combinaison secrète que le joueur doit trouver.
     *
     * @param combinaisonSecrete la valeur que le joueur doit trouver
     */
    public static void devMode(StringBuilder combinaisonSecrete){
        System.out.println("\n"+"----------------DEV MODE ACTIF------------------");
        System.out.println("La combinaison secrète est : "+combinaisonSecrete);
        System.out.println("--------------------------------------------------"+"\n");
    }

    /**
     * Cette méthode permet d'afficher le gagant en fonction d'un int
     *
     * @param numeroGagnant numéro du gagant
     */
    public static void gagnant(int numeroGagnant){
        switch (numeroGagnant) {
            case 1:
                System.out.println("L'ordinateur a gagné !");
                break;
            case 2:
                System.out.println("Vous avez gagné !");
                break;
        }
    }

    public static String saisieNuméros(int longNbAleaConf){

        String choix = new String();
        Scanner sc = new Scanner(System.in);
            try {
                do {
                    System.out.println("Veuillez saisir un nombre ! (" + longNbAleaConf + ")");
                    choix = sc.next();
                }while (choix.length() != longNbAleaConf);
            }catch (InputMismatchException e) {
                System.out.println("Merci de saisir des chiffres("+e+")");
                saisieNuméros(longNbAleaConf);
            }
            return choix ;
    }

    public static String saisieSignes(int longNbAleaConf) {

            String saisie;
            Scanner sc = new Scanner(System.in);
            do {
                saisie = sc.next();
            }while (saisie.length() != longNbAleaConf);
            for (int i = 0; i < longNbAleaConf; i++)
                if (saisie.charAt(i) != '+' && saisie.charAt(i) != '-' && saisie.charAt(i) != '=')
                    saisieSignes(longNbAleaConf);
            return saisie;
        }
    }
