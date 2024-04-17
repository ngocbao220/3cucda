package OurPackage.Controller;
import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Game2Controller extends Game1Controller implements Initializable {

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

    @FXML
    private AnchorPane PaneGame2;
    @FXML
    private JFXButton but_home;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        but_home.setOnAction(e -> LoadScene( "Game-view.fxml", PaneGame2));
    }
}
