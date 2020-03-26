package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pa1.Cell;
import pa1.City;
import pa1.GameMap;
import pa1.Player;
import pa1.ministers.Minister;
import pa1.technologies.Technology;

public class InfoBar extends VBox {

    private final GameMap gameMap;

    private final Text playerInfo = new Text();

    private final ObservableList<City> cities = FXCollections.observableArrayList();
    private final ObservableList<City> neighbors = FXCollections.observableArrayList();
    private final ObservableList<Technology> technologies = FXCollections.observableArrayList();
    private final ObservableList<Minister> ministers = FXCollections.observableArrayList();

    private ListView<City> cityListView = new ListView<>(cities);
    private ListView<City> neighborListView = new ListView<>(neighbors);
    private ListView<Minister> ministerListView = new ListView<>(ministers);
    private ListView<Technology> technologyListView = new ListView<>(technologies);

    private Slider troopSlider = createTroopSlider();

    private Button sendTroopsButton = new Button("Send Troops");
    private Button menuButton = new Button("Exit to Menu");

    private Player currentPlayer;

    public InfoBar(GameMap gameMap) {
        this.gameMap = gameMap;

        HBox buttonGroup = new HBox(sendTroopsButton, menuButton);
        buttonGroup.setSpacing(12);

        getChildren().addAll(
                playerInfo,
                new Label("Cities"), cityListView,
                new Label("Ministers"), ministerListView,
                new Label("Technologies"), technologyListView,
                new Label("Neighbors"), neighborListView,
                new Label("Enter number of troops:"), troopSlider,
                buttonGroup
        );


        initHandlers();

        // Set the ID to "info-bar" so that the style defined in "style.css" can
        // be applied to make this info bar better looking
        setId("info-bar");
    }

    private void initHandlers() {
        /**
         * TODO: register the event handlers that handle the mouse click events of list views
         *
         * Step 1:
         * Register a mouse click event handler for cityListView such that when a city in the
         * cityListView is selected, the selectCity() method is called.
         *
         * 1. You can use the setOnMouseClicked() method of cityListView to register the event handler.
         * 2. The event handler should:
         *    2.1. Get the currently selected city using cityListView.getSelectionModel().getSelectedItem()
         *    2.2. Check if the selected city is null (because a cityListView can be clicked
         *         with no item selected. In that case, "cityListView.getSelectionModel().getSelectedItem()"
         *         returns null)
         *    2.3. If the selected city is not null, call the method selectCity() with the selected city.
         *
         *
         *
         * Step 2:
         * Register a mouse click event handler for neighborListView such that when a neighbor in
         * neighborListView is selected, the text of sendTroopsButton changes accordingly.
         *
         * 1. You can use the setOnMouseClicked() method of neighborListView to register the event handler.
         * 2. The event handler should:
         *    2.1. Get the currently selected neighbor using neighborListView.getSelectionModel().getSelectedItem();
         *    2.2. Check if the the selected neighbor is null.
         *    2.3. If the selected neighbor is not nul,
         *         Get the owner of the neighbor.
         *         (HINT: you can get the owner of a city using the getCityOwner() method of gameMap.)
         *         If the owner is the same as currentPlayer, set the text of sendTroopsButton to "Send Troops".
         *         Otherwise, set the text of sendTroopsButton to "Attack".
         *         (HINT: you can set the text of a button using its setText(String) method.)
         */
        cityListView.setOnMouseClicked( e -> {
            if (cityListView.getSelectionModel().getSelectedItem() != null) {
                selectCity(cityListView.getSelectionModel().getSelectedItem());
            }
        });

        neighborListView.setOnMouseClicked( e -> {
            if (neighborListView.getSelectionModel().getSelectedItem() != null) {
                Player a = gameMap.getCityOwner(neighborListView.getSelectionModel().getSelectedItem());
                if (currentPlayer.equals(a)) {
                    sendTroopsButton.setText("Send Troops");
                }
                else {
                    sendTroopsButton.setText("Attack");
                }
            }
        });

        /**
         * Note that using the mouse click event handlers are not efficient to achieve the above goals.
         * Because the method selectCity() needs to be called only when the selected city is changed,
         * and the text of sendTroopsButton needs to be updated only when the selected neighbor is changed.
         * Therefore, it's more efficient to use change listeners.
         * We don't use change listeners because we haven't covered them yet.
         * If you're interested, the following example shows how to use a change listener to achieve the
         * goal at step 1.
         */
        /*
        cityListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<City>() {
            @Override
            public void changed(ObservableValue<? extends City> observableValue, City oldVal, City newVal) {
                if(newVal != null){
                    selectCity(newVal);
                }
            }
        });
        */
    }

    private void selectCity(City city) {
        /**
         * TODO: update the neighboring cities and the troop slider
         *
         * 1. Clear the contents in the observable list "neighbors" by calling its clear() method
         * 2. Add the neighboring cities of "city" to "neighbors"
         *    Hint:
         *        2.1. You can get the neighboring cities of a city using the getNeighboringCities() method of gameMap
         *        2.2. The getNeighboringCities() method returns the neighbors as a List<City> object.
         *             You can use the addAll() method of "neighbors" to add all elements in the List<City> object.
         * 3. Set the troopSlider value to 0 and set the max value to the number of troops in the selected city.
         *    Hint: use the setValue() and setMax() method of troopSlider.
         */
        neighbors.clear();
        neighbors.addAll(gameMap.getNeighboringCities(city));
        troopSlider.setValue((double)0);
        troopSlider.setMax(city.getTroops());
    }

    public void displayPlayer(Player player) {
        currentPlayer = player;
        playerInfo.setText(String.format("%s   %d golds   %d science   %d production",
                player.getName(), player.getGold(), player.getSciencePoint(), player.getProductionPoint())
        );

        cities.clear();
        ministers.clear();
        technologies.clear();
        neighbors.clear();

        cities.addAll(player.getCities());
        ministers.addAll(player.getMinisters());
        technologies.addAll(player.getTechnologies());
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


    // select the city located at the given cell
    public void selectCell(Cell cell) {
        for(City city: currentPlayer.getCities()) {
            Cell location = gameMap.getCityLocation(city);
            if(location.equals(cell)){
                cityListView.getSelectionModel().select(city);
                selectCity(city);
            }
        }
    }

    public void setMenuButtonHandler(EventHandler<ActionEvent> handler) {
        menuButton.setOnAction(handler);
    }

}
