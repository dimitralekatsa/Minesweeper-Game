import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a new instance of MinesweeperGame
        MinesweeperGame game = new MinesweeperGame(10, 10, 10);

        // Create a new scene and set it on the stage
        Scene scene = new Scene(game);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Minesweeper");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}