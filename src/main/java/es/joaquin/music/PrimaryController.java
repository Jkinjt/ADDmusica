package es.joaquin.music;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.singleton.UserSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
			Parent modal;
			try {
				modal = fxmlLoader.load();
				Stage modalStage = new Stage();
				modalStage.initModality(Modality.APPLICATION_MODAL);
				modalStage.initOwner(App.rootstage);
				Scene modalScene = new Scene(modal);
				modalStage.setScene(modalScene);
				modalStage.showAndWait();
				modalStage.setResizable(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			UserSingleton.getUser().setSongs(UserSingleton.getUser().getSongs());
			try {
				UserSingleton.getUser().setUserList(UserSingleton.getUser().getUserList());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		newModal("registrer.fxml");

	}
	
	public void newModal(String root) {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(root));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
