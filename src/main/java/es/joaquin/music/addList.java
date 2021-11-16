package es.joaquin.music;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.DiscDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.SongDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserListDaoImpMariaDB;
import es.joaquin.music.singleton.UserSingleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class addList {
	// tiene que ser estatica para que se pueda usar en el método setSongTable
		private static DiscDaoImpMariaDB dDAO = new DiscDaoImpMariaDB();
		private UserSingleton userSignleton;
		

		@FXML
		private Button saveListButton;
		@FXML
		private Button exitButton;

		@FXML
		private TextField searchBar;
	
		@FXML
		private TableView<UserList> userLists;
		@FXML
		private TableColumn<UserList, String> userListNameColum;
		@FXML
		private TableColumn<UserList, String> userListDescriptionColum;
		@FXML
		private TableColumn<UserList, String> userListSubscriptionColum;

		@FXML
		private TableView<UserList> allUserLists;
		@FXML
		private TableColumn<UserList, String> listNameColum;
		@FXML
		private TableColumn<UserList, String> listDescriptionColum;
		@FXML
		private TableColumn<UserList, String> listSubscriptionColum;
		@FXML
		private Button search;
		@FXML
		private Button disconet;

		@FXML
		private void initialize() {
			try {
				setUserList(UserSingleton.getUser().getUserList());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		}

		// metodo que añade a la tabla las cancines resultado de la busqueda
		public void setUserListSearchTable(List<UserList> userList) throws DAOException {

			allUserLists.setItems(FXCollections.observableArrayList(userList));
			listNameColum.setCellValueFactory(new PropertyValueFactory<UserList, String>("name"));
			listDescriptionColum.setCellValueFactory(new PropertyValueFactory<UserList, String>("description"));
			listSubscriptionColum.setCellValueFactory(eachList -> {
				SimpleStringProperty v = new SimpleStringProperty();
				// se convierte cada entero en una cadena
				v.setValue("" + eachList.getValue().getId());
				return v;
			});
			
		}

		public void seeker() {
			UserListDaoImpMariaDB sDAO = new UserListDaoImpMariaDB();
			// carga las listas con el resultado de la base de datos
			List<UserList> userList=new ArrayList<UserList>();
			try {
				userList = sDAO.getUserListByName(searchBar.getText());
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (userList.size() != 0) {
				try {
					setUserListSearchTable(userList);
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se ha encontrado una lista con ese nombre");
				alert.showAndWait();
			}

		}

		public void getUserList() {

			UserList ul = allUserLists.getSelectionModel().getSelectedItem();
			if (UserSingleton.getUser().addList(ul)) {
				try {
					setUserList(UserSingleton.getUser().getUserList());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se ha encontrado una lista con ese nombre");
				alert.showAndWait();
			}

		}

		public void removeList() {
			if(showRomove()) {
				
				UserList ul = userLists.getSelectionModel().getSelectedItem();
				UserSingleton.getUser().removeList(ul);
				try {
					setUserList(UserSingleton.getUser().getUserList());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		}
		
		public void addList(){

			UserList ul = allUserLists.getSelectionModel().getSelectedItem();
			if (UserSingleton.getUser().addList(ul)) {
				try {
					setUserList(UserSingleton.getUser().getUserList());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("Ya se ha añadido a esta lista");
				alert.showAndWait();
			}

		}

		public void setUserList(List<UserList> songsList) throws DAOException {
			this.userLists.setItems(FXCollections.observableArrayList(songsList));
			this.userListNameColum.setCellValueFactory(new PropertyValueFactory<UserList, String>("name"));
			listNameColum.setCellValueFactory(new PropertyValueFactory<UserList, String>("description"));
			this.userListSubscriptionColum.setCellValueFactory(eachList -> {
				SimpleStringProperty v = new SimpleStringProperty();
				// se convierte cada entero en una cadena
				v.setValue("" + eachList.getValue().getId());
				return v;
			});
		}

		public void endList() {
			
					newModal("secondary.fxml");
				
			
		}

		

		public void exit() {
			 Stage stage = (Stage) this.exitButton.getScene().getWindow();
		        stage.close();

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
		
		public boolean showRomove() {
	        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Confirme la acción");
	        alert.setHeaderText("¿Estas seguro de querer borrar la lista?");
	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.get() == ButtonType.OK) {
	            return true;
	        } else {
	            return false;
	        }
	    }

}
