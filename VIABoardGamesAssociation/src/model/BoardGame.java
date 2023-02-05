package model;

import javax.lang.model.element.UnknownAnnotationValueException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A class creating BoardGame objects being an extension of the Game class
 * @author Oliwier Wijas
 * @version 1.0
 */

public class BoardGame extends Game implements Serializable
{
  private Student borrower;
  private Student owner;
  private Date dateOfLoan;
  private StudentList reservants;
  private int numberOfRatings;
  private ArrayList<Integer> ratings;

  private double averageRating;

  /**
   * Constructor creating BoardGame object
   * @param name the name to be set to the BoardGame object
   * @param numberOfPlayers number of players to be set to the BoardGame object
   * @param description the description to be set to the BoardGame object
   */
  public BoardGame(String name, String numberOfPlayers, String description)
  {
    super(name, numberOfPlayers, description);
    borrower = null;
    owner = null;
    dateOfLoan = null;
    reservants = new StudentList();
    numberOfRatings = 0;
    ratings = new ArrayList<Integer>();
  }

  /**
   * Sets the owner of the BoardGame object
   * @param owner owner to be set to the BoardGame object
   */
  public void setOwner(Student owner)
  {
    this.owner = owner;
  }

  /**
   * Sets the borrower of the BoardGame object
   * @param borrower borrower to be set to the BoardGame object
   */
  public void setBorrower(Student borrower)
  {
    this.borrower = borrower;
  }

  /**
   * Gets the owner of the BoardGame object
   * @return the borrower of the BoardGame object
   */
  public Student getOwner()
  {
    return owner;
  }

  /**
   * Gets the borrower of the BoardGame object
   * @return the borrower of the BoardGame object
   */
  public Student getBorrower()
  {
    return borrower;
  }

  /**
   * Gets the date of loan of the BoardGame object
   * @return the date of loan of the BoardGame object
   */
  public Date getDateOfLoan()
  {
    return dateOfLoan;
  };

  /**
   * Lents the BoardGame object to the borrower and changes the date of loan to the current date
   * @param member the borrower of the BoardGame object
   */
  public void lentBoardGame(Student member)
  {
    if (this.borrower == null)
    {
      this.borrower = member;
      dateOfLoan = Date.getTodaysDate();
    }
    if(this.borrower != null){
      this.borrower = member;
    }
  }

  /**
   * Checks if the Student object is a reservant of the BoardGame object
   * @param student the student to be checked if is a reservant
   * @return boolean value if the given Student object is a reservant
   */
  public boolean isAReservant(Student student)
  {
    for (int i = 0; i < reservants.size(); i++)
    {
      if (reservants.getStudentByIndex(i).equals(student))
        return true;
    }
    return false;
  }

  /**
   * Sets borrower and date of loan to the next person from the reservants list or if the list is empty, to null
   */
  public void returnBoardGame()
  {
    if (isReserved())
    {
      borrower = reservants.getStudentByIndex(0);
      reservants.removeByIndex(0);
      dateOfLoan = Date.getTodaysDate();
    }
    else
    {
      borrower = null;
      dateOfLoan = null;
    }
  }

  /**
   * Returns true if the BoardGame object is lent; false if not
   * @return boolean value whether the BoardGame is lent or not
   */
  public boolean isLent()
  {
    if (borrower == null)
    {
      return false;
    }
    return true;
  }

  /**
   * Adds the member to the reservants list
   * @param member the reservant of the BoardGame object
   */
  public void reserve(Student member)
  {
      reservants.addMember(member);
  }

  /**
   * Returns true if the BoardGame object is reserved by at least one person; false if not
   * @return boolean value if the BoardGame is reserved by someone or not
   */
  public boolean isReserved()
  {
    if (reservants.size() == 0)
    {
      return false;
    }
    return true;
  }

  /**
   * Cancels reservation of the entered Student
   * @param member the student that wants to cancel the reservation
   */
  public void cancelReservation(Student member)
  {
    reservants.removeMember(member);
  }

  /**
   * Adds a rating to the BoardGame object
   * @param rating the rating of the BoardGame object to be added
   */
  public void rate(int rating)
  {
    numberOfRatings++;
    ratings.add(rating);
    getRating();
  }

  /**
   * Gets average rating of the BoardGame object
   * @return the average of the ratings of the BoardGame object
   */
  public double getRating()
  {
    double sum = 0;
    for (int i = 0; i < ratings.size(); i++)
    {
      sum += ratings.get(i);
    }
    DecimalFormat f = new DecimalFormat("##.00");
    double arvRating = sum / (double)ratings.size();
    this.averageRating = Double.parseDouble(f.format(arvRating));
    return averageRating;
  }

  /**
   * Returns list of Student objects with all reservants of the BoardGame object
   * @return list of Student objects that contains all reservants
   */
  public Student[] getAllReservants()
  {
    Student[] reservants1 = reservants.getAllMembers();
    return reservants1;
  }

  /**
   * Returns boolean value when a given object has the same parameters as the current BoardGame object
   * @param obj the object given to compare with the current BoardGame object
   * @return boolean value of the comparison
   */
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    BoardGame other = (BoardGame) obj;
    return super.equals(other) && this.owner == other.owner && this.borrower == other.borrower && this.dateOfLoan == other.dateOfLoan && this.reservants.equals(other.reservants) && this.numberOfRatings == other.numberOfRatings && this.ratings.equals(other.ratings);
  }

  /**
   * Returns name of the BoardGame object
   * @return String with the BoardGame Object's information
   */
  public String toString(){
    return getName();
  }
}
