package Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Grille {
    private Case[][] grille;
    private final int taille;
    private ArrayList<Bloc> blocs;

    public Grille(int taille) {
        if (taille <= 0) throw new IllegalArgumentException("La taille doit être positive");
        this.taille = taille;
        grille = new Case[taille][taille];
        blocs = new ArrayList<>(taille);

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                int blocId = calculateBlocId(i, j, taille);

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

    private int calculateBlocId(int i, int j, int taille) {
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


    public Case[][] getGrille() {
        return grille;
    }

    public ArrayList<Bloc> getBlocs() {
        return blocs;
    }

    public int getTaille() {
        return taille;
    }

    public void genererGrille() {
        Random rand = new Random();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j].setValeur(rand.nextInt(taille)+1);
            }
        }
    }

    public void setCase(int row, int col, int valeur) {
        grille[row][col].setValeur(valeur);
    }

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