import javax.swing.*;

public class Ligne extends Bloc {
    private int ligneId;
    public Ligne(int taille, int ligneId) {
        super(taille);
        this.ligneId = ligneId;
    }
}
