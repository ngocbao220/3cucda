package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizController extends GeneralController implements Initializable {
    @FXML
    private AnchorPane PaneGame2;

    @FXML
    private Pane result;

    @FXML
    private Pane pane_5050;

    @FXML
    private Label point;

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_5050;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton but_home;

    @FXML
    private JFXButton but_start;

    @FXML
    private JFXButton but_next;

    @FXML
    private JFXButton but_opt1;

    @FXML
    private JFXButton but_opt2;

    @FXML
    private JFXButton but_yes, but_no;

    @FXML
    private JFXButton but_opt3;

    @FXML
    private JFXButton buttonTheEnd;

    @FXML
    private JFXButton but_opt4;

    @FXML
    private JFXButton but_set;

    @FXML
    private JFXButton but_trans;

    @FXML
    private ImageView i1;

    @FXML
    private ImageView i10;

    @FXML
    private ImageView i2;

    @FXML
    private ImageView i3;

    @FXML
    private ImageView i4;

    @FXML
    private ImageView i5;

    @FXML
    private ImageView i6;

    @FXML
    private ImageView i7;

    @FXML
    private ImageView i8;

    @FXML
    private ImageView i9;

    @FXML
    private Label l1;

    @FXML
    private Label l10;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private Label l4;

    @FXML
    private Label l5;

    @FXML
    private Label l6;

    @FXML
    private Label l7;

    @FXML
    private Label l8;

    @FXML
    private Label l9;

    @FXML
    private Pane mulchoice;

    @FXML
    private Circle circle;

    @FXML
    private Label opt1;

    @FXML
    private Label opt2;

    @FXML
    private Label countdownLabel;

    @FXML
    private Label opt3;

    @FXML
    private Label opt4;

    @FXML
    private Label num_ques;

    @FXML
    private Pane p1;

    @FXML
    private Pane p10;

    @FXML
    private Pane p2;

    @FXML
    private Pane p3;

    @FXML
    private Pane p4;

    @FXML
    private Pane p5;

    @FXML
    private Pane p6;

    @FXML
    private Pane menu;

    @FXML
    private Pane p7;

    @FXML
    private Pane p8;

    @FXML
    private Pane p9;

    @FXML
    private Pane pane_next;

    @FXML
    private Pane pane_no;

    @FXML
    private Pane pane_opt1;

    @FXML
    private Pane pane_opt2;

    @FXML
    private Pane pane_opt3;

    @FXML
    private Pane pane_opt4;

    @FXML
    private Pane pane_yes;

    @FXML
    private Label question;

    @FXML
    private JFXButton next, prev;

    @FXML
    private JFXButton ques1, ques2, ques3, ques4, ques5, ques6, ques7, ques8, ques9, ques10;

    @FXML
    private Pane ready;

    @FXML
    private Pane rule;

    @FXML
    private Pane start;

    boolean check = false;
    boolean firstClick = true;
    int counter = 1;
    static int correct = 0;
    static int wrong = 0;
    private static final int START_TIME = 900; // 15 phút (900 giây)
    private int timeRemaining = START_TIME;

    boolean check1 = true, check2 = true, check3 = true, check4 = true;
    boolean Check = false;
    boolean[] booleanQues = new boolean[11];
    boolean[] CorrectAns = new boolean[11];
    int k = 0;

    int index = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        but_home.setOnAction(e -> LoadScene( "Game-view.fxml", PaneGame2));
        for (int i = 1; i < 11; i++) {
            booleanQues[i] = false;
        }
        chose_Ques();
        prev.setOnAction(event -> {
            if (counter > 1){
                counter--;
                LoadQuestion();
                getQuestion();
            }
        });
        next.setOnAction(event -> {
            if (counter < 10) {
                counter++;
                LoadQuestion();
                getQuestion();
            }
        });
        changeButton_color(but_opt1);
        changeButton_color(but_opt2);
        changeButton_color(but_opt3);
        changeButton_color(but_opt4);
        click_opt();
        time_run();
        getQuestion();
        LoadQuestion();
    }
    public void LoadQuestion() {
        if (counter == 1) {
            num_ques.setText("Questione 1 of 10");
            question.setText("Every morning, I get up early to go _ _ _ _ _ _ the gym before work.");
            opt1.setText("a. to");
            opt2.setText("b. at");
            opt3.setText("c. in");
            opt4.setText("d. on");
        }
        else if (counter == 2) {
            num_ques.setText("Questione 2 of 10");
            question.setText("What is the plural form of \"child\"?");
            opt1.setText("a. Childs");
            opt2.setText("b. Childes");
            opt3.setText("c. Children");
            opt4.setText("d. Childs'");
        }
        else if (counter == 3) {
            num_ques.setText("Questione 3 of 10");
            question.setText("Which of the following is a synonym for \"happy\"?");
            opt1.setText("a. Sad");
            opt2.setText("b. Joyful");
            opt3.setText("c. Angry");
            opt4.setText("d. Bored");
        }
        else if (counter == 4) {
            num_ques.setText("Questione 4 of 10");
            question.setText("What is the capital of France?");
            opt1.setText("a. London");
            opt2.setText("b. Paris");
            opt3.setText("c. Berlin");
            opt4.setText("d. Madrid");
        }
        else if (counter == 5) {
            num_ques.setText("Questione 5 of 10");
            question.setText("Who wrote \"Romeo and Juliet\"?");
            opt1.setText("a. William Shakespeare");
            opt2.setText("b. Charles Dickens");
            opt3.setText("c. Jane Austen");
            opt4.setText("d. Mark Twain");
        }
        else if (counter == 6) {
            num_ques.setText("Questione 6 of 10");
            question.setText("Which planet is known as the \"Red Planet\"?");
            opt1.setText("a. Earth");
            opt2.setText("b. Mars");
            opt3.setText("c. Venus");
            opt4.setText("d. Jupiter");
        }
        else if (counter == 7) {
            num_ques.setText("Questione 7 of 10");
            question.setText("What is the chemical symbol for water?");
            opt1.setText("a. Wo");
            opt2.setText("b. Hy");
            opt3.setText("c. Wa");
            opt4.setText("d. H2O");
        }
        else if (counter == 8) {
            num_ques.setText("Questione 8 of 10");
            question.setText("Who painted the Mona Lisa?");
            opt1.setText("a. Vincent van Gogh");
            opt2.setText("b. Leonardo da Vinci");
            opt3.setText("c. Pablo Picasso");
            opt4.setText("d. Michelangelo");
        }
        else if (counter == 9) {
            num_ques.setText("Questione 9 of 10");
            question.setText("Which of the following is a type of mammal?");
            opt1.setText("a. Snake");
            opt2.setText("b. Eagle");
            opt3.setText("c. Whale");
            opt4.setText("d. Crocodile");
        }
        else if (counter == 10) {
            num_ques.setText("Questione 10 of 10");
            question.setText("Please _ _ _ _ _ _ your shoes before entering the house.");
            opt1.setText("a. removing");
            opt2.setText("b. remove");
            opt3.setText("c. removed");
            opt4.setText("d. removes");
        }
    }


    public void op1clicked() {
        booleanQues[counter] = true;
        checkAns(opt1.getText().toString());
        if (checkAns(opt1.getText().toString())) {
            CorrectAns[counter] = true;
        } else {
            CorrectAns[counter] = false;
        }
        for (int i = 1; i < 11; i++) {
            if (booleanQues[i] && i == 10) {
                counter = i;
                getCompletedQuestions();
                break;
            }
            if (!booleanQues[i]) {
                counter = i;
                LoadQuestion();
                getCompletedQuestions();
                getQuestion();
                break;
            }
        }

    }

    public void op2clicked() {
        booleanQues[counter] = true;
        checkAns(opt2.getText().toString());
        if (checkAns(opt2.getText().toString())) {
            CorrectAns[counter] = true;
        } else {
            CorrectAns[counter] = false;
        }

        for (int i = 1; i < 11; i++) {
            if (booleanQues[i] && i == 10) {
                counter = i;
                getCompletedQuestions();
                break;
            }
            if (!booleanQues[i]) {
                counter = i;
                LoadQuestion();
                getCompletedQuestions();
                getQuestion();
                break;
            }
        }


    }

    public void op3clicked() {
        booleanQues[counter] = true;
        checkAns(opt3.getText().toString());
        if (checkAns(opt3.getText().toString())) {
            CorrectAns[counter] = true;
        } else {
            CorrectAns[counter] = false;
        }

        for (int i = 1; i < 11; i++) {
            if (booleanQues[i] && i == 10) {
                counter = i;
                getCompletedQuestions();
                break;
            }
            if (!booleanQues[i]) {
                counter = i;
                LoadQuestion();
                getCompletedQuestions();
                getQuestion();
                break;
            }
        }

    }

    public void op4clicked() {
        booleanQues[counter] = true;
        checkAns(opt4.getText().toString());
        if (checkAns(opt4.getText().toString())) {
            CorrectAns[counter] = true;
        } else {
            CorrectAns[counter] = false;
        }
        for (int i = 1; i < 11; i++) {
            if (booleanQues[i] && i == 10) {
                counter = i;
                getCompletedQuestions();
                break;
            }
            if (!booleanQues[i]) {
                counter = i;
                LoadQuestion();
                getCompletedQuestions();
                getQuestion();
                break;
            }
        }

    }
    boolean checkAns(String ans) {
        if (counter == 1) {
            if (ans.equals("a. to")) {
                return true;
            }
            return false;
        }
        if (counter == 2) {
            if (ans.equals("c. Children")) {
                return true;
            }
            return false;
        }
        if (counter == 3) {
            if (ans.equals("b. Joyful")) {
                return true;
            }
            return false;
        }
        if (counter == 4) {
            if (ans.equals("b. Paris")) {
                return true;
            }
            return false;
        }
        if (counter == 5) {
            if (ans.equals("a. William Shakespeare")) {
                return true;
            }
            return false;
        }
        if (counter == 6) {
            if (ans.equals("b. Mars")) {
                return true;
            }
            return false;
        }
        if (counter == 7) {
            if (ans.equals("d. H2O")) {
                return true;
            }
            return false;
        }
        if (counter == 8) {
            if (ans.equals("b. Leonardo da Vinci")) {
                return true;
            }
            return false;
        }
        if (counter == 9) {
            if (ans.equals("c. Whale")) {
                return true;
            }
            return false;
        }
        if (counter == 10) {
            if (ans.equals("b. remove")) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void time_run () {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            timeRemaining--;
            updateLabel();
            if (timeRemaining <= 0) {
                timeline.stop();
                countdownLabel.setText("Time's up!");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateLabel() {
        // Cập nhật label để hiển thị thời gian còn lại
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        String time = String.format("%02d : %02d", minutes, seconds);
        countdownLabel.setText(time);
    }

    public void getCompletedQuestions() {
        if (booleanQues[1]) {
            ques1.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
        if (booleanQues[2]) {
            ques2.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
        if (booleanQues[3]) {
            ques3.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
        if (booleanQues[4]) {
            ques4.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
        if (booleanQues[5]) {
            ques5.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
        if (booleanQues[6]) {
            ques6.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
        if (booleanQues[7]) {
            ques7.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
        if (booleanQues[8]) {
            ques8.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
        if (booleanQues[9]) {
            ques9.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
        if (booleanQues[10]) {
            ques10.setStyle("-fx-background-color:  #2C5B36; -fx-text-fill: white;");
        }
    }

    public void getQuestion() {
        if (counter == 1) {
            ques1.setStyle("-fx-border-color:  #2C5B36");
        }
        if (counter == 2) {
            ques2.setStyle("-fx-border-color:  #2C5B36");
        }
        if (counter == 3) {
            ques3.setStyle("-fx-border-color:  #2C5B36");
        }
        if (counter == 4) {
            ques4.setStyle("-fx-border-color:  #2C5B36");
        }
        if (counter == 5) {
            ques5.setStyle("-fx-border-color:  #2C5B36");
        }
        if (counter == 6) {
            ques6.setStyle("-fx-border-color:  #2C5B36");
        }
        if (counter == 7) {
            ques7.setStyle("-fx-border-color:  #2C5B36");
        }
        if (counter == 8) {
            ques8.setStyle("-fx-border-color:  #2C5B36");
        }
        if (counter == 9) {
            ques9.setStyle("-fx-border-color:  #2C5B36");
        }
        if (counter == 10) {
            ques10.setStyle("-fx-border-color:  #2C5B36");
        }
    }

    public void changeButton_color(JFXButton button) {
        button.setStyle("-fx-background-color: transparent;");
        button.setOnMouseEntered(mouseEvent -> button.setStyle("-fx-background-color: rgba(128, 128, 128, 0.08);"));
        button.setOnMouseExited(mouseEvent -> button.setStyle("-fx-background-color: transparent;"));

    }

    public void click_opt() {
        but_opt1.setOnAction(event -> {
            check2 = true; check3 = true; check4 = true;
            but_opt2.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt2.setStyle("-fx-text-fill: black;");
            but_opt3.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt3.setStyle("-fx-text-fill: black;");
            but_opt4.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt4.setStyle("-fx-text-fill: black;");
            if (check1) {
                but_opt1.setStyle("-fx-background-color: #2C5B36; -fx-border-color:  #2C5B36;");
                opt1.setStyle("-fx-text-fill: white;");
                check1 = false;
                but_opt1.setOnMouseEntered(null);
                but_opt1.setOnMouseExited(null);
            } else {
                op1clicked();
                changeButton_color(but_opt1);
                opt1.setStyle("-fx-text-fill: black;");
                check1 = true;
                CircleChang();
            }
        });

        but_opt2.setOnAction(event -> {
            check1 = true; check3 = true; check4 = true;
            but_opt1.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt1.setStyle("-fx-text-fill: black;");
            but_opt3.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt3.setStyle("-fx-text-fill: black;");
            but_opt4.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt4.setStyle("-fx-text-fill: black;");
            if (check2) {
                but_opt2.setStyle("-fx-background-color: #2C5B36; -fx-border-color:  #2C5B36;");
                opt2.setStyle("-fx-text-fill: white;");
                check2 = false;
                but_opt2.setOnMouseEntered(null);
                but_opt2.setOnMouseExited(null);
            } else {
                op2clicked();
                changeButton_color(but_opt2);
                opt2.setStyle("-fx-text-fill: black;");
                check2 = true;
                CircleChang();
            }
        });

        but_opt3.setOnAction(event -> {
            check2 = true; check1 = true; check4 = true;
            but_opt2.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt2.setStyle("-fx-text-fill: black;");
            but_opt1.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt1.setStyle("-fx-text-fill: black;");
            but_opt4.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt4.setStyle("-fx-text-fill: black;");
            if (check3) {
                but_opt3.setStyle("-fx-background-color: #2C5B36; -fx-border-color:  #2C5B36;");
                opt3.setStyle("-fx-text-fill: white;");
                check3 = false;
                but_opt3.setOnMouseEntered(null);
                but_opt3.setOnMouseExited(null);
            } else {
                op3clicked();
                changeButton_color(but_opt3);
                opt3.setStyle("-fx-text-fill: black;");
                check3 = true;
                CircleChang();
            }
        });

        but_opt4.setOnAction(event -> {
            check2 = true; check3 = true; check1 = true;
            but_opt2.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt2.setStyle("-fx-text-fill: black;");
            but_opt3.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt3.setStyle("-fx-text-fill: black;");
            but_opt1.setStyle("-fx-background-color: transparent; -fx-border-color: #D9D9D9;");
            opt1.setStyle("-fx-text-fill: black;");
            if (check4) {
                but_opt4.setStyle("-fx-background-color: #2C5B36; -fx-border-color:  #2C5B36;");
                opt4.setStyle("-fx-text-fill: white;");
                check4 = false;
                but_opt4.setOnMouseEntered(null);
                but_opt4.setOnMouseExited(null);
            } else {
                op4clicked();
                changeButton_color(but_opt4);
                opt4.setStyle("-fx-text-fill: black;");
                check4 = true;
                CircleChang();
            }
        });
    }

    public void chose_Ques() {
        ques1.setOnAction(event -> {
            counter = 1;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
        ques2.setOnAction(event -> {
            counter = 2;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
        ques3.setOnAction(event -> {
            counter = 3;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
        ques4.setOnAction(event -> {
            counter = 4;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
        ques5.setOnAction(event -> {
            counter = 5;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
        ques6.setOnAction(event -> {
            counter = 6;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
        ques7.setOnAction(event -> {
            counter = 7;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
        ques8.setOnAction(event -> {
            counter = 8;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
        ques9.setOnAction(event -> {
            counter = 9;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
        ques10.setOnAction(event -> {
            counter = 10;
            booleanQues[counter] = false;
            CircleChang();
            LoadQuestion();
            getQuestion();
        });
    }

    public void CircleChang() {
        double b = 0;
        for (int i = 1; i < 11; i++) {
            if (booleanQues[i]) b++;
        }
        double a = b * 10 / 100 * 2 * Math.PI * 65;
        System.out.println(counter);
        System.out.println(a);
        String s = (int)a + " 100000";
        circle.setStyle("-fx-stroke-dash-array: " + s + ";");
    }

}