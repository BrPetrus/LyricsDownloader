import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Bruno on 27/07/2016.
 */
public class LyricsDownloader extends Application{
    /**
     *  Start the application
     */
    public void start(Stage primaryStage) throws Exception{
        // Set up the root node and scene
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        Scene myScene = new Scene(root, 400, 800);

        // Load css
        myScene.getStylesheets().add("gui.css");

        // Show the scene, set title ...
        primaryStage.setTitle("Lyrics Downloader");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
