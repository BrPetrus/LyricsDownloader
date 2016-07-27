import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bruno on 27/07/2016.
 */
public class MainController implements Initializable{
    @FXML
    private TextField tf_title;
    @FXML
    private TextField tf_artist;
    @FXML
    private TextArea ta_lyrics;

    public void initialize(URL location, ResourceBundle resources) {
        ta_lyrics.setEditable(false);
    }

    public void clean() {
        tf_title.setText("");
        tf_artist.setText("");
    }

    public void search() {

    }
}
