package Game;

import java.util.Set;

/**
 * Représente une case dans un jeu de type Sudoku ou similaire.
 * Chaque case possède une valeur, un identifiant de bloc et un ensemble de valeurs possibles.
 */
public class Case {
    private Integer valeur;
    private int blocId;
    private Set<Integer> valeursPossibles;

    /**
     * Constructeur de la classe Case.
     *
     * @param blocId Identifiant du bloc auquel appartient cette case.
     * @param valeur Valeur actuelle de la case (0 pour indiquer une case vide).
     */
    public Case(int blocId, Integer valeur) {
        this.blocId = blocId;
        this.valeur = valeur;
    }

    /**
     * Obtient la valeur actuelle de la case.
     *
     * @return La valeur de la case.
     */
    public Integer getValeur() {
        return valeur;
    }

    /**
     * Obtient l'identifiant du bloc auquel appartient cette case.
     *
     * @return L'identifiant du bloc.
     */
    public int getBlocId() {
        return blocId;
    }

    /**
     * Définit la valeur de la case.
     *
     * @param valeur La nouvelle valeur de la case.
     */
    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    /**
     * Obtient l'ensemble des valeurs possibles pour cette case.
     *
     * @return Un ensemble contenant les valeurs possibles.
     */
    public Set<Integer> getValeursPossibles() {
        return valeursPossibles;
    }

    /**
     * Définit l'ensemble des valeurs possibles pour cette case.
     *
     * @param valeursPossibles Un ensemble de valeurs représentant les possibilités.
     */
    public void setValeursPossibles(Set<Integer> valeursPossibles) {
        this.valeursPossibles = valeursPossibles;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la case.
     *
     * @return La valeur de la case sous forme de chaîne.
     */
    @Override
    public String toString() {
        return this.valeur.toString();
    }
}
