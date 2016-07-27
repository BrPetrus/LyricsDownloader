import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Bruno on 27/07/2016.
 */
public class LyricsDownloader extends Application{
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        Scene myScene = new Scene(root);
        myScene.getStylesheets().add("gui.css");

        primaryStage.setTitle("Lyrics Downloader");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
