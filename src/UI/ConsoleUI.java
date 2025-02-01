package UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Game.Case;
import Game.Sudoku;

// TODO Interface textuelle dans la console
public class ConsoleUI {
    public static final Map<Integer, String> colorMatch = createColorMap(); // pour le multidoku avec blocs de couleur
    Sudoku sudoku;
    int taille;

    private static Map<Integer, String> createColorMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "\u001B[31m"); // Red
        map.put(1, "\u001B[32m"); // Green
        map.put(2, "\u001B[34m"); // Blue
        map.put(3, "\u001B[35m"); // Purple
        map.put(4, "\u001B[36m"); // Cyan
        map.put(5, "\u001B[33m"); // Yellow
        map.put(6, "\u001B[91m"); // Bright Red
        map.put(7, "\u001B[92m"); // Bright Green
        map.put(8, "\u001B[94m"); // Bright Blue
        return map;
    }

    public ConsoleUI() {
        this.taille = demanderTaille();
        this.sudoku = new Sudoku(taille); // A changer plus tard, on instanciera un Sudoku / Multidoku et non une grille
    }

    public void start() {
        while (!sudoku.isValid()) {
            sudoku.initialiser();
            afficherGrille();
        }
        System.out.println("valid");
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
        int blockHeight = 1, blockWidth = taille; // Defaults

        // Find the best (blockHeight × blockWidth)
        for (int h = 1; h <= taille; h++) {
            if (taille % h == 0) {
                int w = taille / h;
                if (w >= h) { // Ensure rectangular blocks are valid
                    blockHeight = h;
                    blockWidth = w;
                }
            }
        }

        // Print the grid with separators
        for (int i = 0; i < taille; i++) {
            // Print horizontal separator between block rows
            if (i > 0 && i % blockHeight == 0) {
                System.out.println("-".repeat(taille * 2 + (taille / blockWidth) - 1));
            }

            for (int j = 0; j < taille; j++) {
                // Print vertical separator between block columns
                if (j > 0 && j % blockWidth == 0) {
                    System.out.print("| ");
                }

                // Get cell value and apply color
                Case c = sudoku.getGrille()[i][j];
                Integer valeur = c.getValeur();
                System.out.print(valeur + " ");

                //String color = colorMatch.getOrDefault(c.getBlocId(), "\u001B[37m");
                //System.out.print(color + valeur + " \u001B[0m");
            }
            System.out.println(); // New line after row
        }
    }

}
