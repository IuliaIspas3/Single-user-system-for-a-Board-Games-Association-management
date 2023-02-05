package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import java.util.Optional;

/**
 * A class operating Manage Students Menu
 * @author Iulia Ispas
 * @version 1.0
 */
public class ManageStudentsController
{
  @FXML Tab addTab;
  @FXML Tab editTab;
  @FXML Tab removeTab;

  @FXML private TextField searchAdd;
  @FXML private TextField firstnameAdd;
  @FXML private TextField lastnameAdd;
  @FXML private TextField ViaIdAdd;
  @FXML private ListView<Student> guestsList;
  @FXML private Button changeStatus;
  @FXML private Button addMember;
  @FXML private MenuBar menuBar;
  @FXML private Menu menuManage;
  @FXML private MenuItem exit;
  @FXML private MenuItem menuBoardGames;
  @FXML private MenuItem menuBorrowing;
  @FXML private MenuItem menuStudents;
  @FXML private MenuItem menuEvents;
  @FXML private MenuItem menuUpcomingGames;

  @FXML private TextField searchEdit;
  @FXML private ListView<Student> studentsList;
  @FXML private TextField firstnameEdit;
  @FXML private TextField lastnameEdit;
  @FXML private TextField ViaIdEdit;
  @FXML private Button changeInformation;

  @FXML private TextField firstnameRemove;
  @FXML private TextField lastnameRemove;
  @FXML private TextField ViaIdRemove;
  @FXML private TextField searchRemove;
  @FXML private ListView<Student> membersList;
  @FXML private Button removeButton;

  private Student student, member, guest;
  private ModelManager modelManager;

  private ViewHandler viewHandler;
  private Scene scene;

  /**
   * Initialize method called in the ViewHandler to initialize the view
   * @param viewHandler the ViewHandler object
   * @param scene the Scene object
   * @param modelManager the ModelManager object
   */
  public void init(ViewHandler viewHandler, Scene scene,ModelManager modelManager){
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;
  }

  /**
   * Returns the Scene object
   * @return the Scene object
   */
  public Scene getScene()
  {
    return scene;
  }

  /**
   * Reset method called in the ViewHandler that updates all ListViews and clears the TextFields when the tab is changed
   */
  public void reset(){
    if (modelManager != null)
    {
      updateGuestsList();
      updateStudentList();
      updateMembersList();
    }
    if (searchAdd != null && searchEdit != null && searchRemove != null)
    {
      searchAdd.clear();
      firstnameAdd.clear();
      lastnameAdd.clear();
      ViaIdAdd.clear();

      searchEdit.clear();
      firstnameEdit.clear();
      lastnameEdit.clear();
      ViaIdEdit.clear();

      searchRemove.clear();
      firstnameRemove.clear();
      lastnameRemove.clear();
      ViaIdRemove.clear();
    }
  }

  /**
   * Initialize method called once the application is run
   */
  public void initialize()
  {
    this.modelManager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "website/xml/eventsWebsite.xml", "website/xml/boardGamesWebsite.xml", "website/xml/upcomingBoardGamesWebsite.xml");
    if(guestsList != null){
      updateGuestsList();
    }
    else if(studentsList != null){
      updateStudentList();
    }
    else if(membersList != null){
      updateMembersList();
    }
  }

  /**
   * Updates ViewLists containing Student objects of type guest
   */
  private void updateGuestsList()
  {
    StudentList guests = modelManager.getAllGuests();
    guestsList.getItems().clear();
    for (int i = 0; i < guests.size(); i++)
    {
      guestsList.getItems().add(guests.getGuestById(i));
    }
  }

  /**
   * Updates ViewLists containing Student objects
   */
  private void updateStudentList()
  {
    StudentList students = modelManager.getAllStudents();
    studentsList.getItems().clear();
    for (int i = 0; i < students.size(); i++)
    {
      studentsList.getItems().add(students.getStudentByIndex(i));
    }
  }

  /**
   * Updates ViewLists containing Student objects of type member
   */
  private void updateMembersList()
  {
    StudentList members = modelManager.getAllMembers();
    membersList.getItems().clear();
    for (int i = 0; i < members.size(); i++)
    {
      membersList.getItems().add(members.getMemberById(i));
    }
  }

  /**
   * Listener that allows the user to search through the lists
   * @param keyEvent the KeyEvent object that tells the application the keyboard interactions
   */
  public void textChangeListener(KeyEvent keyEvent)
  {
    if (keyEvent.getSource() == searchAdd)
    {
      searchAdd.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue)
        {
          guestsList.getItems().clear();
          StudentList guests = modelManager.containGuest(searchAdd.getText());
          for (int i = 0; i < guests.size(); i++)
          {
            if (guests.getGuestById(i) != null) {
              guestsList.getItems().add(guests.getGuestById(i));
            }
          }
        }
      });
    }
    else if (keyEvent.getSource() == searchEdit)
    {
      searchEdit.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue)
        {
          studentsList.getItems().clear();
          StudentList students = modelManager.containStudents(searchEdit.getText());
          for (int i = 0; i < students.size(); i++)
          {
            studentsList.getItems().add(students.getStudentByIndex(i));
          }
        }
      });
    }
    else if (keyEvent.getSource() == searchRemove)
    {
      searchRemove.textProperty().addListener(new ChangeListener()
      {
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue)
        {
          membersList.getItems().clear();
          StudentList members = modelManager.containMember(searchRemove.getText());
          for (int i = 0; i < members.size(); i++)
          {
            membersList.getItems().add(members.getMemberById(i));
          }
        }
      });
    }
  }

  /**
   * Handles all user interactions
   * @param e the ActionEvent object that tells the application user interactions
   */
  public void handleAction(ActionEvent e)
  {
    if(e.getSource() == menuBoardGames){
      viewHandler.openView("ManageBoardGames");
    }
    if(e.getSource() == menuBorrowing){
      viewHandler.openView("ManageBorrowing");
    }
    if(e.getSource() == menuStudents){
      viewHandler.openView("ManageStudents");
    }
    if(e.getSource() == menuEvents){
      viewHandler.openView("ManageEvents");
    }
    if(e.getSource() == menuUpcomingGames){
      viewHandler.openView("ManageUpcomingBoardGames");
    }
    if (e.getSource() == exit)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to exit?", ButtonType.YES, ButtonType.CANCEL);
      alert.setHeaderText(null);
      Optional<ButtonType> result = alert.showAndWait();
      if(result.get() == ButtonType.YES)
      {
        System.exit(0);
      }
    }
    if (e.getSource() == changeStatus)
    {
      Student guest = guestsList.getSelectionModel().getSelectedItem();
      StudentList students = modelManager.getAllStudents();
      BoardGamesList boardGamesList = modelManager.getAllBoardGames();
      if (this.guest != null)
      {

        for (int i = 0; i < students.size(); i++)
        {
          if (students.getStudentByIndex(i)!=null && students.getStudentByIndex(i).equals(guest))
          {
            if(boardGamesList.isABorrower(guest) || boardGamesList.isAnOwner(guest))
            {
            for (int j = 0; j < boardGamesList.size(); j++)
              {
                if (boardGamesList.getBoardGameByIndex(j).isLent() && boardGamesList.getBoardGameByIndex(j).getBorrower().equals(guest))
                {
                  boardGamesList.getBoardGameByIndex(j).getBorrower().setAMember();
                }
                if (boardGamesList.getBoardGameByIndex(j).getOwner() != null && boardGamesList.getBoardGameByIndex(j).getOwner().equals(guest))
                {
                  boardGamesList.getBoardGameByIndex(j).getOwner().setAMember();
                }
              }
            }
            students.getStudentByIndex(i).setAMember();
          }
        }
        searchAdd.clear();
        firstnameAdd.clear();
        lastnameAdd.clear();
        ViaIdAdd.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("You change guest : "+guest+" to member.");
        alert.show();
        modelManager.saveAllGames(boardGamesList);
        modelManager.saveAllStudents(students);
        updateGuestsList();
        updateStudentList();
        updateMembersList();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("You did not select any guest from the list. Try again.");
        alert.show();
      }
    }
    else if (e.getSource() == addMember)
    {
      StudentList students = modelManager.getAllStudents();
      if (!firstnameAdd.getText().equals("") && !lastnameAdd.getText().equals("") && !ViaIdAdd.getText().equals(""))
      {
        String firstName = firstnameAdd.getText();
        String lastName = lastnameAdd.getText();
        int ViaId = 0;
        try
        {
          ViaId = Integer.parseInt(ViaIdAdd.getText());
        }
        catch (IllegalArgumentException exception)
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("VIA ID can only contain digits.");
          alert.show();
          return;
        }

        this.member = null;

        try
        {
          this.member = new Student(firstName, lastName, ViaId);
          this.member.setAMember();
        }
        catch (IllegalArgumentException exception)
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);
          alert.setContentText("Entered VIA ID has to have 6 digits.");
          alert.show();
          return;
        }

        if (students.memberExists(member))
          {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Same data.");
            alert.show();
          }
          if(students.sameID(member)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Same id.");
            alert.show();
          }else{
            modelManager.addMember(member);
          }
          updateStudentList();
          updateMembersList();
        searchAdd.clear();
        firstnameAdd.clear();
        lastnameAdd.clear();
        ViaIdAdd.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("The member has been added.");
        alert.show();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Error, make sure you entered the information.");
        alert.show();
      }

    }
    else if (e.getSource() == changeInformation)
    {
      BoardGamesList games = modelManager.getAllBoardGames();
      Student student = studentsList.getSelectionModel().getSelectedItem();
      StudentList students = modelManager.getAllStudents();
      if (student != null)
      {
        Student temp = new Student(student.getFirstName(), student.getLastName(), student.getVIAID());
        for (int i = 0; i < students.size(); i++)
        {
          if (students.getStudentByIndex(i)!=null && students.getStudentByIndex(i).equals(student))
          {
            student = students.getStudentByIndex(i);
            if (!firstnameEdit.getText().equals("") && !lastnameEdit.getText().equals("") && !ViaIdEdit.getText().equals(""))
            {
              String firstName = firstnameEdit.getText();
              String lastName = lastnameEdit.getText();
              int ViaId = 0;
              try
              {
                ViaId = Integer.parseInt(ViaIdEdit.getText());
              }
              catch (IllegalArgumentException exception)
              {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("VIA ID can only contain digits.");
                alert.show();
                return;
              }
              try
              {
                student.setVIAID(ViaId);
                student.setFirstName(firstName);
                student.setLastName(lastName);
              }
              catch (IllegalArgumentException exception)
              {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Entered VIA ID has to have 6 digits.");
                alert.show();
                return;
              }
              searchEdit.clear();
              firstnameEdit.clear();
              lastnameEdit.clear();
              ViaIdEdit.clear();
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setHeaderText(null);
              alert.setContentText("Updated.");
              alert.show();
          }
            else
             {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Error, make sure you entered the information.");
                alert.show();
             }
        }
      }
        EventList events = modelManager.getAllEvents();
        for (int i = 0; i < games.size(); i++)
        {
          if (games.getBoardGameByIndex(i).getOwner().equalsWithoutMembership(temp))
          {
            games.getBoardGameByIndex(i).setOwner(student);
          }
          if (games.getBoardGameByIndex(i).getBorrower() != null && games.getBoardGameByIndex(i).getBorrower().equalsWithoutMembership(temp))
          {
            games.getBoardGameByIndex(i).setBorrower(student);
          }
          Student[] reservants = games.getBoardGameByIndex(i).getAllReservants();
          for (int j = 0; j < reservants.length; j++)
          {
            if (reservants[j].equalsWithoutMembership(temp))
            {
              reservants[j] = student;
            }
          }
          for (int j = 0; j < events.size(); j++)
          {
            if (events.getEventByIndex(j).isAParticipant(temp))
            {
              StudentList participants = events.getEventByIndex(j).getParticipants();
              for (int k = 0; k < participants.size(); k++)
              {
                if (participants.getStudentByIndex(k).equalsWithoutMembership(temp))
                {
                  participants.removeByIndex(k);
                  participants.addStudent(student);
                }
              }
            }
          }
        }
        modelManager.saveAllGames(games);
        modelManager.saveAllEvents(events);
        modelManager.saveAllBoardGamesXML(games);
        modelManager.saveAllEventsXML(events);
        modelManager.saveAllStudents(students);
        updateStudentList();
      }
      else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("No student has been selected.");
        alert.show();
      }
    }
    else if (e.getSource() == removeButton)
    {
      StudentList students = modelManager.getAllStudents();
      BoardGamesList boardGamesList = modelManager.getAllBoardGames();
      EventList eventList = modelManager.getAllEvents();
      if (member!=null && !boardGamesList.isABorrower(member) && !boardGamesList.isAnOwner(member) && !boardGamesList.isAReservant(member))
      {
        students.removeMember(member);
        modelManager.saveAllStudents(students);
        updateMembersList();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Member has been removed.");
        alert.showAndWait();
      }
      else if (member == null)
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("No member has been selected.");
        alert.showAndWait();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("The members is a current borrower, owner or a reservant.");
        alert.show();
      }
      firstnameRemove.clear();
      lastnameRemove.clear();
      ViaIdRemove.clear();
      modelManager.saveAllStudents(students);
      updateStudentList();
      updateMembersList();
    }
  }

  /**
   * Listener that listens to user interactions in the ListView objects
   * @param e the MouseEvent object that tells the application about user interactions
   */
  public void listChangeListener(MouseEvent e)
  {
    if(e.getSource() == guestsList){
      if(guestsList.getSelectionModel().getSelectedItem() != null){
        this.guest = guestsList.getSelectionModel().getSelectedItem();
      }

    }
    if (e.getSource() == studentsList)
    {
      this.student = (Student) studentsList.getSelectionModel().getSelectedItem();
      if (student!=null)
      {
        firstnameEdit.setText(student.getFirstName());
        lastnameEdit.setText(student.getLastName());
        ViaIdEdit.setText(String.valueOf(student.getVIAID()));
      }
    }
    else if (e.getSource() == membersList)
    {
      this.member = (Student) membersList.getSelectionModel().getSelectedItem();
      if (member!=null)
      {
        firstnameRemove.setText(member.getFirstName());
        lastnameRemove.setText(member.getLastName());
        ViaIdRemove.setText(String.valueOf(member.getVIAID()));
      }
    }
  }
}
