import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
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
    private Stage myStage;

    public void initialize(URL location, ResourceBundle resources) {
        ta_lyrics.setEditable(false);
    }
    public void init(Stage myStage) {
        this.myStage = myStage;
    }

    /**
     * Clean button method
     */
    public void clean() {
        tf_title.setText("");
        tf_artist.setText("");
    }

    /**
     * Download lyrics from metrolyrics, edit them and print them to textarea
     */
    public void search() {
        String base = "";
        try {
            // Prepare the URL link
            base = "http://www.metrolyrics.com/";
            String title = tf_title.getText(); title = title.trim(); title = title.replaceAll(" ", "-");
            String artist = tf_artist.getText(); artist = artist.trim(); artist = artist.replaceAll(" ", "-");
            base += artist + "-lyrics-" + title + ".html";
            System.out.println(base);

            // Connect to metrolyrics and download the lyrics
            Document doc = Jsoup.connect(base).get();
            Elements d = doc.getElementsByAttributeValue("id", "lyrics-body-text");
            String lyrics = d.toString();
            System.out.println(lyrics);

            // Format them -> remove unnecessary attributes
            lyrics = lyrics.replaceAll("<p class=\"verse\">", "");
            lyrics = lyrics.replaceAll("</p>", "\n");
            lyrics = lyrics.replaceAll("<br>", "\n");
            lyrics = lyrics.replaceAll("<div id=\"lyrics-body-text\" class=\"js-lyric-text\">", "");
            lyrics = lyrics.replaceAll("</div>", "");

            // Print the lyrics
            ta_lyrics.setText(lyrics);
        }
        catch(Exception e) {
            e.printStackTrace();
            ta_lyrics.setText("We couldn't connect to metrolyrics :/." +
                    "\nCheck if you entered the right information." +
                    "\nAlso try removing words like 'the' (The Script->Script)" +
                    "\nIt might just be that metrolyrics doesn't have the lyrics" +
                    "\nURL we extracted: \"" + base + "\""
            );
        }
    }

    public void downloadLyrics() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Lyrics");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showSaveDialog(myStage);
        if (file != null) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                out.print(ta_lyrics.getText().replaceAll("\n", "\r\n"));
                out.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
