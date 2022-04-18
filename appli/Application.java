/**
 * @file Appli.Appli.java
 * @author  Théo Constant 108 et Ethan Brossard 110
 * @brief
 *      Ce fichier Appli.Appli.java est le fichier qui contient la fonction main().
 *      C'est ici que le jeu va se lancer.
 */

package appli;

import Jeu.Jeu;

public class Application {
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.jouer();
    }
}
