package OurPackage.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController extends GeneralController {

    @FXML
    private JFXButton but_diction;

    @FXML
    private JFXButton but_set;

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


}
