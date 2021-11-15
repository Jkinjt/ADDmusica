package es.joaquin.music;

import java.io.IOException;

import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.singleton.UserSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Registrer {
	private UserSingleton userSingleton;
	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private Button cancel;
	@FXML
	private Button save;

	public void registrer() throws IOException {
		if (saveUser()) {
			App.setRoot("secondary");
		}
	}

	public boolean saveUser() {
		boolean result = false;
		UserDaoImpMariaDB user = new UserDaoImpMariaDB();
		// se comprueba que el correo no existe y los campos de texto no esten vacios
		if ((!name.getText().equals("") && !email.getText().equals("")) && !user.getUserByEmail(email.getText())) {
			// se introducen los atributos al usuario
			user = new UserDaoImpMariaDB(name.getText(), email.getText(), "./foto");
			// si la id es menor que -1 y se procede a guardar en la base de datos
			if (user.getId() == -1) {
				// Se comprubea que el usuario se ha guardado
				if (user.save()) {
					// se almacena al usuario en el singleton
					userSingleton = UserSingleton.getInstance(user);
					result = true;
				}
			} 
		}else {
			// si encuentra al usuario se vulve a poner la id en -1 para que no subreescriba
			// los usuarios en la base de datos
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			// se comprueba el campo vacio
			if (name.getText().equals("")) {
				alert.setContentText("campo de nombre vacio");
			} else if (email.getText().equals("")) {
				alert.setContentText("campo del email vacio");
			} else if (user.getId() != -1) {
				alert.setContentText("El correo ya esta en uso");
				user.setId(-1);
			}
			alert.showAndWait();
			result = false;
		} 

		return result;
	}

	public void back() throws IOException {
		App.setRoot("primary");

	}
}
