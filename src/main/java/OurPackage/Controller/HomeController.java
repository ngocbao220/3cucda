package OurPackage.Controller;

import OurPackage.Module.DatabaseBookmark;
import OurPackage.Module.DatabaseManager;
import OurPackage.Module.TextToSpeech;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

import static OurPackage.Controller.DictionaryController.*;


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
    private ListView<String> listMarkedWord = new JFXListView<>();

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton showNons;

    @FXML
    private ListView<String> listNon;

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
    private Pane stackPaneMeanWord;

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
    private ImageView loading;

    @FXML
    private JFXButton butSay;

    @FXML
    private Pane nonSwitchTran;

    @FXML
    private JFXButton opendic;

    @FXML
    private JFXButton opengame;

    @FXML
    private JFXButton turnOffMean;

    @FXML
    private JFXButton removeFromMarkedWord;

    @FXML
    private JFXButton opengame2;

    @FXML
    private JFXButton buttonShowInfor;

    @FXML
    private JFXButton openset;

    @FXML
    private JFXButton opentran;

    @FXML
    private HBox root;

    @FXML
    private ImageView iSay;

    @FXML
    private ImageView stop;

    @FXML
    private ImageView imageNewNon;

    @FXML
    private TextField searchTab;

    @FXML
    private WebView infoWord;

    @FXML
    private Label nonNothing;

    private String wordToSpeed;

    private boolean checkOnScreen = false;

     int count = 0;

    ObservableList<String> items = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        if (sizeOfTempList < ListLog.size()) {
            imageNewNon.setVisible(true);
        }
        historyActivites.loadHistory();
        displayHis(historyActivites.getHistoryList(), listNon);

        changeButton_color(butSay);
        but_home.setStyle("-fx-background-color: #333333;");
        List = DatabaseManager.list;

        if (MarkedWord.isEmpty()) {
            listMarkedWord.setVisible(false);
            removeFromMarkedWord.setVisible(false);
            nonNothing.setVisible(true);
        } else {
            listMarkedWord.setVisible(true);
            removeFromMarkedWord.setVisible(true);
            nonNothing.setVisible(false);
        }

        TranslateTransition transition = createTransition(stackPaneMeanWord, 1300, 0.3);
        transition.play();

        searchTab.setOnKeyPressed(e -> Non());
        setupMouseEvents(move_dic_home);
        setupMouseEvents(move_game_home);
        setupMouseEvents(move_trans_home);
        setupMouseEvents(move_set_home);
        setupMouseEvents(move_game_2);

        if (!MarkedWord.isEmpty()) {
            items.addAll(MarkedWord);
            listMarkedWord.setItems(items);
        }

        // Mo webview hien nghia
        buttonShowInfor.setOnAction(e -> {
            checkOnScreen = true;
            butSay.setVisible(true);
            iSay.setVisible(true);
            // Hieu ung chuyen canh
            TranslateTransition transition1 = createTransition(stackPaneMeanWord, -1300, 1);
            transition1.play();
            // Hien thi thong tin
            listMarkedWord.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    List.forEach((key, value) -> {
                        if (key.equals(newVal)) {
                            infoWord.getEngine().loadContent(value);
                            wordToSpeed = key;
                        }
                    });
                }
            });
            buttonShowInfor.setVisible(false);
            turnOffMean.setVisible(true);
        });

        listMarkedWord.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                if (!checkOnScreen) {
                    buttonShowInfor.setVisible(true);
                }
                List.forEach((key, value) -> {
                    if (key.equals(newVal)) {
                        infoWord.getEngine().loadContent(value);
                        wordToSpeed = key;
                    }
                });
            }
        });

        // dong webview hien nghia
        turnOffMean.setOnAction(e -> {
            if (checkOnScreen) {
                butSay.setVisible(false);
                iSay.setVisible(false);
                stop.setVisible(false);
                listMarkedWord.getSelectionModel().clearSelection();
                TranslateTransition transition1 = createTransition(stackPaneMeanWord, -20, 0.5);
                transition1.play();

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
                    TranslateTransition transition2 = createTransition(stackPaneMeanWord, 1320, 0.6);
                    transition2.play();
                }));

                timeline.play();
                turnOffMean.setVisible(false);
                buttonShowInfor.setVisible(false);
                checkOnScreen = false;
            }
        });

        // Xoa MarkedWord
        removeFromMarkedWord.setOnAction(e -> {
            if (!listMarkedWord.getSelectionModel().isEmpty()) {
                ListLog.add("Đã gỡ: " + listMarkedWord.getSelectionModel().getSelectedItem() + "    " + DictionaryController.getTimeNow());
                historyActivites.insertHistory("Đã gỡ: " + listMarkedWord.getSelectionModel().getSelectedItem() + "    " + DictionaryController.getTimeNow());
                currentIndex ++;
                bookMark.removeBookmark(listMarkedWord.getSelectionModel().getSelectedItem());
                items.remove(listMarkedWord.getSelectionModel().getSelectedItem());
                imageNewNon.setVisible(true);

                if (MarkedWord.isEmpty()) {
                    listMarkedWord.setVisible(false);
                    buttonShowInfor.setVisible(false);
                    removeFromMarkedWord.setVisible(false);
                    nonNothing.setVisible(true);
                }
                listMarkedWord.setItems(items);
            } else return;
        });

        showNons.setOnAction(e -> {
            listNon.setVisible(count % 2 == 0);
            count++;
            imageNewNon.setVisible(false);
        });

        listMarkedWord.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);
                    setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
                }
            }
        });


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
            LoadScene("QuizGame-view.fxml", Back);
        });
    }
    // ham tao hieu ung chuyen dong
    private TranslateTransition createTransition(Pane stackPane, double deltaX, double speed) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(speed), stackPane);
        transition.setByX(deltaX);
        return transition;
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

    @FXML
    private void SayWord() {
        if (wordToSpeed != null) {
            TextToSpeech.speak(wordToSpeed, butSay, loading, true, stop, iSay);
        } else return;
    }

    public void setupMouseEvents(Pane pane) {
        pane.setOnMouseEntered(event -> onMouseEntered(pane));
        pane.setOnMouseExited(event -> onMouseExt(pane));
    }

    private void Non() {
        String[] countWords = searchTab.getText().split(" ");
        int numberOfWords = countWords.length;

        if(searchTab.getText().isEmpty()) {
            nonSwitchDic.setVisible(false);
            nonSwitchTran.setVisible(false);
        } else {

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

    public void changeButton_color(JFXButton button) {
        button.setOnMouseEntered(mouseEvent -> button.setStyle("-fx-background-color: rgba(128, 128, 128, 0.08);"));
        button.setOnMouseExited(mouseEvent -> button.setStyle("-fx-background-color: transparent;"));
    }
}
