package Game;

import java.util.Set;

public class Case {
    private Integer valeur;
    private int blocId;
    private Set<Integer> valeursPossibles;

    public Case(int blocId, Integer valeur) {
        this.blocId = blocId;
        this.valeur = valeur;
    }

    public Integer getValeur() {
        return valeur;
    }

    public int getBlocId() {
        return blocId;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    public Set<Integer> getValeursPossibles() {
        return valeursPossibles;
    }

    public void setValeursPossibles(Set<Integer> valeursPossibles) {
        this.valeursPossibles = valeursPossibles;
    }

    @Override
    public String toString() {
        return this.valeur.toString();
    }
}
