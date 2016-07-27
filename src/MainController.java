import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
        try {
            String base = "http://www.directlyrics.com/";
            String title = tf_title.getText(); title = title.trim(); title = title.replaceAll(" ", "-");
            String artist = tf_artist.getText(); artist = artist.trim(); artist = artist.replaceAll(" ", "-");
            base += artist + "-" + title + "-lyrics.html";
            System.out.println(base);

            Document doc = Jsoup.connect(base).get();
            Elements d = doc.getElementsByAttributeValue("class", "lyrics lyricsselect");
            String lyrics = d.toString();

            int firstP = lyrics.indexOf("<p>");
            int secondP = lyrics.indexOf("</p");
            lyrics = lyrics.substring(firstP + 3, secondP);
            lyrics = lyrics.replace("<br>", "\n");
            while(true) {
                firstP = lyrics.indexOf("<ins");
                secondP = lyrics.indexOf("</script>");
                if (firstP == -1 || secondP == -1)
                    break;
                String lyricsFirstPart = lyrics.substring(0, firstP);
                String lyricsLastPart = lyrics.substring(secondP+9);
                lyrics = lyricsFirstPart+lyricsLastPart;
            }


            ta_lyrics.setText(lyrics);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
