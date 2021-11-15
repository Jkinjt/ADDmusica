package es.joaquin.music;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.singleton.UserSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {
	UserSingleton userSingleton;

	@FXML
	private TextField email;
	@FXML
	private Button conect;
	@FXML
	private Button registrer;

	@FXML
	private void getin() throws IOException, JAXBException {
		if (setName()) {
			App.setRoot("secondary");
		}
	}

	@FXML
	private boolean setName() throws IOException, JAXBException {
		boolean result = false;
		UserDaoImpMariaDB user = new UserDaoImpMariaDB();
		// se comprueba si el usuario se encuentra en la base de datos
		if (user.getUserByEmail(email.getText())) {
			// se setea la instancia
			userSingleton = UserSingleton.getInstance(user);
			result = true;

		}
		// en caso de que no se encuentre el usuario salta una alerta
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Correo introducido incorrecto");
			alert.showAndWait();
			result = false;
		}

		return result;
	}

	public void registrerUser() throws IOException {
		App.setRoot("registrer");

	}
}
