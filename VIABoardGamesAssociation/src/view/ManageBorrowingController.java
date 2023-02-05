package view;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.StudentList;
import model.ModelManager;
import model.BoardGamesList;
import model.*;
import javafx.scene.control.RadioButton;

import java.util.Optional;

/**
 * A class operating Manage Borrowing Menu
 * @author Dominika Janczyszyn
 * @version 1.0
 */
public class ManageBorrowingController{
    @FXML private Tab borrowTab, editTab, returnTab, reserveTab, cancelTab, infoTab;
    @FXML private MenuBar menuBar;
    @FXML private Menu menuManage;
    @FXML private MenuItem exit;
    @FXML private MenuItem menuBoardGames;
    @FXML private MenuItem menuBorrowing;
    @FXML private MenuItem menuStudents;
    @FXML private MenuItem menuEvents;
    @FXML private MenuItem menuUpcomingGames;
    /// LEND TAB///
    @FXML private TextField gameTextField, studentTextField, nameTextField2, lastNameTextField2, idTextField2, lendingTextField;
    @FXML private ListView<BoardGame> gameListView;
    @FXML private ListView<Student> studentListView;
    @FXML private Button borrowButton;
    //// EDIT RESERVATION TAB ////
    @FXML private TextField gameTextFieldEdit, titleTextFieldEdit, borrowerTextFieldEdit, memberTextFieldEdit, nameTextFieldEdit, lastNameTextFieldEdit, idTextFieldEdit ;
    @FXML private ListView<BoardGame> gameListViewEdit;
    @FXML private ListView<Student> memberViewListEdit;
    @FXML private Button changeButton;
    @FXML private RadioButton radio1, radio2, radio3, radio4, radio5;


    /// RESERVE TAB ///
    @FXML private TextField gameTextFieldReserve, studentTextFieldReserve;
    @FXML private ListView<BoardGame>gameListViewReserve;
    @FXML private ListView<Student> studentListViewReserve;
    @FXML private Button reserveButton;
    ////CANCEL RESERVATION TAB/////
    @FXML private ListView<Student> studentListViewCancel;
    @FXML private TextField studentTextFieldCancel, gameTextFieldCancel;
    @FXML private ListView<BoardGame> reservationListView;
    @FXML private Button cancelButton;

    ///////RETURN TAB//////////////
    @FXML private TextField gameTextFieldReturn, titleTextFieldReturn, borrowerTextFieldReturn;
    @FXML private ListView<BoardGame> gameListViewReturn;
    @FXML private Button returnButton;
    //////INFO TAB//////////
    @FXML TableView<BoardGame> tab;
    @FXML
    TableColumn <BoardGame, String>titleColl;
    @FXML
    TableColumn<BoardGame, String> borrowerColl;
    @FXML
    TableColumn<BoardGame, String> reservantColl;
    @FXML TableColumn<BoardGame, String> dateColl;
    @FXML TableColumn<BoardGame, Double> ratingColl;
    private ModelManager manager;
    private BoardGamesList boardGamesList;
    private StudentList studentList;
    private BoardGame boardGame;
    private Student student;
    private ViewHandler viewHandler;
    private Scene scene;
    private EventList eventList;

    /**
     * Initialize method called in the ViewHandler to initialize the view
     * @param viewHandler the ViewHandler object
     * @param scene the Scene object
     * @param modelManager the ModelManager object
     */
    public void init(ViewHandler viewHandler, Scene scene, ModelManager modelManager){
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.manager = modelManager;

    }

    /**
     * Reset method called in the ViewHandler that updates all ListViews when the tab is changed
     */
    public void reset()
    {
        updateStudentArea1(studentList, studentListView);
        updateNotLendGamesArea(boardGamesList, gameListView);
        updateStudentArea1(studentList, memberViewListEdit);
        updateLentBoardGames(boardGamesList, gameListViewEdit);
        updateLentBoardGames(boardGamesList, gameListViewReturn);
        updateLentBoardGames(boardGamesList, gameListViewReserve);
        updateStudentArea2(studentList, studentListViewReserve);
        updateStudentArea2(studentList, studentListViewCancel);
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
     * Updates the information once the tab is changed
     * @param event the Event object
     */
    public void tabChanger(Event event) {
        this.manager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "website/xml/eventsWebsite.xml", "website/xml/boardGamesWebsite.xml", "website/xml/upcomingBoardGamesWebsite.xml");
        this.boardGamesList = manager.getAllBoardGames();
        this.studentList = manager.getAllStudents();
        this.eventList = manager.getAllEvents();
        if(borrowTab.isSelected()){
            updateStudentArea1(studentList, studentListView);
            updateNotLendGamesArea(boardGamesList, gameListView);
            gameTextField.setText("");
            studentTextField.setText("");
            nameTextField2.setText("");
            lastNameTextField2.setText("");
            idTextField2.setText("");
        }
        else if(editTab.isSelected()){
            updateLentBoardGames(boardGamesList, gameListViewEdit);
            updateStudentArea1(studentList, memberViewListEdit);
            titleTextFieldEdit.clear();
            borrowerTextFieldEdit.clear();
            memberTextFieldEdit.clear();
            gameTextFieldEdit.clear();
            nameTextFieldEdit.clear();
            lastNameTextFieldEdit.clear();
            idTextFieldEdit.clear();
        }
        else if(reserveTab.isSelected()){
            updateStudentArea2(studentList, studentListViewReserve);
            updateLentBoardGames(boardGamesList, gameListViewReserve);
            gameTextFieldReserve.clear();
            studentTextFieldReserve.clear();

        }
        else if(cancelTab.isSelected()){
            updateStudentArea2(studentList, studentListViewCancel);
        }
        else if(returnTab.isSelected()){
            updateLentBoardGames(boardGamesList, gameListViewReturn);
            gameTextFieldReturn.clear();
            titleTextFieldReturn.clear();
            borrowerTextFieldReturn.clear();
        }
        else if(infoTab.isSelected()){
            tab.getItems().clear();
            titleColl.setCellValueFactory(new PropertyValueFactory<>("name"));
            borrowerColl.setCellValueFactory(new PropertyValueFactory<>("borrower"));
            reservantColl.setCellValueFactory(new PropertyValueFactory<>("reservants"));
            dateColl.setCellValueFactory(new  PropertyValueFactory<>("dateOfLoan"));
            ratingColl.setCellValueFactory(new PropertyValueFactory<>("rating"));
            for(int i = 0; i < boardGamesList.size(); i++){
                tab.getItems().add(boardGamesList.getBoardGameByIndex(i));
            }
        }

    }

    /**
     * Updates the listView objects adding to them the Student object of type member and the Student objects that are not borrowers of any BoardGame object
     * @param students the StudentList from which the data is taken
     * @param listView the ListView which will display the Student objects of type member and the Student objects that are not borrowers of any BoardGame object
     */
    private void updateStudentArea1(StudentList students, ListView listView) {
        listView.getItems().clear();
        for (int i = 0; i < students.size(); i++) {
            if(students.getStudentByIndex(i).isAMember()){
                listView.getItems().add(students.getStudentByIndex(i));
            }
            else if(!boardGamesList.isABorrower(students.getStudentByIndex(i))){
                listView.getItems().add(students.getStudentByIndex(i));
            }
        }
    }

    /**
     * Updates the listView objects adding to them the Student object of type member
     * @param students the StudentList from which the data is taken
     * @param listView the ListView which will display the Student objects of type member
     */
    private void updateStudentArea2(StudentList students, ListView listView) {
        listView.getItems().clear();
        for (int i = 0; i < students.size(); i++) {
            if(students.getStudentByIndex(i).isAMember()){
                listView.getItems().add(students.getStudentByIndex(i));
            }
        }
    }

    /**
     * Updates the ListView objects adding to them BoardGame objects that are not lent
     * @param games the BoardGamesList from which the data is taken
     * @param listView the ListView which will display the BoardGame objects that are not lent
     */
    private void updateNotLendGamesArea(BoardGamesList games, ListView listView){
        listView.getItems().clear();
        for(int i = 0; i < games.size(); i++){
            if(games.getBoardGameByIndex(i) != null && !games.getBoardGameByIndex(i).isLent()){
                listView.getItems().add(games.getBoardGameByIndex(i));
            }
        }
    }

    /**
     * Updates the ListView objects adding to them BoardGame objects that are lent
     * @param games the BoardGamesList from which the data is taken
     * @param listView the ListView which will display the BoardGame objects that are lent
     */
    private void updateLentBoardGames(BoardGamesList games, ListView listView){
        listView.getItems().clear();
        for(int i = 0; i < games.size(); i++){
            if(games.getBoardGameByIndex(i).isLent()){
                listView.getItems().add(games.getBoardGameByIndex(i));
            }
        }
    }

    /**
     * Updates the ListView objects adding to them BoardGame objects that are reserved
     * @param games the BoardGamesList from which the data is taken
     * @param listView the ListView which will display the BoardGame objects that are reserved
     */
    private void updateReservedBardGamesArea(BoardGamesList games, ListView listView, Student student){
        listView.getItems().clear();
        for (int i = 0; i < games.size(); i++){
            Student[] reservants = games.getBoardGameByIndex(i).getAllReservants();
            for(int j = 0; j < reservants.length; j++){
                Student student1 = reservants[j];
                if(student1.equals(student)){
                    listView.getItems().add(games.getBoardGameByIndex(i));
                }
            }
        }
    }

    /**
     * Listeners that tells the application about user interactions with all buttons
     * @param e the ActionEvent object that tells the application user interactions
     */
    public void buttonListener(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (e.getSource() == borrowButton) {
            if(student != null && boardGame!=null){
                String person = student.toString();
                String game = boardGame.getName();
                alert.setHeaderText("You lent:\n " + game);
                alert.setContentText("borrower:\n" + person);
                boardGame.lentBoardGame(student);
                gameTextField.setText("");
                studentTextField.setText("");
                nameTextField2.setText("");
                lastNameTextField2.setText("");
                idTextField2.setText("");
            }
            else if(boardGame!=null && nameTextField2.getText()!="" && lastNameTextField2.getText()!="" &&idTextField2.getText()!="") {
                String name = nameTextField2.getText();
                String lastName = lastNameTextField2.getText();
                try{
                    int Id = Integer.parseInt(idTextField2.getText());
                    Student student1 = new Student(name, lastName, Id);
                    if(!studentList.sameID(student1)){
                        studentList.addStudent(student1);
                        String game = boardGame.getName();
                        if(student1.isAMember() == false && !manager.getAllBoardGames().isABorrower(student1)){
                            boardGame.lentBoardGame(student1);
                            alert.setHeaderText("You lent:\n " + game);
                            alert.setContentText("borrower:\n" + student1);
                        }else{
                            alert.setContentText("This student is not allowed to borrow game!\n Because this student borrowed one game already.");
                        }
                    }else{
                        alert.setHeaderText(null);
                        alert.setContentText("Student is already in the system!");
                    }

                }catch (IllegalArgumentException ex){
                    alert.setHeaderText("ERROR!");
                    alert.setContentText("VIA ID has to contain 6 digits");
                }
            }else{
                alert.setHeaderText(null);
                alert.setContentText("No data entered.");
            }
            boardGame = null;
            student = null;
            studentTextField.clear();
            gameTextField.clear();
            nameTextField2.clear();
            lastNameTextField2.clear();
            idTextField2.clear();
            alert.showAndWait();
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            updateStudentArea1(studentList, studentListView);
            updateNotLendGamesArea(boardGamesList, gameListView);
            updateWebsite(boardGamesList);
        }
        else if(e.getSource() == changeButton){
            if(student != null && boardGame !=null){
                Student temp = boardGame.getOwner();
                boardGame.lentBoardGame(student);
                if(!temp.isAMember() && !boardGamesList.isAnOwner(temp) && !boardGamesList.isABorrower(temp) && !eventList.isAParticipant(temp)){
                    studentList.removeGuest(temp);
                }
                titleTextFieldEdit.clear();
                borrowerTextFieldEdit.clear();
                memberTextFieldEdit.clear();
                gameTextFieldEdit.clear();
                nameTextFieldEdit.clear();
                lastNameTextFieldEdit.clear();
                idTextFieldEdit.clear();
                alert.setHeaderText("YOU CHANGED BORROWER");
                alert.setContentText("You changed borrower to: "+student);
                alert.show();

            }
            else if(nameTextFieldEdit.getText()!="" && lastNameTextFieldEdit.getText()!="" &&idTextFieldEdit.getText()!="" && boardGame!=null ){
                String name = nameTextFieldEdit.getText();
                String lastName = lastNameTextFieldEdit.getText();
                Student student1 = boardGame.getBorrower();
                int Id = 0;
                try{
                    Id = Integer.parseInt(idTextFieldEdit.getText());

                }catch (IllegalArgumentException er){
                    alert.setHeaderText("ERROR!");
                    alert.setContentText("VIA ID has to contain only digits");
                    alert.show();
                }
                try
                {
                    student1.setVIAID(Id);
                    student1.setFirstName(name);
                    student1.setLastName(lastName);
                }
                catch(IllegalArgumentException er)
                {
                    alert.setHeaderText("ERROR!");
                    alert.setContentText("VIA ID has to contain 6 digits");
                    alert.show();
                }
            }
            else{
                alert.setHeaderText(null);
                alert.setContentText("No data entered.");
                alert.show();
            }
            boardGame = null;
            student = null;
            titleTextFieldEdit.clear();
            borrowerTextFieldEdit.clear();
            gameTextFieldEdit.clear();
            memberTextFieldEdit.clear();
            nameTextFieldEdit.clear();
            lastNameTextFieldEdit.clear();
            idTextFieldEdit.clear();
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            updateStudentArea1(studentList, memberViewListEdit);
            updateLentBoardGames(boardGamesList, gameListViewEdit);
            updateWebsite(boardGamesList);

        }
        else if(e.getSource() == reserveButton){
            if(boardGame!=null && student !=null){
                for (int i = 0; i < boardGame.getAllReservants().length; i++)
                {
                    Student[] reservants = boardGame.getAllReservants();
                    if (reservants[i].equals(student))
                    {
                        alert.setHeaderText(null);
                        alert.setContentText("Student has already reserved this game.");
                        alert.show();
                        return;
                    }
                }
                boardGame.reserve(student);
                alert.setHeaderText("You made a reservation");
                alert.setContentText(student+" reserved a game "+boardGame);
                gameTextFieldReserve.clear();
                studentTextFieldReserve.clear();
            }

            else{
                alert.setHeaderText(null);
                alert.setContentText("You must choose student and a game!");
            }
            alert.showAndWait();
            boardGame = null;
            student = null;
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            gameTextFieldReserve.clear();
            studentTextFieldReserve.clear();
            updateWebsite(boardGamesList);
        }
        else if(e.getSource() == returnButton){
            if(boardGame != null){
                alert.setHeaderText("The game has been returned");
                Student borrower = boardGame.getBorrower();
                boardGame.returnBoardGame();
                gameTextFieldReturn.clear();
                titleTextFieldReturn.clear();
                borrowerTextFieldReturn.clear();
                if(borrower.isAMember()==false  && !boardGamesList.isAnOwner(borrower)  && !eventList.isAParticipant(borrower) && !boardGamesList.isABorrower(borrower)){
                    studentList.removeGuest(borrower);
                }
                if(radio1.isSelected()) boardGame.rate(1);
                if(radio2.isSelected()) boardGame.rate(2);
                if(radio3.isSelected()) boardGame.rate(3);
                if(radio4.isSelected()) boardGame.rate(4);
                if(radio5.isSelected()) boardGame.rate(5);

            }else{
                alert.setHeaderText(null);
                alert.setContentText("You must choose a game!");
            }
            boardGame = null;
            student = null;
            titleTextFieldReturn.clear();
            borrowerTextFieldReturn.clear();
            gameTextFieldReturn.clear();
            radio1.setSelected(false);
            radio2.setSelected(false);
            radio3.setSelected(false);
            radio4.setSelected(false);
            radio5.setSelected(false);

            gameListViewReturn.getItems().clear();
            alert.showAndWait();
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            updateWebsite(boardGamesList);
            boardGamesList = manager.getAllBoardGames();
            updateLentBoardGames(boardGamesList, gameListViewReturn);
        }
        else if(e.getSource() == cancelButton){
            if(student != null && boardGame != null){
                boardGame.cancelReservation(student);
                alert.setHeaderText("The reservation has been canceled.");
            }
            else {
                alert.setHeaderText(null);
                alert.setContentText("You must choose student and a game!");
            }
            boardGame = null;
            student = null;
            studentTextFieldCancel.clear();
            gameTextFieldCancel.clear();
            alert.showAndWait();
            manager.saveAllGames(boardGamesList);
            manager.saveAllStudents(studentList);
            updateStudentArea2(studentList, studentListViewCancel);
            reservationListView.getItems().clear();
            updateWebsite(boardGamesList);
        }


    }

    /**
     * Listener that tells the application about user interactions
     * @param e the MouseEvent object that tells the application about the mouse interactions
     */
    public void MouseClickedList(MouseEvent e) {
        if(e.getSource() == gameListView){
            if(gameListView.getSelectionModel().getSelectedItem() != null){
                boardGame = (BoardGame) gameListView.getSelectionModel().getSelectedItem();
            }
        }
        else if(e.getSource() == studentListView){
            if(studentListView.getSelectionModel().getSelectedItem() !=null){
                student = (Student) studentListView.getSelectionModel().getSelectedItem();
            }
        }
        else if(e.getSource() == gameListViewEdit){
            if(gameListViewEdit.getSelectionModel().getSelectedItem() != null){
                boardGame = (BoardGame) gameListViewEdit.getSelectionModel().getSelectedItem();
                titleTextFieldEdit.setText(boardGame.getName());
                borrowerTextFieldEdit.setText(boardGame.getBorrower().toString());
            }
        }
        else if(e.getSource() == memberViewListEdit){
            if(memberViewListEdit.getSelectionModel().getSelectedItem() != null){
                student = (Student) memberViewListEdit.getSelectionModel().getSelectedItem();
                borrowerTextFieldEdit.setText(student.toString());
            }
        }
        else if(e.getSource() == gameListViewReserve){
            if(gameListViewReserve.getSelectionModel().getSelectedItem() != null){
                boardGame = (BoardGame) gameListViewReserve.getSelectionModel().getSelectedItem();
            }
        }
        else if(e.getSource() == studentListViewReserve){
            if(studentListViewReserve.getSelectionModel().getSelectedItem() != null){
                student = (Student) studentListViewReserve.getSelectionModel().getSelectedItem();
            }
        }
        else if(e.getSource() == studentListViewCancel){
            if( studentListViewCancel.getSelectionModel().getSelectedItem()!=null){
                student = (Student) studentListViewCancel.getSelectionModel().getSelectedItem();
                updateReservedBardGamesArea(boardGamesList, reservationListView, student);
            }
        }
        else if(e.getSource() == reservationListView){
            if( reservationListView.getSelectionModel().getSelectedItem() !=null){
                boardGame = (BoardGame) reservationListView.getSelectionModel().getSelectedItem();
            }
        }
        else if(e.getSource() == gameListViewReturn){
            if(gameListViewReturn.getSelectionModel().getSelectedItem() != null){
                boardGame = (BoardGame) gameListViewReturn.getSelectionModel().getSelectedItem();
                titleTextFieldReturn.setText(boardGame.getName());
                borrowerTextFieldReturn.setText(boardGame.getBorrower().getFirstName() +" "+ boardGame.getBorrower().getLastName());
            }
        }


    }

    /**
     * Listener that allows the user to search through the lists
     * @param e the KeyEvent object that tells the application the keyboard interactions
     */
    public void textChangeListener(KeyEvent e) {
        if(e.getSource() == studentTextField){
            studentTextField.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    studentListView.getItems().clear();

                    StudentList students1 = manager.containStudents(studentTextField.getText());
                    for (int i = 0; i < students1.size(); i++) {
                        studentListView.getItems().add(students1.getStudentByIndex(i));
                    }

                }
            });
        }
        else if(e.getSource() == gameTextField){
            gameTextField.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    gameListView.getItems().clear();
                    BoardGamesList games1 = manager.containGame(gameTextField.getText());
                    for (int i = 0; i < games1.size(); i++) {
                        if(!games1.getBoardGameByIndex(i).isLent()){
                            gameListView.getItems().add(games1.getBoardGameByIndex(i));
                        }

                    }

                }
            });
        }
        else if(e.getSource() == gameTextFieldEdit){
            gameTextFieldEdit.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    gameListViewEdit.getItems().clear();
                    BoardGamesList games1 = manager.containGame(gameTextFieldEdit.getText());
                    for (int i = 0; i < games1.size(); i++) {
                        if(games1.getBoardGameByIndex(i).isLent())
                        {
                            gameListViewEdit.getItems().add(games1.getBoardGameByIndex(i));
                        }
                    }
                }
            });
        }
        else if(e.getSource() == studentTextFieldReserve){
            studentTextFieldReserve.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    studentListViewReserve.getItems().clear();
                    StudentList students1 = manager.containMember(studentTextFieldReserve.getText());
                    for (int i = 0; i < students1.size(); i++) {
                        studentListViewReserve.getItems().add(students1.getMemberById(i));
                    }
                }
            });
        }
        else if(e.getSource() == memberTextFieldEdit){
            memberTextFieldEdit.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    memberViewListEdit.getItems().clear();
                    StudentList students1 = manager.containStudents(memberTextFieldEdit.getText());
                    for (int i = 0; i < students1.size(); i++) {
                        memberViewListEdit.getItems().add(students1.getStudentByIndex(i));
                    }
                }
            });
        }
        else if(e.getSource() == gameTextFieldReserve){
            gameTextFieldReserve.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    gameListViewReserve.getItems().clear();
                    BoardGamesList games1 = manager.containGame(gameTextFieldReserve.getText());
                    for (int i = 0; i < games1.size(); i++) {
                        if(games1.getBoardGameByIndex(i).isLent()){
                            gameListViewReserve.getItems().add(games1.getBoardGameByIndex(i));
                        }
                    }
                }
            });
        }
        else if(e.getSource() == studentTextFieldCancel){
            studentTextFieldCancel.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    studentListViewCancel.getItems().clear();
                    StudentList student1 = manager.containMember(studentTextFieldCancel.getText());
                    for (int i = 0; i < student1.size(); i++) {
                        studentListViewCancel.getItems().add(student1.getMemberById(i));
                    }
                }
            });
        }
        else if(e.getSource() == gameTextFieldReturn){
            gameTextFieldReturn.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    gameListViewReturn.getItems().clear();
                    BoardGamesList games1 = manager.containGame(gameTextFieldReturn.getText());
                    for (int i = 0; i < games1.size(); i++) {
                        if(games1.getBoardGameByIndex(i).isLent()){
                            gameListViewReturn.getItems().add(games1.getBoardGameByIndex(i));
                        }
                    }
                }
            });
        }
    }

    /**
     * Allows user to change views
     * @param event the Event object that tells application about user interactions
     */
    public void handleActions(Event event) {
        if(event.getSource() == menuBoardGames){
            viewHandler.openView("ManageBoardGames");
        }
        if(event.getSource() == menuBorrowing){
            viewHandler.openView("ManageBorrowing");
        }
        if(event.getSource() == menuStudents){
            viewHandler.openView("ManageStudents");
        }
        if(event.getSource() == menuEvents){
            viewHandler.openView("ManageEvents");
        }
        if(event.getSource() == menuUpcomingGames){
            viewHandler.openView("ManageUpcomingBoardGames");
        }
        if (event.getSource() == exit)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to exit?", ButtonType.YES, ButtonType.CANCEL);
            alert.setHeaderText(null);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.YES)
            {
                System.exit(0);
            }
        }

    }

    /**
     * Updates XML file which contain BoardGameList object
     * @param boardGamesList the object to be saved to the XML file
     */
    private void updateWebsite(BoardGamesList boardGamesList)
    {
        manager.saveAllBoardGamesXML(boardGamesList);
    }
}