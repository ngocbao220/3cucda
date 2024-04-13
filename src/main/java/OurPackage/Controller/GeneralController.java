package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneralController implements Initializable {

    public Pane BackEnd;

    private final String Screen_Path = "src/main/resources/OurPackage/Screen/";
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
    private Pane translate;

    @FXML
    private JFXButton but_ENG_VIE;

    @FXML
    private Label lb_tran1;

    @FXML
    private Label lb_tran2;

    @FXML
    private JFXButton bonus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        but_home.setOnAction(e ->FXMLLoaderUtil.loadFXMLToPane(Screen_Path + "Home-view", BackEnd));
        but_trans.setOnAction(e ->FXMLLoaderUtil.loadFXMLToPane(Screen_Path + "Translate-view", BackEnd));
        but_diction.setOnAction(e ->FXMLLoaderUtil.loadFXMLToPane(Screen_Path + "Dictionary-view", BackEnd));
        but_game.setOnAction(e ->FXMLLoaderUtil.loadFXMLToPane(Screen_Path + "Game-view", BackEnd));
        but_home.setOnAction(e ->FXMLLoaderUtil.loadFXMLToPane(Screen_Path + "Home-view", BackEnd));
    }
}
