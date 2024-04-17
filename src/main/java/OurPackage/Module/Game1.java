package OurPackage.Module;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Game1 {
    private final String DATA = "Data/DataForGame";
    private final String Split = "\t";

    public Map<String, String> myMap = new HashMap<>();

    public void ReadData() throws IOException {
        FileReader fileReader = new FileReader(DATA);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(Split);
            String word = parts[1];
            String answer = parts[0];
            myMap.put(word, answer);
        }
    }
}
