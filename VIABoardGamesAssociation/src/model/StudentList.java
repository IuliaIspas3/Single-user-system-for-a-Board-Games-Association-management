package model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * A class containing Student objects
 * @author Simona-Luana Draghici
 * @version 1.0
 */

public class StudentList implements Serializable
{
  private ArrayList<Student> students;

  /**
   * No-argument constructor initializing ArrayList of Student objects
   */
  public StudentList()
  {
    students = new ArrayList<Student>();
  }

  /**
   * Adds a Student object of type guest to the StudentList
   * @param guest the guest to be added to the StudentList
   */
  public void addGuest(Student guest)
  {
    if (guest != null)
    {
      if (!guest.isAMember())
      {
        students.add(guest);
      }
    }
  }

  /**
   * Adds a Student object of type member to the StudentList
   * @param member the member to be added to the StudentList
   */
  public void addMember(Student member)
  {
    if (member != null)
    {
      if (member.isAMember())
      {
        students.add(member);
      }
    }
  }

  /**
   * Adds a Student object to the StudentList
   * @param student the student to be added to the StudentList
   */
  public void addStudent(Student student)
  {
    students.add(student);
  }

  /**
   * Returns the Student object of type guest with the given index
   * @param index the index of the Student object of type guest
   * @return the Student object of type guest with the given index
   */
  public Student getGuestById(int index)
  {
    if (students.get(index) != null)
    {
      if (!students.get(index).isAMember())
      {
        return students.get(index);
      }
    }
    return null;
  }

  /**
   * Returns the Student object of type member with the given index
   * @param index the index of the Student object of type member
   * @return the Student object of type member with the given index
   */
  public Student getMemberById(int index)
  {
    if (students.get(index) != null)
    {
      if (students.get(index).isAMember())
      {
        return students.get(index);
      }
    }
    return null;
  }

  /**
   * Returns true if the given Student object has the same VIA ID as any other Student object in the StudentList object
   * @param member the Student object of type member
   * @return the boolean value of checking if the given Student object has the same VIA ID as any other Student object in the StudentList object
   */
  public boolean sameID(Student member){
    boolean equals = false;
    for(int i = 0; i < students.size(); i++){
      if(students.get(i).getVIAID() == member.getVIAID())
      {
        equals = true;
      }
    }
    return equals;
  }

  /**
   * Returns true if the Student object of type member is already in the system
   * @param member the Student object of type member
   * @return the boolean value of checking if the given Student object is already in the system
   */
  public boolean memberExists(Student member){
    boolean equals = false;
    for(int i = 0; i < students.size() ; i++){
      if(students.get(i).equals(member)){
        equals = true;
      }
    }
    return equals;
  }



  /**
   * Gets the Student object of type member from the StudentList using the given index as a parameter
   * @param index the index of the Student object of type member
   * @return the Student object of type member with the given index
   */
  public Student getStudentByIndex(int index)
  {
    return students.get(index);
  }

  /**
   * Returns number of the Student objects of type guest in the StudentList
   * @return number of the Student objects of type guest in the StudentList
   */
  public int getNumberOfGuests()
  {
    int number = 0;
    for (int i = 0; i < students.size(); i++)
    {
      if (!students.get(i).isAMember())
      {
        number++;
      }
    }
    return number;
  }

  /**
   *Gets all the Student objects of type guest from the StudentList as a Student array
   * @return all the Student objects of type guest from the StudentList as a Student array
   */
  public Student[] getAllGuests()
  {
    Student[] guests = new Student[getNumberOfGuests()];
    int j = 0;
    for (int i = 0; i < students.size(); i++)
    {
      if (!students.get(i).isAMember())
      {
        guests[j] = students.get(i);
        j++;
      }
    }
    return guests;
  }

  /**
   * Returns number of the Student objects of type member in the StudentList
   * @return number of the Student objects of type member in the StudentList
   */
  public int getNumberOfMembers()
  {
    int number = 0;
    for (int i = 0; i < students.size(); i++)
    {
      if (students.get(i).isAMember())
      {
        number++;
      }
    }
    return number;
  }

  /**
   *Gets all the Student objects of type member from the StudentList as a Student array
   * @return all the Student objects of type member from the StudentList as a Student array
   */
  public Student[] getAllMembers()
  {
    Student[] members = new Student[getNumberOfMembers()];
    int j = 0;
    for (int i = 0; i < students.size(); i++)
    {
      if (students.get(i).isAMember())
      {
        members[j] = students.get(i);
        j++;
      }
    }
    return members;
  }

  /**
   * Removes a Student object of type guest given as an argument from the StudentList
   * @param guest the Student object of type guest to be removed from the StudentList
   */
  public void removeGuest(Student guest)
  {
    if (!guest.isAMember())
    {
      students.remove(guest);
    }
  }

  /**
   * Removes a Student object of type member given as an argument from the StudentList
   * @param member the Student object of type member to be removed from the StudentList
   */
  public void removeMember(Student member)
  {
    if (member.isAMember())
    {
      students.remove(member);
    }
  }

  /**
   * Removes a Student object with the given index as an argument from the StudentList
   * @param index index of the Student object to be removed from the StudentList
   */
  public void removeByIndex(int index)
  {
    students.remove(index);
  }

  /**
   * Returns size of the ArrayList containing Student objects
   * @return size of StudentList
   */
  public int size()
  {
    return students.size();
  }

  /**
   * Returns information about all the Student objects contained in the StudentList object
   * @return String that contains information about all the Student objects contained in the StudentList object
   */
  public String toString()
  {
    String returnStr = "";

    for(int i = 0; i<students.size(); i++)
    {
      Student temp = students.get(i);
      returnStr += temp.toString() +"\n";
    }
    return returnStr;
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

      StudentList other = (StudentList) obj;

      for (int i = 0; i < students.size(); i++)
      {
        if (!students.get(i).equals(other.students.get(i)))
        {
          return false;
        }
      }

      return true;
    }
}
