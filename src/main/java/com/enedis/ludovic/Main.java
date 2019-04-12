package com.enedis.ludovic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Execution de l'application");
        Messages.bienvenue();
        Menu.gameChoice();
        logger.info("Fermeture de l'application");
    }
}

