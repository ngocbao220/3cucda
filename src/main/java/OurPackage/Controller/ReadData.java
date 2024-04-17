package OurPackage.Controller;

import javafx.fxml.Initializable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Spliterator;

public class ReadData implements Initializable {

    private final String DATA_PATH = "Data/DataForGame.txt";
    private final String CHAR_SPLIT = "\t";
    Map<String, String> data = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(DATA_PATH);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] parts = line.split(CHAR_SPLIT);
            String word = parts[1];
            String definition = parts[0];
            data.put(word, definition);
        }
    }
}
