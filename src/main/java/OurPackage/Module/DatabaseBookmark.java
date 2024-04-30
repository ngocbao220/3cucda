package OurPackage.Module;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static OurPackage.Module.Constructor.MarkedWord;
import static OurPackage.Module.DatabaseManager.connect;

public class DatabaseBookmark {

    protected String tableName;

    public DatabaseBookmark(String tableName) {
        this.tableName = tableName;
        importData();
    }

    public void importData() {
        String sql = "SELECT DISTINCT word FROM " + tableName + " WHERE isBookmarked = 1;";
        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String word = rs.getString("word");
                MarkedWord.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: Couldn't import bookmark list!");
        }
    }


    public void updateBookmark(String word) {
        // SQL statement để cập nhật bookmark
        String sql = "UPDATE " + tableName + " SET isBookmarked = 1 WHERE word = ?";

        // Sử dụng try-with-resources để đảm bảo rằng tất cả tài nguyên JDBC được đóng gói gọn gàng
        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);
            // Đặt các tham số cho PreparedStatement
            pstmt.setString(1, word);

            // Thực thi câu lệnh
            int affectedRows = pstmt.executeUpdate();
            MarkedWord.add(word);
            System.out.println("Updated " + affectedRows + " rows.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: Couldn't set word into bookmark list!");
        }
    }

    public void remove(String word) {
        if (MarkedWord.contains(word)) {
            String sql = "UPDATE " + tableName + " SET isBookmarked = 0 WHERE word = ?;";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                pstmt.setString(1, word);
                pstmt.executeUpdate();

                MarkedWord.removeIf(e -> e.equals(word));

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error: Couldn't unset word into bookmark list!");
            }
        }
    }

    public void clear() {
        // Clear tu trong list mark
        MarkedWord.clear();

        // Dat tat ca isBookmark trong database ve 0

        String sql = "UPDATE " + tableName + " SET isBookmark = 0";

        // Sử dụng try-with-resources để đảm bảo rằng tất cả tài nguyên JDBC được đóng gói gọn gàng
        try {
             PreparedStatement pstmt = connect.prepareStatement(sql);

            // Thực thi câu lệnh
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Updated " + affectedRows + " rows. All bookmarks are now reset to 0.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: Couldn't set bookmark = 0 !");
        }
    }

    public boolean isBookmarked(String word) {
        return MarkedWord.contains(word);
    }
}
