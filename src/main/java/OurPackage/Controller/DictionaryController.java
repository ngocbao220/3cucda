package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DictionaryController extends GeneralController{

    @FXML
    private Pane Back;

    @FXML
    private Button CloseNonHis;

    @FXML
    private Label CloseHistoryWord;

    @FXML
    private Pane DisplayContent;

    @FXML
    private ListView<String> DisplayHistoryWord;

    @FXML
    private Label Example;

    @FXML
    private Button HistoryWord;

    @FXML
    private ListView<String> ListWord;

    @FXML
    private Label LoveWord;

    @FXML
    private Button MarkWord;

    @FXML
    private Label Mean;

    @FXML
    private AnchorPane Menu;

    @FXML
    private VBox Menutab;

    @FXML
    private Pane PaneHistory;

    @FXML
    private Label Pronounce;

    @FXML
    private Pane SayNothing;

    @FXML
    private Button SayWord;

    @FXML
    private TextField Search;

    @FXML
    private Pane SearchTab;

    @FXML
    private Label Type;

    @FXML
    private Label Word;

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
    private HBox root;

    @FXML
    private Button ButtonMark;

    public Map<String, String> HisWord = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        ButtonMark.setOnMouseEntered(event -> {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), e -> {
                        LoveWord.setVisible(true);
                    })
            );
            timeline.play();
        });

        ButtonMark.setOnMouseExited(event -> {
            LoveWord.setVisible(false);
        });
    }

    @FXML
    void CLoseNonLoveWord(MouseEvent event) {
        LoveWord.setVisible(false);
    }

    @FXML
    void Mark(ActionEvent event) {
        HomeController homeController = new HomeController();
        homeController.MarkedWord.put("hello","xin chào");
        System.out.println("Dathem");
    }

    @FXML
    void Say(ActionEvent event) {
        System.out.println("I'llSpeak");
    }

    @FXML
    void CloseNonHis(ActionEvent event) {
        PaneHistory.setVisible(false);
    }

    @FXML
    void Showhistroyword(ActionEvent event) {
        PaneHistory.setVisible(true);
        if (HisWord.isEmpty()) {
            SayNothing.setVisible(true);
        }
    }
}
