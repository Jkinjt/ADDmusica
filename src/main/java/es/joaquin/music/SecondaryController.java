package es.joaquin.music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.DiscDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.SongDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserListDaoImpMariaDB;
import es.joaquin.music.singleton.UserSingleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SecondaryController {
	private UserSingleton userSignleton;
	private UserDaoImpMariaDB user;
	// tiene que ser estatica para que se pueda usar en el método setSongTable
	private static DiscDaoImpMariaDB dDAO = new DiscDaoImpMariaDB();

	@FXML
	private TextField searchBar;
	@FXML
	private Text userName;
	@FXML
	private Text songListened;
	@FXML
	private TableView<Song> songsTable;
	@FXML
	private TableColumn<Song, String> songNameColum;
	@FXML
	private TableColumn<Song, String> songDiscColum;
	@FXML
	private TableColumn<Song, String> songArtistColum;

	@FXML
	private TableView<UserList> userLists;
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
	private Button editList;

	@FXML
	private void initialize() {
		userSignleton = UserSingleton.getInstance();
		user = UserSingleton.getUser();
		userName.setText(user.getName());
		setListTable();
		try {
			setSongTable(user.getSongs());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void switchToPrimary() throws IOException {
		Stage stage = (Stage) this.disconet.getScene().getWindow();
        stage.close();
	}

	public void setListTable() {
		try {
			userLists.setItems(FXCollections.observableArrayList(user.getUserList()));
			listNameColum.setCellValueFactory(new PropertyValueFactory<UserList, String>("name"));
			listDescriptionColum.setCellValueFactory(new PropertyValueFactory<UserList, String>("description"));
			// para poder introducir números se hace una función flecha
			listSubscriptionColum.setCellValueFactory(eachList -> {
				SimpleStringProperty v = new SimpleStringProperty();
				// se convierte cada entero en una cadena
				v.setValue("" + eachList.getValue().getId());
				return v;
			});
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void setSongTable(List<Song> songsList) throws DAOException {

		songsTable.setItems(FXCollections.observableArrayList(songsList));
		songNameColum.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));
		songDiscColum.setCellValueFactory(eachSong -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(eachSong.getValue().getDisc().getName());
			return v;
		});
		songArtistColum.setCellValueFactory(eachSong -> {
			SimpleStringProperty v = new SimpleStringProperty();
			try {
				// para que se llame al getArtist del dao y traiga los artistas
				dDAO = new DiscDaoImpMariaDB(eachSong.getValue().getDisc());
				v.setValue(dDAO.getArtist().getName());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return v;
		});
	}

	public void seeker() {
		SongDaoImpMariaDB sDAO = new SongDaoImpMariaDB();
		List<Song> songsList = sDAO.getSongByName(searchBar.getText());
		try {
			setSongTable(songsList);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void changeCreateList() {
			newModal("createList.fxml");
		

	}

	public void getUserList() {

		UserListDaoImpMariaDB us = new UserListDaoImpMariaDB(this.userLists.getSelectionModel().getSelectedItem());
		UserSingleton.setUserList(us);

	}

	public void editList() {
		if(UserSingleton.getUserList()!=null) {
			newModal("editList.fxml");
			
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("No has seleccionado una lista");
			alert.showAndWait();
		}

	}
	
	public void addList() {
		newModal("addList.fxml");
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