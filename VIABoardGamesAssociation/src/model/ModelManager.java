package model;

import parser.ParserException;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class creating ModelManager objects
 * @author Dominika Janczyszyn
 * @author Oliwier Wijas
 * @version 1.0
 */
public class ModelManager
{
  private String upcomingBoardGameFile;
  private String boardGamesFile;
  private String studentFile;
  private String eventFile;
  private String eventsWebsiteFile;
  private String boardGamesWebsiteFile;
  private String upcomingBoardGamesWebsiteFile;

  /**
   * Constructor initializing all files
   * @param upcomingBoardGameFile the binary file containing UpcomingBoardGame objects
   * @param boardGamesFile the binary file containing BoardGame objects
   * @param studentFile the binary file containing Student objects
   * @param eventFile the binary file containing Event objects
   * @param eventsWebsiteFile the XML file containing Event objects
   * @param boardGamesWebsiteFile the XML file containing BoardGame objects
   * @param upcomingBoardGamesWebsiteFile the XML file containing UpcomingBoardGame objects
   */
  public ModelManager(String upcomingBoardGameFile, String boardGamesFile, String studentFile, String eventFile, String eventsWebsiteFile, String boardGamesWebsiteFile, String upcomingBoardGamesWebsiteFile){
    this.upcomingBoardGameFile = upcomingBoardGameFile;
    this.boardGamesFile = boardGamesFile;
    this.studentFile = studentFile;
    this.eventFile = eventFile;
    this.eventsWebsiteFile = eventsWebsiteFile;
    this.boardGamesWebsiteFile = boardGamesWebsiteFile;
    this.upcomingBoardGamesWebsiteFile = upcomingBoardGamesWebsiteFile;
  }

  /**
   * Gets all UpcomingBoardGame objects from the binary file and creates UpcomingBoardGamesList object
   * @return UpcomingBoardGamesList object with all read objects from the binary file
   */
  public UpcomingBoardGamesList getAllUpcomingGames(){
    UpcomingBoardGamesList upcomingBoardGames = new UpcomingBoardGamesList();

    try
    {
      upcomingBoardGames = (UpcomingBoardGamesList) MyFileHandler.readFromBinaryFile(upcomingBoardGameFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return upcomingBoardGames;
  }

  /**
   * Saves all UpcomingBoardGames objects from the UpcomingBoardGamesList object to the binary file
   * @param upcomingBoardGames the UpcomingBoardGameList object that contains UpcomingBoardGames objects
   */
  public void saveAllUpcomingGames(UpcomingBoardGamesList upcomingBoardGames)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(upcomingBoardGameFile, upcomingBoardGames);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }


  /**
   * Gets all BoardGame objects from the binary file and creates BoardGamesList object
   * @return BoardGamesList object with all read objects from the binary file
   */
  public BoardGamesList getAllBoardGames(){
    BoardGamesList boardGames = new BoardGamesList();

    try
    {
      boardGames = (BoardGamesList)MyFileHandler.readFromBinaryFile(boardGamesFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return boardGames;
  }
  /**
   * Saves all BoardGame objects from the BoardGamesList object to the binary file
   * @param boardGames the BoardGameList object that contains BoardGames objects
   */
  public void saveAllGames(BoardGamesList boardGames)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(boardGamesFile, boardGames);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  /**
   * Gets all Student objects from the binary file and creates StudentList object
   * @return StudentList object with all read objects from the binary file
   */
  public StudentList getAllStudents(){
    StudentList studentList = new StudentList();

    try
    {
      studentList = (StudentList) MyFileHandler.readFromBinaryFile(studentFile);

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return studentList;
  }

  /**
   * Gets all Student objects from the binary file and creates StudentList object with the Student object of type guest
   * @return StudentList object with all read Student objects of type guest from the binary file
   */
  public StudentList getAllGuests(){
    StudentList studentList = getAllStudents();

    Student[] temp = studentList.getAllGuests();
    StudentList guests = new StudentList();
    for (int i = 0; i < temp.length; i++)
    {
      guests.addGuest(temp[i]);
    }
    return guests;
  }

  /**
   * Gets all Student objects from the binary file and creates StudentList object with the Student object of type member
   * @return StudentList object with all read Student objects of type member from the binary file
   */
  public StudentList getAllMembers(){
    StudentList studentList = getAllStudents();

    Student[] temp = studentList.getAllMembers();
    StudentList members = new StudentList();
    for (int i = 0; i < temp.length; i++)
    {
      members.addMember(temp[i]);
    }
    return members;
  }

  /**
   * Saves all Student objects from the StudentList object to the binary file
   * @param studentList the StudentList object that contains Student objects
   */
  public void saveAllStudents(StudentList studentList)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(studentFile, studentList);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  /**
   * Gets all Event objects from the binary file and creates EventList object
   * @return EventList object with all read objects from the binary file
   */
public EventList getAllEvents(){
  EventList eventsList = new EventList();

  try
  {
    eventsList = (EventList) MyFileHandler.readFromBinaryFile(eventFile);

  }
  catch (FileNotFoundException e)
  {
    System.out.println("File not found");
  }
  catch (IOException e)
  {
    System.out.println("IO Error reading file");
  }
  catch (ClassNotFoundException e)
  {
    System.out.println("Class Not Found");
  }
  return eventsList;
}
  /**
   * Saves all Event objects from the EventList object to the binary file
   * @param eventsList the EventList object that contains Event objects
   */
  public void saveAllEvents(EventList eventsList)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(eventFile, eventsList);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  /**
   * Returns StudentList object with the Student objects that contain the given String
   * @param text the text that each Student object has to contain
   * @return the StudentList object with the Student objects that contain the given String
   */
  public StudentList containStudents(String text){
    StudentList allStudents = new StudentList();
    StudentList containStudent = new StudentList();

    try
    {
      allStudents = (StudentList)MyFileHandler.readFromBinaryFile(studentFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");

    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }

    for(int i = 0 ; i < allStudents.size(); i++){
      String student = allStudents.getStudentByIndex(i).toString();
      if(student.contains(text)){
        containStudent.addStudent(allStudents.getStudentByIndex(i));
      }
    }



    return containStudent;
  }

  /**
   * Returns BoardGamesList object with the BoardGame objects that contain the given String
   * @param text the text that each BoardGame object has to contain
   * @return the BoardGamesList object with the BoardGame objects that contain the given String
   */
  public BoardGamesList containGame(String text){

    BoardGamesList allGames = new BoardGamesList();
    BoardGamesList containGame = new BoardGamesList();

    try
    {
      allGames = (BoardGamesList) MyFileHandler.readFromBinaryFile(boardGamesFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");

    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }


    for(int i = 0 ; i < allGames.size(); i++){
      String games = allGames.getBoardGameByIndex(i).toString();
      if(games.contains(text)){
        containGame.addBoardGame(allGames.getBoardGameByIndex(i));
      }
    }
    return containGame;
  }

  /**
   * Returns UpcomingBoardGamesList object with the UpcomingBoardGame objects that contain the given String
   * @param text the text that each UpcomingBoardGame object has to contain
   * @return the UpcomingBoardGamesList object with the UpcomingBoardGame objects that contain the given String
   */
  public UpcomingBoardGamesList containUpcomingBoardGame (String text)
  {
    UpcomingBoardGamesList allBoardGames = new UpcomingBoardGamesList();
    UpcomingBoardGamesList containBoardGame = new UpcomingBoardGamesList();

    allBoardGames = getAllUpcomingGames();
    for (int i = 0; i < allBoardGames.size(); i++)
    {
      String boardGame = allBoardGames.getUpcomingBoardGameByIndex(i).toString();
      if (boardGame.contains(text))
      {
        containBoardGame.addUpcomingGame(getAllUpcomingGames().getUpcomingBoardGameByIndex(i));
      }
    }
    return containBoardGame;
  }

  /**
   * Adds the Event object to the binary file
   * @param event the Event object to be added to the binary file
   */
  public void addEvent(Event event)
  {
    EventList allEvents = getAllEvents();

    allEvents.addEvent(event);

    saveAllEvents(allEvents);
  }

  /**
   * Removes the Event object from the binary file
   * @param event the Event object to be removed from the binary file
   */
  public void removeEvent(Event event)
  {
    EventList allEvents = getAllEvents();

    allEvents.removeEvent(event);

    saveAllEvents(allEvents);
  }

  /**
   * Removes the Student object from the binary file
   * @param student the Student object to be removed from the binary file
   */
  public void removeStudent(Student student)
  {
    StudentList allStudents = getAllStudents();

    allStudents.removeGuest(student);

    saveAllStudents(allStudents);
  }

  /**
   * Returns the StudentList object conatining all participants of the given Event object
   * @param event the Event object with participants
   * @return the StudentList object with all participants of the given Event object
   */
  public StudentList getAllParticipants(Event event)
  {
    return event.getParticipants();
  }

  /**
   * Adds the given Student object to the given Event object's participants list
   * @param student the student to be registered for the event
   * @param event the event for which student will be registered
   */
  public void registerForAnEvent(Student student, Event event)
  {
    EventList allEvents = getAllEvents();

    for (int i = 0; i < allEvents.size(); i++)
    {
      if (allEvents.getEventByIndex(i).equals(event))
      {
        allEvents.getEventByIndex(i).addParticipant(student);
        break;
      }
    }

    saveAllEvents(allEvents);
  }

  /**
   * Adds the student object to the binary file
   * @param student the Student object to be added to the binary file
   */
  public void addStudent(Student student)
  {
    StudentList allStudents = getAllStudents();

    allStudents.addStudent(student);

    saveAllStudents(allStudents);
  }

  /**
   * Returns EventList object with the Event objects that contain the given String
   * @param text the text that each Event object has to contain
   * @return the EventList object with the Event objects that contain the given String
   */
  public EventList containEvent(String text)
  {
    EventList allEvents = new EventList();
    EventList containEvent = new EventList();

    allEvents = getAllEvents();

    for (int i = 0; i < allEvents.size(); i++)
    {
      String event = allEvents.getEventByIndex(i).toString();
      if (event.contains(text))
      {
        containEvent.addEvent(allEvents.getEventByIndex(i));
      }
    }
    return containEvent;
  }

  /**
   * Saves all Event objects from the EventList object to the XML file
   * @param eventsList the EventList object that contains Event objects
   */
  public void saveAllEventsXML(EventList eventsList)
  {
    try
    {
      MyFileHandler.writeToXMLFile(eventsWebsiteFile, eventsList);
    }
    catch (ParserException e)
    {
      System.out.println("Parser Error");
    }
  }

  /**
   * Adds the BoardGame object to the binary file
   * @param boardGame the BoardGame object to be added to the binary file
   */
  public void addBoardGame(BoardGame boardGame)
  {
    BoardGamesList allBoardGames = getAllBoardGames();

    allBoardGames.addBoardGame(boardGame);

    saveAllGames(allBoardGames);
  }

  /**
   * Returns StudentList object with the Student objects of type guest that contain the given String
   * @param text the text that each Student object of type guest has to contain
   * @return the StudentList object with the Student objects of type guest that contain the given String
   */
  public StudentList containGuest (String text)
  {
    StudentList allGuests = getAllGuests();
    StudentList containGuest = new StudentList();

    for (int i = 0; i < allGuests.size(); i++)
    {
      if (allGuests.getGuestById(i) != null)
      {
        String guest = allGuests.getGuestById(i).toString();
        if (guest.contains(text))
        {
          containGuest.addGuest(allGuests.getGuestById(i));
        }
      }
    }
    return containGuest;
  }

  /**
   * Adds a member to the binary file
   * @param member the Student of type member to be added to the binary file
   */
  public void addMember(Student member)
  {
    StudentList students = getAllStudents();
    if (member.isAMember())
    {
      students.addMember(member);
    }
    saveAllStudents(students);
  }

  /**
   * Adds a guest to the binary file
   * @param guest the Student object of type guest to be added to the binary file
   */
  public void addGuest(Student guest)
  {
    StudentList students = getAllStudents();
    if (!guest.isAMember())
    {
      students.addGuest(guest);
    }
    saveAllStudents(students);
  }

  /**
   * Returns StudentList object with the Student objects of type member that contain the given String
   * @param text the text that each Student object of type member has to contain
   * @return the StudentList object with the Student objects of type member that contain the given String
   */
  public StudentList containMember (String text)
  {
    StudentList allMembers = getAllMembers();
    StudentList containMember = new StudentList();

    for (int i = 0; i < allMembers.size(); i++)
    {
      if (allMembers.getMemberById(i) != null)
      {
        String member = allMembers.getMemberById(i).toString();
        if (member.contains(text))
        {
          containMember.addMember(allMembers.getMemberById(i));
        }
      }
    }
    return containMember;
  }

  /**
   * Saves all BoardGame objects from the BoardGamesList object to the XML file
   * @param boardGamesList the BoardGamesList object that contains BoardGame objects
   */
  public void saveAllBoardGamesXML(BoardGamesList boardGamesList)
  {
    try
    {
      MyFileHandler.writeToXMLFile(boardGamesWebsiteFile, boardGamesList);
    }
    catch (ParserException e)
    {
      System.out.println("Parser Error");
    }
  }

  /**
   * Saves all UpcomingBoardGame objects from the UpcomingBoardGamesList object to the XML file
   * @param upcomingBoardGamesList the UpcomingBoardGamesList object that contains UpcomingBoardGame objects
   */
  public void saveAllUpcomingBoardGamesXML(UpcomingBoardGamesList upcomingBoardGamesList)
  {
    try
    {
      MyFileHandler.writeToXMLFile(upcomingBoardGamesWebsiteFile, upcomingBoardGamesList);
    }
    catch (ParserException e)
    {
      System.out.println("Parser Error");
    }
  }
}
