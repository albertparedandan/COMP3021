package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pa1.Cell;
import pa1.City;
import pa1.GameMap;
import pa1.Player;

import java.util.List;

public class GameCanvas extends StackPane {

    // Indices of the canvas layers
    private static final int BACKGROUND_LAYER = 0;
    private static final int LINE_LAYER = 1;
    private static final int ICON_LAYER = 2;
    private static final int FLAG_LAYER = 3;
    private static final int TEXT_LAYER = 4;

    private static final Image[] flags = new Image[7];

    {
        for (int i = 0; i < flags.length; ++i) {
            flags[i] = new Image("player-" + i + ".png", 32, 32, false, false);
        }
    }

    // The pixel width and height of a game map cell
    private static final int CELL_WIDTH = 128;
    private static final int CELL_HEIGHT = 128;

    private Canvas[] canvasLayers;
    private GameMap gameMap;
    private Image background;

    private InfoBar infoBar;

    public GameCanvas(GameMap gameMap, InfoBar infoBar) {
        /**
         * TODO: construct a game canvas
         *
         *
         * The game canvas is a extension of a stack pane where different canvases are layered
         * to create a graphical representation of a game map
         * 1. Compute the width and height of canvases from the size of gameMap.
         *      e.g., the canvas width is gameMap.getWidth() * CELL_WIDTH
         * 2. Initialize the member variables gameMap, infoBar using the parameters gameMap and infoBar.
         * 3. Initialize the member variable canvasLayers, which is a Canvas array of size 5 (for the
         *    5 layers defined at the top)
         *    For each entry in canvasLayers, initialize it with a Canvas with the width and height
         *    computed at step 1.
         * 4. Initialize the member variable background.
         *    You can use the Image's constructor
         *    Image(String filename, double width, double height, boolean preserveRatio, boolean smooth) to create an image.
         *    Note the filename for the background image is "background.png".
         *    The width and height of the image is the same as those of canvases.
         *    Set preserveRatio and smooth to false.
         * 5. Create the 5 Canvas objects for canvasLayers, add them one by one to be the children of this stack pane.
         *    e.g., getChildren().add(canvasLayers[0]) adds the first layer, which is the background layer.
         *    The canvas added first will be displayed at the bottom. So you need to add the canvases
         *    in the following order.
         *    canvasLayers[BACKGROUND_LAYER]
         *    canvasLayers[LINE_LAYER]
         *    canvasLayers[ICON_LAYER]
         *    canvasLayers[FLAG_LAYER]
         *    canvasLayers[TEXT_LAYER]
         */



















        // set a mouse click event handler so that when a cell is clicked, the corresponding city
        // in the info bar is selected.
        setOnMouseClicked(e -> {
            int x = (int) e.getX() / CELL_WIDTH;
            int y = (int) e.getY() / CELL_HEIGHT;
            infoBar.selectCell(new Cell(x, y));
        });
    }

    private void drawCity(City city, Image flag) {

        /**
         * TODO: draw the icon, name and flag of a city
         *
         * 1. Draw the city icon on the icon layer, i.e., canvasLayers[ICON_LAYER]
         *    HINT:
         *    - Use the city.getImage() method to obtain the city image to draw.
         *        Each image has a size of CELL_WIDTH x CELL_HEIGHT
         *    - You can get a city's location at the gameMap using the getCityLocation() method of gameMap
         *    - Use the city's location to compute the location of the icon on the screen
         *      e.g., the x coordinate of the top-left of the icon on the screen is location.getX() * CELL_WIDTH,
         *            suppose "location" (a Cell object) is the city's location obtained using getCityLocation()
         *      You may find the toCanvasCoordinates() method in this class useful.
         *    - Get the graphics context of canvas using getGraphicsContext2D()
         *        then use the drawImage() of Canvas through the graphics context to draw the city image
         *
         * 2. Draw the city name on the text layer, i.e., canvasLayers[TEXT_LAYER]
         *    HINT:
         *    - Use strokeText() of graphics context to draw text
         *
         * 3. Draw the top-left flag on the flag layer, i.e., canvasLayers[FLAG_LAYER]
         *
         */














    }

    private void drawLinesBetweenCities(City a, City b) {

        /**
         * TODO: draw a line between two cities
         * If both cities belong to the same owner, draw a light green line, else draw a red line.
         * You need to make sure the line's endpoints are at the center of the cells
         *
         * HINT:
         * - You can use gameMap.getCityOwner(City) to get the owner of a city
         * - You can set the line width using the setLineWidth() method of GraphicsContext.
         * - You can set the line color using the setStroke() method of GraphicsContext,
         *   e.g., gc.setStroke(Color.LIGHTGREEN); suppose gc is the GraphicsContext object of the canvas
         * - Use the strokeLine() method of GraphicsContext to draw a line
         *
         */















    }


    private static Cell toCanvasCoordinates(Cell cell) {
        return new Cell(cell.getX() * CELL_WIDTH, cell.getY() * CELL_HEIGHT);
    }

    public void render() {
        // clear the old content on the canvases
        for (Canvas canvas : canvasLayers)
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // draw the background image on the background canvas layer
        Canvas bg = canvasLayers[BACKGROUND_LAYER];
        bg.getGraphicsContext2D().drawImage(background, 0, 0);

        List<Player> players = gameMap.getPlayers();
        for (int i = 0; i < players.size(); ++i) {
            Image flag = flags[i];
            for (City city : players.get(i).getCities()) {
                // draw the icon, name and flag of each city
                drawCity(city, flag);
                // draw the lines between neighboring cities
                for (City neighbor : gameMap.getNeighboringCities(city)) {
                    if (city.getId() < neighbor.getId())
                        drawLinesBetweenCities(city, neighbor);
                }
            }
        }

    }
}
