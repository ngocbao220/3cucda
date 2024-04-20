package OurPackage.Module;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.io.*;

import java.util.Map;

import static OurPackage.Controller.MonkeyGameController.Map1;
import static OurPackage.Controller.MonkeyGameController.Map2;

public class SomethingForGame {


    // Ham doc du lieu
    public static void ReadData(String DATA, String Split, Map<String, String> map) throws IOException {
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
        System.out.println(map.size());
    }


    // Ham hien thi tu
    public static void displayAllWords(Map<String, String> dictionary, JFXListView<String> listView) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            String word = entry.getValue().trim().toLowerCase();
            items.add(word);
        }
        listView.setItems(items);
    }


    // Ham lay nghia
    public static String getDef(String word, Map<String, String> map) {
        for(String line : map.keySet()) {
            if(map.get(line).trim().toLowerCase().equals(word)) return line;
        }
        return "";
    }


    // ham tim kiem
    public static void searchWord(TextField inputTextField, Map<String, String> dictionary, JFXListView<String> listView) {
        String input = inputTextField.getText().trim().toLowerCase();
        if (input.isEmpty()) {
            displayAllWords(dictionary, listView); // Hiển thị tất cả các từ khi không có input
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

        // Đặt sự kiện khi nhập liệu vào TextField
        inputTextField.setOnKeyReleased(event -> {
            // Gọi hàm searchWord để tìm kiếm và hiển thị kết quả
            searchWord(inputTextField, dictionary, listView);
        });
    }


    // Them tu moi
    public static void AddData(String word, String Mean) throws IOException {
        Map2.put(Mean, word);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\java\\BTL_APP\\3cucda\\Data\\Data2ForGame.txt", true));
        bufferedWriter.write(word +"\t" + Mean + "\n");
        bufferedWriter.close();
        System.out.println("Ghi thanh cong");
    }


    // Them tu trong listView
    public static void AddItemsOfListView(JFXListView<String> listView) {
        String selectedItem = listView.getSelectionModel().getSelectedItem().toLowerCase();
        try {
            AddData(selectedItem , getDef(selectedItem, Map1));
            Map2.put(getDef(selectedItem, Map1), selectedItem);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Them tat ca
    public static void AddAll(String pathData1, String pathData2) throws IOException {
        FileReader fileReader = new FileReader(new File(pathData1));
        FileWriter fileWriter = new FileWriter(new File(pathData2));

        int character;
        while ((character = fileReader.read()) != -1) {
            fileWriter.write(character);
        }

        fileReader.close();
        fileWriter.close();
    }


    // Thiet lap File rong
    public static void EmptyFile(String PathData) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(PathData));
        fileWriter.write("");
        Map2.clear();
        fileWriter.close();
    }


    //Go tu
    public static void remove(String word, String DataPath) throws IOException {

    }
}
