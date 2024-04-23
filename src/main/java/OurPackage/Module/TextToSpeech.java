package OurPackage.Module;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TextToSpeech {
    public static void speak(String wordToSpeech) {
        try {
            String apiKey = "ec3d616154994f4a891ce5d297175493";  // khóa API của bạn
            String text = wordToSpeech;
            String language = "en-us";
            String urlString = String.format(
                    "http://api.voicerss.org/?key=%s&hl=%s&src=%s",
                    apiKey, language, text.replace(" ", "%20")
            );

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream is = connection.getInputStream();
            OutputStream os = new FileOutputStream("Data/SpeechText.mp3");

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            is.close();
            os.close();

            System.out.println("Audio content written to file 'SpeechText.mp3'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
