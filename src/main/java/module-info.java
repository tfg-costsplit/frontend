module io.github.costsplit.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires api;
	requires java.net.http;
	requires javafx.graphics;
    opens io.github.costsplit.ctrl to javafx.fxml;
    exports io.github.costsplit.costsplitFrontend;
    exports io.github.costsplit.model;
}