package com.main;

import java.util.Random;
import java.util.Scanner;

public class PlusOuMoins {

    public static void choixMode(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Veuilez choisir votre mode de jeu. 1 - Challenger, 2 - Défenseur, 3 - Duel");
        int choixMode = sc.nextInt();
        switch (choixMode) {
            case 1:
                System.out.println("Bienvenue dans le +/-, mode Challenger !");
                plusOuMoinsChallenger();
                break;
            case 2:
                System.out.println("Bienvenue dans le +/-, mode Défenseur !");
                plusOuMoinsDefenseur();
                break;
            case 3:
                System.out.println("Bievenue dans le +/-, mode duel !");
                plusOuMoinsDuel();
                break;
            default:
                Tools.gameChoice();
        }
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
    public static void mainGameChal(int longNbAleaConf, String choix, StringBuilder nbAlea, StringBuilder reponse){

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

    public static void plusOuMoinsChallenger() {

        String choix ; //Saisie de l'utlisateur
        StringBuilder reponse = new StringBuilder();
        StringBuilder nbAlea = new StringBuilder();

        int longNbAleaConf = 3; //todo A coder dans le fichier de conf
        int nombreTourConf = 4; //todo A coder dans le fichier de conf
        int i = 0;

        Scanner sc = new Scanner(System.in);

        boolean winLoose ;

        nbAlea = Tools.geneNbAlea(longNbAleaConf, 1, 9); //Génération du nombre aléatoire

        System.out.println(nbAlea);

            do {
                    reponse.delete(0, longNbAleaConf);//On réinitialise la réponse afin de ne pas mettre bout à bout les réponses
                        //Demande de première saisie utillisateur et boucle pour avoir le bon nombre de chiffre saisi par l'utilisateur
                        do {
                            i++;
                            Tools.affichageTour(i, nombreTourConf);
                            System.out.println("Veuillez saisir un nombre(" + longNbAleaConf + " chiffres)");
                            choix = sc.next();
                        } while (choix.length() != longNbAleaConf);

                        mainGameChal(longNbAleaConf, choix, nbAlea, reponse);
                        winLoose = Tools.combinaisonValide(reponse, longNbAleaConf);


            } while (!winLoose && i < nombreTourConf);

        if (!winLoose)
            System.out.println("Vous avez perdu !");
        else
            System.out.println("Bravo ! Vous avez trouvé la combinaison secrète ! (" + choix + ")");


        //On propose de rejouer, choisir un autre jeux ou quitter le programme
        System.out.println("Que souhaitez vous faire ? 1: Rejouer -- 2: Choisir un autre jeu --3: Quitter");
        int replay = sc.nextInt();
        switch (replay){
            case 1:
                plusOuMoinsChallenger();
                break;
            case 2:
                Tools.gameChoice();
                break;
            case 3:
                return;
        }
    }


    /**
     *
     * @param longNbAleaConf
     * @param reponseEnSigne
     * @param reponseOrdi
     * @param r 
     */
    public static void mainGameDef(int longNbAleaConf, String reponseEnSigne, StringBuilder reponseOrdi, Random r){
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

    public static void plusOuMoinsDefenseur(){

        Random r = new Random();
        StringBuilder reponseOrdi = new StringBuilder(); //Nombre généré par l'ordinateur
        String reponseEnSigne; //Saisie de l'utlisateur
        String codeSecretUtilisateur;
        StringBuilder testerEquals = new StringBuilder(); //prend en valeur un nombre de = égale à longNbAlea
        int longNbAleaConf = 6; //todo A coder dans le fichier de conf
        int nombreTourConf = 10; //todo A coder dans le fichier de conf
        int i = 0;

        //On récupère le nombre de l'utilisateur que l'ordinateur doit deviner
        System.out.println("Veuillez saisir un nombre ! (" +longNbAleaConf+")");
        Scanner sc = new Scanner(System.in);
        codeSecretUtilisateur = sc.next();

        reponseOrdi = Tools.geneNbAlea(longNbAleaConf, 1, 9);
        testerEquals = Tools.geneTesterEquals(longNbAleaConf);

        //Ici l'ordinateur doit générer une nouvelle réponse en fonction de la variable choix
        do{

            i++;
            Tools.affichageTour(i, nombreTourConf);
            //On récupère ici la réponse de l'utilisateur
            System.out.println("L'ordinateur vous donne comme réponse : "+reponseOrdi);
            System.out.println("Pour chaque nombre, indiquer + ou - ou = (pour rappel, votre code secret est "+codeSecretUtilisateur+")");
            reponseEnSigne = sc.next();
            System.out.println("Vous avez saisi: " +reponseEnSigne);

            mainGameDef(longNbAleaConf, reponseEnSigne, reponseOrdi, r);

        }while (!reponseEnSigne.equals(testerEquals.toString()) && i < nombreTourConf);

        System.out.println("L'ordinateur a gagné !");
    }

    public static void plusOuMoinsDuel(){

        Random r = new Random();

        StringBuilder reponseOrdi = new StringBuilder(); //Nombre généré par l'ordinateur
        StringBuilder testerEquals = new StringBuilder(); //prend en valeur un nombre de = égale à longNbAlea
        StringBuilder reponse = new StringBuilder();
        StringBuilder nbAlea = new StringBuilder();

        String reponseEnSigne; //Saisie de l'utlisateur
        String codeSecretUtilisateur;
        String choix; //Saisie de l'utlisateur

        int longNbAleaConf = 3; //todo A coder dans le fichier de conf
        int gagnant ;

        Scanner sc = new Scanner(System.in);

        reponseOrdi =  Tools.geneNbAlea(longNbAleaConf, 1, 9);
        nbAlea = Tools.geneNbAlea(longNbAleaConf, 1, 9);
        testerEquals = Tools.geneTesterEquals(longNbAleaConf);

        System.out.println("L'odinateur commence !");

        //On récupère le nombre de l'utilisateur que l'ordinateur doit deviner
        System.out.println("Veuillez saisir le nombre que l'ordinateur doit deviner ! ("+longNbAleaConf+" chiffres)");
        codeSecretUtilisateur = sc.next();

        do {

            System.out.println("L'ordinateur vous donne comme réponse : " + reponseOrdi);
            System.out.println("Pour chaque nombre, indiquer + ou - ou = (pour rappel, votre code secret est " + codeSecretUtilisateur + ")");
            reponseEnSigne = sc.next();
            System.out.println("Vous avez saisi: " + reponseEnSigne);
            mainGameDef(longNbAleaConf, reponseEnSigne, reponseOrdi, r);

            if (reponseEnSigne.equals(testerEquals.toString())) {
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
                gagnant = 2;
            }
        }
            while (!reponse.toString().equals(testerEquals.toString())) ;
        switch (gagnant) {
            case 1:
                System.out.println("L'ordinateur a gagné !");
                break;
            case 2:
                System.out.println("Vous avez gagné !");
                break;
        }
    }
}
