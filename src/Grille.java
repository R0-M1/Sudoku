import java.util.ArrayList;

public class Grille {
    private int taille;
    private ArrayList<Bloc> blocs;

    public Grille(int taille) {
        this.taille = taille;
        for(int i : taille) {
            blocs.add(new Bloc(taille));
        }
    }
    public boolean validerGrille() {
        // TODO appelle les fonctions vérifier lignes/colonnes et vérifier bloc
        throw new RuntimeException("implémenter la fonction");
    }
}