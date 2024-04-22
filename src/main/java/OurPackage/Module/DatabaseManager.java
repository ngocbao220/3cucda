package OurPackage.Module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseManager {
    private static Connection connection;

    private static Connection connect;

    public static Map<String, String> list = new LinkedHashMap<>();

    private final static String DATABASE_PATH = "./Data/";
    private final static String DATABASE_NAME = "dict_hh.db";
    public static void connectingToDatabase(String dbPath) {
        String url = new StringBuilder()
                .append("jdbc:sqlite:")
                .append(dbPath)
                .toString();
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection(String dbPath) {
        connectingToDatabase(dbPath);
        return connection;
    }
    public static void DictionaryWords() {
        String sql = "SELECT word, html FROM av";

        try {
            connect = getConnection(DATABASE_PATH + DATABASE_NAME);
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.put(rs.getString("html"), rs.getString("word"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
