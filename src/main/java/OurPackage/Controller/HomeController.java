package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class HomeController extends GeneralController {

    @FXML
    private Pane Back;

    @FXML
    private Pane DisplayContent;

    @FXML
    private AnchorPane Home;

    @FXML
    private AnchorPane Menu;

    @FXML
    private VBox Menutab;

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
    private JFXButton but_trans;

    @FXML
    private ImageView catcute;

    @FXML
    private Pane inner_pane;

    @FXML
    private Pane menu_inner_pane;

    @FXML
    private Pane move_dic_home;

    @FXML
    private Pane move_game_2;

    @FXML
    private Pane move_game_home;

    @FXML
    private Pane move_set_home;

    @FXML
    private Pane move_trans_home;

    @FXML
    private Pane nonSwitchDic;

    @FXML
    private Pane nonSwitchTran;

    @FXML
    private JFXButton opendic;

    @FXML
    private JFXButton opengame;

    @FXML
    private JFXButton opengame2;

    @FXML
    private JFXButton openset;

    @FXML
    private JFXButton opentran;

    @FXML
    private HBox root;

    @FXML
    private TextField searchTab;

    Timeline timeline;

    protected Map<String, String> MarkedWord = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        searchTab.setOnKeyPressed(e -> Non());
        setupMouseEvents(move_dic_home);
        setupMouseEvents(move_game_home);
        setupMouseEvents(move_trans_home);
        setupMouseEvents(move_set_home);
        setupMouseEvents(move_game_2);

        opendic.setOnAction(e -> {
            but_diction.fire();
        });
        opengame.setOnAction(e -> {
            but_game.fire();
        });
        opentran.setOnAction(e -> {
            but_trans.fire();
        });
        openset.setOnAction(e -> {
            but_set.fire();
        });
        opengame2.setOnAction(e -> {
            LoadScene("Game2-view.fxml", Back);
        });

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

    private void Non() {
        String[] countWords = searchTab.getText().split(" ");
        int numberOfWords = countWords.length;

        if (numberOfWords > 1) {
            nonSwitchDic.setVisible(false);
            nonSwitchTran.setVisible(true);

            searchTab.setOnAction(e -> {
                nonSwitchTran.setVisible(false);
                but_trans.fire();
                TranslateTextController.strTemp1 = searchTab.getText();
            });

        } else if (numberOfWords == 1) {
            nonSwitchDic.setVisible(true);
            nonSwitchTran.setVisible(false);

            searchTab.setOnAction(e -> {
                nonSwitchDic.setVisible(false);
                but_diction.fire();
                DictionaryController.strTemp = searchTab.getText();
            });

        }
    }
}
