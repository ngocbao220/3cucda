package OurPackage.Controller;

import OurPackage.Module.*;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static OurPackage.Controller.HomeController.MarkedWord;
import static OurPackage.Module.TranslateText.translate;


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
    private Button removeWordOnHisWord;

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

    public Map<String, String> List = new LinkedHashMap<>();

    public static String wordToSpeed;

    public static String strTemp = "";

    @FXML
    private WebView InfoOfWords;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        if (!strTemp.equals(".")) {
            Search.setText(strTemp);
            ForSearchingDicWord(Search, List, ListDic);
        }
        // Dua chuot vao 1 giay sau hien goi y
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
        // cai phong chu cho listDic
        ListDic.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    // Thiết lập font chữ tùy chỉnh cho các mục trong ListView
                    setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
                }
            }
        });

        // Phong chu cho HisWord
        DisplayHistoryWord.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    // Thiết lập font chữ tùy chỉnh cho các mục trong ListView
                    setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
                }
            }
        });

        removeWordOnHisWord.setOnAction(e -> {
            if (HisWord.isEmpty()) return;

            if(DisplayHistoryWord.getSelectionModel().isEmpty()) {
                DisplayHistoryWord.getSelectionModel().select(0);
            }

            String s = DisplayHistoryWord.getSelectionModel().getSelectedItem();
            HisWord.remove(s);
            displayDicWords(HisWord, DisplayHistoryWord);
        });

    }

    @FXML
    void CLoseNonLoveWord(MouseEvent event) {
        LoveWord.setVisible(false);
    }

    @FXML
    void Mark(ActionEvent event) {
        if (!ListDic.getSelectionModel().isEmpty()) {
            String s = ListDic.getSelectionModel().getSelectedItem();
            MarkedWord.put(s, translate(s, "vi"));
            System.out.println("Dathem");
        }
        return ;
    }

    @FXML
    void Say(ActionEvent event) {
        TextToSpeech.speak(wordToSpeed);
        System.out.println("I'llSpeak: " + wordToSpeed);
    }

    @FXML
    void CloseNonHis(ActionEvent event) {
        PaneHistory.setVisible(false);
    }

    @FXML
    void showHistoryWord(ActionEvent event) {
        PaneHistory.setVisible(true);
        System.out.println(HisWord.isEmpty());
        if (HisWord.isEmpty()) {
            SayNothing.setVisible(true);
            removeWordOnHisWord.setVisible(false);
        } else {
            SayNothing.setVisible(false);
            displayDicWords(HisWord, DisplayHistoryWord);
            DisplayHistoryWord.setVisible(true);
            removeWordOnHisWord.setVisible(true);
        }
    }


    public void DicWords() {
        DatabaseManager.DictionaryWords();
        List = DatabaseManager.list;
        ForSearchingDicWord(Search, List, ListDic);

        // Hien info cua tu minh tim kiem
        InfoOfWords.getEngine().loadContent("<h1>welcome</h1><h3><i>/'welkəm/</i></h3><h2>tính từ</h2><ul><li>được tiếp đãi ân cần, được hoan nghênh<ul style=\"list-style-type:circle\"><li>a welcome guest:<i> một người khách được tiếp đãi ân cần</i></li><li>to make someone welcome:<i> đón tiếp ai ân cần; làm cho ai thấy mình là khách được hoan nghênh</i></li><li>to be welcome:<i> cứ tự nhiên, cứ việc dùng, được tự do</i></li><li>you are welcome to my bicycle:<i> anh cứ việc dùng xe đạp của tôi</i></li><li>you are welcome to go with them or to stay at home:<i> anh muốn đi với họ hay ở nhà cũng được, xin cứ tự nhiên</i><br/></li></ul></li><li>hay, dễ chịu, thú vị<ul style=\"list-style-type:circle\"><li>a welcome change:<i> sự thay đổi dễ chịu</i></li><li>welcome news:<i> tin hay, tin vui</i></li><li>to be most welcome:<i> đến đúng lúc</i></li></ul></li></ul><h2>thành ngữ</h2><ol><li>you are welcome<ul><li>(từ Mỹ,nghĩa Mỹ),  (thông tục) không dám, có gì đâu (nói để đáp lại lời cm n)</li></ul></li></ol><h2>thán từ</h2><ul><li>hoan nghênh!<ul style=\"list-style-type:circle\"><li>Welcome to Vietnam!:<i> hoanh nghênh các bạn đến thăm Việt nam!</i></li></ul></li></ul><h2>danh từ</h2><ul><li>sự được tiếp đ i ân cần, sự đón tiếp ân cần; sự hoan nghênh<ul style=\"list-style-type:circle\"><li>to receive a warm welcome:<i> được đón tiếp niềm nở</i></li><li>to meet with a cold welcome:<i> được đón tiếp một cách lạnh nhạt</i></li><li>to wear out (outstay) one's welcome:<i> ở chi lâu đến nỗi người ta không muốn tiếp nữa</i></li><li>to bid someone welcome:<i> chào mừng ai</i></li></ul></li></ul><h2>ngoại động từ</h2><ul><li>đón tiếp ân cần; hoan nghênh<ul style=\"list-style-type:circle\"><li>to welcome a friend home:<i> đón tiếp ân cần một người bạn ở nhà mình, hoan nghênh một người bạn đi xa mới</i>về nước<br/></li><li>to welcome a suggestion:<i> hoan nghênh một lời gợi ý</i></li></ul></li></ul>");
        ListDic.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                List.forEach((key, value) -> {
                    if (key.equals(newVal)) {
                        InfoOfWords.getEngine().loadContent(value);
                        wordToSpeed = key;
                        HisWord.put(key, value);
                    }
                });
            }
        });

        DisplayHistoryWord.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                List.forEach((key, value) -> {
                    if (key.equals(newVal)) {
                        InfoOfWords.getEngine().loadContent(value);
                        wordToSpeed = key;
                    }
                });
            }
        });
    }

    public static void displayDicWords(Map<String, String> dictionary, ListView<String> listView) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                items.add(entry.getKey());
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
            if (entry.getKey().startsWith(input.toLowerCase())) {
                result.add(entry.getKey());
            }
        }
        listView.setItems(result);
    }

    public static void ForSearchingDicWord(TextField inputTextField, Map<String, String> dictionary, ListView<String> listView) {

        displayDicWords(dictionary, listView);

        if (!inputTextField.getText().isEmpty()) {
            searchDicWord(inputTextField, dictionary, listView);
        }

        // Đặt sự kiện khi nhập liệu vào TextField
        inputTextField.setOnKeyReleased(event -> {
            // Gọi hàm searchWord để tìm kiếm và hiển thị kết quả
            searchDicWord(inputTextField, dictionary, listView);
        });
    }

}

