package OurPackage.Controller;
import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Game2Controller extends GeneralController implements Initializable {
    @FXML
    private JFXButton but_trans;

    @FXML
    private JFXButton no;

    @FXML
    private Label opt1;

    @FXML
    private Label opt2;

    @FXML
    private Label opt3;

    @FXML
    private Label opt4;

    @FXML
    private Label question;

    @FXML
    private Pane pane_opt1;

    @FXML
    private Pane pane_opt2;

    @FXML
    private Pane pane_opt3;

    @FXML
    private Pane pane_opt4;

    @FXML
    private Pane ready;
    @FXML
    private AnchorPane PaneGame2;
    @FXML
    private JFXButton but_home;

    @FXML
    private JFXButton but_opt1;

    @FXML
    private JFXButton but_opt2;

    @FXML
    private JFXButton but_opt3;

    @FXML
    private JFXButton but_opt4;

    @FXML
    private JFXButton but_set;

    @FXML
    private JFXButton but_diction;
    @FXML
    private JFXButton yes;
    private Timeline blinker;

    @FXML
    private Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    @FXML
    private Pane p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
    @FXML
    private ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9,i10;
    int counter = 0;
    static int correct = 0;
    static int wrong = 0;

    int index = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        but_home.setOnAction(e -> LoadScene( "Game-view.fxml", PaneGame2));
        setupMouseEvents(pane_opt1);
        setupMouseEvents(pane_opt2);
        setupMouseEvents(pane_opt3);
        setupMouseEvents(pane_opt4);
        answered();
    }
    public void LoadQuestion() {
        if (counter == 0) {
            question.setText("Every morning, I get up early to go _ _ _ _ _ _ the gym before work.");
            opt1.setText("a. to");
            opt2.setText("b. at");
            opt3.setText("c. in");
            opt4.setText("d. on");
        }
        else if (counter == 1) {
            //question.setText("She can _ _ _ _ _ _ many languages.");
            changeLabelTextWithAnimation(question,"She can _ _ _ _ _ _ many languages.");
            changeLabelTextWithAnimation(opt1,"a. speak");
            changeLabelTextWithAnimation(opt2,"b. speaks");
            changeLabelTextWithAnimation(opt3,"c. spoke");
            changeLabelTextWithAnimation(opt4,"d. speaking");
        }
        else if (counter == 2) {
            changeLabelTextWithAnimation(question,"They are planning to _ _ _ _ _ _ a new \nstore next year.");
            changeLabelTextWithAnimation(opt1,"a. opens");
            changeLabelTextWithAnimation(opt2,"b. open");
            changeLabelTextWithAnimation(opt3,"c. opened");
            changeLabelTextWithAnimation(opt4,"d. opening");
        }
        else if (counter == 3) {
            changeLabelTextWithAnimation(question,"You must _ _ _ _ _ _ your seatbelt \nwhile driving.");
            changeLabelTextWithAnimation(opt1,"a. wears");
            changeLabelTextWithAnimation(opt2,"b. wearing");
            changeLabelTextWithAnimation(opt3,"c. wear");
            changeLabelTextWithAnimation(opt4,"d. worn");
        }
        else if (counter == 4) {
            changeLabelTextWithAnimation(question,"The teacher asked us to _ _ _ _ _ _ \nour textbooks.");
            changeLabelTextWithAnimation(opt1,"a. brings");
            changeLabelTextWithAnimation(opt2,"b. brought");
            changeLabelTextWithAnimation(opt3,"c. bringing");
            changeLabelTextWithAnimation(opt4,"d. bring");
        }
        else if (counter == 5) {
            changeLabelTextWithAnimation(question,"We need to _ _ _ _ _ _ the time of \nthe meeting.");
            changeLabelTextWithAnimation(opt1,"a. confirming");
            changeLabelTextWithAnimation(opt2,"b. confirm");
            changeLabelTextWithAnimation(opt3,"c. confirming");
            changeLabelTextWithAnimation(opt4,"d. confirms");
        }
        else if (counter == 6) {
            changeLabelTextWithAnimation(question,"Can you help me _ _ _ _ _ _ this form");
            changeLabelTextWithAnimation(opt1,"a. complete");
            changeLabelTextWithAnimation(opt2,"b. completing");
            changeLabelTextWithAnimation(opt3,"c. completes");
            changeLabelTextWithAnimation(opt4,"d. completed");
        }
        else if (counter == 7) {
            changeLabelTextWithAnimation(question,"I want to _ _ _ _ _ _ a movie tonight.");
            changeLabelTextWithAnimation(opt1,"a. watch");
            changeLabelTextWithAnimation(opt2,"b. watches");
            changeLabelTextWithAnimation(opt3,"c. watched");
            changeLabelTextWithAnimation(opt4,"d. watching");
        }
        else if (counter == 8) {
            changeLabelTextWithAnimation(question,"Please _ _ _ _ _ _ quiet during the \npresentation.");
            changeLabelTextWithAnimation(opt1,"a. be");
            changeLabelTextWithAnimation(opt2,"b. is");
            changeLabelTextWithAnimation(opt3,"c. are");
            changeLabelTextWithAnimation(opt4,"d. being");
        }
        else if (counter == 9) {
            changeLabelTextWithAnimation(question,"Please ______ your shoes before entering \nthe house.");
            changeLabelTextWithAnimation(opt1,"a. removing");
            changeLabelTextWithAnimation(opt2,"b. remove");
            changeLabelTextWithAnimation(opt3,"c. removed");
            changeLabelTextWithAnimation(opt4,"d. removes");
        }
    }

    @FXML
    public void op1clicked(ActionEvent event) {
        checkAns(opt1.getText().toString());
        if (checkAns(opt1.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {

        } else {
            counter++;
            LoadQuestion();
            answered();
        }
    }
    @FXML
    public void op2clicked(ActionEvent event) {
        checkAns(opt2.getText().toString());
        if (checkAns(opt2.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {

        } else {
            counter++;
            LoadQuestion();
            answered();
        }
    }
    @FXML
    public void op3clicked(ActionEvent event) {
        checkAns(opt3.getText().toString());
        if (checkAns(opt3.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {

        } else {
            counter++;
            LoadQuestion();
            answered();
        }
    }
    @FXML
    public void op4licked(ActionEvent event) {
        checkAns(opt4.getText().toString());
        if (checkAns(opt4.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {

        } else {
            counter++;
            LoadQuestion();
            answered();
        }
    }
    boolean checkAns(String ans) {
        if (counter == 0) {
            if (ans.equals("a. to")) {
                return true;
            }
            return false;
        }
        if (counter == 1) {
            if (ans.equals("a. speak")) {
                return true;
            }
            return false;
        }
        if (counter == 2) {
            if (ans.equals("b. open")) {
                return true;
            }
            return false;
        }
        if (counter == 3) {
            if (ans.equals("c. wear")) {
                return true;
            }
            return false;
        }
        if (counter == 4) {
            if (ans.equals("d. bring")) {
                return true;
            }
            return false;
        }
        if (counter == 5) {
            if (ans.equals("b. confirm")) {
                return true;
            }
            return false;
        }
        if (counter == 6) {
            if (ans.equals("a. complete")) {
                return true;
            }
            return false;
        }
        if (counter == 7) {
            if (ans.equals("a. watch")) {
                return true;
            }
            return false;
        }
        if (counter == 8) {
            if (ans.equals("a. be")) {
                return true;
            }
            return false;
        }
        if (counter == 9) {
            if (ans.equals("b. remove")) {
                return true;
            }
            return false;
        }
        return false;
    }
    public void changeLabelTextWithAnimation(Label chang ,String newText) {
        // Tạo hiệu ứng FadeTransition
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), chang);
        fadeOut.setFromValue(1); // Độ rõ của Label từ 100%
        fadeOut.setToValue(0); // Độ rõ của Label sau khi kết thúc từ 0%

        // Sự kiện khi hiệu ứng kết thúc
        fadeOut.setOnFinished(event -> {
            // Khi hiệu ứng Fade kết thúc, cập nhật văn bản của Label
            chang.setText(newText);

            // Tạo hiệu ứng Fade mới để hiển thị văn bản mới
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), chang);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        });

        fadeOut.play();
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
    public void answered() {
        if (counter == 0) {
            p1.setStyle("-fx-background-color: #F18757;");
            l1.setStyle("-fx-text-fill: white;");
            i1.setVisible(true);
        }
        if (counter == 1) {
            p2.setStyle("-fx-background-color: #F18757;");
            l2.setStyle("-fx-text-fill: white;");
            i2.setVisible(true);
        }
        if (counter == 2) {
            p3.setStyle("-fx-background-color: #F18757;");
            l3.setStyle("-fx-text-fill: white;");
            i3.setVisible(true);
        }
        if (counter == 3) {
            p4.setStyle("-fx-background-color: #F18757;");
            l4.setStyle("-fx-text-fill: white;");
            i4.setVisible(true);
        }
        if (counter == 4) {
            p5.setStyle("-fx-background-color: #F18757;");
            l5.setStyle("-fx-text-fill: white;");
            i5.setVisible(true);
        }
        if (counter == 5) {
            p6.setStyle("-fx-background-color: #F18757;");
            l6.setStyle("-fx-text-fill: white;");
            i6.setVisible(true);
        }
        if (counter == 6) {
            p7.setStyle("-fx-background-color: #F18757;");
            l7.setStyle("-fx-text-fill: white;");
            i7.setVisible(true);
        }
        if (counter == 7) {
            p8.setStyle("-fx-background-color: #F18757;");
            l8.setStyle("-fx-text-fill: white;");
            i8.setVisible(true);
        }
        if (counter == 8) {
            p9.setStyle("-fx-background-color: #F18757;");
            l9.setStyle("-fx-text-fill: white;");
            i9.setVisible(true);
        }
        if (counter == 9) {
            p10.setStyle("-fx-background-color: #F18757;");
            l10.setStyle("-fx-text-fill: white;");
            i10.setVisible(true);
        }
    }
}



