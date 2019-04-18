package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe principal du programme
 */
public class Main {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Cette méthode permet d'executer le programme, et d'afficher le menu, et d'activer le mode dev via un argument au lancement
     * @param args permet d'activer le mode dev en fonction d'un paramètre passer au lancement du programme
     */
    public static void main(String[] args) {

        Menu menu = new Menu();

        logger.info("Execution de l'application");
        Messages.bienvenue();
        menu.gameChoice();
        logger.info("Fermeture de l'application");
    }
}

