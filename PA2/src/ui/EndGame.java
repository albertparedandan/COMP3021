package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pa1.Player;


public class EndGame extends VBox {

    Button button;
    Label lbTitle;
    Text winnerText;

    public EndGame(Player winner) {
        /**
         * TODO: construct the End Game pane
         * This class is an extension of VBox with the following children:
         *  - lbTitle: A Label object that displays the title "Game Ended"
         *  - winnerText: A Text object that displays the message winner.getName() + " Wins the Game"
         *  - A Button object with the text "Return to Main Menu".
         *    When it is clicked, the menu is shown.
         *    Its handler is set in the initHandlers() method in GameApplication
         *
         */










        // set the IDs of winnerText and this end game VBox so that the styles defined in "style.css"
        // can be applied to make the end game scene better looking.
        winnerText.setId("winner");
        setId("end-game");
    }

    public void setMenuButtonHandler(EventHandler<ActionEvent> handler) {
        button.setOnAction(handler);
    }
}
