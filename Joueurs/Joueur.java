package Joueurs;

import Cartes.Carte;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private final String nom;
    private int nbTeteBoeuf = 0;
    private Carte carteChoisi;
    private ArrayList<Carte> cartes;

    /**
     * @brief Constructeur qui initialise le nom du joueur et ses attributs
     * @param nom : String, Le nom du joeur
     */
    public Joueur(String nom) {
        this.nom = nom;
        carteChoisi = null;
        cartes = new ArrayList<>();
    }

    /* ========== GETTERS ========== */
    /**
     * @brief Donne le nom du joueur
     * @return String, Le nom du joueur
     */
    public String getNom() { return nom; }
    
    /**
     * @brief Donne le nombre de tête de boeuf du joueur
     * @return int, Le nombre de tête de boeuf
     */
    public int getTeteBoeuf() { return nbTeteBoeuf; }

	/**
	 * @brief Donne la carte choisi par le joueur
	 * @return Carte, la carte choisi par le joueur
	 */
	public Carte getCarteChoisi() { return carteChoisi; }

    /**
     * @brief Donne les cartes du joeur
     * @return ArrayList<Carte>, Les cartes du joueur
     */
    public ArrayList<Carte> getCartes() { return cartes; }

	/**
	 * @brief Definie la carte choisi par le joueur
	 * @param carte Carte, la carte
	 */
	public void setCarteChoisi(Carte carte) { carteChoisi = carte; }

    /* ========== METHODS ==========*/
    /**
     * @brief Ajoute un nombre de tête de boeuf au joueur
	 * @param nbAdd : int, le nombre de tête de boeuf à ajouter
     */
    public void ajouterTeteBoeuf(int nbAdd) {
    	nbTeteBoeuf += nbAdd;
    }
    
    /**
     * @brief Regarde si les cartes du joueur sont vides
     * @return boolean, Si les cartes du joueur sont vides
     */
    public boolean estVide() {
    	return !(cartes.size() > 0);
    }

    /**
     * @brief Affiche les cartes du joueur
     */
    public void afficherCartes() {
        System.out.print("- Vos cartes : ");
        for (int i = 0; i < cartes.size()-1; ++i) {
            System.out.print(cartes.get(i) + ", ");
        }
        System.out.println(cartes.get(cartes.size()-1));
    }
    
    /**
     * @brief Permet au joueur de choisir une carte qu'il poss�de selon le num�ro
	 * @return Carte, La carte choisi par le joueur
     */
    public void choisirCarte() {
		assert (!estVide());
		Scanner sc = new Scanner(System.in);
		Carte carte;
		boolean carteEstChoisi = false;
		System.out.print("Saisissez votre choix : ");
		do {
			// Le joueur rentre un numero
			if (sc.hasNextInt()) {
				int n = sc.nextInt();
				// On vérifie si le numero correspond a une carte que le joueur possede
				for (int i = 0; i < cartes.size(); i++) {
					// C'est bon
					if (cartes.get(i).getNumero() == n) {
						carte = cartes.get(i);
						cartes.remove(i);
						carteChoisi = carte;
						carteEstChoisi = true;
						break;
					}
					// Le numero de correspond a aucune carte
					if (i == cartes.size() - 1)
						System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");
				}
			}
			// Le joueur ne rentre pas un nombre
			else {
				System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");
				sc.nextLine();
			}
		} while (!carteEstChoisi);
	}

	/**
	 * @brief Enleve une carte au joueur
	 * @param carte : Carte, la carte a enlever
	 */
	public void enleverCarte(Carte carte) {
		// On veut enlever une carte selon son numero de carte, or le numero d'une carte est de type int
		// Pour pas que ce soit considérer comme un indice mais bien un élément à enlever
		// on passe par l'objet Integer
		cartes.remove(Integer.valueOf(carte.getNumero()));
	}

	/**
	 * @brief Enleve la carte choisi par le joueur
	 */
	public void enleverCarteChoisi() {
		enleverCarte(carteChoisi);
	}

	/**
	 * @brief Clone le joueur
	 * @return Joueurs.Joueur, Un clone du joueur
	 */
	public Joueur clone() {
		// on recopie le nom et les tetes de boeuf du joueur
		Joueur j = new Joueur(nom);
		j.nbTeteBoeuf = nbTeteBoeuf;
		// on recopie le deck
		for (int i = 0; i < cartes.size(); ++i) j.cartes.add(cartes.get(i).clone());
		return j;
	}

    /**
     * @brief to String, nom
     * @return String, Le nom du joueur
     */
    public String toString() { return nom; }

}
