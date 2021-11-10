module es.joaquin.music {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
	requires com.sun.xml.txw2;
	requires java.sql;

    opens es.joaquin.music to javafx.fxml, java.xml.bind;
    opens es.joaquin.music.uitls to java.xml.bind;
    exports es.joaquin.music;
}
