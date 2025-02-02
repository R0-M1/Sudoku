package Game;

import java.util.*;

// Multidoku pouvant gérer des blocs non carrés
public class Multidoku extends Sudoku {

    public Multidoku(int taille) {
        super(taille);
    }

    @Override
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
}
