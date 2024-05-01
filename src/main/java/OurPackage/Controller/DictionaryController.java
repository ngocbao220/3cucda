package OurPackage.Controller;

import OurPackage.Module.*;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.*;

import static OurPackage.Module.Constructor.*;


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
    private JFXButton SayWord;

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
    private JFXButton RemoveWord;

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
    private ImageView iSay;

    @FXML
    private ImageView stop;

    @FXML
    private ImageView loading;

    @FXML
    private Pane inner_pane;

    @FXML
    private Pane menu_inner_pane;

    @FXML
    private HBox root;

    @FXML
    private Button ButtonMark;

    public static Map<String, String> List = new LinkedHashMap<>();

    public List<String> ListLog = new ArrayList<>();

    public static String wordToSpeed;

    public static String strTemp = "";

    @FXML
    private WebView InfoOfWords;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        but_diction.setStyle("-fx-background-color: #333333;");
        if (!strTemp.equals(".")) {
            Search.setText(strTemp);
            ForSearchingDicWord(Search, List, ListDic);
        }
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
                    if (MarkedWord.contains(item)) {
                        setTextFillForMarkedWord();
                    } else {
                        setTextFillForNormalWord();
                    }
                    setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
                }
            }

            private void setTextFillForMarkedWord() {
                setStyle("-fx-text-fill: #9ACD32;");
            }
            private void setTextFillForNormalWord() {
                setStyle("-fx-text-fill: #000000;");
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

        RemoveWord.setOnMouseClicked(e -> {
            RemoveWord.setVisible(false);
            if (!ListDic.getSelectionModel().isEmpty()) {
                String s = ListDic.getSelectionModel().getSelectedItem();
                databaseBookmark.remove(s);
                ListLog.add("Đã gỡ:" + s);
            }
        });

    }

    @FXML
    void CLoseNonLoveWord(MouseEvent event) {
        LoveWord.setVisible(false);
    }

    @FXML
    void Mark(ActionEvent event) {
        if (!ListDic.getSelectionModel().isEmpty()) {
            RemoveWord.setVisible(true);
            String s = ListDic.getSelectionModel().getSelectedItem();
            databaseBookmark.updateBookmark(s);
            ListLog.add("Đã thêm:" + s);
        }
        if (!DisplayHistoryWord.getSelectionModel().isEmpty()) {
            RemoveWord.setVisible(true);
            String s = DisplayHistoryWord.getSelectionModel().getSelectedItem();
            databaseBookmark.updateBookmark(s);
            ListLog.add("Đã thêm:" + s);
        }
        else return;
    }

    @FXML
    void Say(ActionEvent event) {
        TextToSpeech.speak(wordToSpeed, SayWord, loading,true, stop, iSay);
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
        ForSearchingDicWord(Search, List, ListDic);

        // Hien info cua tu minh tim kiem
        InfoOfWords.getEngine().loadContent("<h1>welcome</h1><h3><i>/'welkəm/</i></h3><h2>tính từ</h2><ul><li>được tiếp đãi ân cần, được hoan nghênh<ul style=\"list-style-type:circle\"><li>a welcome guest:<i> một người khách được tiếp đãi ân cần</i></li><li>to make someone welcome:<i> đón tiếp ai ân cần; làm cho ai thấy mình là khách được hoan nghênh</i></li><li>to be welcome:<i> cứ tự nhiên, cứ việc dùng, được tự do</i></li><li>you are welcome to my bicycle:<i> anh cứ việc dùng xe đạp của tôi</i></li><li>you are welcome to go with them or to stay at home:<i> anh muốn đi với họ hay ở nhà cũng được, xin cứ tự nhiên</i><br/></li></ul></li><li>hay, dễ chịu, thú vị<ul style=\"list-style-type:circle\"><li>a welcome change:<i> sự thay đổi dễ chịu</i></li><li>welcome news:<i> tin hay, tin vui</i></li><li>to be most welcome:<i> đến đúng lúc</i></li></ul></li></ul><h2>thành ngữ</h2><ol><li>you are welcome<ul><li>(từ Mỹ,nghĩa Mỹ),  (thông tục) không dám, có gì đâu (nói để đáp lại lời cm n)</li></ul></li></ol><h2>thán từ</h2><ul><li>hoan nghênh!<ul style=\"list-style-type:circle\"><li>Welcome to Vietnam!:<i> hoanh nghênh các bạn đến thăm Việt nam!</i></li></ul></li></ul><h2>danh từ</h2><ul><li>sự được tiếp đ i ân cần, sự đón tiếp ân cần; sự hoan nghênh<ul style=\"list-style-type:circle\"><li>to receive a warm welcome:<i> được đón tiếp niềm nở</i></li><li>to meet with a cold welcome:<i> được đón tiếp một cách lạnh nhạt</i></li><li>to wear out (outstay) one's welcome:<i> ở chi lâu đến nỗi người ta không muốn tiếp nữa</i></li><li>to bid someone welcome:<i> chào mừng ai</i></li></ul></li></ul><h2>ngoại động từ</h2><ul><li>đón tiếp ân cần; hoan nghênh<ul style=\"list-style-type:circle\"><li>to welcome a friend home:<i> đón tiếp ân cần một người bạn ở nhà mình, hoan nghênh một người bạn đi xa mới</i>về nước<br/></li><li>to welcome a suggestion:<i> hoan nghênh một lời gợi ý</i></li></ul></li></ul>");
        ListDic.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                RemoveWord.setVisible(MarkedWord.contains(newVal));
                List.forEach((key, value) -> {
                    if (key.equals(newVal)) {
                        InfoOfWords.getEngine().loadContent(value);
                        wordToSpeed = key;
                        HisWord.put(key, value);
                    }
                });
            }
        });
        // thong tin cua tu duoc tra truoc do
        DisplayHistoryWord.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                RemoveWord.setVisible(MarkedWord.contains(newVal));
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

