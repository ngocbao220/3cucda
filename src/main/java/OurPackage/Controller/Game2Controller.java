package OurPackage.Controller;
import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Game2Controller extends Game1Controller implements Initializable {
    @FXML
    private AnchorPane PaneGame2;

    @FXML
    private JFXButton ans_corect1;

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton but_home;

    @FXML
    private JFXButton but_set;

    @FXML
    private JFXButton but_start;

    @FXML
    private JFXButton but_trans;

    @FXML
    private JFXButton corect1_next;

    @FXML
    private JFXButton no;

    @FXML
    private Pane pane_corect1;

    @FXML
    private Pane ques1;

    @FXML
    private JFXButton ques1_an1;

    @FXML
    private JFXButton ques1_an2;

    @FXML
    private JFXButton ques1_an3;

    @FXML
    private JFXButton ques1_an4;

    @FXML
    private Pane ready;

    @FXML
    private Pane rule;

    @FXML
    private JFXButton rule_next;

    @FXML
    private Pane start;

    @FXML
    private JFXButton yes;
    private Timeline blinker;

    private List<Pane> panes;

     int index = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        but_home.setOnAction(e -> LoadScene( "Game-view.fxml", PaneGame2));
        setupBlinkingButton();
        panes = new ArrayList<>();
        panes.add(start);
        panes.add(rule);
        panes.add(ready);
        panes.add(ques1);
        panes.add(pane_corect1);
        but_start.setOnAction(event -> switchPane());
        rule_next.setOnAction(event -> switchPane());
        yes.setOnAction(event -> switchPane());
        clickAns(ques1_an1);
        clickAns(ques1_an2);
        clickAns(ques1_an3);
        clickAns(ques1_an4);

    }
    public void switchPane() {
        int currentIndex = index;
        if (currentIndex != -1) {
            // Ẩn Pane hiện tại
            panes.get(index).setVisible(false);

            // Tìm và hiển thị Pane kế tiếp
            int nextIndex = (currentIndex + 1) % panes.size();
            Pane nextPane = panes.get(nextIndex);
            nextPane.setVisible(true);
            index++;
        }
    }


    private void setupBlinkingButton() {
        but_start.setStyle("-fx-background-color: #EF5451;");

        // Tạo timeline để nhấp nháy
        blinker = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(but_start.opacityProperty(), 0.5)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(but_start.opacityProperty(), 0))
        );
        blinker.setCycleCount(Timeline.INDEFINITE);
        blinker.setAutoReverse(true);
        blinker.play();
    }

    @FXML
    private void stopBlinking() {
        if (blinker != null) {
            blinker.stop();
        }
    }

    public void clickAns(JFXButton button) {
        button.setOnAction(event -> switchPane());
    }




}
