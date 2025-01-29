package Game;

import java.util.ArrayList;

public class Grille {
    private int taille;
    private ArrayList<Bloc> blocs = new ArrayList<>();

    public Grille(int taille) {
        this.taille = taille;

        // ajouter une gestion de la difficulté en mettant un certaines nombres de cases random en fonction du nombre de difficulté

        for(int i = 0; i < taille; i++) {
            ArrayList<Case> cases = new ArrayList<>();
            for(int j = 0; j < taille; j++) {
                cases.add(new Case(1));
            }
            blocs.add(new Bloc(taille, cases));
        }
    }

    public void getGrille() {
        throw new RuntimeException("implémenter la fonction");
    }

    public boolean validerGrille() {
        // TODO appelle les fonctions vérifier lignes/colonnes et vérifier bloc
        throw new RuntimeException("implémenter la fonction");
    }
}