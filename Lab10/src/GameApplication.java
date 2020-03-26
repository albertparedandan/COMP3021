import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pa1.City;
import pa1.GameEngine;
import pa1.GameMap;
import pa1.Player;
import pa1.exceptions.TooPoorException;
import pa1.ministers.Minister;
import pa1.technologies.Technology;
import ui.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GameApplication extends Application {

    Stage stage;

    // GameMap
    private static GameEngine gameEngine = new GameEngine();

    // UI Panes
    Menu menu = new Menu();
    GameCanvas gameCanvas;
    InfoBar infoBar = new InfoBar(gameEngine.getMap());

    // UI Scenes
    Scene menuScene = new Scene(menu);
    Scene gameplayScene;


    public void initHandlers() {

        /**
         * TODO: initialize event handlers
         *
         * 1. Call the setNewGameHandler() method of menu with an event handler that invokes
         *    the newGameHandler() method of this class
         * 2. Call the setMenuButtonHandler() method of infoBar with an event handler that set the scene
         *    of stage to menuScene
         *
         */
        menu.setNewGameHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                newGameHandler();
            }
        });

        infoBar.setMenuButtonHandler( e -> {
            stage.setScene(menuScene);
        });
    }

    public void newGameHandler() {

        GameMap gameMap = gameEngine.getMap();
        try {
            gameMap.loadPlayers("players.txt");
            gameMap.loadMap("map.txt");
        } catch (IOException e) {
            Platform.exit();
        }

        gameCanvas = new GameCanvas(gameMap, infoBar);
        HBox hBox = new HBox(gameCanvas, infoBar);
        gameplayScene = new Scene(hBox);
        gameplayScene.getStylesheets().add("style.css");
        stage.setScene(gameplayScene);
        Player player = gameMap.getPlayers().get(0);
        infoBar.displayPlayer(player);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        menuScene.getStylesheets().add("style.css");
        stage.setScene(menuScene);
        stage.show();
        initHandlers();
    }

    public static void main(String[] args) {
        launch();
    }


}
