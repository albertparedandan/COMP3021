
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ministers.Minister;
import technologies.Technology;

public class InfoBar extends VBox {

    private final GameMap gameMap;

    private final ObservableList<City> cities = FXCollections.observableArrayList();
    private final ObservableList<Minister> ministers = FXCollections.observableArrayList();
    private final ObservableList<Technology> technologies = FXCollections.observableArrayList();

    private final Label playerName = new Label();
    private final Text playerResources = new Text();

    private ListView<City> cityListView;
    private ListView<Minister> ministerListView;
    private ListView<Technology> technologyListView;
    private ListView<String> logListView = new ListView<>();


    public InfoBar(GameMap gameMap) {
        this.gameMap = gameMap;

        /**
         * TODO: construct the info bar UI
         * The info bar is an extension of VBox with the following children.
         *  - infoGroup: an HBox of (playerName, playerResources) labels/texts
         *  - a label with text "Cities"
         *  - cityListView: ListView of the player's cities
         *  - a label with text "Ministers"
         *  - ministerListView: ListView of the player's ministers
         *  - a label with text "Technologies"
         *  - technologyListView: ListView of the player's technologies
         *  - a label with text "Logs"
         *  - logListView: ListView of logs
         *
         *  1. Create an HBox by passing playerName, playerResources as parameters to its constructor.
         *  2. Create a labels with the "Cities", "Ministers", "Technologies", "Logs" as their text contents.
         *  3. Initialize cityListView, ministerListView and technologyListView with the corresponding observable list
         *     E.g., cityListView = new ListView<>(cities).
         *     Note that at this constructor, the list views are empty because their corresponding observable lists
         *     are still empty.
         *     The contents will be added in the displayPlayer() method.
         *  4. Add infoGroup, all the labels created in step 2 and listviews created in step 3 to be the children of this info bar.
         *     Hint: this info bar is an extension of VBox, so you can use getChildren().addAll(...)
         *  5. You are free to set the style, e.g., spacing, padding, font size, etc.
         *
         * Note that this method is not exactly the same as the one in PA2!
         * Do not directly copy the solution of this method to PA2!
         */
        HBox infoGroup = new HBox(playerName, playerResources);
        Label cities = new Label("Cities");
        Label ministers = new Label("Ministers");
        Label technologies = new Label("Technologies");
        Label logs = new Label("Logs");
        cityListView = new ListView<City>();
        ministerListView = new ListView<Minister>();
        technologyListView = new ListView<Technology>();
        logListView = new ListView<String>();
        this.getChildren().addAll(infoGroup, cities, cityListView, ministers, ministerListView, technologies, technologyListView, logs, logListView);
    }

    public void displayPlayer(Player player) {
        /**
         * TODO: display a player in this info bar
         * 1. Set the playerResources text to display the player's resources.
         *    The format is like "1000 golds   1000 science   1000 production".
         *    Hint: you can use Text.setText(String) to set the content of a Text object.
         * 2. Clear the information of the previous player by calling the clear() method of all observable lists.
         * 3. Add the players' cities, ministers and technologies to the corresponding observable lists.
         *    e.g., cities.addAll(player.getCities())
         * 4. Set the playerName label to the player's name.
         *    Hint: you can use the method Label.setText(String) to set the content of a Label object.
         *
         *
         * Note that this method is not exactly the same as the one in PA2!
         * Do not directly copy the solution of this method to PA2!
         */
        playerResources.setText(player.getGold() + "golds " + player.getSciencePoint() + " science " + player.getProductionPoint() + " production");
        cities.clear();
        ministers.clear();
        technologies.clear();
        cities.addAll(player.getCities());
        cityListView.getItems().addAll(cities);
        ministers.addAll(player.getMinisters());
        ministerListView.getItems().addAll(ministers);
        technologies.addAll(player.getTechnologies());
        technologyListView.getItems().addAll(technologies);
        playerName.setText(player.getName());
    }

    /**
     * Convenience method to print the output to the logger UI
     */
    public void writeLog(Player player, String message) {
        if (message == null || message.isEmpty())
            return;

        String format = "[%s]: %s";
        logListView.getItems().add(String.format(format, player.getName(), message));
    }
}
