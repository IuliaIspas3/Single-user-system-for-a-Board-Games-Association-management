package model;

import java.io.Serializable;

/**
 * A class creating Student objects
 * @author Simona-Luana Draghici
 * @version 1.0
 */
public class Student implements Serializable

{
  private String firstName;
  private String lastName;
  private int VIAID;
  private boolean isAMember;

  /**
   * The 3-argument constructor initializing the Student object, which is a guest by default
   * @param firstName the first name to be set to the Student object
   * @param lastName the last name to be set to the Student object
   * @param id the VIA ID to be set to the Student object
   * @throws IllegalArgumentException throw the Exception when VIA ID has more or less than 6 digits
   */
  public Student(String firstName, String lastName, int id) throws IllegalArgumentException
  {
    this.firstName = firstName;
    this.lastName = lastName;
    if(1000000 > id && id > 99999){
      this.VIAID = id;
    }
    else {
      throw new IllegalArgumentException();
    }

    this.VIAID = VIAID;
    isAMember = false;
  }

  /**
   * Sets the first name for the Student object
   * @param firstName the first name to be set to the Student object
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * Sets the last name
   * @param lastName the last name to be set to the Student object
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Sets the VIA ID
   * @param id the VIA ID to be set to the Student object
   */
  public void setVIAID(int id)
  {
    if(1000000 > id && id > 99999){
      this.VIAID = id;
    }
    else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Gets the first name of the Student object
   * @return returning the first name of the Student object
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Gets the last name of the Student object
   * @return returning the last name of the Student object
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Gets the VIA ID of the Student object
   * @return returning the VIA ID of the Student object
   */

  public int getVIAID()
  {
    return VIAID;
  }

  /**
   * Sets a student as a member
   */
  public void setAMember()
  {
    isAMember = true;
  }

  /**
   * Verifies if a student is a member or a guest
   * @return the boolean value of checking if the Student object is a member
   */
  public boolean isAMember()
  {
    return isAMember;
  }

  /**
   * Returns boolean value when a given object has the same parameters as the current Student object
   * @param obj the object given to compare with the given Student object
   * @return boolean value of the comparison
   */
  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Student other = (Student) obj;

    return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.VIAID == other.VIAID && this.isAMember == other.isAMember;
  }

  /**
   * Returns boolean value when a given object has the same parameters, except VIA ID, as the current Student object
   * @param obj the object given to compare with the given Student object
   * @return boolean value of the comparison
   */
  public boolean equalsWithoutMembership(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Student other = (Student) obj;

    return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.VIAID == other.VIAID;
  }

  /**
   * Returns the information about the Student object as a String
   * @return the String with the information about the Student object
   */
  public String toString()
  {
    return firstName +" "+lastName+ "("+VIAID+")";
  }

}
