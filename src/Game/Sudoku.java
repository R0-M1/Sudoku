package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sudoku {
    protected Grille grille;
    protected Solveur solveur;
    /**
     * Initialise un Sudoku avec une grille vide de la taille spécifiée.
     * @param taille La taille de la grille (ex: 9 pour un Sudoku classique).
     */
    public Sudoku(int taille) {
        this.grille = new Grille(taille);
        this.solveur = new Solveur(grille);
    }
    /**
     * Résout la grille en utilisant les méthodes de résolution spécifiées.
     * @param methodes Liste des méthodes de résolution à appliquer.
     * @return true si la grille est résolue, false sinon.
     */
    public boolean solve(List<MethodeResolution> methodes) {
        List<MethodeResolution> regles = new java.util.ArrayList<>(List.copyOf(methodes));
        return solveur.solve(regles.remove(MethodeResolution.BACKTRACKING), regles);
    }
    /**
     * Retourne la grille actuelle sous forme de tableau 2D.
     * @return Tableau de cases représentant la grille.
     */
    public Game.Case[][] getGrille() {
        return grille.getGrille();
    }
    /**
     * Génère un Sudoku en fonction du niveau de difficulté spécifié.
     * @param difficulte Niveau de difficulté (FACILE, MOYEN, DIFFICILE).
     */
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
    /**
     * Supprime un certain nombre de cases de la grille tout en maintenant sa validité.
     * @param nb Nombre de cases à supprimer.
     */
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
    /**
     * Retourne la taille de la grille.
     * @return Taille de la grille.
     */
    public int getTaille() {
        return grille.getTaille();
    }
    /**
     * Retourne les logs de résolution du Sudoku.
     * @return Liste des logs de résolution.
     */
    public List<String> getLog() {
        return solveur.getLog();
    }
}
