package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class DictionaryController {

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
    int c = 2;
    @FXML
    public void switchfom(ActionEvent event) {
        if (event.getSource() == but_diction) {
            home.setVisible(false);
            dictionary.setVisible(true);
            game.setVisible(false);
            translate.setVisible(false);
        } else if (event.getSource() == but_home) {
            home.setVisible(true);
            dictionary.setVisible(false);
            game.setVisible(false);
            translate.setVisible(false);
        } else if (event.getSource() == but_game) {
            home.setVisible(false);
            dictionary.setVisible(false);
            game.setVisible(true);
            translate.setVisible(false);
        } else if (event.getSource() == but_trans) {
            home.setVisible(false);
            dictionary.setVisible(false);
            game.setVisible(false);
            translate.setVisible(true);
        }
    }
}
