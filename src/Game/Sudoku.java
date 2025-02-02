package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sudoku {
    protected Grille grille;
    protected Solveur solveur;

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

    public void generer(Difficulte difficulte) {
        solveur = new Solveur(grille);
        solveur.solve(true, new ArrayList<>());

        Random rand = new Random();
        int taille = getTaille();
        switch (difficulte) {
            case FACILE:
                supprimerCases(rand.nextInt((int) (0.3 * taille * taille), (int) (0.45 * taille * taille)));
                break;
            case MOYEN:
                supprimerCases(rand.nextInt((int) (0.46 * taille * taille), (int) (0.60 * taille * taille)));
                break;
            case DIFFICILE:
                supprimerCases(rand.nextInt((int) (0.61 * taille * taille), (int) (0.75 * taille * taille)));
                break;
        }
    }

    protected void supprimerCases(int nb) {
        Random rand = new Random();
        int supprimees = 0;

        while (supprimees < nb) {
            int row = rand.nextInt(getTaille());
            int col = rand.nextInt(getTaille());

            if (grille.getGrille()[row][col].getValeur() != 0) { // Vérifie que la case n'est pas déjà vide
                int valeurTemp = grille.getGrille()[row][col].getValeur(); // Sauvegarde la valeur
                grille.getGrille()[row][col].setValeur(0); // Supprime la valeur

                if (grille.isValid()) { // Vérifie que la grille reste valide
                    supprimees++; // Confirme la suppression
                } else {
                    grille.getGrille()[row][col].setValeur(valeurTemp); // Restaure si invalide
                }
            }
        }
    }

    public int getTaille() {
        return grille.getTaille();
    }

    public List<String> getLog() {
        return solveur.getLog();
    }
}
