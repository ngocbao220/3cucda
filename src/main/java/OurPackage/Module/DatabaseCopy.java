package OurPackage.Module;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseCopy {

    public static List<Object[]> fetchData(Connection conn, String query) {
        List<Object[]> data = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("word");
                row[2] = rs.getString("html");
                row[3] = rs.getString("description");
                row[4] = rs.getString("pronounce");
                row[5] = rs.getInt("isBookmarked");
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void insertData(Connection conn, List<Object[]> data) {
        String sql = "INSERT INTO av (id, word, html, description, pronounce, isBookmarked) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Object[] row : data) {
                pstmt.setInt(1, (Integer) row[0]);
                pstmt.setString(2, (String) row[1]);
                pstmt.setString(3, (String) row[2]);
                pstmt.setString(4, (String) row[3]);
                pstmt.setString(5, (String) row[4]);
                pstmt.setInt(6, (Integer) row[5]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
