package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.ModelManager;

import java.io.IOException;

/**
 * A class operating all the views in Menu
 * @author Dominika Janczyszyn
 * @version 1.0
 */
public class ViewHandler {
    private Stage stage;
    private ManageBorrowingController manageBorrowingController;
    private ManageBoardGameController manageBoardGameController;
    private ModelManager modelManager;
    private StartController startController;
    private ManageStudentsController manageStudentsController;
    private ManageEventsController manageEventsController;
    private ManageUpcomingBoardGamesController manageUpcomingBoardGamesController;

    /**
     * Constructor creating ViewHandler
     * @param stage JavaFX Stage object
     * @param modelManager ModelManager object
     */
    public ViewHandler(Stage stage, ModelManager modelManager){
        this.stage = stage;
        this.modelManager = modelManager;
    }

    /**
     * Start/initialize method that is loading all the views and opens MainView(StartController)
     */
    public void start()
    {
        loadViewMain();
        loadBoardGamesView();
        loadBorrowingView();
        loadStudentsView();
        loadEventsView();
        loadUpcomingBoardGamesView();
        openView("MainView");
    }

    /**
     * Opens selected GUI based on name
     * @param name name of the GUI
     */
    public void openView(String name)
    {
        switch (name)
        {
            case "MainView":
                stage.setScene(startController.getScene());
                stage.setResizable(false);
                manageBoardGameController.reset();
                break;
            case "ManageBoardGames":
                stage.setScene(manageBoardGameController.getScene());
                stage.setResizable(false);
                manageBorrowingController.reset();
                break;
            case "ManageBorrowing":
                stage.setScene(manageBorrowingController.getScene());
                stage.setResizable(false);
                manageBorrowingController.reset();
                break;
            case "ManageStudents":
                stage.setScene(manageStudentsController.getScene());
                stage.setResizable(false);
                manageStudentsController.reset();
                break;
            case "ManageEvents":
                stage.setScene(manageEventsController.getScene());
                stage.setResizable(false);
                manageEventsController.reset();
                break;
            case "ManageUpcomingBoardGames":
                stage.setScene(manageUpcomingBoardGamesController.getScene());
                stage.setResizable(false);
                manageUpcomingBoardGamesController.reset();
                break;
        }

        String title = "";

        if(stage.getScene().getRoot().getUserData() !=null)
        {
            title = stage.getScene().getRoot().getUserData().toString();
        }

        stage.setTitle(title);
        stage.show();
    }

    /**
     * Loads MainView (Start Controller)
     */
    private void loadViewMain()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Start.fxml"));
            Region root = loader.load();
            startController = loader.getController();
            startController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Loads BoardGamesView (ManageBoardGameController)
     */
    public void loadBoardGamesView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageBoardGames.fxml"));
            Region root = loader.load();
            manageBoardGameController = loader.getController();
            manageBoardGameController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Loads BorrowingView (ManageBorrowingController)
     */
    public void loadBorrowingView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageBorrowing.fxml"));
            Region root = loader.load();
            manageBorrowingController = loader.getController();
            manageBorrowingController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Loads StudentView (ManageStudentsController)
     */
    public void loadStudentsView(){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageStudents.fxml"));
            Region root = loader.load();
            manageStudentsController = loader.getController();
            manageStudentsController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Loads EventsView (ManageEventsController)
     */
    public void loadEventsView(){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageEvents.fxml"));
            Region root = loader.load();
            manageEventsController = loader.getController();
            manageEventsController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Loads UpcomingBoardGamesView (ManageUpcomingBoardGamesController)
     */
    public void loadUpcomingBoardGamesView(){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManageUpcomingBoardGames.fxml"));
            Region root = loader.load();
            manageUpcomingBoardGamesController = loader.getController();
            manageUpcomingBoardGamesController.init(this, new Scene(root), modelManager);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

