package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class containing the Date object.
 * @author Iulia Ispas
 * @version 1.0
 */

public class Date implements Serializable
{
  private int day, month, year;

  /**
   * The constructor initializing the Date object
   * @param day the day to be set to the Date object
   * @param month the month to be set to the Date object
   * @param year the year to be set to the Date object
   */
  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Sets the day of the Date object
   * @param day the day to be set to the mDate object
   */
  public void setDay(int day)
  {
    this.day = day;
  }

  /**
   * Sets the month of the Date object
   * @param month the month to be set to the Date object
   */
  public void setMonth(int month)
  {
    this.month = month;
  }

  /**
   * Sets the year of the Date object
   * @param year the year to be set to the Date object
   */
  public void setYear(int year)
  {
    this.year = year;
  }

  /**
   * Gets the day of the Date object
   * @return day the day of the Date object
   */
  public int getDay()
  {
    return day;
  }

  /**
   * Gets the month of the Date object
   * @return month the month of the Date object
   */
  public int getMonth()
  {
    return month;
  }

  /**
   * Gets the year of the Date object
   * @return year the year of the Date object
   */
  public int getYear()
  {
    return year;
  }

  /**
   * Gets the current date
   * @return the current date
   */
  public static Date getTodaysDate()
  {
    LocalDate currentDate = LocalDate.now();
    int currentDay = currentDate.getDayOfMonth();
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();
    Date today = new Date(currentDay, currentMonth, currentYear);
    return today;
  }

  /**
   * Verifies if the Date object given as a parameter is before the current date
   * @param date the Date object which needs to be checked
   * @return if the Date object is before the current date or not
   */
  public boolean isBefore(Date date)
  {
    Date today = getTodaysDate();
    if (year < date.getYear())
    {
      return true;
    }
    else if (year == date.getYear())
    {
      if (month < date.getMonth())
      {
        return true;
      }
      else if (month == date.getMonth())
      {
        if (day < date.getDay())
        {
          return true;
        }
        else
        {
          return false;
        }
      }
    }
    return false;
  }

  /**
   * Creates a copy for a Date object
   * @return a copy of a Date object
   */
  public Date copy()
  {
    return new Date(day, month, year);
  }

  /**
   *
   * @param obj the object given to compare with the current Date object
   * @return if the Date objects are equal or not
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass()!=obj.getClass())
      return false;
    Date other = (Date) obj;
    return day == other.day && month == other.month && year == other.year;
  }

  /**
   * Returns the Date object as a string
   * @return the Date object as a string
   */
  public String toString()
  {
    return day + "/" + month + "/" + year;
  }
}
