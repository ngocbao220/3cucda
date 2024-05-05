package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import static OurPackage.Module.DatabaseManager.insertWordToDB;

public class SettingController extends GeneralController {

    @FXML
    private Pane Back;

    @FXML
    private Pane DisplayContent;

    @FXML
    private Label non;

    @FXML
    private TextField IPAforWord;

    @FXML
    private TextField MeanOfWord;

    @FXML
    private Pane paneNon;

    @FXML
    private AnchorPane Menu;

    @FXML
    private VBox Menutab;

    @FXML
    private JFXButton but_add;

    @FXML
    private JFXButton but_delete;

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
    private JFXButton clearBookMark;

    @FXML
    private JFXButton resetActivitiesHistoryApp;

    @FXML
    private Pane inner_pane;

    @FXML
    private JFXListView<String> listWord;

    @FXML
    private Pane menu_inner_pane;

    @FXML
    private CheckBox musicApp;

    @FXML
    private TextField newWord;

    @FXML
    private Pane paneAddNewWordToDictionary;

    @FXML
    private Pane paneDeleteWord;

    @FXML
    private HBox root;

    @FXML
    private TextField searchWordToDelete;

    @FXML
    private JFXButton sendFeedBack;

    @FXML
    private JFXButton doDelete;

    @FXML
    private JFXButton resetApp;

    @FXML
    private JFXButton addNewWordForDic;

    @FXML
    private Button closePaneAddNewWord;

    @FXML
    private TextArea textFeedBack;


    @FXML
    private TextField typeWord;

    int countDelete = 0;

    private void actionOfNon() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), paneNon);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        but_set.setStyle("-fx-background-color: #333333;");

        but_add.setOnAction(e -> {
            paneAddNewWordToDictionary.setVisible(true);
        });

        closePaneAddNewWord.setOnAction(e -> {
            paneAddNewWordToDictionary.setVisible(false);
        });

        but_delete.setOnAction(e -> {
            paneDeleteWord.setVisible(countDelete % 2 == 0);
            countDelete++;
        });

        resetActivitiesHistoryApp.setOnAction(e -> {

        });

        addNewWordForDic.setOnAction(e -> {
            String word = newWord.getText();
            String pronounce = IPAforWord.getText();
            String type = typeWord.getText();
            String mean = MeanOfWord.getText();
            if (!word.isEmpty() && !pronounce.isEmpty() && !type.isEmpty() && !mean.isEmpty()) {
                insertWordToDB(word, pronounce, type, mean);
                non.setText("Thành công thêm từ: " + word + " !");
                actionOfNon();
            } else {
                non.setText("Lỗi : Vui lòng nhập đủ thông tin từ!");
                actionOfNon();
            }
        });
    }
}
