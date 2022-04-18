package Jeu;

import Cartes.Carte;
import Cartes.Paquet;
import Cartes.Serie;
import Joueurs.Joueur;
import appli.Console;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Jeu {
    private Paquet paquet;
    private Serie[] series;
    private ArrayList<Joueur> joueurs;
    private boolean enCours;
    private final int NB_SERIES = 4;

    /**
     * @brief Constructeur qui initialise le paquet, les series et les joueurs
     */
    public Jeu() {
        enCours = true;
        // Initialise le paquet
        paquet = new Paquet();
        paquet.melanger();
        
        // Initialise les joueurs
        joueurs = new ArrayList<>();
        String nomFichier = "src/Joueurs/config.txt";
        try {
            Scanner in = new Scanner(new FileInputStream(nomFichier));
            while (in.hasNextLine()) {
                joueurs.add(new Joueur(in.nextLine().trim()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier \"" + nomFichier + "\" est introuvable.");
        }
        assert (2 <= joueurs.size() && joueurs.size() <= 10) : "Il y a " + joueurs.size() + " joueurs, le jeu se joue entre 2 et 10 joueurs";

        // Distribution des cartes aux joueurs
        for (Joueur joueur: joueurs) {
            for (int i = 0; i < 10; ++i) {
                joueur.getCartes().add(paquet.enlever());
            }
            // Tri croissant des cartes des joueurs
            Collections.sort(joueur.getCartes(), (c1, c2) -> c1.getNumero() - c2.getNumero());
        }

        // Initialisation des séries
        series = new Serie[NB_SERIES];
        for (int i = 0; i < NB_SERIES; ++i) {
            series[i] = new Serie();
            series[i].ajouterCarte(paquet.enlever());
        }
    }

    /* ========== GETTERS ========== */

    /**
     * @brief Donne le nombre de joeur
     * @return int, Le nombre de joueur
     */
    public int getNombreJoueurs() { return joueurs.size(); }

    /**
     * Donne un joueur selon son indice dans la liste
     * @param index : Int, Indice du joueur
     * @return Joueur, Le joueur
     */
    public Joueur getJoueur(int index) {
        assert (0 <= index && index < getNombreJoueurs());
        return joueurs.get(index);
    }


    /* ========== METHODS ========== */
    /**
     * @brief Trie les joueurs dans l'ordre croissant des cartes choisi par les joueurs
     */
    public void triJoueurs() {
        // Comparator = fonction lambda
        Collections.sort(joueurs, (j1, j2) -> j1.getCarteChoisi().getNumero() - j2.getCarteChoisi().getNumero());
    }

    /**
     * @brief Vérifie si le jeu est terminer et change l'état du jeu en cours si c'est la fin
     */
    public void verifJeuEnCours() {
        for (int i = 0; i < getNombreJoueurs(); i++) {
            // Si il y a encore des cartes à jouer on continue de jouer
            if (!getJoueur(i).estVide()) { break; }
            // Sinon le jeu est finie
            if (i >= getNombreJoueurs()-1) { enCours = false; }
        }
    }


    /* ========== METEHODES AFFICHAGES ========== */
    /**
     * @brief Affiche les séries
     */
    private void afficherSeries() {
        for (int i = 0; i < NB_SERIES; ++i) {
            System.out.print("- série n° " + (i+1) +" : ");
            System.out.println(series[i]);
        }
    }

    /**
     * @brief Affiche le message de bienvenue au jeu
     */
    private void afficherMessageBienvenue() {
        System.out.print("Les " + getNombreJoueurs() + " joueurs sont ");
        for (int i = 0; i < getNombreJoueurs()-1; ++i) {
            System.out.print(getJoueur(i));
            if (i < getNombreJoueurs()-2)
                System.out.print(", ");
        }
        System.out.println(" et " + getJoueur(getNombreJoueurs()-1) + ". Merci de jouer à 6 qui prend !");
    }

    /**
     * @brief Affiche le message des cartes posées par les joueurs
     */
    private void afficherCartesPosees() {
        System.out.print("Les cartes ");
        for (int i = 0; i < getNombreJoueurs()-1; i++) {
            System.out.print(joueurs.get(i).getCarteChoisi().getNumero() + " (" + joueurs.get(i).getNom() + ")");
            if (i < getNombreJoueurs()-2)
                System.out.print(", ");
        }
        System.out.println(" et " + joueurs.get(joueurs.size()-1).getCarteChoisi().getNumero() + " (" + joueurs.get(joueurs.size()-1).getNom() + ") ont été posées.");
        afficherSeries();
    }

    /**
     * @brief Affiche le message des têtes de boeuf récupérées par les joueurs
     * @param compteurTDB : int[], le têtes de boeuf ramassées par les joueurs
     */
    private void afficherTDBRecuperees(int[] compteurTDB) {
        boolean personneRamasseTDB = true;
        for (int i = 0; i < compteurTDB.length; ++i) {
            if (compteurTDB[i] > 0) {
                if (compteurTDB[i] == 1)
                    System.out.println(getJoueur(i).getNom() + " a ramassé " + compteurTDB[i] + " tête de boeufs");
                else
                    System.out.println(getJoueur(i).getNom() + " a ramassé " + compteurTDB[i] + " têtes de boeufs");
                personneRamasseTDB = false;
            }
        }
        if (personneRamasseTDB) {
            System.out.println("Aucun joueur ne ramasse de tête de boeufs.");
        }
    }

    /**
     * @brief Affiche le message avant que le premier joueur doit choisir une série
     */
    private void afficherMessageChoisirSerie() {
        // Affichage du message
        System.out.print("Les cartes ");
        for (int j = 0; j < getNombreJoueurs()-1; ++j) {
            System.out.print(joueurs.get(j).getCarteChoisi().getNumero() + " (" + joueurs.get(j).getNom() + ")");
            if (j < getNombreJoueurs()-2)
                System.out.print(", ");
        }
        System.out.println(" et " + joueurs.get(joueurs.size()-1).getCarteChoisi().getNumero() + " (" + joueurs.get(joueurs.size()-1).getNom() + ") vont être posées.");
        System.out.println("Pour poser la carte " + getJoueur(0).getCarteChoisi().getNumero() + ", " + getJoueur(0).getNom() + " doit choisir la série qu'il va ramasser.");
        afficherSeries();
    }

    /**
     * @brief Affiche le score final dans l'ordre croissant des têtes de boeuf des joueurs
     */
    private void afficherScore() {
        System.out.println("** Score final");
        // Trier joueurs selon les têtes de boeuf décroissant
        Collections.sort(joueurs, (j1, j2) -> j1.getTeteBoeuf() - j2.getTeteBoeuf());
        for (Joueur j: joueurs) {
            if (j.getTeteBoeuf() > 1)
                System.out.println(j.getNom() + " a ramassé " + j.getTeteBoeuf() + " tête de boeufs");
            else
                System.out.println(j.getNom() + " a ramassé " + j.getTeteBoeuf() + " têtes de boeufs");
        }
    }


    /* ========== METHODS JOUEURS ========== */
    /**
     * @brief Détermine la série où le joueur doit poser sa carte choisi
     * @param idxJoueur : int, l'indice du joueur
     * @return le numero de la serie (-1 si aucune serie est possible)
     */
    public int determineSerie(int idxJoueur) {
        int numSerieDetermine = -1;
        int ecartMin = 104;
        int ecart;
        for (int numSerie = 0; numSerie < series.length; ++numSerie) {
            ecart = getJoueur(idxJoueur).getCarteChoisi().getNumero() - series[numSerie].getDerniereCarte().getNumero();
            if (0 < ecart && ecart < ecartMin) {
                ecartMin = ecart;
                numSerieDetermine = numSerie;
            }
        }
        return numSerieDetermine;
    }

    /**
     * @brief Fais jouer un joueur pour choisir sa carte et affiche les messages
     * @param j : Joueur, le joueur qui doit jouer son tour
     */
    public void joueurChoisiCarte(Joueur j) {
        System.out.println("A " + j.getNom() + " de jouer.");
        Console.pause();
        afficherSeries();
        j.afficherCartes();
        j.choisirCarte();
        Console.clearScreen();
    }

    /**
     * @brief Demande de choisir une série à un joueur (entre 1 et 4)
     * @return int, renvoie la numéro de la série (entre 0 et 3)
     */
    public int joueurChoisiSerie() {
        Scanner sc = new Scanner(System.in);
        boolean numSerieEstChoisi = false;
        int numSerieChoisi = -1;

        System.out.print("Saisissez votre choix : ");
        do {
            if (sc.hasNextInt()) {
                numSerieChoisi = sc.nextInt();
                if (1 <= numSerieChoisi && numSerieChoisi <= 4) {
                    numSerieEstChoisi = true;
                }
                else {
                    System.out.print("Ce n'est pas une série valide, saisissez votre choix : ");
                }
            }
            else {
                System.out.print("Ce n'est pas une série valide, saisissez votre choix : ");
                sc.nextLine();
            }
        } while (!numSerieEstChoisi);

        return --numSerieChoisi;
    }

    /**
     * @brief Pose la carte du joueur dans la série
     * @param idxJoueur int, indice du joueur
     * @param numSerie int, numéro de la série
     */
    public void joueurPoseCarte(int idxJoueur, int numSerie) {
        series[numSerie].ajouterCarte(getJoueur(idxJoueur).getCarteChoisi());
        getJoueur(idxJoueur).enleverCarteChoisi();
    }

    /**
     * @brief Un joueur récupère lees têtes de boeuf d'une série, elle se vide et il pose sa carte
     * @param idxJoueur int, indice du joueur
     * @param numSerie int, numéro de la série
     * @param compteurTDB int[], compteur des têtes de boeuf du tour
     */
    public void joueurRecupereSerie(int idxJoueur, int numSerie, int[] compteurTDB) {
        // On ajoute les têtes de boeuf
        for (Carte carte: series[numSerie].getSerie()) {
            compteurTDB[idxJoueur] += carte.getTeteDeBoeuf();
        }
        getJoueur(idxJoueur).ajouterTeteBoeuf(compteurTDB[idxJoueur]);
        // On vide la série et pose la carte du joueur
        series[numSerie].vider();
        joueurPoseCarte(idxJoueur, numSerie);
    }

    /**
     * @brief Pose la carte du joueur dans la série déterminer et si elle est pleine, il la récupère
     * @param idxJoueur : int, l'indice du joueur
     * @param compteurTDB : int, compteur des têtes de boeuf du tour
     */
    public void joueurJoueCarte(int idxJoueur, int[] compteurTDB) {
        int numSerieDetermine = determineSerie(idxJoueur);
        // Si la serie est pleine
        if (series[numSerieDetermine].estPlein()) {
            joueurRecupereSerie(idxJoueur, numSerieDetermine, compteurTDB);
        }
        // Si la serie n'est pas pleine
        else {
            joueurPoseCarte(idxJoueur, numSerieDetermine);
        }
    }


    /* ========== JOUER ========== */
    /**
     * @brief Pour jouer au jeu du 6 qui prend !
     */
    public void jouer() {
        // Message de bienvenue
        afficherMessageBienvenue();

        // Jouer tant que le jeu est en cours
        do {
            // Compteur des TDB récupéré par les joueurs lors de ce tour
            int[] compteurTDB = new int[getNombreJoueurs()];
            for (int i = 0; i < compteurTDB.length; ++i) compteurTDB[i] = 0;

            // Chaque joueur choisi sa carte
        	for (int i = 0; i < getNombreJoueurs(); ++i) {
                joueurChoisiCarte(getJoueur(i));
        	}

        	// On trie l'ordre des joueurs
        	triJoueurs();

        	// On place les cartes dans les séries
            // Le premier joueur est le seul joueur qui peut avoir choisi une carte trop petite pour la poser quelque part
            // Si c'est le cas, il choisit la série qu'il va récupérer
            if (determineSerie(0) == -1) {
                afficherMessageChoisirSerie();
                joueurRecupereSerie(0, joueurChoisiSerie(), compteurTDB);
            }
            // Sinon on joue sa carte normalement
            else {
                joueurJoueCarte(0, compteurTDB);
            }

            // On joue les cartes des autres joueurs
            for (int j = 1; j < getNombreJoueurs(); ++j) {
                joueurJoueCarte(j, compteurTDB);
            }

            // Affichage du message des cartes posées
            afficherCartesPosees();

            // Affichage des TDB ramasse
            afficherTDBRecuperees(compteurTDB);

            // On vérifie si le jeu est terminer
            verifJeuEnCours();

        } while (enCours);

        // FIN DU JEU : affichage du score
        afficherScore();
    }
}
