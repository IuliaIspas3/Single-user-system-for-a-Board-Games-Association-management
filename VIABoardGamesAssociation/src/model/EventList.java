package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Event objects
 * @author Iulia Ispas
 * @version 1.0
 */


public class EventList implements Serializable
{
  private ArrayList<Event> events;

  /**
   * The no-argument constructor initializing the Event objects list
   */
  public EventList()
  {
    events = new ArrayList<Event>();
  }


  /**
   * Adds the Event object to the EventsList
   * @param event the event to be added in the EventsList
   */
  public void addEvent(Event event)
  {
    events.add(event);
  }


  /**
   * Gets the Event object from the EventsList using the name given as an argument
   * @param name the name of the Event object
   * @return the event with the given name
   */
  public Event getEvent(String name)
  {
    for (int i = 0; i < events.size(); i++)
    {
      if (events.get(i).getName().equals(name))
      {
        return events.get(i);
      }
    }
    return null;
  }


  /**
   * Gets all the Event objects from the EventsList as an Event array
   * @return all the Event objects from the EventsList as an Event array
   */
  public Event[] getAllEvents()
  {
    Event[] event = new Event[events.size()];
    return events.toArray(event);
  }


  /**
   * Gets all the Event objects that have passed in an Event array
   * @return an Event array with all the Event objects that have passed
   */
  public Event[] getAllPastEvents()
  {
    Event[] event = new Event[events.size()];
    ArrayList<Event> pastevents = new ArrayList<Event>();
    for (int i = 0; i < events.size(); i++)
    {
      if (events.get(i).pastEvent())
      {
        pastevents.add(events.get(i));
      }
    }
    return pastevents.toArray(event);
  }


  /**
   * Gets all the Event objects that are going to happen in the future
   * @return an Event array with all the Event objects that are going to happen in the future
   */
  public Event[] getAllFutureEvents()
  {
    Event[] event = new Event[events.size()];
    ArrayList<Event> futureevents = new ArrayList<Event>();
    for (int i = 0; i < events.size(); i++)
    {
      if (!events.get(i).pastEvent())
      {
        futureevents.add(events.get(i));
      }
    }
    return futureevents.toArray(event);
  }


  /**
   * Removes an Event object given as an argument from the EventsList
   * @param event the Event object to be removed from the EventsList
   */
  public void removeEvent(Event event)
  {
    for (int i = 0; i < events.size(); i++)
    {
      if (events.get(i).equals(event))
      {
        events.remove(events.get(i));
      }
    }
  }

  /**
   * Checks if the given Student object is a participant of any Event object
   * @param student the Student object given to be checked if is a participant
   * @return the boolean value of checking if the given student is a participant of any Event object
   */
  public boolean isAParticipant(Student student)
  {
    for (int i = 0; i < events.size(); i++)
    {
      if (events.get(i).isAParticipant(student))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the Event object with the given index
   * @param index the index of the Event object
   * @return the Event object with the given index
   */
  public Event getEventByIndex(int index)
  {
    return events.get(index);
  }
  /**
   * Returns the size of the EventsList
   * @return the size of the EventsList
   */
  public int size()
  {
    return events.size();
  }

  /**
   * Returns the information about each Event object from the EventsList as a String
   * @return the String with the information about each Event object from the EventsList
   */
  public String toString()
  {
    String list = "";
    for (int i = 0; i < events.size(); i++)
    {
      list += events.get(i).toString() + "\n";
    }
    return list;
  }
}
