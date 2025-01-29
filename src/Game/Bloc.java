package Game;

import java.util.ArrayList;

public class Bloc {
    protected int taille;
    private ArrayList<Case> cases;

    public Bloc(int taille, ArrayList<Case> cases) {
        this.taille = taille;
        this.cases = cases;
    }

    public boolean validerBloc() {
        // TODO vérifie que les valeurs des cases sont bien différentes
        throw new RuntimeException("implémenter la fonction");
    }
}
