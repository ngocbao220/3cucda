package OurPackage.Controller;

import OurPackage.Module.TranslateText;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class TranslateTextController extends GeneralController implements Initializable {
    private String Language = "vi";

    private int count = 0;

    private Timeline timer;

    @FXML
    private CheckBox TurnOnMedia;

    @FXML
    private Pane MainPane;

    @FXML
    private MediaView mediaview;

    @FXML
    private Label LangIn;

    @FXML
    private Label LangOut;

    @FXML
    private StackPane Sl;

    @FXML
    private StackPane Sr;

    @FXML
    private TextArea TextIn;

    @FXML
    private TextArea TextOut;

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton but_home;

    @FXML
    private JFXButton but_trans;

    @FXML
    private JFXButton but_trans1;

    @FXML
    private ImageView catcute;

    @FXML
    private AnchorPane dictionary;

    @FXML
    private Pane game;

    @FXML
    private AnchorPane home;

    @FXML
    private Pane inner_pane;

    @FXML
    private Label lb_tran1;

    @FXML
    private Label lb_tran2;

    @FXML
    private Button swapLang;

    @FXML
    private JFXButton but_set;

    @FXML
    private Pane menu_inner_pane;

    @FXML
    private HBox root;

    @FXML
    private AnchorPane side_anPane;

    @FXML
    private JFXButton swap;

    @FXML
    private Pane translate;

    private final String Media_Path = "src/main/resources/OurPackage/SupportScreen/media/trăng.mp4";

    Media media = new Media(new File(Media_Path).toURI().toString());

    MediaPlayer mediaPlayer = new MediaPlayer(media);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        timer = new Timeline(new KeyFrame(Duration.millis(10),
                event -> {
                    String inputText = TextIn.getText();
                    if (inputText.isBlank()) {
                        TextOut.clear();
                        return;
                    }
                    TextOut.setText(TranslateText.translate(inputText, Language));
                }));
        timer.setCycleCount(1);

       /* mediaview.fitWidthProperty().bind(MainPane.widthProperty());
        mediaview.fitHeightProperty().bind(MainPane.heightProperty());

        mediaview.setMediaPlayer(mediaPlayer);

        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));

        mediaview.getMediaPlayer().play();*/
    }

    // Phương thức để hoán đổi vị trí giữa hai StackPane
    private void swapStackPanes(StackPane stackPane1, StackPane stackPane2) {
        double a = 525.0 - 81.0;
        double b = -a;

        TranslateTransition transition1 = createTransition(stackPane1, a, 0);
        TranslateTransition transition2 = createTransition(stackPane2, b, 0);

        transition1.play();
        transition2.play();
    }

    // Phương thức để tạo hiệu ứng di chuyển cho StackPane
    private TranslateTransition createTransition(StackPane stackPane, double deltaX, double deltaY) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), stackPane);
        transition.setByX(deltaX);
        transition.setByY(deltaY);
        return transition;
    }
    // Sự kiện ấn nút đổi
    @FXML
    void swapLang(ActionEvent event) {
        if (count %2 != 0 ) {
            swapStackPanes(Sr, Sl);
            TextIn.setText(TextOut.getText());
            Language = "vi";
            timer.play();
            count++;
        } else {
            swapStackPanes(Sl, Sr);
            TextIn.setText(TextOut.getText());
            Language = "en";
            timer.play();
            count++;
        }
    }

    @FXML
    void MouseEn(MouseEvent event) {
        /*swapLang.setScaleX(1.1);
        swapLang.setScaleY(1.1);*/
    }

    @FXML
    void MouseEx(MouseEvent event) {
        /*swapLang.setScaleX(1.0);
        swapLang.setScaleY(1.0);*/
    }
    // tu dong dich khi nhap van ban
    @FXML
    void translate(KeyEvent event) {
        timer.play();
    }

    public void switchtoHome(ActionEvent event) throws IOException {
        super.switchtoHome(event);
    }

    public void switchtoGame(ActionEvent event) throws IOException {
        super.switchtoGame(event);
    }

    public void switchtoDic(ActionEvent event) throws IOException {
        super.switchtoDic(event);
    }

    public void switchtoTran(ActionEvent event) throws IOException {
        super.switchtoTran(event);
    }

    public void switchtoSet(ActionEvent event) throws IOException {
        super.switchtoSet(event);
    }


    /*void openMedia(boolean a) {
        if(a) {
            mediaview.setVisible(true);
            mediaview.getMediaPlayer().play();
        }
        else mediaview.setVisible(false);
    }

    @FXML
    void TurnOnMedia(ActionEvent event) {
        if (TurnOnMedia.isSelected()) {
            openMedia(true);
        }
        else openMedia(false);
    }*/
}
