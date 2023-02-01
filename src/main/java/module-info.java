module com.delicacy.delicacy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.logging;

    requires java.sql;

    opens com.delicacy.delicacy to javafx.fxml;
    exports com.delicacy.delicacy;
}