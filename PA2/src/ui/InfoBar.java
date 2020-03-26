package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import pa1.Cell;
import pa1.City;
import pa1.GameMap;
import pa1.Player;
import pa1.ministers.Minister;
import pa1.technologies.ManufacturingTech;
import pa1.technologies.Technology;
import pa1.technologies.TradingTech;
import pa1.technologies.WarTech;

import java.util.ArrayList;
import java.util.List;

public class InfoBar extends VBox {

    private final GameMap gameMap;

    private final List<Button> buttons = new ArrayList<>();
    private final ObservableList<City> cities = FXCollections.observableArrayList();
    private final ObservableList<City> neighbors = FXCollections.observableArrayList();
    private final ObservableList<Technology> technologies = FXCollections.observableArrayList();
    private final ObservableList<Minister> ministers = FXCollections.observableArrayList();

    private final Label playerName = new Label();
    private final Text playerResources = new Text();

    private Slider troopSlider;
    private ListView<City> cityListView;
    private ListView<City> neighborListView;
    private ListView<Minister> ministerListView;
    private ListView<Technology> technologyListView;

    private ListView<String> logListView = new ListView<>();

    private Button skipButton = new Button("Skip Turn");
    private Button menuButton = new Button("Exit to Menu");
    private Button saveButton = new Button("Save Game");

    public void setGameActionHandler(GameActionHandler gameActionHandler) {
        this.gameActionHandler = gameActionHandler;
    }

    private GameActionHandler gameActionHandler;

    private Player currentPlayer;


    public InfoBar(GameMap gameMap) {
        this.gameMap = gameMap;

        /**
         * TODO: construct the info bar UI
         * The info bar is an extension of VBox with the following children.
         *  - infoGroup: an HBox of (playerName, playerResources) labels/texts
         *  - a Label object with text "Cities"
         *  - cityListView: ListView of the player's cities
         *  - a Label object with text "Ministers"
         *  - ministerListView: ListView of the player's ministers
         *  - a Label object with text "Technologies"
         *  - technologyListView: ListView of the player's technologies
         *  - a Label object with text "Neighbors"
         *  - neighborListView: ListView of the neighbors of the selected city
         *  - lbSlider: a Label object with text "Enter number of troops (0)",
         *    make sure the number changes as you move the slider below it.
         *  - troopSlider: Slider to select number of troops
         *  - Button group to select action
         *  - a label with text "Logs"
         *  - logListView: ListView of logs
         *
         *  1. Create an HBox by passing playerName, playerResources as parameters to its constructor.
         *  2. Create a labels with the "Cities", "Ministers", "Technologies", "Neighbors", "Logs" as their text contents.
         *  3. Initialize cityListView, ministerListView, technologyListView and neighborListView
         *     with the corresponding observable list
         *     E.g., cityListView = new ListView<>(cities).
         *     Note that at this constructor, the list views are empty because their corresponding observable lists
         *     are still empty.
         *     The contents in cityListView, ministerListView and technologyListView will be added in the displayPlayer() method.
         *     The contents in neighborListView will be added in the onSelectedCityChangeHandler() method.
         *  4. Create a Label object with text "Enter number of troops (0)", call it lbSlider.
         *  4. Initialize troopSlider using the slider returned by the createTroopSlider() method.
         *  5. Add a listener to the value property of troopSlider that changes the number in lbSlider.
         *     For example, given a Label object "label" and a Slider object "slider",
         *     if we want to change the text in "label" when the value in "slider" changes,
         *     we can add a listener to the value property of "slider", i.e.,
         *     slider.valueProperty().addListener((ov, oldVal, newVal) -> {
         *         label.setText("The value of the slider is " + newVal);
         *     });
         *     Note that "newVal" is a Number object, you can convert it to an integer using its intValue() method.
         *  6. Create the button group using the createActionButtonGroup() method.
         *  7. Add infoGroup, all the labels created in step 2, all the ListView objects created in step 3,
         *     lbSlider, troopSlider and the button group created at step 6
         *     to be the children of this info bar.
         *     Hint: this info bar is an extension of VBox, so you can use getChildren().addAll(...)
         *  8. Uncomment the four lines of code at the bottom of this constructor that add a listener to cityListView.
         *     Note the four lines of code should be put after the code for previous 7 steps!!!
         */





















        // for Step 8
        // Add a listener to cityListView so that the neighborListView updates its content
        // when the selected city is changed.
        // uncomment the following 4 lines when you start to implement this constructor.
//        cityListView.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
//            if(oldVal != newVal && newVal != null){
//                onSelectedCityChangeHandler(newVal);
//            }
//        });

        // Set the ID to "info-bar" so that the style defined in "style.css" can
        // be applied to make this info bar better looking
        setId("info-bar");
    }

    private void onSelectedCityChangeHandler(City city) {
        /**
         * TODO: complete the on selected city changed handler
         * This method will be called when the selected item in the cityListView is changed
         * 1. Clear the contents in the observable list "neighbors" by calling its clear() method
         * 2. Add the neighboring cities of "city" to "neighbors"
         *    Hint:
         *        2.1. You can get the neighboring cities of a city using the getNeighboringCities() method of gameMap
         *        2.2. The getNeighboringCities() method returns the neighbors as a List<City> object.
         *             You can use the addAll() method of "neighbors" to add all elements in the List<City> object.
         * 3. Set the troopSlider value to 0 and set the max value to the number of troops in the selected city.
         *    Hint: use the setValue() and setMax() method of troopSlider.
         */















    }

    private void actionButtonHandler(int actionNumber) {
        /**
         * TODO: complete the action button handler
         * This method will be invoked when one of the action buttons (buttons to select an action) is clicked
         *
         * 1. Get all the selected items in the listViews, using getSelectionModel().getSelectedItem().
         *    e.g., the selected city is cityListView.getSelectionModel().getSelectedItem();
         * 2. Get the value of troopSlider
         * 3. Invoke the handle() method of the gameActionHandler interface
         *    using actionNumber and the variables obtained in step 1 and 2.
         *
         */







    }

    public void displayPlayer(Player player) {
        /**
         * TODO: display a player in this info bar
         * 1. Set currentPlayer to player
         * 2. Set playerName to display the name of the player.
         *    Set playerResources to display the resources of the player.
         *    e.g., "100 golds   200 science   300 production"
         *    Hint: you can use the setText() method of Label and Text.
         * 3. Clear the information of the previous player by calling the clear() method of all observable lists.
         * 4. Add the player's cities, ministers and technologies to the corresponding observable lists.
         *    e.g., cities.addAll(player.getCities())
         */











    }

    // Create the troop slider
    private Slider createTroopSlider() {
        Slider troopSlider = new Slider();
        troopSlider.setMin(0);
        troopSlider.setMax(0);
        troopSlider.setValue(0);
        troopSlider.setBlockIncrement(1);
        troopSlider.setShowTickLabels(true);
        troopSlider.setShowTickMarks(true);
        troopSlider.setSnapToTicks(false);
        return troopSlider;
    }

    // Create the 3-column button group
    private Pane createActionButtonGroup() {

        VBox[] vBoxes = new VBox[3];

        buttons.add(new Button("Collect Tax"));
        buttons.add(new Button("Get Science Pts"));
        buttons.add(new Button("Get Production Pts"));
        buttons.add(new Button("Build a Bank"));
        buttons.add(new Button("Build a Road"));
        buttons.add(new Button("Build a University"));
        buttons.add(new Button("Improve Crops"));
        buttons.add(new Button("Upgrade Tech"));
        buttons.add(new Button("Send Troops/Attack"));
        buttons.add(new Button("Recruit Troops"));

        for (int i = 0; i < buttons.size(); i++) {
            final int actionNum = i;
            buttons.get(i).setOnAction(e -> actionButtonHandler(actionNum));
        }

        buttons.add(skipButton);
        buttons.add(menuButton);
        buttons.add(saveButton);

        for (int i = 0; i < 3; ++i) {
            VBox vBox = new VBox();
            vBox.setSpacing(5);
            int end = Math.min((i + 1) * 5, buttons.size());
            vBox.getChildren().addAll(buttons.subList(i * 5, end));
            vBoxes[i] = vBox;
        }

        HBox buttonGroup = new HBox();
        buttonGroup.getChildren().addAll(vBoxes);
        buttonGroup.setSpacing(5);

        return buttonGroup;
    }

    public void setDisableButtons(boolean isDisabled) {
        /**
         * TODO: disable/enable ALL buttons in this component
         *
         * The buttons includes those in this.buttons, skipButton and menuButton
         * Hint: you can disable a button using Button.setDisable(true)
         *       or enable a button using Button.setDisable(false)
         */







    }

    // select the city located at the given cell
    public void selectCell(Cell cell) {
        for(City city: currentPlayer.getCities()) {
            Cell location = gameMap.getCityLocation(city);
            if(location.equals(cell)){
                cityListView.getSelectionModel().select(city);
            }
        }
    }

    public void setSkipButtonHandler(EventHandler<ActionEvent> handler) {
        skipButton.setOnAction(handler);
    }

    public void setMenuButtonHandler(EventHandler<ActionEvent> handler) {
        menuButton.setOnAction(handler);
    }


    /**
     * Write log to logListView
     */
    public void writeLog(Player player, String message) {
        if (message == null || message.isEmpty())
            return;

        String format = "[%s]: %s";
        logListView.getItems().add(String.format(format, player.getName(), message));
    }

    /**
     * Clear the content in the logListView
     */
    public void clearLog() {
        logListView.getItems().clear();
    }
}
