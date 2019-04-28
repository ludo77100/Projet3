<b>Projet 3 parcours Développeur d'application Java:</b>

Ce projet consiste à développer un programme contenant 2 jeux, une recherche de combinaison secrète à l'aide d'indication +/=/-, et un Mastermind !

Pour chaque jeu, on peut configurer grâce au config.properties:
- le nombre de cases de la combinaison secrète
- le nombre d'essais possibles
- le mode dev (pour afficher la combinaison à trouver)

Pour le mastermind:
- le nombre couleur/chiffre utilisables (de 4 à 10)

Comment compiler l'application:
- Cloner le repo depuis: https://github.com/ludo77100/Projet3.git
- Importer la configuration maven
- Depuis le panneau maven, taper la commande "clean install" dans execute maven goal

Depuis un terminal, se placer dans le dossier /emplacementDuProjetCloné/Projet3/target/ <br />
Lancer ensuite le programme avec la commande (vous pouvez passer en argument dm pour lancer le mode dev): java -jar Projet3-1.0-SNAPSHOT.jar

Vous pouvez configurer les règles du jeux grâce au fichier config.properties, en ouvrant ce fichier dans l'archive Jar grâce un gestionnaire d'archive.<br />
Vous pouvez ensuite modifier les paramèttres, enregistrer le config.properties et relancer le jeu dans le terminal.
