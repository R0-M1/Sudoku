import java.util.Scanner;

public class MenuTextuel {

    // Méthode pour afficher le menu
    public static void afficherMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Entrer une grille a resoudre");
        System.out.println("2. Choisir la methode de resolution");
        System.out.println("3. Afficher la grille resolue");
        System.out.println("4. Afficher les operations effectuees");
        System.out.println("5. Generer une nouvelle grille");
        System.out.println("6. Quitter");
        System.out.print("Choisissez une option: ");
    }

    // Méthode pour entrer une grille de Game.Sudoku
    public static int[][] entrerGrille(Scanner scanner) {
        int[][] grille = new int[9][9];
        System.out.println("Entrez les valeurs de la grille (0 pour les cases vides):");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print("Entrez la valeur de la case (" + (i+1) + "," + (j+1) + "): ");
                grille[i][j] = scanner.nextInt();
            }
        }
        return grille;
    }

    // Méthode pour résoudre la grille en utilisant une méthode spécifique
    public static boolean resoudreGrille(int[][] grille, String methode) {
        // Implémenter la résolution de Game.Sudoku ici
        // Par exemple, appliquer des règles, retour sur trace, ou les deux.
        System.out.println("Résolution de la grille en utilisant la méthode: " + methode);
        // Implémenter la logique de résolution ici
        return true;  // Retourner true si résolu, sinon false
    }

    // Méthode pour afficher la grille
    public static void afficherGrille(int[][] grille) {
        System.out.println("Game.Grille actuelle:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grille[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Méthode pour loguer les opérations
    public static void logOperations(String operations) {
        System.out.println("Opérations effectuées :");
        System.out.println(operations);
    }

    // Méthode pour générer une grille avec un niveau de difficulté
    public static int[][] genererGrille(int difficulte) {
        // Implémenter ici la logique pour générer une grille selon le niveau de difficulté
        System.out.println("Génération d'une grille avec difficulté: " + difficulte);
        // Retourner une grille générée pour le test
        int[][] grille = new int[9][9];
        return grille;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] grille = new int[9][9];
        String operations = ""; // Variable pour stocker les opérations
        boolean grilleResolue = false;

        while (true) {
            afficherMenu();
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    grille = entrerGrille(scanner);
                    grilleResolue = false; // Game.Grille à résoudre
                    System.out.println("Game.Grille entrée avec succès.");
                    break;

                case 2:
                    if (grilleResolue) {
                        System.out.println("La grille a déjà été résolue.");
                        break;
                    }
                    System.out.println("Choisissez la méthode de résolution:");
                    System.out.println("1. Règles simples");
                    System.out.println("2. Retour sur trace");
                    System.out.println("3. Mixte");
                    int methodeChoisie = scanner.nextInt();
                    String methode = "";

                    switch (methodeChoisie) {
                        case 1:
                            methode = "Règles simples";
                            break;
                        case 2:
                            methode = "Retour sur trace";
                            break;
                        case 3:
                            methode = "Mixte";
                            break;
                        default:
                            System.out.println("Choix invalide.");
                            continue;
                    }

                    grilleResolue = resoudreGrille(grille, methode);
                    break;

                case 3:
                    if (!grilleResolue) {
                        System.out.println("La grille n'a pas été résolue.");
                    } else {
                        afficherGrille(grille);
                    }
                    break;

                case 4:
                    logOperations(operations);
                    break;

                case 5:
                    System.out.println("Entrez le niveau de difficulté (1 à 5):");
                    int difficulte = scanner.nextInt();
                    grille = genererGrille(difficulte);
                    break;

                case 6:
                    System.out.println("Au revoir!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}
