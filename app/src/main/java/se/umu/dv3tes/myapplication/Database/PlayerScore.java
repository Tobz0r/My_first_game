package se.umu.dv3tes.myapplication.Database;

/**
 * Used by database to save a score with a playername
 * @author Tobias Estefors
 */
public class PlayerScore {

    private int score;
    private String name;

    public PlayerScore(String name, int score){
        this.name=name;
        this.score=score;
    }

    /**
     * Returns the name of the player
     * @return a string with a playername
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a player name
     * @param name the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns this playes score
     * @return integer containing score
     */
    public int getScore() {
        return score;
    }

    /**
     * sets the score for this player
     * @param score for this player
     */
    public void setScore(int score) {
        this.score = score;
    }




}
