package model; /**
 * A class creating Game objects
 * @author Dominika Janczyszyn
 * @version 1.0
 */
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that creates UpcomingBoardGamesList objects
 */
public class UpcomingBoardGamesList implements Serializable
{

    private ArrayList<UpcomingBoardGame> upcomingBoardGames;

  /**
   * Constructor creating UpcomingBoardGamesList object and initializing upcomingBoardGame ArrayList
   */
  public UpcomingBoardGamesList(){
    upcomingBoardGames = new ArrayList<>();
  }

  /**
   * Adds the UpcomingBoardGame Object to ArrayList
   * @param upcomingBoardGame the UpcomingBoardGame Object to be added to upcomingBoardGames arrayList
   */
  public void addUpcomingGame(UpcomingBoardGame upcomingBoardGame){
    upcomingBoardGames.add(upcomingBoardGame);
  }

  /**
   * Removes the UpcomingBoardGame object from the UpcomingBoardGamesList
   * @param upcomingBoardGame the UpcomingBoardGame object to be removed from the UpcomingBoardGamesList
   */
  public void removeUpcomingGame(UpcomingBoardGame upcomingBoardGame){
    for(int i = 0; i < upcomingBoardGames.size(); i++){
      if(upcomingBoardGames.get(i).equals(upcomingBoardGame)){
        upcomingBoardGames.remove(i);
      }
    }
  }

  /**
   * Returns the UpcomingBoardGame object with the given index
   * @param index the given index of the UpcomingBoardGame object
   * @return the UpcomingBoardGame object with the given index
   */
  public UpcomingBoardGame getUpcomingBoardGameByIndex(int index){

    return upcomingBoardGames.get(index);

  }

  /**
   * Gets the size of upcomingBoardGames arrayList
   * @return size of upcomingBoardGames arrayList
   */
  public int size(){
    return upcomingBoardGames.size();
  }

  /**
   * Returns information about the UpcomingBoardGamesList object
   * @return String that contains information about the UpcomingBoardGamesList object
   */
  public String toString(){
    return upcomingBoardGames.toString();
  }
}
