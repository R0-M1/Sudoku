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
        this.log = new ArrayList<>();
    }

    public boolean solve(boolean backtracking, List<MethodeResolution> regles) {
        this.log.clear();
        ajouterLog("Début de la résolution");
        updateAllValeursPossibles();

        this.regles = regles;
        solveRegles();
        if (backtracking) {
            solveBackTracking();
        }

        boolean isComplete = grille.isComplete();
        if (isComplete) {
            ajouterLog("La grille est résolue !");
        } else {
            ajouterLog("La grille n'a pas pu être résolue.");
        }

        return isComplete;
    }

    public boolean solveBackTracking() {
        for (int row = 0; row < taille; row++) {
            for (int col = 0; col < taille; col++) {
                if (grille.getGrille()[row][col].getValeur() == 0) { // Trouver une case vide
                    Set<Integer> valeursPossibles = grille.getGrille()[row][col].getValeursPossibles();
                    List<Integer> possiblesList = new ArrayList<>(valeursPossibles);
                    Collections.shuffle(possiblesList);
                    for (Integer valeur : possiblesList) {
                        grille.setCase(row, col, valeur); // Essaye un chiffre
                        ajouterLog("Essai de la valeur " + valeur + " en (" + row + ", " + col + ")");

                        updateAllValeursPossibles();

                        if (grille.isValid() && solveBackTracking()) {
                            solveRegles();
                            return true; // Si la grille est valide
                        }

                        grille.setCase(row, col, 0); // Annuler et revenir en arrière (BackTrack)
                        ajouterLog("Retour arrière en (" + row + ", " + col + ")");
                    }
                    return false; // Aucun chiffre valide, retour arrière
                }
            }
        }
        return true; // Sudoku résolu
    }

    private void solveRegles() {
        boolean modifie;
        do {
            modifie = false;  // On réinitialise la modification à chaque passage dans la boucle
            // Appliquer chaque règle et mettre à jour 'modifie' si une règle a changé quelque chose
            for (MethodeResolution m : regles) {
                switch (m) {
                    case ELIMINATION_DIRECTE:
                        modifie |= solveEliminationDirecte();  // Si la règle modifie la grille, on l'indique
                        break;
                    case PLACEMENT_FORCE:
                        modifie |= solvePlacementForce();
                        break;
                    case PAIRES:
                        modifie |= solvePaires();
                        break;
                }
            }
        } while (modifie);
    }

    // Règle 1 : Elimination directe
    public boolean solveEliminationDirecte() {
        boolean modification = false;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                Case currentCase = grille.getGrille()[i][j];
                if (currentCase.getValeur() == 0) {
                    Set<Integer> possibles = currentCase.getValeursPossibles();
                    if (possibles.size() == 1) {
                        currentCase.setValeur(possibles.iterator().next());
                        ajouterLog("1. Elimination directe : valeur " + currentCase.getValeur() + " en (" + i + ", " + j + ")");
                        modification = true;

                        updateAllValeursPossibles();
                    }
                }
            }
        }
        return modification;
    }

    // Règle 2 : Placement Forcé
    private boolean solvePlacementForce() {
        boolean modification = false;

        for (int num = 1; num <= taille; num++) {
            // Vérifier chaque ligne
            for (int i = 0; i < taille; i++) {
                int indexPossible = -1;
                int count = 0;
                for (int j = 0; j < taille; j++) {
                    if (grille.getGrille()[i][j].getValeur() == 0 && grille.getGrille()[i][j].getValeursPossibles().contains(num)) {
                        indexPossible = j;
                        count++;
                    }
                }
                if (count == 1) {
                    grille.getGrille()[i][indexPossible].setValeur(num);
                    ajouterLog("2. Placement Forcé : valeur " + num + " en (" + i + ", " + indexPossible + ")");
                    modification = true;

                    updateAllValeursPossibles();
                }
            }

            // Vérifier chaque colonne
            for (int j = 0; j < taille; j++) {
                int indexPossible = -1;
                int count = 0;
                for (int i = 0; i < taille; i++) {
                    if (grille.getGrille()[i][j].getValeur() == 0 && grille.getGrille()[i][j].getValeursPossibles().contains(num)) {
                        indexPossible = i;
                        count++;
                    }
                }
                if (count == 1) {
                    grille.getGrille()[indexPossible][j].setValeur(num);
                    ajouterLog("2. Placement Forcé : valeur " + num + " en (" + indexPossible + ", " + j + ")");
                    modification = true;

                    updateAllValeursPossibles();
                }
            }
        } // TODO peut etre rajouter pour les blocs
        return modification;
    }

    // Règle 3 : Paires
    private boolean solvePaires() {
        boolean modification = false;
        // Vérifier les lignes
        for (int i = 0; i < taille; i++) {
            modification |= solvePairesInLine(i);
        }

        // Vérifier les colonnes
        for (int j = 0; j < taille; j++) {
            modification |= solvePairesInColumn(j);
        }

        // Vérifier les blocs
        for (int b = 0; b < grille.getBlocs().size(); b++) {
            modification |= solvePairesInBloc(b);
        }

        return modification;
    }

    // Vérifier les paires nues dans une ligne
    private boolean solvePairesInLine(int row) {
        boolean modification = false;
        List<Case> emptyCases = new ArrayList<>();

        // Recueillir les cases vides dans la ligne
        for (int col = 0; col < taille; col++) {
            Case currentCase = grille.getGrille()[row][col];
            if (currentCase.getValeur() == 0) {
                emptyCases.add(currentCase);
            }
        }

        // Vérifier si on peut trouver une paire nue
        for (int i = 0; i < emptyCases.size(); i++) {
            for (int j = i + 1; j < emptyCases.size(); j++) {
                Case case1 = emptyCases.get(i);
                Case case2 = emptyCases.get(j);

                // Si les deux cases ont exactement les mêmes valeurs possibles
                Set<Integer> intersection = new HashSet<>(case1.getValeursPossibles());
                intersection.retainAll(case2.getValeursPossibles());
                if (intersection.size() == 2) {
                    // C'est une paire nue, on élimine ces deux valeurs des autres cases vides
                    modification |= eliminatePairValuesInLine(row, case1, case2, intersection);
                }
            }
        }
        return modification;
    }

    // Eliminer les valeurs de la paire des autres cases de la ligne
    private boolean eliminatePairValuesInLine(int row, Case case1, Case case2, Set<Integer> pairValues) {
        boolean modification = false;
        for (int col = 0; col < taille; col++) {
            Case currentCase = grille.getGrille()[row][col];
            if (currentCase != case1 && currentCase != case2 && currentCase.getValeur() == 0) {
                Set<Integer> possibles = currentCase.getValeursPossibles();
                if (possibles.removeAll(pairValues)) {
                    currentCase.setValeursPossibles(possibles);
                    ajouterLog("3. Paires : Elimination des valeurs " + pairValues + " en (" + row + ", " + col + ")");
                    modification = true;
                }
            }
        }
        return modification;
    }

    // Vérifier les paires nues dans une colonne
    private boolean solvePairesInColumn(int col) {
        boolean modification = false;
        List<Case> emptyCases = new ArrayList<>();

        // Recueillir les cases vides dans la colonne
        for (int row = 0; row < taille; row++) {
            Case currentCase = grille.getGrille()[row][col];
            if (currentCase.getValeur() == 0) {
                emptyCases.add(currentCase);
            }
        }

        // Vérifier si on peut trouver une paire nue
        for (int i = 0; i < emptyCases.size(); i++) {
            for (int j = i + 1; j < emptyCases.size(); j++) {
                Case case1 = emptyCases.get(i);
                Case case2 = emptyCases.get(j);

                // Si les deux cases ont exactement les mêmes valeurs possibles
                Set<Integer> intersection = new HashSet<>(case1.getValeursPossibles());
                intersection.retainAll(case2.getValeursPossibles());
                if (intersection.size() == 2) {
                    // C'est une paire nue, on élimine ces deux valeurs des autres cases vides
                    modification |= eliminatePairValuesInColumn(col, case1, case2, intersection);
                }
            }
        }
        return modification;
    }

    // Eliminer les valeurs de la paire des autres cases de la colonne
    private boolean eliminatePairValuesInColumn(int col, Case case1, Case case2, Set<Integer> pairValues) {
        boolean modification = false;
        for (int row = 0; row < taille; row++) {
            Case currentCase = grille.getGrille()[row][col];
            if (currentCase != case1 && currentCase != case2 && currentCase.getValeur() == 0) {
                Set<Integer> possibles = currentCase.getValeursPossibles();
                if (possibles.removeAll(pairValues)) {
                    currentCase.setValeursPossibles(possibles);
                    ajouterLog("3. Paires : Elimination des valeurs " + pairValues + " en (" + row + ", " + col + ")");
                    modification = true;
                }
            }
        }
        return modification;
    }

    // Vérifier les paires nues dans un bloc
    private boolean solvePairesInBloc(int blocId) {
        boolean modification = false;
        List<Case> emptyCases = new ArrayList<>();

        // Recueillir les cases vides dans le bloc
        Bloc bloc = grille.getBlocs().get(blocId);
        for (Case currentCase : bloc.getCases()) {
            if (currentCase.getValeur() == 0) {
                emptyCases.add(currentCase);
            }
        }

        // Vérifier si on peut trouver une paire nue
        for (int i = 0; i < emptyCases.size(); i++) {
            for (int j = i + 1; j < emptyCases.size(); j++) {
                Case case1 = emptyCases.get(i);
                Case case2 = emptyCases.get(j);

                // Si les deux cases ont exactement les mêmes valeurs possibles
                Set<Integer> intersection = new HashSet<>(case1.getValeursPossibles());
                intersection.retainAll(case2.getValeursPossibles());
                if (intersection.size() == 2) {
                    // C'est une paire nue, on élimine ces deux valeurs des autres cases du bloc
                    modification |= eliminatePairValuesInBloc(blocId, case1, case2, intersection);
                }
            }
        }
        return modification;
    }

    // Eliminer les valeurs de la paire des autres cases du bloc
    private boolean eliminatePairValuesInBloc(int blocId, Case case1, Case case2, Set<Integer> pairValues) {
        boolean modification = false;
        Bloc bloc = grille.getBlocs().get(blocId);
        for (Case currentCase : bloc.getCases()) {
            if (currentCase != case1 && currentCase != case2 && currentCase.getValeur() == 0) {
                Set<Integer> possibles = currentCase.getValeursPossibles();
                if (possibles.removeAll(pairValues)) {
                    currentCase.setValeursPossibles(possibles);
                    ajouterLog("3. Paires : Elimination des valeurs " + pairValues + " en bloc " + blocId);
                    modification = true;
                }
            }
        }
        return modification;
    }



    // Détermine les valeurs possibles pour une case en appliquant des contraintes
    private void updateValeursPossibles(int row, int col) {
        Case currentCase = grille.getGrille()[row][col];
        Set<Integer> valeursPossibles = new HashSet<>();

        for (int num = 1; num <= taille; num++) {
            valeursPossibles.add(num);
        }

        // Supprime les valeurs déjà présentes dans la ligne et la colonne
        for (int i = 0; i < taille; i++) {
            valeursPossibles.remove(grille.getGrille()[row][i].getValeur()); // Vérifie ligne
            valeursPossibles.remove(grille.getGrille()[i][col].getValeur()); // Vérifie colonne
        }

        // Supprime les valeurs déjà présentes dans le bloc
        int blocId = currentCase.getBlocId();
        for (Case c : grille.getBlocs().get(blocId).getCases()) {
            valeursPossibles.remove(c.getValeur());
        }

        currentCase.setValeursPossibles(valeursPossibles);
    }

    private void updateAllValeursPossibles() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille.getGrille()[i][j].getValeur() == 0) {
                    updateValeursPossibles(i, j);
                }
            }
        }
    }

    private void ajouterLog(String message) {
        log.add(message);
        System.out.println(message);  // TODO A enlever pour une version finale
    }

    // Accesseur pour récupérer le log
    public List<String> getLog() {
        return log;
    }
}
