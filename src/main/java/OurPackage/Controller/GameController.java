package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends GeneralController {
    @FXML
    private Pane Back;

    @FXML
    private Pane DisplayContent;

    @FXML
    private AnchorPane Menu;

    @FXML
    private VBox Menutab;

    @FXML
    private AnchorPane PaneTabGame;

    @FXML
    private Pane SearchTab;

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_game;

    @FXML
    private JFXButton but_home;

    @FXML
    private JFXButton but_set;

    @FXML
    private JFXButton but_to_game1;

    @FXML
    private JFXButton but_to_game2;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        but_to_game1.setOnAction(e -> LoadScene("Game1-view.fxml", DisplayContent));
        but_to_game2.setOnAction(e -> LoadScene("Game2-view.fxml", DisplayContent));
    }

}
