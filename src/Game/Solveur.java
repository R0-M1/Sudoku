package Game;

import java.util.HashSet;
import java.util.List;

public class Solveur {
    private Grille grille;

    public Solveur(Grille grille) {
        this.grille = grille;
    }

    // Méthode de résolution en utilisant des règles de déduction simple
    public boolean solve() {
        boolean progression;
        do {
            progression = false;
            for (int i = 0; i < grille.getTaille(); i++) {
                for (int j = 0; j < grille.getTaille(); j++) {
                    if (grille.getGrille()[i][j].getValeur() == 0) {
                        List<Integer> candidats = trouverCandidats(i, j);
                        if (candidats.size() == 1) {
                            grille.setCase(i, j, candidats.getFirst());
                            progression = true;
                        }
                    }
                }
            }
        } while (progression);

        return grille.isValid();
    }

    // Détermine les valeurs possibles pour une case en appliquant des contraintes
    private List<Integer> trouverCandidats(int row, int col) {
        HashSet<Integer> possibles = new HashSet<>();
        // Initialise avec toutes les valeurs possibles
        for (int num = 1; num <= grille.getTaille(); num++) {
            possibles.add(num);
        }

        // Supprime les valeurs déjà présentes dans la ligne
        for (int j = 0; j < grille.getTaille(); j++) {
            possibles.remove(grille.getGrille()[row][j].getValeur());
        }

        // Supprime les valeurs déjà présentes dans la colonne
        for (int i = 0; i < grille.getTaille(); i++) {
            possibles.remove(grille.getGrille()[i][col].getValeur());
        }

        // Supprime les valeurs déjà présentes dans le bloc
        int blocId = grille.getGrille()[row][col].getBlocId();
        for (Case c : grille.getBlocs().get(blocId).getCases()) {
            possibles.remove(c.getValeur());
        }

        return List.copyOf(possibles);
    }
}
