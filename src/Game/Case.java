package Game;

public class Case {
    private Integer valeur;

    public Case(Integer valeur) {
        this.valeur = valeur;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return this.valeur.toString();
    }
}
