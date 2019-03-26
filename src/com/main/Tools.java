package com.main;

import java.util.Random;
import java.util.Scanner;

public class Tools {

    public static void gameChoice() {
        System.out.println("Veuillez choisir votre jeux");
        System.out.println("1: Recherche +/-");
        System.out.println("2: Mastermind");
        Scanner sc = new Scanner(System.in);
        int choixJeu = sc.nextInt();
        switch (choixJeu) {
            case 1:
                PlusOuMoins.choixMode();
            case 2:
                Mastermind.choixMode();
                break;
            default:
                gameChoice();
                break;
        }
    }

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

    public static boolean combinaisonValide(StringBuilder reponse, int longNbAlea){

        for (int i=0; i < longNbAlea; i++){
            if (reponse.charAt(i) != '=')
                return false;
        }
        return true;
    }
    /*
    public static boolean combinaisonValide(StringBuilder reponse, int longNbAlea){

        int i = 0;
        boolean tester ;

        while (i <= longNbAlea -1 && reponse.charAt(i) == '=' )
            i++;

        if (i  == longNbAlea)
            tester = true;
        else
            tester = false;

        return tester;
    }
*/

    /**
     *
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
}
