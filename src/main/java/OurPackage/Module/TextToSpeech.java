package OurPackage.Module;

import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class TextToSpeech {
    public static void speak(String wordToSpeech, Button image, ImageView loading) {
        try {
            String apiKey = "ec3d616154994f4a891ce5d297175493";  // khóa API của bạn
            String text = wordToSpeech;  // Văn bản bạn muốn chuyển đổi
            String language = "en-us";  // Ngôn ngữ giọng nói
            String voice = "Mike"; // tên của giọng nói US: Linda, Amy, John, Mike, Mary
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());

            URL url = new URL("http://api.voicerss.org/?key=" + apiKey + "&hl=" + language + "&v=" + voice + "&src=" + encodedText);
            Media media = new Media(url.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

            loading.setVisible(true);
            action1(loading, 1);

            mediaPlayer.setOnPlaying(() -> {
                loading.setVisible(false);
                image.setPrefSize(image.getWidth()*1.1, image.getHeight()*1.1);
            });
            mediaPlayer.setOnEndOfMedia(() -> {
                image.setPrefSize(image.getWidth()/1.1, image.getHeight()/1.1);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void action1(ImageView imageView, double speech) {

        // Create RotateTransition
        RotateTransition transition = new RotateTransition(Duration.seconds(speech), imageView);
        transition.setByAngle(360);
        transition.setCycleCount(-1);

        // Start the animation
        transition.play();
    }
}
