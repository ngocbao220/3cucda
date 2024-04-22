package OurPackage.Controller;

import OurPackage.Module.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    private ListView<String> ListDic;

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

    public static String strTemp;
    @FXML
    private WebView InfoOfWords;

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
        DicWords();

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
    public Map<String, String> List = new LinkedHashMap<>();

    public void DicWords() {
        DatabaseManager.DictionaryWords();
        List = DatabaseManager.list;
        ForSearchingDicWord(Search, List, ListDic);

        ListDic.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                List.forEach((key, value) -> {
                    if (value.equals(newVal)) {
                        InfoOfWords.getEngine().loadContent(key);
                        HisWord.put(key, value);
                    }
                });
            }
        });
    }

    public static void displayDicWords(Map<String, String> dictionary, ListView<String> listView) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                items.add(entry.getValue());
        }
        listView.setItems(items);
    }

    public static void searchDicWord(TextField inputTextField, Map<String, String> dictionary, ListView<String> listView) {
        String input = inputTextField.getText().trim().toLowerCase();
        if (input.isEmpty()) {
            displayDicWords(dictionary, listView); // Hiển thị tất cả các từ khi không có input
            return;
        }
        ObservableList<String> result = FXCollections.observableArrayList();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            if (entry.getValue().startsWith(input.toLowerCase())) {
                result.add(entry.getValue());
            }
        }
        listView.setItems(result);
    }

    public static void ForSearchingDicWord(TextField inputTextField, Map<String, String> dictionary, ListView<String> listView) {

        displayDicWords(dictionary, listView);

        // Đặt sự kiện khi nhập liệu vào TextField
        inputTextField.setOnKeyReleased(event -> {
            // Gọi hàm searchWord để tìm kiếm và hiển thị kết quả
            searchDicWord(inputTextField, dictionary, listView);
        });
    }

}

