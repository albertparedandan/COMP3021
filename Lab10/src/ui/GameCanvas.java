package ui;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import pa1.Cell;
import pa1.City;
import pa1.GameMap;
import pa1.Player;

import java.util.List;

public class GameCanvas extends StackPane {

    private static final Image[] flags = new Image[7];

    {
        for (int i = 0; i < flags.length; ++i) {
            flags[i] = new Image("player-" + i + ".png", 32, 32, false, false);
        }
    }

    // The pixel width and height of a game map cell
    private static final int CELL_WIDTH = 128;
    private static final int CELL_HEIGHT = 128;


    public GameCanvas(GameMap gameMap, InfoBar infoBar) {
        int canvasWidth = gameMap.getWidth() * CELL_WIDTH;
        int canvasHeight = gameMap.getHeight() * CELL_HEIGHT;

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        getChildren().add(canvas);
        List<Player> players = gameMap.getPlayers();
        for (int i = 0; i < players.size(); ++i) {
            Image flag = flags[i];
            for (City city : players.get(i).getCities()) {
                // draw the icon, name and flag of each city
                Image image = city.getImage();
                Cell location = gameMap.getCityLocation(city);
                int x = location.getX() * CELL_WIDTH;
                int y = location.getY() * CELL_HEIGHT;
                canvas.getGraphicsContext2D().drawImage(image, x, y, CELL_WIDTH, CELL_HEIGHT);
                canvas.getGraphicsContext2D().strokeText(city.getName(), x + CELL_WIDTH / 3, y);
                canvas.getGraphicsContext2D().drawImage(flag, x + 16, y + 16);
            }
        }

        // set a mouse click event handler so that when a cell is clicked, the corresponding city
        // in the info bar is selected.
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = (int) mouseEvent.getX() / CELL_WIDTH;
                int y = (int) mouseEvent.getY() / CELL_HEIGHT;
                infoBar.selectCell(new Cell(x, y));
            }
        });

        setId("canvas");
    }
}
