module com.talalmajeed.encryptionapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.talalmajeed.encryptionapp to javafx.fxml;
    exports com.talalmajeed.encryptionapp;
}