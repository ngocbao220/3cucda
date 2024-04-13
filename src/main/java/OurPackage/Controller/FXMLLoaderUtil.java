package OurPackage.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;

public class FXMLLoaderUtil {

    public static void loadFXMLToPane(String fxmlFile, Pane containerPane) {
        // Load cảnh từ file FXML
        Parent newRoot = loadFXML(fxmlFile);

        // Thêm cảnh mới vào Pane hiện tại
        containerPane.getChildren().setAll(newRoot);
    }

    private static Parent loadFXML(String fxmlFile) {
        try {
            URL resource = FXMLLoaderUtil.class.getResource(fxmlFile);
            if (resource == null) {
                throw new IOException("FXML file not found: " + fxmlFile);
            }
            return FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
