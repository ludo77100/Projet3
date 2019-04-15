package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Messages {

    public static final Logger logger = LogManager.getLogger();

    /**
     * Cette méthode affiche le message bienvenue en ASCII
     */
    public static void bienvenue(){
        logger.info("Affichage message bienvenue ASCII");
        System.out.println(" ____  _                                     ");
        System.out.println("| __ )(_) ___ _ ____   _____ _ __  _   _  ___ ");
        System.out.println("|  _ \\| |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| | | |/ _ \\");
        System.out.println("| |_) | |  __/ | | \\ V /  __/ | | | |_| |  __/");
        System.out.println("|____/|_|\\___|_| |_|\\_/ \\___|_| |_|\\__,_|\\___|");
    }
    /**
     * Cette méthode affiche le message Plus ou Moins en ASCII
     */
    public static void plusOuMoins(){
        logger.info("Affichage message Plus ou Moins ASCII");
        System.out.println(" ____  _                           __  __       _           ");
        System.out.println("|  _ \\| |_   _ ___    ___  _   _  |  \\/  | ___ (_)_ __  ___ ");
        System.out.println("| |_) | | | | / __|  / _ \\| | | | | |\\/| |/ _ \\| | '_ \\/ __|");
        System.out.println("|  __/| | |_| \\__ \\ | (_) | |_| | | |  | | (_) | | | | \\__ \\");
        System.out.println("|_|   |_|\\__,_|___/  \\___/ \\__,_| |_|  |_|\\___/|_|_| |_|___/");
    }
    /**
     * Cette méthode affiche le message Mastermind en ASCII
     */
    public static void mastermind(){
        logger.info("Affichage message Mastermind ASCII");
        System.out.println(" __  __           _                      _           _ ");
        System.out.println("|  \\/  | __ _ ___| |_ ___ _ __ _ __ ___ (_)_ __   __| |");
        System.out.println("| |\\/| |/ _` / __| __/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` |");
        System.out.println("| |  | | (_| \\__ \\ ||  __/ |  | | | | | | | | | | (_| |");
        System.out.println("|_|  |_|\\__,_|___/\\__\\___|_|  |_| |_| |_|_|_| |_|\\__,_|");
    }

}
