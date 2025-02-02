package UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

import Game.Sudoku;
import Game.MethodeResolution;

public class GUI extends Application {
    private TextField[][] gridFields = new TextField[9][9];

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Solver");
        GridPane gridPane = createGrid();
        Button solveButton = new Button("Résoudre");
        solveButton.setOnAction(e -> solveSudoku());

        VBox layout = new VBox(10, gridPane, solveButton);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField cell = new TextField();
                cell.setPrefSize(50, 50);
                cell.setStyle("-fx-font-size: 16px; -fx-alignment: center;");
                gridFields[row][col] = cell;
                gridPane.add(cell, col, row);
            }
        }
        return gridPane;
    }

    private void solveSudoku() {
        // Ici, on suppose que Sudoku et MethodeResolution sont bien implémentés
        Sudoku sudoku = new Sudoku(9);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = gridFields[i][j].getText();
                int value = text.isEmpty() ? 0 : Integer.parseInt(text);
                sudoku.getGrille()[i][j].setValeur(value);
            }
        }

        if (sudoku.solve(List.of(MethodeResolution.ELIMINATION_DIRECTE))) {
            updateGrid(sudoku);
        } else {
            showAlert("Aucune solution trouvée");
        }
    }

    private void updateGrid(Sudoku sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = sudoku.getGrille()[i][j].getValeur();
                gridFields[i][j].setText(value == 0 ? "" : String.valueOf(value));
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
