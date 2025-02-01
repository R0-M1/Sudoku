package Game;

import java.util.List;

public class Sudoku {
    private Grille grille;
    private Solveur solveur;

    public Sudoku(int taille) {
        this.grille = new Grille(taille);
        this.solveur = new Solveur(grille);
    }

    public boolean solve(List<MethodeResolution> methodes) {
        List<MethodeResolution> regles = new java.util.ArrayList<>(List.copyOf(methodes));
        return solveur.solve(regles.remove(MethodeResolution.BACKTRACKING), regles);
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

    public int getTaille() {
        return grille.getTaille();
    }

    public List<String> getLog() {
        return solveur.getLog();
    }
}
