package OurPackage.Controller;

import OurPackage.Module.TranslateText;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private String Language = "vi";

    private int count = 0;

    private Timeline timer;

    @FXML
    private TextArea TextIn;

    @FXML
    private TextArea TextOut;

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton but_home;

    @FXML
    private JFXButton but_trans;

    @FXML
    private JFXButton but_trans1;

    @FXML
    private ImageView catcute;

    @FXML
    private AnchorPane dictionary;

    @FXML
    private Pane game;

    @FXML
    private AnchorPane home;

    @FXML
    private Pane inner_pane;

    @FXML
    private Label lb_tran1;

    @FXML
    private Label lb_tran2;

    @FXML
    private Pane menu_inner_pane;

    @FXML
    private HBox root;

    @FXML
    private AnchorPane side_anPane;

    @FXML
    private JFXButton swap;

    @FXML
    private Pane translate;

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
            TextIn.setText(TextOut.getText());
            Language = "vi";
            timer.play();
            count++;
        } else {
            TextIn.setText(TextOut.getText());
            Language = "en";
            timer.play();
            count++;
        }
    }

    @FXML
    void translate(KeyEvent event) {
        timer.play();
    }

    @FXML
    public void switchfom(ActionEvent event) {
        if (event.getSource() == but_diction) {
            home.setVisible(false);
            dictionary.setVisible(true);
            game.setVisible(false);
            translate.setVisible(false);
        } else if (event.getSource() == but_home) {
            home.setVisible(true);
            dictionary.setVisible(false);
            game.setVisible(false);
            translate.setVisible(false);
        } else if (event.getSource() == but_game) {
            home.setVisible(false);
            dictionary.setVisible(false);
            game.setVisible(true);
            translate.setVisible(false);
        } else if (event.getSource() == but_trans) {
            home.setVisible(false);
            dictionary.setVisible(false);
            game.setVisible(false);
            translate.setVisible(true);
        }
    }

}
