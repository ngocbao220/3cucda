package OurPackage;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Start extends Application {
    public static int seconds = 0;
    public static int minutes = 0;
    public static int hours = 0;

    public static String timeString;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(Start.class.getResource("Screen/Home-view.fxml"));
        Scene scene = new Scene(fxmlLoader1.load());
        stage.setResizable(false);
        stage.setTitle("Our App");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            updateTimer();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        stage.setScene(scene);
        stage.show();
    }

    private void updateTimer() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }
        }
        timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    public static void main(String[] args)   {
        launch();
    }
}