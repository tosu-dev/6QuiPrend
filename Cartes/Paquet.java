package Cartes;

import java.util.ArrayList;
import java.util.Collections;

public class Paquet {
    private ArrayList<Carte> cartes;
    private static final int MAX_CARTES = 104;

    /**
     * @brief Constructeur qui initialise automatiquement les 104 cartes du paquets
     */
    public Paquet() {
        cartes = new ArrayList<>();
        for (int i = 0; i < MAX_CARTES; ++i) cartes.add(new Carte());
    }

    /* ========== GETTERS ==========*/
    /**
     * @brief Donne le nombre de carte dans le paquet
     * @return int, Le nombre de carte dans le paquet
     */
    public int getNombreCartes() { return cartes.size(); }

    /**
     * @brief Donne la derniere carte du paquet
     * @return Carte, La derniere carte du paquet
     */
    public Carte getDerniereCarte() {
        assert !(estVide());
        return cartes.get(getNombreCartes()-1);
    }

    /**
     * @brief Donne si le paquet est vide ou non
     * @return boolean, Si le paquet est vide
     */
    public boolean estVide() { return !(cartes.size() > 0); }


    /* ========== METHODS ==========*/
    /**
     * @brief Melange le paquet de carte
     */
    public void melanger() {
        Collections.shuffle(cartes);
    }

    /**
     * @brief Enleve la derniere carte du paquet et la renvoie
     * @return Carte, La dernier carte du paquet
     */
    public Carte enlever() {
        Carte c = getDerniereCarte();
        cartes.remove(cartes.size()-1);
        return c;
    }
}
