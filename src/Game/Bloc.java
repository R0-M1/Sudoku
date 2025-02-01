package Game;

import java.util.ArrayList;
import java.util.HashSet;

public class Bloc {
    protected int taille;
    private ArrayList<Case> cases;

    public Bloc(int taille, ArrayList<Case> cases) {
        this.taille = taille;
        this.cases = cases;
    }

    public boolean validerBloc() {
        HashSet<Integer> valeurs = new HashSet<>();

        for (Case c : cases) {
            int valeur = c.getValeur();
            if (valeurs.contains(valeur)) {
                return false;
            } else if (valeur != 0) {
                valeurs.add(valeur);
            }
        }

        return true;
    }

    public ArrayList<Case> getCases() {
        return cases;
    }
}