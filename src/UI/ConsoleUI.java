package UI;

import java.util.*;

import Game.Case;
import Game.Sudoku;
import Game.MethodeResolution;

// TODO Interface textuelle dans la console
public class ConsoleUI {
    private static final Map<Integer, String> colorMatch = createColorMap(); // pour le multidoku avec blocs de couleur
    private Sudoku sudoku;

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
            System.out.println("6. Grilles de test");
            System.out.println("7. Quitter");

            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    demanderTaille();
                    demanderGrille();
                    break;
                case 2:
                    List<MethodeResolution> methodes = new ArrayList<>();
                    while (true) {
                        System.out.println("\033[1mChoisissez une méthode de résolution :\033[0m");
                        System.out.println("Méthodes sélectionnées : " + (methodes.isEmpty() ? "Aucune" : methodes));
                        System.out.println("1. Par règle de déduction : Elimination directe " + (methodes.contains(MethodeResolution.ELIMINATION_DIRECTE) ? "✓" : ""));
                        System.out.println("2. Par règle de déduction : Unicité " + (methodes.contains(MethodeResolution.PLACEMENT_FORCE) ? "✓" : ""));
                        System.out.println("3. Par règle de déduction : Paires " + (methodes.contains(MethodeResolution.PAIRES) ? "✓" : ""));
                        System.out.println("4. Par retour sur trace " + (methodes.contains(MethodeResolution.BACKTRACKING) ? "✓" : ""));
                        System.out.println("5. Résoudre");
                        System.out.println("6. Retour au menu principal");

                        int choix2 = scanner.nextInt();
                        switch (choix2) {
                            case 1:
                                if (methodes.contains(MethodeResolution.ELIMINATION_DIRECTE)) {
                                    methodes.remove(MethodeResolution.ELIMINATION_DIRECTE);
                                } else {
                                    methodes.add(MethodeResolution.ELIMINATION_DIRECTE);
                                }
                                continue;
                            case 2:
                                if (methodes.contains(MethodeResolution.PLACEMENT_FORCE)) {
                                    methodes.remove(MethodeResolution.PLACEMENT_FORCE);
                                } else {
                                    methodes.add(MethodeResolution.PLACEMENT_FORCE);
                                }
                                continue;
                            case 3:
                                if (methodes.contains(MethodeResolution.PAIRES)) {
                                    methodes.remove(MethodeResolution.PAIRES);
                                } else {
                                    methodes.add(MethodeResolution.PAIRES);
                                }
                                continue;
                            case 4:
                                if (methodes.contains(MethodeResolution.BACKTRACKING)) {
                                    methodes.remove(MethodeResolution.BACKTRACKING);
                                } else {
                                    methodes.add(MethodeResolution.BACKTRACKING);
                                }
                                continue;
                            case 5:
                                long startTime = System.nanoTime(); // TODO A enlever
                                if (sudoku.solve(methodes)) {
                                    System.out.println("Sudoku résolu !");
                                } else {
                                    System.out.println("Aucune solution trouvée");
                                }
                                long endTime = System.nanoTime(); // TODO A enlever
                                System.out.println(endTime - startTime); // TODO A enlever
                                break;
                            case 6:
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
                    for (String log : sudoku.getLog()) {
                        System.out.println(log);
                    }
                    break;
                case 5:
                    // Génère une grille à partir d'une grille complète et d'un niveau de difficulté
                    break;
                case 6:
                    sudoku = new Sudoku(9);
                    initGrille(); // TODO fonction de test a supprimer
                    break;
                case 7:
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
                sudoku = new Sudoku(scanner.nextInt());
                break;
            } catch (Exception e) {
                System.out.println("Ce n'est pas un entier valide. Veuillez réessayer.");
                scanner.nextLine();
            }
        }
    }

    private void demanderGrille() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer les valeurs de la grille (entrez un nombre entre 1 et " + sudoku.getTaille() + ", ou 0 pour une case vide) :");

        // Loop over each row and column to get the values
        for (int i = 0; i < sudoku.getTaille(); i++) {
            for (int j = 0; j < sudoku.getTaille(); j++) {
                int value;
                while (true) {
                    System.out.print("Entrez la valeur pour la case [" + (i + 1) + "][" + (j + 1) + "]: ");
                    try {
                        value = scanner.nextInt();
                        // Check if the value is within the valid range
                        if (value >= 0 && value <= sudoku.getTaille()) {
                            break;  // Valid value, exit the loop
                        } else {
                            System.out.println("La valeur doit être entre 0 et " + sudoku.getTaille() + ". Veuillez réessayer.");
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

    private void initGrille() {
//        int[][] initialValues = {
//                {3, 6, 7, 0, 0, 9, 0, 5, 8},
//                {0, 2, 0, 5, 0, 1, 7, 0, 0},
//                {5, 9, 0, 8, 0, 7, 0, 2, 6},
//                {0, 3, 9, 0, 0, 0, 2, 1, 4},
//                {6, 4, 8, 1, 7, 2, 9, 0, 5},
//                {1, 5, 2, 0, 9, 4, 0, 6, 7},
//                {4, 1, 6, 9, 8, 3, 5, 7, 2},
//                {2, 0, 3, 7, 1, 0, 6, 4, 9},
//                {0, 7, 5, 2, 0, 0, 0, 8, 1}
//        };


        int[][] initialValues = {
                {0, 0, 0, 6, 7, 2, 0, 5, 0},
                {5, 0, 0, 4, 3, 9, 0, 2, 8},
                {0, 2, 0, 0, 0, 0, 3, 0, 0},
                {0, 0, 0, 0, 9, 0, 5, 0, 6},
                {0, 0, 5, 7, 0, 4, 9, 1, 0},
                {9, 0, 1, 0, 0, 3, 8, 0, 0},
                {0, 9, 2, 3, 1, 7, 0, 8, 5},
                {8, 5, 0, 9, 2, 6, 0, 0, 1},
                {0, 0, 0, 8, 4, 5, 2, 0, 0}
        };


//        int[][] initialValues = {
//                {0, 0, 7, 0, 8, 0, 0, 0, 0},
//                {4, 0, 0, 0, 0, 0, 0, 2, 0},
//                {0, 0, 6, 0, 0, 9, 0, 1, 0},
//                {0, 0, 0, 0, 0, 6, 0, 0, 9},
//                {5, 7, 0, 0, 0, 0, 0, 0, 8},
//                {0, 9, 0, 0, 5, 3, 0, 0, 0},
//                {0, 0, 0, 5, 0, 0, 2, 0, 0},
//                {2, 0, 0, 9, 0, 0, 0, 7, 0},
//                {0, 0, 9, 2, 0, 7, 6, 8, 0}
//        };

        // Set values using setValeur()
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku.getGrille()[i][j].setValeur(initialValues[i][j]);
            }
        }
    }

    private void afficherGrille() {
        int blockHeight = 1, blockWidth = sudoku.getTaille();

        for (int h = 1; h <= sudoku.getTaille(); h++) {
            if (sudoku.getTaille() % h == 0) {
                int w = sudoku.getTaille() / h;
                if (w >= h) {
                    blockHeight = h;
                    blockWidth = w;
                }
            }
        }

        for (int i = 0; i < sudoku.getTaille(); i++) {
            if (i > 0 && i % blockHeight == 0) {
                System.out.println("-".repeat(sudoku.getTaille() * 2 + (sudoku.getTaille() / blockWidth) - 1));
            }

            for (int j = 0; j < sudoku.getTaille(); j++) {
                if (j > 0 && j % blockWidth == 0) {
                    System.out.print("| ");
                }

                Case c = sudoku.getGrille()[i][j];
                Integer valeur = c.getValeur();
                if (valeur == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(valeur + " ");
                }

                // Pour l'affichage avec de la couleur
                //String color = colorMatch.getOrDefault(c.getBlocId(), "\u001B[37m");
                //System.out.print(color + valeur + " \u001B[0m");
            }
            System.out.println();
        }
    }

}
