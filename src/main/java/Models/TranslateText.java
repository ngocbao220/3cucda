package Models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TranslateText {
    private static String encodeValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    public static String translate(String text, String targetLanguage) {
        try {
            text = text.trim();

            String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl="
                    + targetLanguage + "&dt=t&q=" + encodeValue(text);

            URL apiUrl = URI.create(url).toURL();

            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            return processTranslationResponse(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String processTranslationResponse(String jsonResponse) {
        Gson gson = new Gson();

        JsonArray jsonArray = gson.fromJson(jsonResponse, JsonArray.class).get(0).getAsJsonArray();

        StringBuilder translation = new StringBuilder();
        for (JsonElement miniArray : jsonArray) {
            if (miniArray != null) {
                translation.append(miniArray.getAsJsonArray().get(0).getAsString());
            }
        }

        return translation.toString();
    }
}
