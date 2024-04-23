package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.Objects;
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
    private JFXButton but_to_game1;

    @FXML
    private JFXButton but_to_game2;

    @FXML
    private JFXButton but_trans;

    @FXML
    private ImageView catcute;

    @FXML
    private Pane inner_pane;

    @FXML
    private Pane menu_inner_pane;

    @FXML
    private HBox root;

    @FXML
    private ImageView iconMonkey;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlayMusic("tiecbaibien.mp4", -1);
        Play();

        action(iconMonkey, 1.5);
        super.initialize(url, resourceBundle);
        but_to_game1.setOnAction(e -> {
            StopMusic();
            LoadScene("Monkey.fxml", Back);
        });
        but_to_game2.setOnAction(e -> {
            StopMusic();
            LoadScene("Quiz-view.fxml", Back);
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

    /*public final String IMAGE_PATH = "../SupportScreen/img/";*/

    public void action(ImageView imageView, double speech) {

        // Create RotateTransition
        RotateTransition transition = new RotateTransition(Duration.seconds(speech), imageView);
        transition.setFromAngle(-20);
        transition.setToAngle(20);
        transition.setCycleCount(-1);
        transition.setAutoReverse(true);

        // Start the animation
        transition.play();
    }


}
