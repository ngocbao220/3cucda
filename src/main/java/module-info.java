module project_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;
    requires com.google.gson;
    requires javafx.media;
    requires java.sql;
    requires java.desktop;

    exports OurPackage;
    exports OurPackage.Controller;
    opens OurPackage to javafx.fxml;
    opens OurPackage.Controller to javafx.fxml;
}