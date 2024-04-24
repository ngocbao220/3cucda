package OurPackage.Controller;

import OurPackage.Module.TranslateText;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.media.MediaView;
import java.net.URL;
import java.util.ResourceBundle;



public class TranslateTextController extends GeneralController implements Initializable {
    private String Language = "vi";

    private int count = 0;

    private Timeline timer, timeline;

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
    public TextArea TextIn;

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
    private ImageView phihanhgia1;

    @FXML
    private ImageView phihanhgia2;

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
    private JFXButton swapLang;

    @FXML
    private JFXButton but_set;

    @FXML
    private Pane PaneOverSwap;

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

    public static String strTemp1 = "";

    private int time = 100;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        super.initialize(url, resourceBundle);
        timer = new Timeline(new KeyFrame(Duration.millis(time),
                event -> {
                    String inputText = TextIn.getText();
                    if (inputText.isBlank()) {
                        TextOut.clear();
                        return;
                    }
                    TextOut.setText(TranslateText.translate(inputText, Language));
                }));
        timer.setCycleCount(1);

        if (!strTemp1.equals(".")) {
            TextIn.setText(strTemp1);
            timer.play();
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(time + 500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                swapLang.setDisable(false); // Kích hoạt lại nút sau khi đợi 3 giây
            }
        }));
        timeline.setCycleCount(1);
        swapLang.setOnAction(e -> {
            if (count %2 != 0 ) {
                swapLang.setDisable(true);
                swapStackPanes(Sr, Sl);
                TextIn.setText(TextOut.getText());
                Language = "vi";
                timer.play();
                count++;
                timeline.play();
            } else {
                swapLang.setDisable(true);
                swapStackPanes(Sl, Sr);
                TextIn.setText(TextOut.getText());
                Language = "en";
                timer.play();
                count++;
                timeline.play();
            }
        });

        action(phihanhgia1, 1.5);
        action(phihanhgia2, 1.5);
    }

    // Phương thức để hoán đổi vị trí giữa hai StackPane
    private void swapStackPanes(StackPane stackPane1, StackPane stackPane2) {
        double a = Math.abs(stackPane2.getLayoutY() - stackPane1.getLayoutY());
        double b = -a;

        TranslateTransition transition1 = createTransition(stackPane1, 0, a);
        TranslateTransition transition2 = createTransition(stackPane2, 0, b);

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
    void DoSwapLang(ActionEvent event) {

        if (count %2 != 0 ) {
            swapLang.setDisable(true);
            swapStackPanes(Sr, Sl);
            TextIn.setText(TextOut.getText());
            Language = "vi";
            timer.play();
            count++;
            timeline.play();
            swapLang.setDisable(false);
        } else {
            swapLang.setDisable(true);
            swapStackPanes(Sl, Sr);
            TextIn.setText(TextOut.getText());
            Language = "en";
            timer.play();
            count++;
            timeline.play();
            swapLang.setDisable(false);
        }
    }

    public void action(ImageView imageView, double speech) {

        // Create RotateTransition
        RotateTransition transition = new RotateTransition(Duration.seconds(speech), imageView);
        transition.setFromAngle(-20);
        transition.setToAngle(20);
        transition.setCycleCount(-1);
        transition.setAutoReverse(true);

        // Start the animation
        transition.play();
    }
    // tu dong dich khi nhap van ban
    @FXML
    void translate(KeyEvent event) {
        timer.play();
    }
}
