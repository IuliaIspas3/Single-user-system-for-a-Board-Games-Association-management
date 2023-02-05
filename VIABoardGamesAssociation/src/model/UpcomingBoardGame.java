package model;

import java.io.Serializable;

/**
 * A class creating UpcomingBoardGame objects
 * @author Dominika Janczyszyn
 * @version 1.0
 */
public class UpcomingBoardGame extends Game implements Serializable
{
  private int numberOfVotes;

  /**
   * Constructor creating UpcomingBoardGame object
   * @param name the name to be set to the Game object
   * @param numberOfPlayers number of players to be set to the Game object
   * @param description the description to be set to the Game object
   */
  public UpcomingBoardGame(String name, String numberOfPlayers, String description){ // add to the diagram
    super(name, numberOfPlayers,description);
    numberOfVotes = 0;
  }

  /**
   * Adds 1 to the number of votes of the BoardGame object
   */
  public void voteForAGame(){
    numberOfVotes ++;
  }

  /**
   * Gets number of votes of the UpcomingBoardGame Object
   * @return the number of votes of the UpcomingBoardGame Object
   */
  public int getNumberOfVotes(){
    return numberOfVotes;
  }

  /**
   * Returns boolean value when the given object has the same parameters as the current UpcomingBoardGame object
   * @param obj the object given to compare with the current UpcomingBoardGame object
   * @return boolean value of the comparison
   */
  public boolean equals(Object obj){
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    UpcomingBoardGame other = (UpcomingBoardGame) obj;
    return super.equals(other) && this.numberOfVotes == other.numberOfVotes;
  }
  /**
   * Returns information about the UpcomingBoardGame object
   * @return String that contains information about the UpcomingBoardGame object
   */
  public String toString(){
    return super.toString() + "\nNumber of votes: " + numberOfVotes;
  }
}
