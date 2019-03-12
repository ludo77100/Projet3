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

    public static void plusOuMoinsChallenger() {

        String choix; //Saisie de l'utlisateur
        StringBuilder reponse = new StringBuilder();
        StringBuilder nbAlea = new StringBuilder();
        StringBuilder testerEquals = new StringBuilder(); //prend en valeur un nombre de = égale à longNbAlea
        int longNbAleaConf = 6; //todo A coder dans le fichier de conf
        Scanner sc = new Scanner(System.in);

        nbAlea = Tools.geneNbAlea(longNbAleaConf); //Génération du nombre aléatoire
        testerEquals = Tools.geneTesterEquals(longNbAleaConf); //Génération du testeur pour arréter la boucle du jeu si réponse ok

        System.out.println(nbAlea);

        do{

            reponse.delete(0, longNbAleaConf);//On réinitialise la réponse afin de ne pas mettre bout à bout les réponses

            //Demande de première saisie utillisateur et boucle pour avoir le bon nombre de chiffre saisi par l'utilisateur
            do {
                System.out.println("Veuillez saisir un nombre(" + longNbAleaConf + " chiffres)");
                choix = sc.next();
            }while (choix.length() != longNbAleaConf);


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
            System.out.println("Votre proposition est : "+choix+ " -> Réponse : " +reponse);

        }while (!reponse.toString().equals(testerEquals.toString()));

        System.out.println("Bravo ! Vous avez trouvé la combinaison secrète ! (" +choix+ ")");
        System.out.println(" ");

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

    public static void plusOuMoinsDefenseur(){

        Random r = new Random();
        StringBuilder reponseOrdi = new StringBuilder(); //Nombre généré par l'ordinateur
        String reponseEnSigne; //Saisie de l'utlisateur
        String codeSecretUtilisateur;
        StringBuilder testerEquals = new StringBuilder(); //prend en valeur un nombre de = égale à longNbAlea
        int longNbAleaConf = 6; //todo A coder dans le fichier de conf
        int nombreTourConf = 10; //todo A coder dans le fichier de conf

        //On récupère le nombre de l'utilisateur que l'ordinateur doit deviner
        System.out.println("Veuillez saisir un nombre ! (" +longNbAleaConf+")");
        Scanner sc = new Scanner(System.in);
        codeSecretUtilisateur = sc.next();

        reponseOrdi = Tools.geneNbAlea(longNbAleaConf);
        testerEquals = Tools.geneTesterEquals(longNbAleaConf);

        //Ici l'ordinateur doit générer une nouvelle réponse en fonction de la variable choix
        do{

            //On récupère ici la réponse de l'utilisateur
            System.out.println("L'ordinateur vous donne comme réponse : "+reponseOrdi);
            System.out.println("Pour chaque nombre, indiquer + ou - ou = (pour rappel, votre code secret est "+codeSecretUtilisateur+")");
            reponseEnSigne = sc.next();
            System.out.println("Vous avez saisi: " +reponseEnSigne);

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
        }while (!reponseEnSigne.equals(testerEquals.toString()));

        System.out.println("L'ordinateur a gagné !");
    }

    public static void plusOuMoinsDuel(){

    }
}
