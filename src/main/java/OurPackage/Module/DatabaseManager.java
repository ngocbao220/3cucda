package OurPackage.Module;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class DatabaseManager extends Dictionary{
    private static Connection connection;

    protected static Connection connect;

    public Connection getConnect() {
        return connect;
    }

    public static void setConnect(Connection connect) {
        DatabaseManager.connect = connect;
    }

    public static Map<String, String> list = new LinkedHashMap<>();

    public static String notication = new String();

    public DatabaseManager(String dbPath, String dbName) {
        super(dbPath, dbName);
    }

    // Tao Ket Noi
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

    // Lay cac tu trong database vao Map
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


    // Them tu vao database
    // can them tu, phat am, tu loai, nghia
    public void insertWordToDB(String word, String pronounce, String partOfSpeech, String meaning) {
        String sql = "INSERT INTO av (word, html) VALUES (?, ?)";
        String html = new StringBuilder().append("<h1>")
                                         .append(word).append("</h1><h3><i>/")
                                         .append(pronounce).append("/</i></h3>")
                                         .append(partOfSpeech).append("<br/><ul><li>")
                                         .append(meaning).append("</li></ul>").toString();
        try {
            connect = getConnection(getDbPath() + getDbName());
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, word);
            pstmt.setString(2, html);

            list.put(word, html);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                notication = "A record was inserted successfully!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xoa tu khoi database
    public void deleteWordFromDB(String wordDelete) {
        String sql = "DELETE FROM av WHERE word = ?";
        try {
            connect = getConnection(getDbPath() + getDbName());
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, wordDelete);

            list.remove(wordDelete);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                notication = "A record was deleted successfully!";
            } else {
                notication = "No record was found with the specified word.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void copyDataFromDefaultTable() {
        String sql = new StringBuilder().append("INSERT or IGNORE INTO av SELECT * FROM defaultAV;").toString();
        try {
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        DictionaryWords();
    }

    public void deleteAllDatabase() {
        String sql = "DELETE FROM av;";
        try {
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetData() {
        deleteAllDatabase();
        copyDataFromDefaultTable();
    }

    /*public static void main(String[] args) {

    }*/

}
