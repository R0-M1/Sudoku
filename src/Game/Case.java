package Game;

public class Case {
    private Integer valeur;
    private int blocId;

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

    @Override
    public String toString() {
        return this.valeur.toString();
    }
}
