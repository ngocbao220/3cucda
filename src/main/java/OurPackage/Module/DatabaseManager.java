package OurPackage.Module;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class DatabaseManager extends Dictionary{
    private static Connection connection;

    protected static Connection connect;

    public static Map<String, String> list = new LinkedHashMap<>();

    public DatabaseManager(String dbPath, String dbName) {
        super(dbPath, dbName);
    }


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
    public void DictionaryWords() {
        String sql = "SELECT word, html FROM av";

        try {
            connect = getConnection(getDbPath() + getDbName());
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.put(rs.getString("word"), rs.getString("html"));
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
