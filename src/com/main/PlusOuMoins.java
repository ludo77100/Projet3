package com.main;

import java.util.Random;
import java.util.Scanner;

public class PlusOuMoins {

    private int longNbAleaConf ; //todo A coder dans le fichier de conf
    private int nombreTourConf ; //todo A coder dans le fichier de conf
    private int devMode ;

    public PlusOuMoins(int longNbAleaConf, int nombreTourConf, int devMode) {
        this.longNbAleaConf = longNbAleaConf;
        this.nombreTourConf = nombreTourConf;
        this.devMode = devMode;
    }


    /**
     * Cette méthode permet de générer la réponse en fonction de la réponse de l'utilisateur
     *
     * @param longNbAleaConf passé dans le fichier conf, longueur du nombre à trouvé
     * @param choix c'est la réponse rentré par l'utilisateur
     * @param nbAlea ce nombre est généré aléatoirement (dans Tools méthode geneNbAlea)
     * @param reponse réponse généré par l'ordinateur (avec les signes =+-)
     *
     */
    private void mainGameChal(int longNbAleaConf, String choix, StringBuilder nbAlea, StringBuilder reponse){

        for (int i = 0; i < longNbAleaConf; i++) {
            int y = Character.getNumericValue(nbAlea.charAt(i));
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
    }

    public void plusOuMoinsChallenger() {

        boolean replay = true;
        while (replay == true) { //Boucle utilisé afin de pouvoir rejouer en fin de partie
            replay = false ;

            String choix; //Saisie de l'utlisateur
            StringBuilder reponse = new StringBuilder();
            StringBuilder nbAlea = new StringBuilder();

            int numeroTour = 0;

            Scanner sc = new Scanner(System.in);

            boolean winLoose;

            nbAlea = Tools.geneNbAlea(longNbAleaConf, 1, 9); //Génération du nombre aléatoire

            System.out.println(nbAlea);

            do {
                reponse.delete(0, reponse.length());//On réinitialise la réponse afin de ne pas mettre bout à bout les réponses
                //Demande de première saisie utillisateur et boucle pour avoir le bon nombre de chiffre saisi par l'utilisateur
                do {
                    numeroTour++;
                    Tools.affichageTour(numeroTour, nombreTourConf);
                    System.out.println("Veuillez saisir un nombre(" + longNbAleaConf + " chiffres)");
                    choix = sc.next();
                } while (choix.length() != longNbAleaConf);

                mainGameChal(longNbAleaConf, choix, nbAlea, reponse);
                winLoose = Tools.combinaisonValide(reponse, longNbAleaConf);


            } while (!winLoose && numeroTour < nombreTourConf);

            if (!winLoose)
                System.out.println("Vous avez perdu !");
            else
                System.out.println("Bravo ! Vous avez trouvé la combinaison secrète ! (" + choix + ")");
            replay = Menu.finDePArtie();
        }
    }

    /**
     *
     * @param longNbAleaConf
     * @param reponseEnSigne
     * @param reponseOrdi
     * @param r 
     */
    private void mainGameDef(int longNbAleaConf, String reponseEnSigne, StringBuilder reponseOrdi, Random r){
        for(int i = 0; i < longNbAleaConf; i++) {
            switch (reponseEnSigne.charAt(i)) {
                case ('='):
                    break;
                case ('+'):
                    int z = Character.getNumericValue(reponseOrdi.charAt(i));
                    int geneNbAleaz = z + r.nextInt((9+1) - z);
                    reponseOrdi.deleteCharAt(i);
                    reponseOrdi.insert(i, geneNbAleaz);
                    break;
                case ('-'):
                    int y = Character.getNumericValue(reponseOrdi.charAt(i));
                    if (y > 1) {
                        int geneNbAleay = 1 + r.nextInt(y + - 1);
                        reponseOrdi.deleteCharAt(i);
                        reponseOrdi.insert(i, geneNbAleay);
                        break;
                    }
                    else {
                        int geneNbAleay = 1 + r.nextInt((y+1) - 1);
                        reponseOrdi.deleteCharAt(i);
                        reponseOrdi.insert(i, geneNbAleay);
                    }
            }
        }
    }

    public void plusOuMoinsDefenseur() {

        boolean replay = true;
        while (replay == true) {

            Random r = new Random();
            StringBuilder reponseOrdi = new StringBuilder(); //Nombre généré par l'ordinateur
            StringBuilder reponseEnSigne = new StringBuilder();
            String saisieUtilisateur; //Saisie de l'utlisateur
            String codeSecretUtilisateur;
            int numeroTour = 0;
            boolean winLoose;

            //On récupère le nombre de l'utilisateur que l'ordinateur doit deviner
            System.out.println("Veuillez saisir un nombre ! (" + longNbAleaConf + ")");
            Scanner sc = new Scanner(System.in);
            codeSecretUtilisateur = sc.next();

            reponseOrdi = Tools.geneNbAlea(longNbAleaConf, 1, 9);

            //Ici l'ordinateur doit générer une nouvelle réponse en fonction de la variable choix
            do {
                numeroTour++;
                reponseEnSigne.delete(0, reponseEnSigne.length());
                Tools.affichageTour(numeroTour, nombreTourConf);

                //On récupère ici la réponse de l'utilisateur
                System.out.println("L'ordinateur vous donne comme réponse : " + reponseOrdi);
                System.out.println("Pour chaque nombre, indiquer + ou - ou = (pour rappel, votre code secret est " + codeSecretUtilisateur + ")");
                saisieUtilisateur = sc.next();
                System.out.println("Vous avez saisi: " + saisieUtilisateur);

                reponseEnSigne.append(saisieUtilisateur); //On passe en StringBuilder afin de pouvoir utiliser la méthode combinaisonValide dans Tools

                mainGameDef(longNbAleaConf, saisieUtilisateur, reponseOrdi, r);
                winLoose = Tools.combinaisonValide(reponseEnSigne, longNbAleaConf);
            } while  (!winLoose && numeroTour < nombreTourConf );
            Tools.winLoose(winLoose);
            replay = Menu.finDePArtie();
        }
    }

    public void plusOuMoinsDuel() {

        boolean replay = true;

        while (replay == true) {
            replay = false ;

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

            Scanner sc = new Scanner(System.in);

            reponseOrdi = Tools.geneNbAlea(longNbAleaConf, 1, 9);
            nbAlea = Tools.geneNbAlea(longNbAleaConf, 1, 9);


            System.out.println("L'odinateur commence !");

            //On récupère le nombre de l'utilisateur que l'ordinateur doit deviner
            System.out.println("Veuillez saisir le nombre que l'ordinateur doit deviner ! (" + longNbAleaConf + " chiffres)");
            codeSecretUtilisateur = sc.next();

            do {
                numeroTour++;
                Tools.affichageTour(numeroTour, nombreTourConf);
                System.out.println("L'ordinateur vous donne comme réponse : " + reponseOrdi);
                System.out.println("Pour chaque nombre, indiquer + ou - ou = (pour rappel, votre code secret est " + codeSecretUtilisateur + ")");
                saisieUtilisateur = sc.next();
                System.out.println("Vous avez saisi: " + saisieUtilisateur);
                mainGameDef(longNbAleaConf, saisieUtilisateur, reponseOrdi, r);
                reponseEnSigne.delete(0, reponseEnSigne.length());
                reponseEnSigne.append(saisieUtilisateur);
                winLoose = Tools.combinaisonValide(reponseEnSigne, longNbAleaConf);
                if (winLoose) {
                    gagnant = 1;
                    break;
                } else {

                    System.out.println("A votre tour, à vous de trouver le nombre de l'ordinateur");
                    System.out.println(nbAlea);

                    reponse.delete(0, longNbAleaConf);//On réinitialise la réponse afin de ne pas mettre bout à bout les réponses

                    //Demande de première saisie utillisateur et boucle pour avoir le bon nombre de chiffre saisi par l'utilisateur
                    do {
                        System.out.println("Veuillez saisir un nombre(" + longNbAleaConf + " chiffres)");
                        choix = sc.next();
                    } while (choix.length() != longNbAleaConf);

                    mainGameChal(longNbAleaConf, choix, nbAlea, reponse);
                    winLoose = Tools.combinaisonValide(reponse, longNbAleaConf);
                    gagnant = 2;
                }
            }while (!winLoose);

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