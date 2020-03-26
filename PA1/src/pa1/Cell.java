package pa1;


/**
 * An immutable class that represents a cell in the grid map
 */
// TODO
public class Cell {
    private final int x, y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO
        Cell temp = (Cell)obj;
        if (obj.getClass() == Cell.class) {
            if (this.x == temp.getX() && this.y == temp.getY()) {
                return true;
            }
        }
        return false;
    }
}