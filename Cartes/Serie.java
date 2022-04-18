package Cartes;

import Cartes.Carte;

import java.util.ArrayList;

public class Serie {
    private ArrayList<Carte> serie;
    private static final int NB_CARTES_MAX = 5;

    /**
     * @brief Constructeur
     */
    public Serie() {
        serie = new ArrayList<>();
    }

    /*========== GETTERS ==========*/
    /**
     * @brief Donne la liste de carte
     * @return ArrayList<Carte>, La liste de carte
     */
    public ArrayList<Carte> getSerie() { return serie; }

    /**
     * @brief Donne la dernière carte d'une serie
     * @return Carte, La derniere carte
     */
    public Carte getDerniereCarte() { return serie.get(serie.size()-1); }

    /**
     * @brief Donne le nombre de carte max que peut contenir la serie
     * @return int, le nombre de carte max
     */
    public int getNbCartesMax() { return NB_CARTES_MAX; }

    /**
     * @brief Dis si la série est pleine ou s'il reste de la place pour placer une carte
     * @return boolean, si la séries est pleine ou non
     */
    public boolean estPlein() { return serie.size() >= NB_CARTES_MAX; }

    /*========== METHODS ==========*/
    /**
     * @brief Ajoute une carte dans une serie
     * @param carte : Carte, La carte a ajouter
     */
    public void ajouterCarte(Carte carte) {
        if (!estPlein())
            serie.add(carte);
    }

    /**
     * @brief Vide la serie et la renvoie
     * @return ArrayList<Carte>, La serie
     */
    public ArrayList<Carte> vider() {
        ArrayList<Carte> cartes = new ArrayList<>(serie);
        serie.clear();
        return cartes;
    }

    /**
     * @brief to String, les cartes de la serie
     * @return La chaine de caractere qui represente les cartes de la serie
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < serie.size()-1; ++i) {
            s.append(serie.get(i).toString()).append(", ");
        }
        s.append(getDerniereCarte().toString());
        return s.toString();
    }

}
