package com.main;

import java.util.Scanner;

public class Mastermind {

    private int longNbAleaConf ; //todo A coder dans le fichier de conf //Longueur de la combinaison
    private int nombreTourConf ; //todo A coder dans le fichier de conf//nombre de tour possible
    private int nombreChiffresUtilisables ; //todo A coder dans le fichier de conf//nombre de chiffres utilisables
    private int devMode ;

    public Mastermind(int longNbAleaConf, int nombreTourConf, int nombreChiffresUtilisables, int devMode) {
        this.longNbAleaConf = longNbAleaConf;
        this.nombreTourConf = nombreTourConf;
        this.nombreChiffresUtilisables = nombreChiffresUtilisables;
        this.devMode = devMode;
    }

    /**
     *
     *
     *
     * @param nombrePositionOk
     * @param nombrePositionMauvaise
     * @param nombreMauvais
     * @param recupSaisieUtilisateur
     * @param tentativeCombinaison
     * @param sc
     * @param combinaisonSecrete
     * @return La valeur du nombre de pion en bonne position
     */
    private int mainGameChal(int nombrePositionOk, int nombrePositionMauvaise, int nombreMauvais, String recupSaisieUtilisateur, StringBuilder tentativeCombinaison, Scanner sc, StringBuilder combinaisonSecrete){

        nombrePositionOk = 0;
        nombrePositionMauvaise = 0;
        nombreMauvais = 0;

        //Demande au joueur de saisir sa tentative pour trouver la combinaison secrète


        System.out.println("Veuillez saisir une combinaison (Chiffre de 1 à " + nombreChiffresUtilisables + ")");
        recupSaisieUtilisateur = sc.next();
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
        System.out.println("Vous avez " + nombrePositionMauvaise + " pion en mauvaise position");
        System.out.println("Vous avez " + nombreMauvais + " mauvais pion");

        tentativeCombinaison.delete(0, longNbAleaConf);
        return nombrePositionOk;

    }

    public void mastermindChallenger() {

        boolean replay = true;

        while (replay == true) {

            replay = false;

            int numeroTour = 0;//Compteur de nombre de tour
            int nombrePositionOk = 0;
            int nombrePositionMauvaise = 0;
            int nombreMauvais = 0;

            String recupSaisieUtilisateur = new String();

            StringBuilder tentativeCombinaison = new StringBuilder();
            StringBuilder combinaisonSecrete = new StringBuilder();//Combinaison secrète généré par l'ordinateur

            Scanner sc = new Scanner(System.in);

            //L'ordinateur doit générer la solution
            combinaisonSecrete = Tools.geneNbAlea(longNbAleaConf, 1, nombreChiffresUtilisables);

            if (devMode == 1)
                Tools.devMode(combinaisonSecrete);

            do {

                numeroTour++;
                Tools.affichageTour(numeroTour, nombreTourConf);
                nombrePositionOk = mainGameChal(nombrePositionOk, nombrePositionMauvaise, nombreMauvais, recupSaisieUtilisateur, tentativeCombinaison, sc, combinaisonSecrete);

            } while (nombrePositionOk != longNbAleaConf && numeroTour < nombreTourConf);

            if (nombrePositionOk == longNbAleaConf)
                System.out.println("Bravo, vous avez gagné");
            else
                System.out.println("Vous avez perdu !");
            replay = Menu.finDePArtie();
        }
    }

    private StringBuilder mainGameDef(StringBuilder tentativeCombinaison, StringBuilder combinaisonSecrete) {

        for (int i = 0; i < longNbAleaConf; i++) {
            if (tentativeCombinaison.charAt(i) != combinaisonSecrete.charAt(i)) {
                tentativeCombinaison.deleteCharAt(i);
                StringBuilder nouveauChiffre = Tools.geneNbAlea(1, 0, longNbAleaConf);
                tentativeCombinaison.insert(i, nouveauChiffre);
            }
        }
        return tentativeCombinaison;
    }

    public void mastermindDefenseur() {

        boolean replay = true ;

        while (replay == true){

            replay = false ;

        int numeroTour = 0;//Compteur de nombre de tour

        String recupSaisieUtilisateur ;

        StringBuilder tentativeCombinaison = new StringBuilder();
        StringBuilder combinaisonSecrete = new StringBuilder();

        Scanner sc = new Scanner(System.in);

        //L'utilisateur décide de la combinaison secrète

        System.out.println("Veuillez saisir la combinaison secrète que l'ordinateur doit deviner");
        recupSaisieUtilisateur = sc.next();
        combinaisonSecrete.append(recupSaisieUtilisateur);

        //L'ordinateur génère sa première réponse
        tentativeCombinaison = Tools.geneNbAlea(longNbAleaConf, 0, nombreChiffresUtilisables);

        System.out.println(tentativeCombinaison);

        //On compare la combinaison a la réponse de l'ordi
        do {
            numeroTour++;
            Tools.affichageTour(numeroTour, nombreTourConf);
            tentativeCombinaison = mainGameDef(tentativeCombinaison, combinaisonSecrete);

        System.out.println(tentativeCombinaison);
        }while (!tentativeCombinaison.toString().equals(combinaisonSecrete.toString()) && numeroTour < nombreTourConf);

        if (!tentativeCombinaison.toString().equals(combinaisonSecrete.toString()))
        System.out.println("L'ordinateur à perdu !");
        else
        System.out.println("L'ordinateur à gagné en "+numeroTour+" tours !");
        replay = Menu.finDePArtie();
    }
    }

    public void mastermindDuel() {

        boolean replay = true;

        while (replay == true) {

            replay = false;

            Scanner sc = new Scanner(System.in);
            String recupSaisieUtilisateur;
            int numeroTour = 0;
            int gagnant = 0;

            //Variable pour l'ordinateur
            StringBuilder tentativeCombinaisonOrdi = new StringBuilder();
            StringBuilder combinaisonSecreteOrdi = new StringBuilder();

            //Variable pour le joueur
            StringBuilder tentativeCombinaisonJoueur = new StringBuilder();
            StringBuilder combinaisonSecreteJoueur = new StringBuilder();
            int nombrePositionOk = 0;
            int nombrePositionMauvaise = 0;
            int nombreMauvais = 0;

            System.out.println("L'ordinateur commence !");

            //L'ordinateur génère sa première réponse
            tentativeCombinaisonOrdi = Tools.geneNbAlea(longNbAleaConf, 0, nombreChiffresUtilisables);
            //L'ordinateur génère la combinaison secrète que le joueur doit trouver
            combinaisonSecreteJoueur = Tools.geneNbAlea(longNbAleaConf, 0, nombreChiffresUtilisables);
            if (devMode == 1)
                Tools.devMode(combinaisonSecreteJoueur);

            System.out.println("Veuillez saisir la combinaison secrète que l'ordinateur doit deviner:");
            recupSaisieUtilisateur = sc.next();
            combinaisonSecreteOrdi.append(recupSaisieUtilisateur);

            do {
                numeroTour++;
                Tools.affichageTour(numeroTour, nombreTourConf);
                tentativeCombinaisonOrdi = mainGameDef(tentativeCombinaisonOrdi, combinaisonSecreteOrdi);
                System.out.println("L'ordinateur donne : " + tentativeCombinaisonOrdi);
                if (tentativeCombinaisonOrdi.toString().equals(combinaisonSecreteOrdi.toString())) {
                    gagnant = 1;
                    break;
                } else {
                    System.out.println("A votre tour, à vous de trouver le nombre de l'ordinateur");
                    System.out.println("mode dev: " + combinaisonSecreteJoueur);
                    nombrePositionOk = mainGameChal(nombrePositionOk, nombrePositionMauvaise, nombreMauvais, recupSaisieUtilisateur, tentativeCombinaisonJoueur, sc, combinaisonSecreteJoueur);
                    if (nombrePositionOk == longNbAleaConf)
                    {
                        gagnant = 2;
                        break;
                    }
                }
            } while (numeroTour < nombreTourConf);

            switch (gagnant) {
                case 1:
                    System.out.println("L'ordinateur a gagné !");
                    break;
                case 2:
                    System.out.println("Vous avez gagné !");
                    break;
            }
            replay = Menu.finDePArtie();
        }
    }
}