package UI;

import java.util.*;

import Game.*;

public class ConsoleUI {
    private static final Map<Integer, String> charMap = createCharMap(); // pour le multidoku avec blocs de couleur
    private Sudoku sudoku;

    private static Map<Integer, String> createCharMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, ".");
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(5, "5");
        map.put(6, "6");
        map.put(7, "7");
        map.put(8, "8");
        map.put(9, "9");
        map.put(10, "A");
        map.put(11, "B");
        map.put(12, "C");
        map.put(13, "D");
        map.put(14, "E");
        map.put(15, "F");
        map.put(16, "G");
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
            System.out.println("5. Générer une grille en fonction d’un niveau de difficulté");
            System.out.println("6. Grilles de test");
            System.out.println("7. Modifier les caractères affichés");
            System.out.println("8. Quitter");

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
                                if (sudoku.solve(methodes)) {
                                    System.out.println("Sudoku résolu !");
                                } else {
                                    System.out.println("Aucune solution trouvée");
                                }
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
                    if (sudoku != null) {
                        afficherGrille();
                    } else {
                        System.out.println("Aucun sudoku initialisé !");
                    }
                    break;
                case 4:
                    for (String log : sudoku.getLog()) {
                        System.out.println(log);
                    }
                    break;
                case 5:
                    demanderTaille();

                    while (true) {
                        System.out.println("\033[1mChoisissez une difficultée :\033[0m");
                        System.out.println("1. Facile");
                        System.out.println("2. Moyen");
                        System.out.println("3. Difficile");

                        int choix3 = scanner.nextInt();
                        switch (choix3) {
                            case 1:
                                sudoku.generer(Difficulte.FACILE);
                                break;
                            case 2:
                                sudoku.generer(Difficulte.MOYEN);
                                break;
                            case 3:
                                sudoku.generer(Difficulte.DIFFICILE);
                                break;
                            default:
                                System.out.println("Choix invalide. Veuillez réessayer.");
                                continue;
                        }
                        break;
                    }

                    break;
                case 6:
                    sudoku = new Sudoku(9);
                    initGrille();
                    break;
                case 7:
                    demanderCharMap();
                    break;
                case 8:
                    System.out.println("Au revoir!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    private void demanderCharMap() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\nMap actuelle:");
            for (Map.Entry<Integer, String> entry : charMap.entrySet()) {
                System.out.println(entry.getKey() + " => " + entry.getValue());
            }

            System.out.println("Entrez l'index que vous souhaitez modifier: ");
            int index = scanner.nextInt();

            System.out.println("Entrez le nouveau caractère pour l'index " + index + ": ");
            String newChar = scanner.next();

            charMap.put(index, newChar);
            System.out.println("Modification effectuée !");

            System.out.print("\nVoulez-vous modifier un autre caractère ? (o/n) : ");
            String reponse = scanner.next().toLowerCase();
            if (!reponse.equals("o")) {
                continuer = false;
            }
        }

        System.out.println("\nMap finale:");
        for (Map.Entry<Integer, String> entry : charMap.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }


    }

    private void demanderTaille() {
        Scanner scanner = new Scanner(System.in);
        String type = "";
        while (true) {
            System.out.println("Choisissez un type de Sudoku :");
            System.out.println("1. Sudoku");
            System.out.println("2. Multidoku (NE FONCTIONNE PAS)");

            switch (scanner.nextInt()) {
                case 1:
                    type = "Sudoku";
                    break;
                case 2:
                    type = "Multidoku";
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    continue;
            }
            break;
        }
        System.out.println("Choisissez une taille de grille :");

        while (true) {
            try {
                if (type.equals("Sudoku")) {
                    sudoku = new Sudoku(scanner.nextInt());
                } else {
                    sudoku = new Multidoku(scanner.nextInt());
                }
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
        Scanner scanner = new Scanner(System.in);
        int[][] initialValues;

        while (true) {
            System.out.println("Choisissez votre grille de test :");
            System.out.println("1. Grille simple (Résolution par règles de déduction possible)");
            System.out.println("2. Grille complexe (Résolution par backtracking obligatoire)");

            switch (scanner.nextInt()) {
                case 1:
                    initialValues = new int[][]{
                            {3, 6, 7, 0, 0, 9, 0, 5, 8},
                            {0, 2, 0, 5, 0, 1, 7, 0, 0},
                            {5, 9, 0, 8, 0, 7, 0, 2, 6},
                            {0, 3, 9, 0, 0, 0, 2, 1, 4},
                            {6, 4, 8, 1, 7, 2, 9, 0, 5},
                            {1, 5, 2, 0, 9, 4, 0, 6, 7},
                            {4, 1, 6, 9, 8, 3, 5, 7, 2},
                            {2, 0, 3, 7, 1, 0, 6, 4, 9},
                            {0, 7, 5, 2, 0, 0, 0, 8, 1}
                    };
                    break;
                case 2:
                    initialValues = new int[][]{
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
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    continue;
            }
            break;
        }

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
                    System.out.print(" ");
                } else {
                    System.out.print(valeur + " ");
                }
            }
            System.out.println();
        }

    }
}