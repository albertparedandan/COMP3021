import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameApplication extends Application {

    public void start(Stage stage) {
        /**
         * TODO: Construct and show the game play scene
         * 1. Instantiate a GameMap, a GameCanvas, an InfoBar using their constructors.
         * 2. Display the GameCanvas by calling its render() method
         * 3. Display the first player on the InfoBar by calling InforBar.displayPlayer()
         *    Note that you can get all players using the getPlayers() method of GameMap.
         *    In this lab, we only need to get the first player and display this first player.
         *    The returned value of getPlayers() is a List<Player>.
         *    And you can get the ith Player in a List<Player> using the its get() method.
         * 4. Call the info bar's writeLog() method using the first player and
         *    the message "It's my turn!" as parameters.
         * 5. Create an HBox with the GameCanvas object and InfoBar object created in step 1.
         * 6. Create a Scene using the HBox object created in step 5. Name it gameplayScene.
         * 7. Add the style sheet to gameplayScene to make it better looking.
         *    i.e., gamePlayScene.getStylesheets().add("style.css");
         *    This "style.css" file is a style template we set, you do not need to touch it.
         * 8. Set the scene of stage to gameplayScene by calling the setScene() method of stage.
         * 9. Show the stage by calling its show() method.
         *
         */
        GameMap gameMap = new GameMap();
        GameCanvas gameCanvas = new GameCanvas(gameMap);
        InfoBar infoBar = new InfoBar(gameMap);
        gameCanvas.render();
        infoBar.displayPlayer(gameMap.getPlayers().get(0));
        infoBar.writeLog(gameMap.getPlayers().get(0), "It's my turn!");
        HBox root = new HBox(gameCanvas, infoBar);
        Scene gameplayScene = new Scene(root);
        gameplayScene.getStylesheets().add("style.css");
        stage.setScene(gameplayScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
