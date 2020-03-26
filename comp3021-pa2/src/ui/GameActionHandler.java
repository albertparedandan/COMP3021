package ui;

import pa1.City;
import pa1.ministers.Minister;
import pa1.technologies.Technology;

public interface GameActionHandler {
    void handle(int actionNum, Minister minister, City selected, City target, Technology tech, int troopNum);
}
