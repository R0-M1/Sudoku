package Game;

public class Sudoku {
    // Instancie une grille et un solveur
    private Grille grille;
    private Solveur solveur;

    public Sudoku(int taille) {
        this.grille = new Grille(taille);
        this.solveur = new Solveur();
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
