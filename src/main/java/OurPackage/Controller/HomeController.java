package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController extends GeneralController {

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
    private JFXButton but_set;

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
    private Pane move_game_2;

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

    @FXML
    private Pane move_trans_home;

    @FXML
    private Pane move_dic_home;

    @FXML
    private Pane move_set_home;

    @FXML
    private Pane move_game_home;

    protected Map<String, String> MarkedWord = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        setupMouseEvents(move_dic_home);
        setupMouseEvents(move_game_home);
        setupMouseEvents(move_trans_home);
        setupMouseEvents(move_set_home);
        setupMouseEvents(move_game_2);
    }
    @FXML
    public void onMouseEntered(Pane pane) {
        TranslateTransition trans = new TranslateTransition();
        trans.setNode(pane);
        trans.setToY(-7);
        trans.setDuration(Duration.seconds(.2));
        trans.play();
    }
    @FXML
    public void onMouseExt(Pane pane) {
        TranslateTransition trans = new TranslateTransition();
        trans.setNode(pane);
        trans.setToY(0);
        trans.setDuration(Duration.seconds(.2));
        trans.play();
    }

    public void setupMouseEvents(Pane pane) {
        pane.setOnMouseEntered(event -> onMouseEntered(pane));
        pane.setOnMouseExited(event -> onMouseExt(pane));
    }



}
