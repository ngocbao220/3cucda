package OurPackage.Controller;

import OurPackage.Module.*;
import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.prefs.Preferences;

public class GeneralController implements Initializable {

    private final String Screen_Path = "/OurPackage/Screen/";

    @FXML
    private Pane DisplayContent;

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton but_home;

    @FXML
    private ImageView catcute;

    @FXML
    private AnchorPane diction_means;

    @FXML
    private AnchorPane dictionary;

    @FXML
    private AnchorPane home;

    @FXML
    private Pane inner_pane;

    @FXML
    private AnchorPane intro_diction;

    @FXML
    private Pane menu_inner_pane;

    @FXML
    private HBox root;

    @FXML
    private AnchorPane side_anPane;

    @FXML
    private JFXButton but_move_game;

    @FXML
    private JFXButton but_move_diction;

    @FXML
    private AnchorPane move_diction;

    @FXML
    private AnchorPane move_game;

    @FXML
    private JFXButton but_trans;

    @FXML
    private JFXButton but_set;

    @FXML
    private Pane game;

    @FXML
    private Pane Back;


    @FXML
    private Pane translate;

    @FXML
    private JFXButton but_ENG_VIE;

    @FXML
    private Label lb_tran1;

    @FXML
    private Label lb_tran2;

    @FXML
    private JFXButton bonus;

    @FXML
    private JFXButton but_to_game1;

    @FXML
    private JFXButton but_to_game2;

    private Stage stage;
    private Scene scene;

    public List<JFXButton> listbut = new ArrayList<>();

    private final static String DATABASE_PATH = "./Data/";

    private final static String DATABASE_NAME = "dict_hh.db";

    public static Set<String> MarkedWord = new HashSet<>();

    public DatabaseManager databaseManager = new DatabaseManager(DATABASE_PATH, DATABASE_NAME);

    public BookMark bookMark ;

    protected HistorySearch historySearch = new HistorySearch(new StringBuilder()
                                                                    .append(DATABASE_PATH)
                                                                    .append("historyDic.txt").toString());

    protected HistorySearch historyActivites = new HistorySearch(new StringBuilder()
                                                                    .append(DATABASE_PATH)
                                                                    .append("activitiesHistoryApp.txt").toString());

    protected static Preferences prefs = Preferences.userNodeForPackage(SettingController.class);
    public static double volumeGame = prefs.getDouble("game", 5);

    public void Load(String s, Pane container) throws IOException {
        Pane parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(s)));
        stage = (Stage) container.getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    public void LoadScene(String s, Pane container) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setNode(container);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(e -> {
            try {
                Load(Screen_Path + s, container);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        fadeTransition.play();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        but_home.setOnAction(e -> {
            but_set.setDisable(true);
            but_diction.setDisable(true);
            but_trans.setDisable(true);
            but_game.setDisable(true);
            but_home.setDisable(true);
            LoadScene("Home-view.fxml", DisplayContent);
            but_set.setDisable(false);
            but_diction.setDisable(false);
            but_trans.setDisable(false);
            but_game.setDisable(false);
            but_home.setDisable(false);
        });
        but_trans.setOnAction(e -> {
            but_trans.setDisable(true);
            but_set.setDisable(true);
            but_diction.setDisable(true);
            but_home.setDisable(true);
            but_game.setDisable(true);
            LoadScene("Translate-view.fxml", DisplayContent);
            but_set.setDisable(false);
            but_diction.setDisable(false);
            but_home.setDisable(false);
            but_game.setDisable(false);
            but_trans.setDisable(false);
        });
        but_game.setOnAction(e -> {
            but_game.setDisable(true);
            but_set.setDisable(true);
            but_diction.setDisable(true);
            but_home.setDisable(true);
            but_trans.setDisable(true);
            LoadScene("Game-view.fxml", DisplayContent);
            but_game.setDisable(false);
            but_set.setDisable(false);
            but_diction.setDisable(false);
            but_home.setDisable(false);
            but_trans.setDisable(false);
        });
        but_diction.setOnAction(e -> {
            but_diction.setDisable(true);
            but_set.setDisable(true);
            but_game.setDisable(true);
            but_home.setDisable(true);
            but_trans.setDisable(true);
            LoadScene("Dictionary-view.fxml", DisplayContent);
            but_set.setDisable(false);
            but_game.setDisable(false);
            but_home.setDisable(false);
            but_trans.setDisable(false);
            but_diction.setDisable(false);
        });
        but_set.setOnAction(e -> {
            but_diction.setDisable(true);
            but_set.setDisable(true);
            but_game.setDisable(true);
            but_home.setDisable(true);
            but_trans.setDisable(true);
            LoadScene("Setting-view.fxml", DisplayContent);
            but_game.setDisable(false);
            but_diction.setDisable(false);
            but_home.setDisable(false);
            but_trans.setDisable(false);
            but_set.setDisable(false);
        });
        listbut.add(but_game);
        listbut.add(but_diction);
        listbut.add(but_trans);
        listbut.add(but_home);
        listbut.add(but_set);
        ChangeButton_color(but_diction);
        ChangeButton_color(but_trans);
        ChangeButton_color(but_game);
        ChangeButton_color(but_set);
        ChangeButton_color(but_home);

        databaseManager.DictionaryWords();
        bookMark = new DatabaseBookmark("av");
        DictionaryController.List = databaseManager.list;
        historySearch.loadHistory();
    }
    public void ChangeButton_color(JFXButton button) {
        button.setOnMouseClicked(event -> {
            button.setStyle("-fx-border-width: 2px;");
            button.setStyle("-fx-border-color: #333333;");
        });
    }
}
