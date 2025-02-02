package Game;

import java.util.*;

public class Grille {
    private Case[][] grille;
    private final int taille;
    private ArrayList<Bloc> blocs;

    /**
     * Constructeur de la grille.
     *
     * @param taille La taille de la grille (doit être positive).
     * @throws IllegalArgumentException si la taille est inférieure ou égale à 0.
     */
    public Grille(int taille) {
        if (taille <= 0) throw new IllegalArgumentException("La taille doit être positive");
        this.taille = taille;
        grille = new Case[taille][taille];
        blocs = new ArrayList<>(taille);

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                int blocId = calculateBlocId(i, j);
                grille[i][j] = new Case(blocId, 0);

                if (blocs.size() <= blocId) {
                    for (int k = blocs.size(); k <= blocId; k++) {
                        blocs.add(new Bloc(taille, new ArrayList<>()));
                    }
                }
                Bloc currentBloc = blocs.get(blocId);
                currentBloc.getCases().add(grille[i][j]);
            }
        }
    }
    /**
     * Calcule l'identifiant unique d'un bloc en fonction de sa position dans la grille.
     *
     * @param i L'index de la ligne.
     * @param j L'index de la colonne.
     * @return L'identifiant unique du bloc.
     */
    private int calculateBlocId(int i, int j) {
        int blockHeight = 1;
        int blockWidth = taille;

        for (int h = 1; h <= taille; h++) {
            if (taille % h == 0) {
                int w = taille / h;

                if (w >= h) {
                    blockHeight = h;
                    blockWidth = w;
                }
            }
        }

        // Calculate unique block ID based on row and column
        return (i / blockHeight) * (taille / blockWidth) + (j / blockWidth);
    }
    /**
     * Retourne la grille de jeu sous forme de tableau 2D de cases.
     *
     * @return La grille de jeu.
     */
    public Case[][] getGrille() {
        return grille;
    }
    /**
     * Retourne la liste des blocs de la grille.
     *
     * @return La liste des blocs.
     */
    public ArrayList<Bloc> getBlocs() {
        return blocs;
    }
    /**
     * Retourne la taille de la grille.
     *
     * @return La taille de la grille.
     */
    public int getTaille() {
        return taille;
    }
    /**
     * Modifie la valeur d'une case de la grille.
     *
     * @param row    L'index de la ligne.
     * @param col    L'index de la colonne.
     * @param valeur La nouvelle valeur de la case.
     */
    public void setCase(int row, int col, int valeur) {
        grille[row][col].setValeur(valeur);
    }
    /**
     * Vérifie si la grille est valide selon les règles du jeu.
     * Une grille est valide si :
     * - Aucune valeur ne se répète dans une même ligne.
     * - Aucune valeur ne se répète dans une même colonne.
     * - Aucune valeur ne se répète dans un même bloc.
     *
     * @return {@code true} si la grille est valide, sinon {@code false}.
     */
    public boolean isValid() {
        // Check rows for duplicates
        for (int i = 0; i < taille; i++) {
            HashSet<Integer> rowValues = new HashSet<>();
            for (int j = 0; j < taille; j++) {
                int value = grille[i][j].getValeur();
                if (value != 0 && !rowValues.add(value)) { // value != 0 to ignore empty cells
                    return false; // Duplicate found in the row
                }
            }
        }

        // Check columns for duplicates
        for (int j = 0; j < taille; j++) {
            HashSet<Integer> colValues = new HashSet<>();
            for (int i = 0; i < taille; i++) {
                int value = grille[i][j].getValeur();
                if (value != 0 && !colValues.add(value)) { // value != 0 to ignore empty cells
                    return false; // Duplicate found in the column
                }
            }
        }

        // Check blocks for duplicates (use the validerBloc method)
        for (Bloc bloc : blocs) {
            if (!bloc.validerBloc()) { // If any block is invalid
                return false;
            }
        }

        return true; // No duplicates found, grid is valid
    }
    /**
     * Vérifie si la grille est complètement remplie et valide.
     *
     * @return {@code true} si la grille est complète et valide, sinon {@code false}.
     */
    public boolean isComplete() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if(grille[i][j].getValeur() == 0) {
                    return false;
                }
            }
        }
        return isValid();
    }
}