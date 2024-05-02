package OurPackage.Module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.util.*;


public class Constructor {
    public static Map<String, String> HisWord = new HashMap<>();

    public static Set<String> MarkedWord = new HashSet<>();

    public static ObservableList<String> ListLog = FXCollections.observableArrayList();

    public static ObservableList<Label> labelList = FXCollections.observableArrayList();
}
