package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.*;

import java.util.Optional;

/**
 * A class operating Manage Upcoming Board Games Menu
 * @author Dominika Janczyszyn
 * @version 1.0
 */
public class ManageUpcomingBoardGamesController {
    private ViewHandler viewHandler;
    private Scene scene;
    private  ModelManager manager;
    @FXML private TextField nameTextFieldAdd;
    @FXML private TextField numberTextFieldAdd;
    @FXML private TextArea descriptionTextFieldAdd;
    @FXML private Button addButton;
    /////// 2 tab////
    @FXML private TextField nameEdit;
    @FXML private TextField numberEdit;
    @FXML private TextField search;
    @FXML private TextArea descriptionEdit;
    @FXML private ListView<UpcomingBoardGame> searchList;
    @FXML private Button edit;
    @FXML private Button vote;
    @FXML private Button remove;
    @FXML private MenuBar menuBar;
    @FXML private Menu menuManage;
    @FXML private Menu exitMenu;
    @FXML private MenuItem exit;
    @FXML private MenuItem menuBoardGames;
    @FXML private MenuItem menuBorrowing;
    @FXML private MenuItem menuStudents;
    @FXML private MenuItem menuEvents;
    @FXML private MenuItem menuUpcomingGames;
    @FXML private TableView<UpcomingBoardGame> tab;
    @FXML private TableColumn<UpcomingBoardGame, String> titleTab;
    @FXML private TableColumn<UpcomingBoardGame, String > numberTab;
    @FXML private TableColumn<UpcomingBoardGame, String> descriptonTab;
    @FXML private TableColumn<UpcomingBoardGame, Integer> votesTab;
    private UpcomingBoardGame upcomingBoardGame;

    private UpcomingBoardGamesList upcomingBoardGamesList;

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
    public void reset(){
        viewHandler.loadUpcomingBoardGamesView();
    }

    /**
     * Initializes ModelManager, updates all ListViews and clears TextFields
     */
    public void initialization() {
        this.manager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "website/xml/eventsWebsite.xml", "website/xml/boardGamesWebsite.xml", "website/xml/upcomingBoardGamesWebsite.xml");
        this.upcomingBoardGamesList = manager.getAllUpcomingGames();
        if(searchList != null){
            updateGamesView();
        }
        if(tab != null){
            tab.getItems().clear();
            titleTab.setCellValueFactory(new PropertyValueFactory<>("name"));
            numberTab.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));
            descriptonTab.setCellValueFactory(new PropertyValueFactory<>("description"));
            votesTab.setCellValueFactory(new  PropertyValueFactory<>("numberOfVotes"));
            for(int i = 0; i < upcomingBoardGamesList.size(); i++){
                tab.getItems().add(upcomingBoardGamesList.getUpcomingBoardGameByIndex(i));
            }
        }
        if (nameTextFieldAdd != null && nameEdit != null)
        {
            nameTextFieldAdd.clear();
            numberTextFieldAdd.clear();
            descriptionTextFieldAdd.clear();

            nameEdit.clear();
            numberEdit.clear();
            descriptionEdit.clear();
            search.clear();
        }
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
     * Updates ViewLists containing UpcomingBoardGame objects
     */
    public void updateGamesView() {
        searchList.getItems().clear();
        for (int i = 0; i < upcomingBoardGamesList.size(); i++) {
            searchList.getItems().add(upcomingBoardGamesList.getUpcomingBoardGameByIndex(i));
        }
    }

    /**
     * Handles all user interactions
     * @param e the ActionEvent object that tells the application user interactions
     */
    public void handleActions(ActionEvent e) {
        if (e.getSource() == menuBoardGames) {
            viewHandler.openView("ManageBoardGames");
        }
        if (e.getSource() == menuBorrowing) {
            viewHandler.openView("ManageBorrowing");
        }
        if (e.getSource() == menuStudents) {
            viewHandler.openView("ManageStudents");
        }
        if (e.getSource() == menuEvents) {
            viewHandler.openView("ManageEvents");
        }
        if(e.getSource() == upcomingBoardGame){
            viewHandler.openView("ManageUpcomingBoardGames");
        }
        if(e.getSource() == menuUpcomingGames){
            viewHandler.openView("ManageUpcomingBoardGames");
        }
        if (e.getSource() == exit) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to exit?", ButtonType.YES, ButtonType.CANCEL);
            alert.setHeaderText(null);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                System.exit(0);
            }
        }
        if(e.getSource() == addButton){
            if (!nameTextFieldAdd.getText().equals("") && !numberTextFieldAdd.getText().equals("") && !descriptionTextFieldAdd.getText().equals("")) {
                String title =  nameTextFieldAdd.getText();
                String numberOfPlayers = numberTextFieldAdd.getText();
                String description = descriptionTextFieldAdd.getText();
                UpcomingBoardGame upcomingBoardGame = new UpcomingBoardGame(title, numberOfPlayers, description);
                upcomingBoardGamesList.addUpcomingGame(upcomingBoardGame);
                nameTextFieldAdd.clear();
                numberTextFieldAdd.clear();
                descriptionTextFieldAdd.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("The game has been added to the system.");
                    alert.show();
                    nameTextFieldAdd.clear();
                    numberTextFieldAdd.clear();
                    descriptionTextFieldAdd.clear();
                    manager.saveAllUpcomingGames(upcomingBoardGamesList);
                    updateWebsite();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No data entered.");
                alert.show();
            }
        }
        if(e.getSource() == edit){
            if (upcomingBoardGame != null && nameEdit.getText() != "" && numberEdit.getText() != "" && descriptionEdit.getText() != "") {
                String title = nameEdit.getText();
                String number = numberEdit.getText();
                String description = descriptionEdit.getText();
                upcomingBoardGame.setName(title);
                upcomingBoardGame.setNumberOfPlayers(number);
                upcomingBoardGame.setDescription(description);
                updateWebsite();
                nameEdit.clear();
                numberEdit.clear();
                descriptionEdit.clear();
                search.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("The game has been edited.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No data entered.");
                alert.show();
            }
            manager.saveAllUpcomingGames(upcomingBoardGamesList);
            updateGamesView();
            updateWebsite();
            nameEdit.clear();
            numberEdit.clear();
            descriptionEdit.clear();
        }
        if(e.getSource() == vote){
            if(upcomingBoardGame != null) {
                upcomingBoardGame.voteForAGame();
                manager.saveAllUpcomingGames(upcomingBoardGamesList);
                updateGamesView();
                updateWebsite();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You voted for the game : " + upcomingBoardGame.getName());
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No game has been selected.");
                alert.show();
            }
        }
        if(e.getSource()== remove){
            if (upcomingBoardGame != null) {
                upcomingBoardGamesList.removeUpcomingGame(upcomingBoardGame);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("The game has been removed.");
                alert.show();
                manager.saveAllUpcomingGames(upcomingBoardGamesList);
                updateGamesView();
                updateWebsite();
                nameEdit.clear();
                numberEdit.clear();
                descriptionEdit.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("No game has been selected.");
                alert.show();
            }
        }
    }

    /**
     * Listener that tells the application about mouse interactions
     * @param mouseEvent the MouseEvent object that tells the application about user interactions
     */
    public void select(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == searchList){
            this.upcomingBoardGame = searchList.getSelectionModel().getSelectedItem();
            if (upcomingBoardGame != null)
            {
                nameEdit.setText(upcomingBoardGame.getName());
                numberEdit.setText(upcomingBoardGame.getNumberOfPlayers());
                descriptionEdit.setText(upcomingBoardGame.getDescription());
            }
        }
    }


    /**
     * Listener that allows the user to search through the lists
     * @param keyEvent the KeyEvent object that tells the application the keyboard interactions
     */
    public void search(KeyEvent keyEvent) {
        if(keyEvent.getSource() == search){
            search.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    searchList.getItems().clear();

                    UpcomingBoardGamesList boardGames = manager.containUpcomingBoardGame(search.getText());
                    for (int i = 0; i < boardGames.size(); i++) {
                        searchList.getItems().add(upcomingBoardGamesList.getUpcomingBoardGameByIndex(i));
                    }
                }
            });
        }
    }

    /**
     * Updates XML file containing all UpcomingBoardGame objects
     */
    private void updateWebsite()
    {
        manager.saveAllUpcomingBoardGamesXML(manager.getAllUpcomingGames());
    }

}
