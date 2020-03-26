package ministers;


/**
 * An abstract class that represents a minister in the game.
 * All actions in the game are done through ministers.
 * Therefore this class will contain the bulk of your implementation of the game.
 */
public abstract class Minister {

    // Attributes
    protected final int intelligence, experience, leadership;

    private boolean isReady = false;

    /**
     * Initializes the attributes of a minister
     *
     * @param intelligence
     * @param experience
     * @param leadership
     */
    protected Minister(int intelligence, int experience, int leadership) {
        this.intelligence = intelligence;
        this.experience = experience;
        this.leadership = leadership;
    }

    /**
     * @return Whether or not the minister is ready
     */
    public boolean isReady() {
        return isReady;
    }

    /**
     * Changes isReady to true
     */
    public void beginTurn() {
        isReady = true;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getExperience() {
        return experience;
    }

    public int getLeadership() {
        return leadership;
    }
}

