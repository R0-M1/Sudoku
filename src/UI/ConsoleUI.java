package UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Game.Case;
import Game.Sudoku;

// TODO Interface textuelle dans la console
public class ConsoleUI {
    private static final Map<Integer, String> colorMatch = createColorMap(); // pour le multidoku avec blocs de couleur
    private Sudoku sudoku;
    private int taille;

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

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("░██████╗██╗░░░██╗██████╗░░█████╗░██╗░░██╗██╗░░░██╗");
        System.out.println("██╔════╝██║░░░██║██╔══██╗██╔══██╗██║░██╔╝██║░░░██║");
        System.out.println("╚█████╗░██║░░░██║██║░░██║██║░░██║█████═╝░██║░░░██║");
        System.out.println("░╚═══██╗██║░░░██║██║░░██║██║░░██║██╔═██╗░██║░░░██║");
        System.out.println("██████╔╝╚██████╔╝██████╔╝╚█████╔╝██║░╚██╗╚██████╔╝");
        System.out.println("╚═════╝░░╚═════╝░╚═════╝░░╚════╝░╚═╝░░╚═╝░╚═════╝░");


        while (true) {
            System.out.println("\033[1mChoisissez une option :\033[0m");
            System.out.println("1. Rentrer une grille");
            System.out.println("2. Choisir méthode de résolution");
            System.out.println("3. Afficher le sudoku");
            System.out.println("4. Afficher la suite d'opérations");
            System.out.println("5. Générer une ou plusieurs grilles à partir d'une grille complète et d’un niveau de difficulté");
            System.out.println("6. Quitter");

            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    demanderTaille();
                    System.out.println(taille);
                    demanderGrille();
                    break;
                case 2:
                    while (true) {
                        System.out.println("\033[1mChoisissez une méthode de résolution :\033[0m");
                        System.out.println("1. Par règle de déduction");
                        System.out.println("2. Par retour sur trace");
                        System.out.println("3. Retour au menu principal");
                        int choix2 = scanner.nextInt();
                        switch (choix2) {
                            case 1:
                                // Appeler regle deduction
                                break;
                            case 2:
                                // Appeler back tracking
                                break;
                            case 3:
                                System.out.println("Retour au menu principal...");
                                break;
                            default:
                                System.out.println("Choix invalide. Veuillez réessayer.");
                                continue;
                        }
                        break;
                    }
                    break;
                case 3:
                    afficherGrille();
                    break;
                case 4:
                    // Implement the show operations sequence here
                    break;
                case 5:
                    // Implement generating grids from a complete grid with difficulty level here
                    break;
                case 6:
                    System.out.println("Au revoir!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    private void demanderTaille() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez une taille de grille :");

        while (true) {
            try {
                this.taille = scanner.nextInt();
                sudoku = new Sudoku(taille);
                break;
            } catch (Exception e) {
                System.out.println("Ce n'est pas un entier valide. Veuillez réessayer.");
                scanner.nextLine();
            }
        }
    }

    private void demanderGrille() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer les valeurs de la grille (entrez un nombre entre 1 et " + taille + ", ou 0 pour une case vide) :");

        // Loop over each row and column to get the values
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                int value;
                while (true) {
                    System.out.print("Entrez la valeur pour la case [" + (i + 1) + "][" + (j + 1) + "]: ");
                    try {
                        value = scanner.nextInt();
                        // Check if the value is within the valid range
                        if (value >= 0 && value <= taille) {
                            break;  // Valid value, exit the loop
                        } else {
                            System.out.println("La valeur doit être entre 0 et " + taille + ". Veuillez réessayer.");
                        }
                    } catch (Exception e) {
                        System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
                        scanner.nextLine();  // Clear the buffer
                    }
                }
                // Set the value in the Sudoku grid (sudoku object is assumed to have a method for setting the cell value)
                sudoku.getGrille()[i][j].setValeur(value);
            }
        }
        System.out.println("Grille entrée avec succès.");
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
                if(valeur == 0){
                    System.out.print(". ");
                } else {
                    System.out.print(valeur + " ");
                }

                //String color = colorMatch.getOrDefault(c.getBlocId(), "\u001B[37m");
                //System.out.print(color + valeur + " \u001B[0m");
            }
            System.out.println();
        }
    }

}
