package OurPackage.Module;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TextToSpeech {
    public static void speak(String wordToSpeech) {
        try {
            String apiKey = "ec3d616154994f4a891ce5d297175493";  // khóa API của bạn
            String text = wordToSpeech;  // Văn bản bạn muốn chuyển đổi
            String language = "en-us";  // Ngôn ngữ giọng nói
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());

            URL url = new URL("http://api.voicerss.org/?key=" + apiKey + "&hl=" + language + "&src=" + encodedText);
            Media media = new Media(url.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
