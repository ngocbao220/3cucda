package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static OurPackage.Module.SomethingForGame.*;

public class MonkeyGameController extends GeneralController {


    @FXML
    private Label AnsLeft;

    @FXML
    private Label AnsRight;

    @FXML
    private JFXButton ButtonAddOtherWord;

    @FXML
    private JFXButton ButtonChooseDefault;

    @FXML
    private JFXButton ButtonChooseOtherType;

    @FXML
    private JFXButton ButtonContinue;

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
    private JFXButton ButtonSet;

    @FXML
    private Pane ChosseTypeForGame;

    @FXML
    private Label Heart;

    @FXML
    private Label LabelDisplayWord;

    @FXML
    private Label LableShowCorrectAnswer;

    @FXML
    private TextField MeanOfThisWord;

    @FXML
    private TextField MeanThisWord;

    @FXML
    private Pane Menu;

    @FXML
    private Pane MenuChosseTheWord;

    @FXML
    private Pane NonWrongAns;

    @FXML
    private Pane PaneAddOtherWord;

    @FXML
    private Pane PaneAnswerRight;

    @FXML
    private Pane PaneChosseTypeGame;

    @FXML
    private AnchorPane PaneGame1;

    @FXML
    private Pane PaneLoss;

    @FXML
    private Pane PaneSetWord;

    @FXML
    private Pane PaneWhenChooseWhenAddOrSetTheWord;

    @FXML
    private Pane PaneWhenLoss;

    @FXML
    private Pane PaneWhenPauseGame;

    @FXML
    private Pane PaneWhenPlayGame;

    @FXML
    private Pane PaneWhenWrongAnswer;

    @FXML
    private Label Point;

    @FXML
    private JFXButton Replay;

    @FXML
    private JFXButton Replay2;

    @FXML
    private TextField SearchOnLeft;

    @FXML
    private TextField SearchOnRight;

    @FXML
    private JFXListView<String> WordChoosing;

    @FXML
    private JFXListView<String> WordDefault;

    @FXML
    private TextField WordNeedSet;

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
    private JFXButton buttonOutToPaneGame;

    @FXML
    private ProgressBar myProgressBar;

    @FXML
    private TextField newWord;

    private Timeline timeline;

    private double time;
    private double k;

    private int point = 0;

    private int heart = 3;

    private final String DATA1 = "C:\\java\\BTL_APP\\3cucda\\Data\\Data1ForGame.txt";
    private final String DATA2 = "C:\\java\\BTL_APP\\3cucda\\Data\\Data2ForGame.txt";

    private final String Split = "\t";

    public static Map<String, String> Map1 = new HashMap<>();
    public static Map<String, String> Map2 = new HashMap<>();

    Set<String> mySet;

    String[] myArray;

    Random random = new Random();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        but_home.setOnAction(e -> LoadScene( "Game-view.fxml", PaneGame1));
        // cho Data2 rong
        try {
            EmptyFile(DATA2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        time = 0.05;
        k = 10;

        PaneWhenWrongAnswer.setVisible(false);
        PaneWhenPauseGame.setVisible(false);
        PaneWhenPlayGame.setVisible(false);
        PaneChosseTypeGame.setVisible(false);
        MenuChosseTheWord.setVisible(false);
        ChosseTypeForGame.setVisible(true);
        PaneWhenChooseWhenAddOrSetTheWord.setVisible(false);
        PaneAddOtherWord.setVisible(false);
        PaneSetWord.setVisible(false);
        PaneWhenLoss.setVisible(false);

        myProgressBar.setStyle("-fx-accent: #FFFF00;");

        AnsRight.setOnMouseClicked(e -> {
            ButtonRight.fire();
        });

        AnsLeft.setOnMouseClicked(e -> {
            ButtonLeft.fire();
        });

        Replay2.setOnAction(e -> {
            Replay.fire();
        });

        timeline = new Timeline(new KeyFrame(Duration.seconds(k*time), event -> {
            double progress = myProgressBar.getProgress();
            if (progress > 0) {
                myProgressBar.setProgress(progress - time);
            } else if(progress <=0) {
                timeline.stop();
                heart--;
                if (heart == 0) {
                    PaneWhenLoss.setVisible(true);
                }
                comboTime(Map2);
            }
        }));
        timeline.setCycleCount(9999);
    }


    // Hiện câu hỏi và nhận diện đáp án
    void ShowQuestion(Map<String, String> map) {
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
            AnsLeft.setText(map.get(CorrectAnswer));
            AnsRight.setText(map.get(WrongAns));
            // Chon Dap an dung
            ButtonLeft.setOnAction(e -> {
                point++;
                if(heart <= 5 && point % 10 == 0) {
                    heart ++;
                }
                if(k >= 6) k -= 1;
                comboTime(map);
            });
            // Chon Dap an sai
            ButtonRight.setOnAction(e -> {
                if(heart > 0) {
                    heart--;
                    if (heart == 0) {
                        PaneWhenLoss.setVisible(true);
                    }
                    else {
                        //Hien dap an dung cho nguoi choi
                        timeline.pause();
                        LableShowCorrectAnswer.setText(map.get(CorrectAnswer));
                        PaneWhenWrongAnswer.setVisible(true);
                        comboTime(map);
                        timeline.pause();
                    }
                }
                if(k <= 8) k+=2;
            });
        }
        else {
            AnsLeft.setText(map.get(WrongAns));
            AnsRight.setText(map.get(CorrectAnswer));
            //Chon dap an dung
            ButtonRight.setOnAction(e -> {
                point++;
                if(heart <= 5 && point % 10 == 0) {
                    heart ++;
                }
                if(k >= 6) k--;
                comboTime(map);
            });
            //Chon dap an sai
            ButtonLeft.setOnAction(e -> {
                if(heart > 0) {
                    heart--;
                    if (heart == 0) {
                        PaneWhenLoss.setVisible(true);
                    }
                    //hien dap an dung cho nguoi choi
                    else {
                        timeline.pause();
                        LableShowCorrectAnswer.setText(map.get(CorrectAnswer));
                        PaneWhenWrongAnswer.setVisible(true);
                        comboTime(map);
                        timeline.pause();
                    }
                }
                if(k <= 8) k += 2;
            });
        }
    }


    // Cập nhật điểm và Mạng
    void UpdatePointAndHeart() {
        Heart.setText(String.valueOf(heart));
        Point.setText(String.valueOf(point));
    }


    // Chạy thanh thời gian
    void RunTime() {
        myProgressBar.setProgress(1.0);
        timeline.play();
    }


    // Cập nhật điểm + chạy thanh thời gian + hiện câu hỏi mới và dừng thanh thời gian cũ
    void comboTime(Map<String, String> map) {
        UpdatePointAndHeart();
        timeline.stop();
        RunTime();
        ShowQuestion(map);
    }


    // Hàm reset thuộc tính
    void reset() {
        point = 0;
        heart = 3;
        k = 10;
        time = 0.05;
        UpdatePointAndHeart();
        ButtonContinue.setDisable(false);
    }

    // Tiep tuc khi dung Game
    @FXML
    void Continue(ActionEvent event) {
        PaneWhenPauseGame.setVisible(false);
        PaneWhenWrongAnswer.setVisible(false);
        timeline.play();
    }


    // Ham xu li khi chon che do choi khac
    @FXML
    void ChooseOtherType(ActionEvent event) throws IOException {
        ChosseTypeForGame.setVisible(false);
        ReadData(DATA1, Split, Map1);
        ReadData(DATA2, Split, Map2);
        ForSearching(SearchOnLeft, Map1, WordDefault);
        ForSearching(SearchOnRight, Map2, WordChoosing);
        MenuChosseTheWord.setVisible(true);
    }

    // Khi an Play o Menu
    @FXML
    void PlaytoGame(ActionEvent event) {
        PaneChosseTypeGame.setVisible(true);
        ChosseTypeForGame.setVisible(true);
        MenuChosseTheWord.setVisible(false);
    }

    // Thoat Man hinh chon che do choi
    @FXML
    void OutToPaneGame(ActionEvent event) {
        PaneChosseTypeGame.setVisible(false);
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

    //tro ve menu
    @FXML
    void back(ActionEvent event) throws IOException {
        PaneChosseTypeGame.setVisible(false);
        PaneWhenPauseGame.setVisible(false);
        PaneWhenPlayGame.setVisible(false);
        PaneWhenLoss.setVisible(false);
        MenuChosseTheWord.setVisible(false);
        EmptyFile(DATA2);
        ForSearching(SearchOnRight, Map2, WordChoosing);
        timeline.stop();
        reset();
    }

    // Dung Game
    @FXML
    void PauseGame(ActionEvent event) {
        PaneWhenPlayGame.setVisible(true);
        PaneWhenPauseGame.setVisible(true);
        timeline.pause();
    }

    // Thoat Tab Game
    @FXML
    void Exit(ActionEvent event) throws IOException {
        timeline.stop();
        reset();
        EmptyFile(DATA2);
        PaneChosseTypeGame.setVisible(false);
        PaneWhenPauseGame.setVisible(false);
        PaneWhenPlayGame.setVisible(false);
        but_home.fire();
    }


    // CHE DO CHOI MAC DINH


    @FXML
    void PlayDefaultGame(ActionEvent event) {
        try {
            ReadData(DATA1, Split, Map1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mySet = Map1.keySet();

        myArray = mySet.toArray(new String[0]);

        Replay.setOnAction(e -> {
            PaneWhenPauseGame.setVisible(false);
            PaneWhenLoss.setVisible(false);
            reset();
            ShowQuestion(Map1);
            RunTime();
        });


        PaneChosseTypeGame.setVisible(false);
        PaneWhenPlayGame.setVisible(true);
        UpdatePointAndHeart();
        ShowQuestion(Map1);
        RunTime();
    }



    // CHE DO CHOI CHON TU MONG MUON



    @FXML
    void PlayOtherTypeGame(ActionEvent event) {
        try {
            ReadData(DATA2, Split, Map2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MenuChosseTheWord.setVisible(false);
        PaneChosseTypeGame.setVisible(false);

        mySet = Map2.keySet();

        myArray = mySet.toArray(new String[0]);
        Replay.setOnAction(e -> {
            PaneWhenPauseGame.setVisible(false);
            PaneWhenLoss.setVisible(false);
            reset();
            ShowQuestion(Map2);
            RunTime();
        });

        PaneWhenPlayGame.setVisible(true);
        UpdatePointAndHeart();
        ShowQuestion(Map2);
        RunTime();
    }


    // Sua tu cung la them tu
    @FXML
    void SetTheWord(ActionEvent event) {
        PaneWhenChooseWhenAddOrSetTheWord.setVisible(true);
        PaneSetWord.setVisible(true);
    }
    @FXML
    void DoSetWord(ActionEvent event) {
        //TO DO
        try {
            AddData(WordNeedSet.getText(), MeanThisWord.getText());
            ForSearching(SearchOnRight, Map2, WordChoosing);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WordNeedSet.setText("");
        MeanOfThisWord.setText("");
        PaneSetWord.setVisible(false);
        PaneWhenChooseWhenAddOrSetTheWord.setVisible(false);
    }


    // Khi chon Them tu moi
    @FXML
    void AddOtherWord(ActionEvent event) {
        PaneWhenChooseWhenAddOrSetTheWord.setVisible(true);
        PaneAddOtherWord.setVisible(true);
    }
    // Hanh dong them tu moi
    @FXML
    void DoAddWord(ActionEvent event) {
        //TO DO
        try {
            AddData(newWord.getText(), MeanOfThisWord.getText());
            ForSearching(SearchOnRight, Map2, WordChoosing);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWord.setText("");
        MeanOfThisWord.setText("");
        PaneAddOtherWord.setVisible(false);
        PaneWhenChooseWhenAddOrSetTheWord.setVisible(false);
    }

    // Them tu tu ListView
    @FXML
    void Add(ActionEvent event) throws IOException {
        AddItemsOfListView(WordDefault);
        ForSearching(SearchOnRight, Map2, WordChoosing);
        System.out.println(Map2.size());
    }

    // Them tat ca
    @FXML
    void addAll(ActionEvent event) throws IOException {
        AddAll(DATA1, DATA2);
        ReadData(DATA2, Split, Map2);
        ForSearching(SearchOnRight, Map2, WordChoosing);
        System.out.println("themtatca");
    }

    @FXML
    void Remove(ActionEvent event) throws IOException {
        String selectedItem = WordChoosing.getSelectionModel().getSelectedItem().toLowerCase();
        remove(DATA2, selectedItem);
        ReadData(DATA2, Split, Map2);
        ForSearching(SearchOnRight, Map2, WordChoosing);
    }

    @FXML
    void RemoveAll(ActionEvent event) throws IOException {
        EmptyFile(DATA2);
        Map2.clear();
        ReadData(DATA2, Split, Map2);
        ForSearching(SearchOnRight, Map2, WordChoosing);
        System.out.println("xoatatca");
    }
}