module org.example.project_app {
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
/*
    requires jfxrt;
*/

    opens Models to javafx.fxml;
    exports Models;
    exports Controller;
    opens Controller to javafx.fxml;
}