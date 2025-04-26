import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class MinesweeperSquare extends Button {

    private boolean isBomb;
    public boolean isRevealed;
    private boolean isFlagged;
    private int x, y;

    public MinesweeperSquare(int x, int y) {
        this.x = x;
        this.y = y;
        this.isBomb = false;
        this.isRevealed = false;
        this.isFlagged = false;
        this.setPrefSize(30, 30);
        this.setOnAction(e -> reveal());
    }

    public void placeBomb() {
        this.isBomb = true;
    }

    public void reveal() {
        if (!isRevealed && !isFlagged) {
            this.isRevealed = true;
            if (isBomb) {
                // Game over
            } else {
                // Reveal neighboring squares
            }
        }
    }

    public void flag() {
        if (!isRevealed) {
            this.isFlagged = !this.isFlagged;
            if (isFlagged) {
                // Add flag image to button
            } else {
                // Remove flag image from button
            }
        }
    }

    // Other methods for getting/setting properties of square
}