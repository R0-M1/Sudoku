import javax.swing.*;

public class Colonne extends Bloc {
    private int colonneId;
    public Colonne(int taille, int colonneId) {
        super(taille);
        this.colonneId = colonneId;
    }
}
