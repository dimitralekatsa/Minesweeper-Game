import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MinesweeperBoard extends GridPane {

    private int numRows;
    private int numCols;
    private int numBombs;
    private int[][] board;
    private Button[][] buttons;
    private Random rand;
    private MinesweeperSquare[][] squares;


    public MinesweeperBoard(int numRows, int numCols, int numBombs) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.numBombs = numBombs;
        this.board = new int[numRows][numCols];
        this.buttons = new Button[numRows][numCols];
        this.rand = new Random();

        initializeBoard();
        addButtons();
    }

    private void initializeBoard() {
        // Initialize the board with empty squares and bombs
        int numSquares = numRows * numCols;
        int[] squareIndices = new int[numSquares];
        for (int i = 0; i < numSquares; i++) {
            squareIndices[i] = i;
        }
        for (int i = 0; i < numBombs; i++) {
            int randomIndex = rand.nextInt(numSquares - i) + i;
            int row = squareIndices[randomIndex] / numCols;
            int col = squareIndices[randomIndex] % numCols;
            board[row][col] = -1;
            squareIndices[randomIndex] = squareIndices[i];
        }

        // Fill in the counts for the adjacent bombs
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (board[row][col] != -1) {
                    int count = 0;
                    for (int i = row - 1; i <= row + 1; i++) {
                        for (int j = col - 1; j <= col + 1; j++) {
                            if (i >= 0 && i < numRows && j >= 0 && j < numCols && board[i][j] == -1) {
                                count++;
                            }
                        }
                    }
                    board[row][col] = count;
                }
            }
        }
    }

    private void addButtons() {
        // Add buttons to the board
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Button button = new Button();
                button.setPrefSize(40, 40);
                int finalRow = row;
                int finalCol = col;
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            handleLeftClick(finalRow, finalCol);
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            handleRightClick(finalRow, finalCol);
                        }
                    }
                });
                add(button, col, row);
                buttons[row][col] = button;
            }
        }
    }

    private void handleLeftClick(int row, int col) {
        // Handle left-click on a button
        Button button = buttons[row][col];
        if (board[row][col] == -1) {
            // Game over
            button.setText("X");
            for (Button[] buttonRow : buttons) {
                for (Button b : buttonRow) {
                    b.setDisable(true);
                }
            }
        } else {
            // Reveal the square
            revealSquare(row, col);
        }
    }


    public int getNumRows() {
        return board.length;
    }

    public int getNumCols() {
        return board[0].length;
    }

    public int getNumBombs() {
        return numBombs;
    }



    private void revealSquare(int row, int col) {
        // Reveal the square at the given row and col
        int numMines = board[row][col];
        Button button = buttons[row][col];
        if (button.getText().equals("F")) {
            // Ignore if the square is flagged
            return;
        } else if (numMines == 0) {
            // Reveal all adjacent squares if this square has no mines
            button.setDisable(true);
            button.setText("");
            for (int i = Math.max(0, row - 1); i <= Math.min(getNumRows() - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(getNumCols() - 1, col + 1); j++) {
                    if (buttons[i][j].isDisabled()) {
                        continue;
                    }
                    revealSquare(i, j);
                }
            }
        } else {
            // Reveal the number of mines adjacent to this square
            button.setDisable(true);
            button.setText(String.valueOf(numMines));
        }
    }


    private void handleRightClick(int row, int col) {
        // Handle right-click on a button
        Button button = buttons[row][col];
        if (button.getText().equals("")) {
            button.setText("F");
        } else if (button.getText().equals("F")) {
            button.setText("?");
        } else {
            button.setText("");
        }
    }

    public void revealAll() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                buttons[row][col].setDisable(true);
                if (board[row][col] == -1) {
                    buttons[row][col].setText("X");
                } else {
                    buttons[row][col].setText(String.valueOf(board[row][col]));
                }
            }
        }
    }



    // other methods here
}