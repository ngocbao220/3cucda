package OurPackage.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizController implements Initializable {

    @FXML
    private Button Play;

    private Media media;

    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;


    @FXML
    void PlayMedia(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choss");
        File selectfile = fileChooser.showOpenDialog(null);
        if (selectfile != null) {
            String url = selectfile.toURI().toString();
            media = new Media(url);
            mediaPlayer= new MediaPlayer(media);

            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.setAutoPlay(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
