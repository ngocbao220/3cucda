package OurPackage.Module;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistorySearch {
    private String historyPath;

    private List<String> historyList = new ArrayList<>();

    public List<String> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<String> historyList) {
        this.historyList = historyList;
    }

    public HistorySearch(String historyPath) {
        this.historyPath = historyPath;
    }

    public void loadHistory() {
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(historyPath))) {
            // Đọc từng dòng trong file
            while ((line = reader.readLine()) != null) {
                historyList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(new StringBuilder().append("Error: can't reading file ")
                                                  .append(historyPath));
        }
    }
    public void insertHistory(String word) {
        try {
            if (historyList.contains(word)) {
                historyList.removeIf(e -> e.equals(word));
            }
            historyList.add(0, word);
            FileWriter fw = new FileWriter(historyPath);
            BufferedWriter bf = new BufferedWriter(fw);
            for (String target : historyList) {
                bf.write(target);
                bf.newLine();
            }
            bf.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(new StringBuilder()
                    .append("Error: Can't insert word to file ")
                    .append(historyPath));
        }
    }

    public void deleteHistory(String word) {
        if (historyList.contains(word)) {
            historyList.removeIf(e -> e.equals(word));
            try {
                FileWriter fw = new FileWriter(historyPath);
                BufferedWriter bf = new BufferedWriter(fw);
                for (String target : historyList) {
                    bf.write(target);
                    bf.newLine();
                }
                bf.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(new StringBuilder()
                        .append("Error: Can't delete word from file ")
                        .append(historyPath));
            }
        }

    }
}
