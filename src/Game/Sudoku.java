package Game;

import Game.Grille;

public class Sudoku {
    // Instancie une grille et un solveur
    private Game.Grille grille;

    public Sudoku(int taille) {
        this.grille = new Grille(taille);
    }

    public Game.Case[][] getGrille() {
        return grille.getGrille();
    }

    public boolean isValid() {
        return grille.isValid();
    }

    public void initialiser() {
        grille.genererGrille();
    }
}
