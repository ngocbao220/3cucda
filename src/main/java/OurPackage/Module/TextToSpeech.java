package OurPackage.Module;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TextToSpeech {
    String WordToSpeech;
    String language;
    String voiceName;
    String speedRate;

    public TextToSpeech(String Word, String language, String voiceName, String speedRate) {
        this.WordToSpeech = Word;
        this.language = language;
        this.voiceName = voiceName;
        this.speedRate = speedRate;
        speak();
    }

    public void speak() {
        String apiKey = "ec3d616154994f4a891ce5d297175493";

        String wordToRead = WordToSpeech;

        try {
            String apiUrl = "http://api.voicerss.org/?";
            String apiKeyParam = "key=" + URLEncoder.encode(apiKey, StandardCharsets.UTF_8);
            String textParam = "src=" + URLEncoder.encode(wordToRead, StandardCharsets.UTF_8);
            String langParam = language;
            String voiceParam = "v=" + voiceName;
            String speedParam = "r=" + speedRate;

            URL url = new URL(apiUrl + apiKeyParam + "&" + textParam + "&" + langParam + "&" + voiceParam + "&" + speedParam);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();

                Thread.sleep(clip.getMicrosecondLength() / 1000);

                audioInputStream.close();
                bufferedInputStream.close();
                inputStream.close();
            } else {
                System.out.println("Error: " + responseCode);
            }

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}