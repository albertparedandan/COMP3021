
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;

public class GameCanvas extends StackPane {

    // Indices of the canvas layers
    private static final int BACKGROUND_LAYER = 0;
    private static final int LINE_LAYER = 1;
    private static final int ICON_LAYER = 2;
    private static final int FLAG_LAYER = 3;
    private static final int TEXT_LAYER = 4;

    // The pixel width and height of a game map cell
    private static final int CELL_WIDTH = 128;
    private static final int CELL_HEIGHT = 128;

    private static final Image[] flags = new Image[7];

    {
        for (int i = 0; i < flags.length; ++i) {
            flags[i] = new Image("player-" + i + ".png", CELL_WIDTH / 4, CELL_HEIGHT / 4, false, false);
        }
    }

    private Canvas[] canvasLayers;
    private final GameMap gameMap;
    private Image background;

    public GameCanvas(GameMap gameMap) {


        /**
         * TODO: construct a game canvas
         *
         *
         * The game canvas is a extension of a stack pane where different canvases are layered
         * to create a graphical representation of a game map
         * 1. Compute the width and height of canvases from the size of gameMap.
         *      e.g., the canvas width is gameMap.getWidth() * CELL_WIDTH
         * 2. Initialize the member variable gameMap using the parameter gameMap.
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
         *
         * Note that this method is not exactly the same as the one in PA2!
         * Do not directly copy the solution of this method to PA2!
         */
        this.gameMap = gameMap;
        double width = (double)(gameMap.getWidth() * CELL_WIDTH);
        double height = (double)(gameMap.getHeight() * CELL_HEIGHT);

        canvasLayers = new Canvas[5];
        for (int i = 0; i < 5; i++) {
            canvasLayers[i] = new Canvas(width, height);
        }

        Image temp = new Image("background.png", width, height, false, false);
        background = temp;

        for (int i = 0; i < 5; i++) {
            this.getChildren().add(canvasLayers[i]);
        }
    }

    private void drawCity(City city, Image flag) {

        /**
         * TODO: draw the icon, name and flag of a city
         *
         * 1. Draw the city icon on the icon layer, i.e., canvasLayers[ICON_LAYER]
         *    HINT:
         *    - Use the city.getImage() method to obtain the city image to draw.
         *        Each image has a size of CELL_WIDTH x CELL_HEIGHT
         *    - You can get a city's location at the gameMap using City.getLocationX() and City.getLocationY()
         *    - Use the city's location to compute the location of the icon on the screen
         *      e.g., the x coordinate of the top-left of the icon on the screen is city.getLocationX() * CELL_WIDTH.
         *    - Get the graphics context of canvas using getGraphicsContext2D()
         *        then use the drawImage() of Canvas through the graphics context to draw the city image
         *
         * 2. Draw the city name on the text layer, i.e., canvasLayers[TEXT_LAYER]
         *    HINT:
         *    - Use strokeText() of graphics context to draw text
         *
         * 3. Draw the top-left flag on the flag layer, i.e., canvasLayers[FLAG_LAYER]
         *
         * Note that this method is not exactly the same as the one in PA2!
         * Do not directly copy the solution of this method to PA2!
         */
        Image temp = city.getImage(CELL_WIDTH, CELL_HEIGHT);
        int x = city.getLocationX();
        int y = city.getLocationY();
        x = x * CELL_WIDTH;
        y = y * CELL_HEIGHT;
        canvasLayers[ICON_LAYER].getGraphicsContext2D().drawImage(temp, x, y);
        canvasLayers[TEXT_LAYER].getGraphicsContext2D().strokeText(city.getName(), x, y);
        canvasLayers[FLAG_LAYER].getGraphicsContext2D().drawImage(flag, x, y);
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
            }
        }

    }
}
