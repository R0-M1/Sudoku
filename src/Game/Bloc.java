package Game;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Représente un bloc dans un jeu de type Sudoku ou similaire.
 * Un bloc contient plusieurs cases et a une taille définie.
 */
public class Bloc {
    private int taille;
    private ArrayList<Case> cases;

    /**
     * Constructeur de la classe Bloc.
     *
     * @param taille La taille du bloc.
     * @param cases  La liste des cases contenues dans le bloc.
     */
    public Bloc(int taille, ArrayList<Case> cases) {
        this.taille = taille;
        this.cases = cases;
    }
    /**
     * Vérifie si le bloc est valide, c'est-à-dire qu'aucune valeur ne se répète.
     * Les valeurs nulles (0) ne sont pas prises en compte.
     *
     * @return {@code true} si le bloc est valide, {@code false} sinon.
     */
    public boolean validerBloc() {
        HashSet<Integer> valeurs = new HashSet<>();

        for (Case c : cases) {
            int valeur = c.getValeur();
            if (valeurs.contains(valeur)) {
                return false;
            } else if (valeur != 0) {
                valeurs.add(valeur);
            }
        }

        return true;
    }
    /**
     * Retourne la liste des cases contenues dans le bloc.
     *
     * @return Une {@code ArrayList} contenant les cases du bloc.
     */
    public ArrayList<Case> getCases() {
        return cases;
    }
}