package OurPackage.Controller;

import OurPackage.Module.TranslateText;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
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
    private Button swap;

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
            count++;
        } else {
            TextIn.setText(TextOut.getText());
            Language = "en";
            count++;
        }
    }

    @FXML
    void translate(KeyEvent event) {
        timer.play();
    }

}
