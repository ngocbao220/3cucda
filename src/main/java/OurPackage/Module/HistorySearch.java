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

    // Doc cac tu duoc luu trong file historyDic.txt vao historyList
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

    // Them tu vao file txt
    public void insertHistory(String word) {
        try {
            // Neu tu do da co trong lich su thi se duoc cap nhat len tren
            if (historyList.contains(word)) {
                historyList.removeIf(e -> e.equals(word));
            }

            historyList.add(0, word);
            // Cap nhat file txt
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

    // Xoa tu khoi file txt
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

    // Xoa tat ca khoi history
    public void clearHistory() {
        historyList.clear();

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
                    .append("Error: Can't clear words from file ")
                    .append(historyPath));
        }
    }
}
