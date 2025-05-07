module es.aketzagonzalez {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens es.aketzagonzalez.ctrl to javafx.fxml;
    exports es.aketzagonzalez.costsplitFrontend;
    exports es.aketzagonzalez.model;
}