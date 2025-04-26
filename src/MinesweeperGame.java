import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class MinesweeperGame extends BorderPane {

    private MinesweeperBoard board;
    private Label timeLabel;
    private Timeline timeline;
    private int timeRemaining;

    public MinesweeperGame(int numRows, int numCols, int numBombs) {
        this.board = new MinesweeperBoard(numRows, numCols, numBombs);
        this.setCenter(board);

        // Add a button to start a new game
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> newGame());

        // Add a label for the countdown timer
        this.timeRemaining = 180; // default time is 180 seconds
        this.timeLabel = new Label("Time: " + timeRemaining);
        this.setTop(newGameButton);
        BorderPane.setAlignment(newGameButton, Pos.CENTER);
        BorderPane.setAlignment(timeLabel, Pos.CENTER);
        this.setBottom(timeLabel);

        // Set up the timeline for the countdown clock
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
            if (timeRemaining == 0) {
                gameOver();
            } else {
                timeRemaining--;
                timeLabel.setText("Time: " + timeRemaining);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void newGame() {
        // Stop the timeline and reset the time label
        timeline.stop();
        timeRemaining = 180;
        timeLabel.setText("Time: " + timeRemaining);

        // Clear the board and start a new game
        this.getChildren().remove(board);
        this.board = new MinesweeperBoard(board.getNumRows(), board.getNumCols(), board.getNumBombs());
        this.setCenter(board);

        // Restart the timeline
        timeline.play();
    }

    private void gameOver() {
        // Stop the timeline and reveal all squares
        timeline.stop();
        board.revealAll();

        // Show a game over message
        // You can replace this with your own game over logic
        System.out.println("Game over!");
    }
}