package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

import static OurPackage.Module.SomethingForGame.*;

public class GameController extends GeneralController {
    @FXML
    private Pane Back;


    @FXML
    private Pane DisplayContent;

    @FXML
    private JFXButton Exit;

    @FXML
    private AnchorPane Menu;

    @FXML
    private VBox Menutab;

    @FXML
    private AnchorPane PaneTabGame;

    @FXML
    private Pane SearchTab;

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton but_home;

    @FXML
    private JFXButton but_set;

    @FXML
    private JFXButton but_to_game1, but_ok;

    @FXML
    private JFXButton but_to_game2;

    @FXML
    private JFXButton but_trans;

    @FXML
    private JFXButton but_how;

    @FXML
    private ImageView catcute;

    @FXML
    private Pane inner_pane;

    @FXML
    private Pane menu_inner_pane;

    @FXML
    private Pane howtoplay;

    @FXML
    private HBox root;

    @FXML
    private ImageView iconMonkey;

    @FXML
    private ImageView threeMonkey;

    Timeline time_load;

    @Override
    void disable() {
        but_to_game1.setDisable(true);
        but_to_game2.setDisable(true);
    }

    @Override
    void unDisable() {
        time_load = new Timeline(new KeyFrame(Duration.seconds(super.time + 0.5), event -> {
            but_to_game1.setDisable(false);
            but_to_game2.setDisable(false);
        }));
        time_load.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        but_game.setStyle("-fx-background-color: #333333;");
        HowtoPlay();
        PlayMusic("tiecbaibien.mp4", -1);
        Play();

        action(iconMonkey, 1.5);
        super.initialize(url, resourceBundle);
        but_to_game1.setOnAction(e -> {
            disable();
            StopMusic();
            LoadScene("Monkey.fxml", Back);
            unDisable();
        });
        but_to_game2.setOnAction(e -> {
            disable();
            StopMusic();
            LoadScene("QuizGame-view.fxml", Back);
            PlayMusic("QizzGame - Senbon Zakura Piano.mp3", -1);
            Play();
            unDisable();
        });

        iconMonkey.setOnMouseClicked(e -> {
            but_to_game1.fire();
        });

        Exit.setOnAction(e -> {
            StopMusic();
            but_home.fire();
        });
    }
    @FXML
    void onMusic() {
        Play();
    }

    @FXML
    void offMusic() {
        PauseMusic();
    }


    public void action(ImageView imageView, double speech) {

        RotateTransition transition = new RotateTransition(Duration.seconds(speech), imageView);
        transition.setFromAngle(-15);
        transition.setToAngle(15);
        transition.setCycleCount(-1);
        transition.setAutoReverse(true);

        transition.play();
    }

    public void HowtoPlay () {
        but_how.setOnAction(event -> {
            howtoplay.setVisible(true);
        });
        but_ok.setOnAction(event -> {
            howtoplay.setVisible(false);
        });
    }
}
