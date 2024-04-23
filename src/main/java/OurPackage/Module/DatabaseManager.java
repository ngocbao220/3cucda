package OurPackage.Module;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseManager {
    private static Connection connection;

    private static Connection connect;

    public static Map<String, String> list = new LinkedHashMap<>();

    public static Map<String, String> listFavoriteWords = new LinkedHashMap<>();


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
        String sql = "SELECT word, html, description FROM av";

        try {
            connect = getConnection(DATABASE_PATH + DATABASE_NAME);
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.put(rs.getString("word"), rs.getString("html"));
                listFavoriteWords.put(rs.getString("word"), rs.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        DictionaryWords();
        for(Map.Entry<String, String> i : listFavoriteWords.entrySet()) {
            System.out.print(i.getKey());
            System.out.print(" ");
            System.out.println(i.getValue());
        }
    }*/

}
