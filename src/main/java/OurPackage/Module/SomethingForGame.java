package OurPackage.Module;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;

import java.util.Map;

import static OurPackage.Controller.MonkeyGameController.*;

public class SomethingForGame {

    // Ham doc du lieu
    public static void ReadData(String DATA, String Split, Map<String, String> map) throws IOException {
        map.clear();
        FileReader fileReader = new FileReader(DATA);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(Split);
            if (parts.length != 2) {
                continue;
            }
            String word = parts[1].trim().toLowerCase();
            String answer = parts[0].trim().toLowerCase();
            map.put(word, answer);
        }
    }


    public static MediaPlayer mediaPlayer;
    static String PathMusic = "file:/C:/java/BTL_APP/3cucda/src/main/resources/OurPackage/SupportScreen/sound/";

    // Ham choi am thanh
    public static void PlayMusic(String FileMusicPath) {
        Media media = new Media(PathMusic + FileMusicPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
    }

    public static void PauseMusic() {
        mediaPlayer.pause();
    }

    public static void StopMusic() {
        mediaPlayer.stop();
    }

    public static void Play() {
        mediaPlayer.play();
    }

    // Ham hien thi tu
    public static void displayAllWords(Map<String, String> dictionary, JFXListView<String> listView) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            String word = entry.getValue().trim();
            items.add(word);
        }
        listView.setItems(items);
    }


    // Ham lay nghia
    public static String getDef(String word, Map<String, String> map) {
        for (String line : map.keySet()) {
            if (map.get(line).trim().toLowerCase().equals(word)) return line;
        }
        return "";
    }


    // ham tim kiem
    public static void searchWord(TextField inputTextField, Map<String, String> dictionary, JFXListView<String> listView) {
        String input = inputTextField.getText().trim().toLowerCase();
        if (input.isEmpty()) {
            displayAllWords(dictionary, listView); // mac dinh hien tu khi chua co gi
            return;
        }
        ObservableList<String> result = FXCollections.observableArrayList();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            if (entry.getValue().startsWith(input)) {
                result.add(entry.getValue());
            }
        }
        listView.setItems(result);
    }


    // Hien thi tu va dat lenh tim kiem
    public static void ForSearching(TextField inputTextField, Map<String, String> dictionary, JFXListView<String> listView) {

        displayAllWords(dictionary, listView);

        inputTextField.setOnKeyReleased(event -> {
            //goi ham tim kiem va hien thi ket qua
            searchWord(inputTextField, dictionary, listView);
        });
    }


    // Them tu moi
    public static void AddData(String word, String Mean) throws IOException {
        Map2.put(Mean, word);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DATA2, true));
        bufferedWriter.write(word + "\t" + Mean + "\n");
        bufferedWriter.close();
    }


    // Them tu trong listView
    public static void AddItemsOfListView(JFXListView<String> listView) {
        String selectedItem = listView.getSelectionModel().getSelectedItem().toLowerCase();
        try {
            AddData(selectedItem, getDef(selectedItem, Map1));
            Map2.put(getDef(selectedItem, Map1), selectedItem);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Them tat ca
    public static void AddAll(String pathData1, String pathData2) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(pathData1)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathData2)));

        String line;
        while ((line = reader.readLine()) != null) {
            // Áp dụng hàm toLowerCase() cho từng dòng và ghi vào file đích
            writer.write(line.toLowerCase());
            writer.newLine(); // Thêm dòng mới sau mỗi dòng
        }

        // Đóng các luồng
        reader.close();
        writer.close();
    }


    // Thiet lap File rong
    public static void EmptyFile(String PathData) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(PathData));
        fileWriter.write("");
        Map2.clear();
        fileWriter.close();
    }


    //Go tu
    public static void remove(String filePath, String searchString, String newContent) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        // Đoc file va luu vao 1 stringbuilder
        while ((line = reader.readLine()) != null) {
            if (line.contains(searchString.trim().toLowerCase())) {
                stringBuilder.append(newContent).append(System.lineSeparator());
            } else {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(stringBuilder.toString());
        writer.close();

    }

}
