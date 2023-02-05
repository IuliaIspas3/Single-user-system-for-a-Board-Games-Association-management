package view;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ModelManager;

/**
 * A class that starts the application
 * @author Dominika Janczyszyn
 * @version 1.0
 */
public class TestGUI extends Application {

    public void start(Stage window) throws Exception
    {
        ModelManager modelManager = new ModelManager("upcoming.bin", "games.bin", "students.bin", "events.bin", "website/xml/eventsWebsite.xml", "website/xml/boardGamesWebsite.xml", "website/xml/upcomingBoardGamesWebsite.xml");
        window.getIcons().add(new Image("file:src/view/vialogowhite.jpg"));
        ViewHandler viewHandler = new ViewHandler(window, modelManager);
        viewHandler.start();
    }
}
