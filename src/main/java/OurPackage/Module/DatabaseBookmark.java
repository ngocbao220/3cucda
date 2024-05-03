package OurPackage.Module;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static OurPackage.Module.Constructor.MarkedWord;
import static OurPackage.Module.DatabaseManager.connect;

public class DatabaseBookmark implements BookMark {

    protected String tableName;

    public DatabaseBookmark(String tableName) {
        this.tableName = tableName;
        importData();
    }

    // Them cac tu duoc danh dau Bookmark vao List MarkWord
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

    // Cap nhat Bookmark khi danh dau 1 tu
    public void updateBookmark(String word) {
        String sql = "UPDATE " + tableName + " SET isBookmarked = 1 WHERE word = ?";

        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);
            // Đặt các tham số cho PreparedStatement
            pstmt.setString(1, word);

            int affectedRows = pstmt.executeUpdate();
            MarkedWord.add(word);
            System.out.println("Updated " + affectedRows + " rows.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: Couldn't set word into bookmark list!");
        }
    }

    // Xoa Bookmark cua 1 tu
    public void removeBookmark(String word) {
        // Neu tu muon xoa co trong Bookmark thi moi thuc thi lenh remove
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

    // Xoa tat ca tu trong Bookmark
    public void clearBookmark() {
        // Clear tu trong list mark
        MarkedWord.clear();

        // Dat tat ca isBookmark trong database ve 0

        String sql = "UPDATE " + tableName + " SET isBookmark = 0";

        try {
             PreparedStatement pstmt = connect.prepareStatement(sql);

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
