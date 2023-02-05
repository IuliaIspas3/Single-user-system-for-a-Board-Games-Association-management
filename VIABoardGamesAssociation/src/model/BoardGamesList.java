package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class creating BoardGamesList objects that contain BoardGame objects
 * @author Oliwier Wijas
 * @version 1.0
 */
public class BoardGamesList implements Serializable
{
  private ArrayList<BoardGame> boardGames;

  /**
   * No-argument constructor initializing ArrayList of BoardGame objects
   */
  public BoardGamesList()
  {
    boardGames = new ArrayList<>();
  }

  /**
   * Adds BoardGame object to the ArrayList
   * @param boardGame boardGame object to be added to the ArrayList
   */
  public void addBoardGame(BoardGame boardGame)
  {
    boardGames.add(boardGame);
  }

  /**
   * Removes BoardGame object from the ArrayList
   * @param boardGame boardGame object to be removed from the ArrayList
   */
  public void removeGame(BoardGame boardGame)
  {
    boardGames.remove(boardGame);
  }

  /**
   * Gets teh BoardGame object using String object name and Student object owner
   * @param name String object name of the BoardGame object to be got
   * @param owner Student object owner of the model.BoardGame object to be got
   * @return the BoardGame object found by the properties given
   */
  public BoardGame getBoardGame(String name, Student owner)
  {
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (name.equals(boardGames.get(i).getName()) && owner.equals(boardGames.get(i).getOwner()))
      {
        return boardGames.get(i);
      }
    }
    return null;
  }

  /**
   * Returns the BoardGame object with the given index
   * @param index the index of the BoardGame object
   * @return the BoardGame object with the given index
   */
  public BoardGame getBoardGameByIndex(int index){
      return boardGames.get(index);

  }

  /**
   * Returns true if the student is a borrower of any BoardGame object, false if not
   * @param student the Student object to be checked if is a borrower
   * @return the boolean value of checking if the student is a borrower
   */
  public boolean isABorrower(Student student)
  {
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).isLent() && boardGames.get(i).getBorrower().equals(student))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the student is an owner of any BoardGame object, false if not
   * @param student the Student object to be checked if is an owner
   * @return the boolean value of checking if the student is an owner
   */
  public boolean isAnOwner(Student student)
  {
    for (int i = 0; i < boardGames.size(); i++)
    {
      if(boardGames.get(i).getOwner() != null){
        if (boardGames.get(i).getOwner().equals(student))
        {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns true if the student is a reservant of any BoardGame object, false if not
   * @param student the Student object to be checked if is a reservant
   * @return the boolean value of checking if the student is a reservant
   */
  public boolean isAReservant(Student student)
  {
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).isAReservant(student))
      {
        return true;
      }
    }
    return false;
  }


  /**
   * Returns size of the ArrayList containing BoardGame objects
   * @return size of BoardGamesList
   */
  public int size()
  {
    return boardGames.size();
  }

  /**
   * Returns information about all the BoardGame objects contained in the BoardGamesList object
   * @return String that contains information about all the BoardGame objects contained in the BoardGameList object
   */
  public String toString()
  {
    String returnStr = "";
    for (int i = 0; i < boardGames.size(); i++)
    {
      returnStr += boardGames.get(i);
    }
    return returnStr;
  }

}
