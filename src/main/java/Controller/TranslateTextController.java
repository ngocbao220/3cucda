package Controller;

import Models.TranslateText;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateTextController implements Initializable {

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton but_home;

    @FXML
    private ImageView catcute;

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
    private JFXButton but_trans;

    @FXML
    private Pane translate;

    private String Language = "vi";

    private int count = 0;

    private Timeline timer;

    @FXML
    private TextArea TextIn;

    @FXML
    private TextArea TextOut;

    @FXML
    private JFXButton swap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timer = new Timeline(new KeyFrame(Duration.millis(10),
                event -> {
                    String inputText = TextIn.getText();
                    if (inputText.isBlank()) {
                        TextOut.clear();
                        return;
                    }
                    TextOut.setText(TranslateText.translate(inputText, Language));
                }));
        timer.setCycleCount(1);
    }


    @FXML
    void swapLang(ActionEvent event) {
        if (count %2 != 0 ) {
            Language = "vi";
            count++;
        } else {
            Language = "en";
            count++;
        }
    }

    @FXML
    void translate(KeyEvent event) {
        timer.play();
    }

}
