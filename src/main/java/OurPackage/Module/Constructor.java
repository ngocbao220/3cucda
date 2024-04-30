package OurPackage.Module;

import java.io.*;
import java.util.*;

import static OurPackage.Controller.MonkeyGameController.DATA2;

public class Constructor {

    public static Map<String, String> HisWord = new HashMap<>();

    public static Set<String> MarkedWord = new HashSet<>();
    /*
    private final String Media_Path = "src/main/resources/OurPackage/SupportScreen/media/";

    Tao 1 doi tuong Media: private Media your_media;

    tao 1 doi tuong MediaView trong scenebuilder: MediaView your_media_view;


    void setupMedia(String mediaName, Pane pane, MediaView mediaview, Media media, boolean status) {
    media = new Media(new File(Media_Path + mediaName).toURI().toString());

    MediaPlayer mediaPlayer = new MediaPlayer(media);

    mediaview.fitWidthProperty().bind(pane.widthProperty());
    mediaview.fitHeightProperty().bind(pane.heightProperty());

    mediaview.setMediaPlayer(mediaPlayer);

    mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
    if (status) {
        mediaview.getMediaPlayer().play();
    } else mediaview.getMediaPlayer().stop()
}*/
}
