package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.*;

public class Game1Controller extends GeneralController {

    @FXML
    private Label AnsLeft;

    @FXML
    private Label AnsRight;

    @FXML
    private JFXButton ButtonLeft;

    @FXML
    private JFXButton ButtonOffMusic;

    @FXML
    private JFXButton ButtonOnMusic;

    @FXML
    private JFXButton ButtonPause;

    @FXML
    private JFXButton ButtonRight;

    @FXML
    private Label Heart;

    @FXML
    private Label LabelDisplayWord;

    @FXML
    private Pane Menu;

    @FXML
    private Pane PaneAnswerRight;

    @FXML
    private AnchorPane PaneGame1;

    @FXML
    private Pane PaneWhenPlayGame;

    @FXML
    private Label Point;

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
    private ProgressBar myProgressBar;

    private Timeline timeline;

    private double time;
    private double k;

    private int point = 0;

    private int heart = 3;

    private final String DATA = "C:\\java\\BTL_APP\\3cucda\\Data\\DataForGame.txt";

    private final String Split = "\t";

    public Map<String, String> myMap = new HashMap<>();

    Set<String> mySet;

    String[] myArray;

    Random random = new Random();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ReadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mySet = myMap.keySet();

        myArray = mySet.toArray(new String[0]);

        but_home.setOnAction(e -> LoadScene( "Game-view.fxml", PaneGame1));

        time = 0.05;
        k = 10;

        myProgressBar.setStyle("-fx-accent: #FFFF00;");

        AnsRight.setOnMouseClicked(e -> {
            ButtonRight.fire();
        });

        AnsLeft.setOnMouseClicked(e -> {
            ButtonLeft.fire();
        });

        RunTime();
        ShowQuestion();
    }
    // Đọc dữ liệu cung cấp cho game
    public void ReadData() throws IOException {
        FileReader fileReader = new FileReader(DATA);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(Split);
            if (parts.length < 2) {
                continue;
            }
            String word = parts[1];
            String answer = parts[0];
            myMap.put(word, answer);
        }
    }
    // Hiện câu hỏi
    void ShowQuestion() {
        String CorrectAnswer = myArray[random.nextInt(myArray.length)];

        String WrongAns;
        // Tim ra phuong an sai
        do {
            WrongAns = myArray[random.nextInt(myArray.length)];
        } while (CorrectAnswer.equals(WrongAns));

        LabelDisplayWord.setText(CorrectAnswer);

        // Random chon dap an dung
        int numberOfCorrectAnswer = random.nextInt(2);
        // neu so dap an dung la 1 thi cho dap an dung vao ben trai
        if (numberOfCorrectAnswer == 1) {
            AnsLeft.setText(myMap.get(CorrectAnswer));
            AnsRight.setText(myMap.get(WrongAns));
            // Chon Dap an dung
            ButtonLeft.setOnAction(e -> {
                point++;
                if(heart <= 5 && point % 10 == 0) {
                    heart ++;
                }
                if(k >= 4) k -= 1;
                comboTime();
            });
            // Chon Dap an sai
            ButtonRight.setOnAction(e -> {
                if(heart > 0) {
                    heart--;
                    comboTime();
                }
                if(k <= 8) k+=2;
                else if (heart <= 0) timeline.stop();
            });
        }
        else {
            AnsLeft.setText(myMap.get(WrongAns));
            AnsRight.setText(myMap.get(CorrectAnswer));
            //Chon dap an dung
            ButtonRight.setOnAction(e -> {
                point++;
                if(heart <= 5 && point % 10 == 0) {
                    heart ++;
                }
                if(k >= 4) k--;
                comboTime();
            });
            //Chon dap an sai
            ButtonLeft.setOnAction(e -> {
                if(heart > 0) {
                    heart--;
                    comboTime();
                }
                if(k <= 8) k += 2;
                else if (heart <= 0 ) timeline.stop();
            });
        }
    }
    // Cập nhật điểm
    void UpdatePointAndHeart() {
        Heart.setText(String.valueOf(heart));
        Point.setText(String.valueOf(point));
    }
    // Chạy thanh thời gian
    void RunTime() {
        myProgressBar.setProgress(1.0);
        timeline = new Timeline(new KeyFrame(Duration.seconds(k*time), event -> {
            double progress = myProgressBar.getProgress();
            if (progress > 0) {
                myProgressBar.setProgress(progress - time);
            } else timeline.stop();
        }));
        timeline.setCycleCount(999);
        timeline.play();
    }
    // Cập nhật điểm + chạy thanh thời gian + hiện câu hỏi mới và dừng thanh thời gian cũ
    void comboTime() {
        UpdatePointAndHeart();
        timeline.stop();
        RunTime();
        ShowQuestion();
    }

    @FXML
    void Continue(ActionEvent event) {
        Menu.setVisible(false);
        timeline.play();
    }

    @FXML
    void PlaytoGame(ActionEvent event) {
        PaneWhenPlayGame.setVisible(true);
        UpdatePointAndHeart();
        ShowQuestion();
        RunTime();
    }

    @FXML
    void TurnOffMusic(ActionEvent event) {
        ButtonOnMusic.setVisible(true);
        ButtonOffMusic.setVisible(false);
    }

    @FXML
    void TurnOnMusic(ActionEvent event) {
        ButtonOffMusic.setVisible(true);
        ButtonOnMusic.setVisible(false);
    }

    @FXML
    void back(ActionEvent event) {
        PaneWhenPlayGame.setVisible(false);
        timeline.stop();
    }

    @FXML
    void PauseGame(ActionEvent event) {
        Menu.setVisible(true);
        timeline.pause();
    }
}