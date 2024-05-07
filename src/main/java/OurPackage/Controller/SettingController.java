package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import static OurPackage.Controller.DictionaryController.ListLog;
import static OurPackage.Module.DatabaseManager.list;
import static OurPackage.Start.*;

public class SettingController extends GeneralController {

    @FXML
    private Pane Back;

    @FXML
    private Pane DisplayContent;

    @FXML
    private Label non;

    @FXML
    private Label timeUsingApp;

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

    private final String DATABASE_PATH = "../3cucda/Data/";

    private void actionOfNon() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), paneNon);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        timeUsingApp.setText(timeString);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            updateTimer();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        DictionaryController.ForSearchingDicWord(searchWordToDelete, list, listWord);

        but_set.setStyle("-fx-background-color: #333333;");

        but_add.setOnAction(e -> {
            paneAddNewWordToDictionary.setVisible(true);
            paneDeleteWord.setVisible(false);
        });

        closePaneAddNewWord.setOnAction(e -> {
            paneAddNewWordToDictionary.setVisible(false);
        });

        but_delete.setOnAction(e -> {
            paneDeleteWord.setVisible(countDelete % 2 == 0);
            countDelete++;
            paneAddNewWordToDictionary.setVisible(false);
        });

        doDelete.setOnAction(e -> {
            String wordDelete = listWord.getSelectionModel().getSelectedItem();
            if(wordDelete != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText(null);
                alert.initOwner(DisplayContent.getScene().getWindow());
                alert.setContentText("Bạn có chắc chắn muốn xóa từ: " + wordDelete + " không?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        System.out.println("Người dùng đã chọn: Đồng ý");
                        databaseManager.deleteWordFromDB(wordDelete);
                        listWord.getItems().remove(wordDelete);

                        non.setText("Thành công xóa từ: " + wordDelete + " !");
                        actionOfNon();
                    } else if (response == ButtonType.CANCEL) {
                        System.out.println("Người dùng đã chọn: Không");
                    }
                });
            } else {
                non.setText("Lỗi : Vui lòng chọn từ muốn xóa !");
                actionOfNon();
            }

        });

        resetActivitiesHistoryApp.setOnAction(e -> {
            if(!ListLog.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText(null);
                alert.initOwner(DisplayContent.getScene().getWindow());
                alert.setContentText("Bạn có chắc chắn muốn đặt lại lịch sử hoạt động không?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        historyActivites.clearHistory();

                        ListLog.clear();
                        non.setText("Thành công xóa lịch sử hoạt động !");
                        actionOfNon();
                    } else if (response == ButtonType.CANCEL) {
                        System.out.println("Người dùng đã chọn: Không");
                    }
                });
            } else {
                non.setText("Lịch sử hoạt động trống!");
                actionOfNon();
            }
        });

        addNewWordForDic.setOnAction(e -> {
            String word = newWord.getText();
            String pronounce = IPAforWord.getText();
            String type = typeWord.getText();
            String mean = MeanOfWord.getText();

            if (!word.isEmpty() && !pronounce.isEmpty() && !type.isEmpty() && !mean.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn muốn thêm từ: " + word + " không?");
                alert.initOwner(DisplayContent.getScene().getWindow());
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        System.out.println("Người dùng đã chọn: Đồng ý");
                        databaseManager.insertWordToDB(word, pronounce, type, mean);
                        paneAddNewWordToDictionary.setVisible(false);
                        non.setText("Thành công thêm từ: " + word + " !");
                        actionOfNon();
                    } else if (response == ButtonType.CANCEL) {
                        System.out.println("Người dùng đã chọn: Không");
                    }
                });
            } else {
                non.setText("Lỗi : Vui lòng nhập đủ thông tin từ!");
                actionOfNon();
            }
        });

        clearBookMark.setOnAction(e -> {
            if (!MarkedWord.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn muốn đặt lại danh sách từ yêu thích không?");
                alert.initOwner(DisplayContent.getScene().getWindow());
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        bookMark.clearBookmark();
                        non.setText("Thành công xóa danh sách từ yêu thích !");
                        actionOfNon();
                    } else if (response == ButtonType.CANCEL) {
                        System.out.println("Người dùng đã chọn: Không");
                    }
                });
            } else {
                non.setText("Danh sách từ yêu thích trống !");
                actionOfNon();
            }
        });

        resetApp.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn đặt lại ứng dụng không?\n  (Mọi thứ đều trở về ban đầu)");
            alert.initOwner(DisplayContent.getScene().getWindow());
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    bookMark.clearBookmark();
                    historyActivites.clearHistory();
                    historySearch.clearHistory();
                    databaseManager.resetData();
                    non.setText("Thành công đặt lại ứng dụng !\n(Khởi động lại sẽ có hiệu lực)");
                    actionOfNon();
                } else if (response == ButtonType.CANCEL) {
                    System.out.println("Người dùng đã chọn: Không");
                }
            });
        });
    }


    private void updateTimer() {
        timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeUsingApp.setText(timeString);
    }

}
