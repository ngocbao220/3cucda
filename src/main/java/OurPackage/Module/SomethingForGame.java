package OurPackage.Module;

import OurPackage.Controller.SettingController;
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
    static String basePath = "src/main/resources/OurPackage/SupportScreen/sound/";

    // Hàm cho phát âm thanh
    public static void PlayMusic(String NameFileMusic, int NumofReplay) {
        String soundPath = getRelativePath(basePath + NameFileMusic);
        if (soundPath != null) {
            Media media = new Media(soundPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(NumofReplay);
            mediaPlayer.setVolume(SettingController.volumeGame);
            System.out.println(SettingController.volumeGame);
        } else {
            System.out.println("Không thể tìm thấy file âm thanh.");
        }
    }

    // Hàm để lấy đường dẫn tương đối của tệp
    public static String getRelativePath(String fileName) {
        String currentDirectory = System.getProperty("user.dir");
        String path = currentDirectory + "/" + fileName;
        File file = new File(path);
        if (file.exists()) {
            return file.toURI().toString();
        } else {
            return null;
        }
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
    public static void AddData(String word, String Mean,Map<String, String> map, String Path_Data) throws IOException {
        if (map.containsKey(Mean)) {
            setWord(Path_Data, word,word + "\t" + Mean);
        } else {
            map.put(Mean, word);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DATA2, true));
            bufferedWriter.write(word + "\t" + Mean + "\n");
            bufferedWriter.close();
        }
    }


    // Them tu trong listView
    public static void AddItemsOfListView(JFXListView<String> listView) {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectedItem = selectedItem.toLowerCase();
            try {
                AddData(selectedItem, getDef(selectedItem, Map1), Map2, DATA2);
                Map2.put(getDef(selectedItem, Map1), selectedItem);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    // Them tat ca
    public static void AddAll(String pathData1, String pathData2) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(pathData1)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathData2)));

        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line.toLowerCase());
            writer.newLine(); // Thêm dòng mới sau mỗi dòng
        }

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


    //Xoa
    public static void remove(String filePath, String searchString) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        // Đoc file va luu vao 1 stringbuilder
        while ((line = reader.readLine()) != null) {
            if (line.contains(searchString.trim().toLowerCase())) continue;
            else {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(stringBuilder.toString());
        writer.close();

    }

    //Sua
    public static void setWord(String filePath, String searchString, String newContent) throws IOException {
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
