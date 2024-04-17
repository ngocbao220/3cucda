package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GeneralController implements Initializable{

    private final String Screen_Path = "/OurPackage/Screen/";

    @FXML
    private Pane DisplayContent;

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
    private JFXButton but_set;

    @FXML
    private Pane game;

    @FXML
    private Pane Back;


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

    @FXML
    private JFXButton but_to_game1;

    @FXML
    private JFXButton but_to_game2;

    private Stage stage;
    private Scene scene;

    public void Load(String s, Pane container) throws IOException {
        Pane parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(s)));
        stage = (Stage) container.getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    public void LoadScene(String s, Pane container) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(200));
        fadeTransition.setNode(container);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(e -> {
            try {
                Load(Screen_Path + s, container);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        fadeTransition.play();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        but_home.setOnAction(e -> LoadScene("Home-view.fxml", DisplayContent));
        but_trans.setOnAction(e -> LoadScene("Translate-view.fxml", DisplayContent));
        but_game.setOnAction(e -> LoadScene("Game-view.fxml", DisplayContent));
        but_diction.setOnAction(e -> LoadScene("Dictionary-view.fxml", DisplayContent));
        but_set.setOnAction(e -> LoadScene("Setting-view.fxml", DisplayContent));
    }
}
