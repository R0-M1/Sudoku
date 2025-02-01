package UI;

import Game.Case;
import Game.Grille;

import java.util.Scanner;

// TODO Interface textuelle dans la console
public class ConsoleUI {
    Grille grille;
    int taille;

    public ConsoleUI() {
        this.taille = demanderTaille();
        this.grille = new Grille(taille); // A changer plus tard, on instanciera un Sudoku / Multidoku et non une grille
    }

    public void start() {
        grille.genererGrille();
        afficherGrille();

    }

    private void menu() {
        // TODO affiche le menu des pages et permet de faire un choix
    }

    private int demanderTaille() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez une taille de grille :");

        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Ce n'est pas un entier valide. Veuillez réessayer.");
                scanner.nextLine();
            }
        }
    }

    private void afficherGrille() {
        int tailleBloc = (int) Math.sqrt(taille);

        for (int i = 0; i < taille; i++) {
            if (i % tailleBloc == 0 && i != 0) {
                System.out.println("-".repeat(taille * 2 + tailleBloc - 1));
            }

            for (int j = 0; j < taille; j++) {
                if (j % tailleBloc == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(grille.getGrille()[i][j] + " ");
            }
            System.out.println();
        }
    }
}
