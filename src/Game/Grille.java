package Game;

import java.util.ArrayList;
import java.util.Random;

public class Grille {
    private Case[][] grille;
    private final int taille;
    private ArrayList<Bloc> blocs = new ArrayList<>();

    public Grille(int taille) {
        if (taille <= 0) throw new IllegalArgumentException("Size must be positive");
        this.taille = taille;
        grille = new Case[taille][taille];

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = new Case(0);
            }
        }
    }

    public Case[][] getGrille() {
        return grille;
    }

    public int getTaille() {
        return taille;
    }

    public void genererGrille() {
        Random rand = new Random();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j].setValeur(rand.nextInt(taille+1));
            }
        }
    }

    public void setCase(int row, int col, int valeur) {
        grille[row][col].setValeur(valeur);
    }

    public boolean validerGrille() {
        // TODO appelle les fonctions vérifier lignes/colonnes et vérifier bloc
        throw new RuntimeException("implémenter la fonction");
    }
}