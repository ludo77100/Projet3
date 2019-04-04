package com.main;

import java.util.Scanner;

public class Mastermind {

    private int longNbAleaConf ; //todo A coder dans le fichier de conf //Longueur de la combinaison
    private int nombreTourConf ; //todo A coder dans le fichier de conf//nombre de tour possible
    private int nombreChiffresUtilisables ; //todo A coder dans le fichier de conf//nombre de chiffres utilisables

    public Mastermind(int longNbAleaConf, int nombreTourConf, int nombreChiffresUtilisables) {
        this.longNbAleaConf = longNbAleaConf;
        this.nombreTourConf = nombreTourConf;
        this.nombreChiffresUtilisables = nombreChiffresUtilisables;
    }

    public void mastermindChallenger() {

        int numeroTour = 0;//Compteur de nombre de tour
        int nombrePositionOk = 0;
        int nombrePositionMauvaise = 0;
        int nombreMauvais = 0;

        String recupSaisieUtilisateur;

        StringBuilder tentativeCombinaison = new StringBuilder();
        StringBuilder combinaisonSecrete = new StringBuilder();//Combinaison secrète généré par l'ordinateur

        Scanner sc = new Scanner(System.in);

        //L'ordinateur doit générer la solution
        combinaisonSecrete = Tools.geneNbAlea(longNbAleaConf, 1, nombreChiffresUtilisables);
        System.out.println(combinaisonSecrete);

        do {

            nombrePositionOk = 0;
            nombrePositionMauvaise = 0;
            nombreMauvais = 0;

            //Demande au joueur de saisir sa tentative pour trouver la combinaison secrète
            numeroTour++;
            Tools.affichageTour(numeroTour, nombreTourConf);
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

        } while (nombrePositionOk != longNbAleaConf && numeroTour < nombreTourConf);

        if (nombrePositionOk == longNbAleaConf)
            System.out.println("Bravo, vous avez gagné");
        else
            System.out.println("Vous avez perdu !");
    }

    public void mastermindDefenseur() {

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
            for (int i = 0; i < longNbAleaConf; i++) {
                if (tentativeCombinaison.charAt(i) != combinaisonSecrete.charAt(i)) {
                    tentativeCombinaison.deleteCharAt(i);
                    StringBuilder nouveauChiffre = Tools.geneNbAlea(1, 0, longNbAleaConf);
                    tentativeCombinaison.insert(i, nouveauChiffre);
                }
            }
        System.out.println(tentativeCombinaison);
        }while (!tentativeCombinaison.toString().equals(combinaisonSecrete.toString()) && numeroTour < nombreTourConf);

        if (!tentativeCombinaison.toString().equals(combinaisonSecrete.toString()))
        System.out.println("L'ordinateur à perdu !");
        else
        System.out.println("L'ordinateur à gagné en "+numeroTour+" tours !");
    }

    public void mastermindDuel(){

    }
}