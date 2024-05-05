package OurPackage;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Start extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(Start.class.getResource("Screen/Home-view.fxml"));

        Scene scene = new Scene(fxmlLoader1.load());
        stage.setResizable(false);
        stage.setTitle("Our App");

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)   {
        launch();
    }
}