package OurPackage.Module;

import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import java.util.Map;

public interface CRUD {
    void ReadData(String DATA, String Split, Map<String, String> map) throws IOException;

    void AddData(String word, String Mean) throws IOException;

    //void AddData();
}
