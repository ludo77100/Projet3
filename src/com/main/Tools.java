package com.main;

import java.util.Random;

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

    public static void winLoose (boolean winLoose){
        if (!winLoose)
            System.out.println("L'ordinateur à perdu !");
        else
            System.out.println("L'ordinateur a gagné !");
    }

    /**
     *
     * Cette méthode permet un affichage en devMode de la combinaison secrète que le joueur doit trouver.
     *
     * @param combinaisonSecrete la valeur que le joueur doit trouver
     */
    public static void devMode(StringBuilder combinaisonSecrete){
        System.out.println("\n"+"----------------DEV MODE ACTIF------------------");
        System.out.println("La combinaison secrète est : "+combinaisonSecrete);
        System.out.println("--------------------------------------------------"+"\n");
    }
}
