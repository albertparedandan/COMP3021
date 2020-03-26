package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Menu extends VBox {


    private final Button newButton;

    public Menu() {
        /**
         * Construct the Menu pane
         * This class is an extension of VBox with the following children:
         *  - A Label object to display the title "DISCOUNT CIVILIZATION"
         *  - newButton: a button with text "New Game"
         *
         * Note that the handlers of the buttons are set in the initHandlers() method in GameApplication
         */
        Label lbTitle = new Label("DISCOUNT CIVILIZATION");
        newButton = new Button("New Game");
        getChildren().addAll(lbTitle, newButton);

        setId("menu");
    }

    public void setNewGameHandler(EventHandler<ActionEvent> handler) {
        newButton.setOnAction(handler);
    }
}
