module es.aketzagonzalez {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires api;
	requires java.net.http;
    opens es.aketzagonzalez.ctrl to javafx.fxml;
    exports es.aketzagonzalez.costsplitFrontend;
    exports es.aketzagonzalez.model;
}