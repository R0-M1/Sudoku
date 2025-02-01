package Game;

import java.util.*;

public class Solveur {
    private Grille grille;
    private int taille;
    private List<String> log;
    private List<MethodeResolution> regles;

    public Solveur(Grille grille) {
        this.grille = grille;
        this.taille = grille.getTaille();
    }

    public boolean solve(boolean backtracking, List<MethodeResolution> regles) {
        // TODO commencer a enregistrer dans log
        this.regles = regles;
        solveRegles();
        if (backtracking) {
            solveBackTracking();
        }
        return grille.isComplete();
    }

    public boolean solveBackTracking() {
        for (int row = 0; row < taille; row++) {
            for (int col = 0; col < taille; col++) {
                if (grille.getGrille()[row][col].getValeur() == 0) { // Trouver une case vide
                    List<Integer> valeursPossibles = calculerValeursPossibles(row, col);
                    Collections.shuffle(valeursPossibles);
                    for (Integer valeur : valeursPossibles) {
                        grille.setCase(row, col, valeur); // Essaye un chiffre

                        if (grille.isValid() && solveBackTracking()) {
                            solveRegles();
                            return true; // Si la grille est valide
                        }

                        grille.setCase(row, col, 0); // Annuler et revenir en arrière (BackTrack)
                    }
                    return false; // Aucun chiffre valide, retour arrière
                }
            }
        }
        return true; // Sudoku résolu
    }

    private void solveRegles() {
        // NOTE: peut etre bouclé dessus
        for (MethodeResolution m : regles) {
            switch (m) {
                case ELIMINATION_DIRECTE:
                    solveEliminationDirecte();
                    break;
                case UNICITE:
                    solveUnicite();
                    break;
                case PAIRES:
                    solvePaires();
                    break;
            }
        }
    }

    // Règle 1 : Elimination directe
    public void solveEliminationDirecte() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                Case currentCase = grille.getGrille()[i][j];
                if (currentCase.getValeur() == 0) {
                    List<Integer> possibles = calculerValeursPossibles(i, j);
                    if (possibles.size() == 1) {
                        currentCase.setValeur(possibles.iterator().next());
                    }
                }
            }
        }
    }

    // Règle 2 : Unicité
    public void solveUnicite() {
        int taille = grille.getTaille();

        for (int num = 1; num <= taille; num++) {
            for (int i = 0; i < taille; i++) {
                int possibleColumn = -1;
                int count = 0;

                for (int j = 0; j < taille; j++) {
                    if (grille.getGrille()[i][j].getValeur() == 0 && calculerValeursPossibles(i, j).contains(num)) {
                        count++;
                        possibleColumn = j;
                    }
                }
                if (count == 1) {
                    grille.getGrille()[i][possibleColumn].setValeur(num);
                }
            }
        }
    }

    // Règle 3 : Paires
    public void solvePaires() {
        int taille = grille.getTaille();

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                Case currentCase = grille.getGrille()[i][j];
                if (currentCase.getValeur() == 0) {
                    List<Integer> possibles = calculerValeursPossibles(i, j);

                    if (possibles.size() == 2) {
                        for (int k = 0; k < taille; k++) {
                            if (k != j && grille.getGrille()[i][k].getValeur() == 0) {
                                List<Integer> autresPossibles = calculerValeursPossibles(i, k);
                                if (possibles.equals(autresPossibles)) {

                                    for (int m = 0; m < taille; m++) {
                                        if (m != j && m != k) {
                                            grille.getGrille()[i][m].getValeur();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Détermine les valeurs possibles pour une case en appliquant des contraintes
    private List<Integer> calculerValeursPossibles(int row, int col) {
        List<Integer> possibles = new ArrayList<>();

        // Initialise avec toutes les valeurs possibles
        for (int num = 1; num <= taille; num++) {
            possibles.add(num);
        }

        // Supprime les valeurs déjà présentes dans la ligne et la colonne
        for (int i = 0; i < taille; i++) {
            possibles.remove(grille.getGrille()[row][i].getValeur()); // Vérifie ligne
            possibles.remove(grille.getGrille()[i][col].getValeur()); // Vérifie colonne
        }

        // Supprime les valeurs déjà présentes dans le bloc
        int blocId = grille.getGrille()[row][col].getBlocId();
        for (Case c : grille.getBlocs().get(blocId).getCases()) {
            possibles.remove(c.getValeur()); // Vérifie bloc
        }

        return possibles;
    }
}
