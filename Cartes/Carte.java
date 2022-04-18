package Cartes;

public class Carte {
    private final int numero;
    private final int teteDeBoeuf;
    private static int compteurCartes = 1;

    /*========== CONSTRUCTORS ==========*/
    /**
     * @brief Constructeur qui initialise le numero et le nombre de tete de boeuf automatiquement
     */
    public Carte() {
        // numero auto et unique
        this(compteurCartes);
        ++compteurCartes;
    }

    /**
     * @brief Constructeur qui initialise le numero choisi et le nombre de tete de boeuf
     * @param numero : Int, numero de la carte
     */
    public Carte(int numero) {
        assert (0 <= numero && numero <= 104);
    	this.numero = numero;
        // calcule du nombre de tete de boeuf
        int x = 0;
        if (numero % 11 == 0) x += 5;
        if (numero % 10 == 5) x += 2;
        else if (numero % 10 == 0) x += 3;
        if (x == 0) x = 1;
        teteDeBoeuf = x;
    }

    /*========== GETTERS ==========*/
    /**
     * @brief Donne le numero de la carte
     * @return Int, Le numero de la carte
     */
    public int getNumero() { return numero; }

    /**
     * @brief Donne le nombre de tete de boeuf de la carte
     * @return Int, Le nombre de tete de boeuf de le carte
     */
    public int getTeteDeBoeuf() { return teteDeBoeuf; }

    /* ========== METHODS ==========*/
    /**
     * @brief Permet de copier une carte
     * @return Cartes.Carte, La copie de la carte
     */
    public Carte clone() { return new Carte(numero); }
    
    /**
     * @brief To string, "$numero" si = 1, sinon "$numero ($teteDeBoeuf)"
     * @return String, La chaine de caractere qui represente la carte
     */
    public String toString() {
        if (teteDeBoeuf == 1) return String.valueOf(numero);
        return numero + " (" + teteDeBoeuf + ")";
    }
}
