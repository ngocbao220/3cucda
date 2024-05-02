package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingController extends GeneralController {

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton but_home;

    @FXML
    private ImageView catcute;

    @FXML
    private AnchorPane diction_means;

    @FXML
    private AnchorPane dictionary;

    @FXML
    private AnchorPane home;

    @FXML
    private Pane inner_pane;

    @FXML
    private AnchorPane intro_diction;

    @FXML
    private Pane menu_inner_pane;

    @FXML
    private HBox root;

    @FXML
    private JFXButton but_set;

    @FXML
    private AnchorPane side_anPane;

    @FXML
    private JFXButton but_move_game;

    @FXML
    private JFXButton but_move_diction;

    @FXML
    private AnchorPane move_diction;

    @FXML
    private AnchorPane move_game;

    @FXML
    private JFXButton but_trans;

    @FXML
    private Pane game;

    @FXML
    private Pane translate;

    @FXML
    private JFXButton but_ENG_VIE;

    @FXML
    private Label lb_tran1;

    @FXML
    private Label lb_tran2;

    @FXML
    private JFXButton bonus;

    private static final String Media_Path = "src/main/resources/OurPackage/SupportScreen/media/";

    @FXML
    private Media media;

    @FXML
    private MediaView mediaView;

    @FXML
    private Pane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        but_set.setStyle("-fx-background-color: #333333;");
        setupMedia("introApp.mp4", pane, mediaView, media, true);
    }

    public static void setupMedia(String mediaName, Pane pane, MediaView mediaview, Media media, boolean status) {
        media = new Media(new File(Media_Path + mediaName).toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaview.fitWidthProperty().bind(pane.widthProperty());
        mediaview.fitHeightProperty().bind(pane.heightProperty());
        mediaview.setLayoutX(pane.getLayoutX());
        mediaview.setLayoutY(pane.getLayoutY());

        mediaview.setMediaPlayer(mediaPlayer);
        // cho mediaPlayer chạy vô hạn
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
        if (status) {
            mediaview.getMediaPlayer().play();
        } else mediaview.getMediaPlayer().stop();
    }
}
