package com.main;

import javax.tools.Tool;
import java.util.Random;
import java.util.Scanner;

public class PlusOuMoins {

    private int longNbAleaConf ; //Dans le fichier de conf
    private int nombreTourConf ; //Dans le fichier de conf
    private int devMode ; //Dans le fichier de conf

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

            if (devMode == 1)
                Tools.devMode(nbAlea);

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
     *Cette méthode permet de généré un nouveau nombre aléatoire en fonction de la réponse en signe donné par l'utilisateur
     *
     * @param longNbAleaConf passé dans le fichier de conf, longueur du nombre à trouver
     * @param reponseEnSigne réponse avec les signes +,- ou = rentré par l'utilisateur
     * @param reponseOrdi la réponse précédement généré par l'ordinateur, le passer en paramètre permet de générer un nombre aléatoire compris dans les bonnes bornes
     * @param r Méthode Random
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

            reponseOrdi = Tools.geneNbAlea(longNbAleaConf, 1, 9); //L'ordinateur génère sa première réponse
            nbAlea = Tools.geneNbAlea(longNbAleaConf, 1, 9); //Génération du nombre que l'utilisateur doit trouver

            if (devMode == 1) //Condition permettant l'affichage de la solution que l'utilisateur doit trouver en dev mode
                Tools.devMode(nbAlea);

            System.out.println("L'odinateur commence !");

            //On récupère le nombre de l'utilisateur que l'ordinateur doit deviner
            System.out.println("Veuillez saisir le nombre que l'ordinateur doit deviner ! (" + longNbAleaConf + " chiffres)");
            codeSecretUtilisateur = sc.next();

            do {
                //On incrémente le numéro du tour + display du tour en cours
                numeroTour++;
                Tools.affichageTour(numeroTour, nombreTourConf);

                //L'ordinateur affiche sa première tentative, pour chaque chiffre l'utilisateur indique +, = ou -
                System.out.println("L'ordinateur vous donne comme réponse : " + reponseOrdi);
                System.out.println("Pour chaque nombre, indiquer + ou - ou = (pour rappel, votre code secret est " + codeSecretUtilisateur + ")");
                saisieUtilisateur = sc.next();
                System.out.println("Vous avez saisi: " + saisieUtilisateur);

                mainGameDef(longNbAleaConf, saisieUtilisateur, reponseOrdi, r); //On génère une nouvelle réponse en fonction de la réponse de l'utilisateur
                reponseEnSigne.delete(0, reponseEnSigne.length()); //On supprime la réponse de l'utilisateur pour la prochaine tentative afin de ne pas mettre bout à bout ses réponses

                reponseEnSigne.append(saisieUtilisateur); //On passe en Stringbuilder afin de pouvoir utliser la méthode CombinaisonValide dans Tools
                winLoose = Tools.combinaisonValide(reponseEnSigne, longNbAleaConf); //On regarde si l'ordinateur à gagné
                if (winLoose) {
                    gagnant = 1;
                    break;
                } else {

                    System.out.println("A votre tour, à vous de trouver le nombre de l'ordinateur");
                    reponse.delete(0, longNbAleaConf);//On réinitialise la réponse afin de ne pas mettre bout à bout les réponses

                    //Demande de saisie utillisateur et boucle pour avoir le bon nombre de chiffre saisi par l'utilisateur
                    do {
                        System.out.println("Veuillez saisir un nombre(" + longNbAleaConf + " chiffres)");
                        choix = sc.next();
                    } while (choix.length() != longNbAleaConf);

                    mainGameChal(longNbAleaConf, choix, nbAlea, reponse); //On génère la réponse de l'ordinateur en signe.
                    winLoose = Tools.combinaisonValide(reponse, longNbAleaConf);//On regarde si l'utilisateur à gagné
                    gagnant = 2;
                }
            }while (!winLoose);
            Tools.gagnant(gagnant);
            replay = Menu.finDePArtie();
        }
    }
}