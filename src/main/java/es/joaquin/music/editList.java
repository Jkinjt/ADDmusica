package es.joaquin.music;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.DiscDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.SongDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserListDaoImpMariaDB;
import es.joaquin.music.singleton.UserSingleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class editList {
	// tiene que ser estatica para que se pueda usar en el método setSongTable
	private static DiscDaoImpMariaDB dDAO = new DiscDaoImpMariaDB();
	private UserSingleton userSignleton;
	private UserDaoImpMariaDB user;
	private UserListDaoImpMariaDB userList;


	@FXML
	private Button saveListButton;
	@FXML
	private Button exitButton;

	@FXML
	private TextField searchBar;
	@FXML
	private TextField nameList;
	@FXML
	private TextField descriptionList;
	@FXML
	private TableView<Song> songsSearch;
	@FXML
	private TableColumn<Song, String> songNameColum;
	@FXML
	private TableColumn<Song, String> songDiscColum;
	@FXML
	private TableColumn<Song, String> songArtistColum;

	@FXML
	private TableView<Song> songsList;
	@FXML
	private TableColumn<Song, String> songsListNameColum;
	@FXML
	private TableColumn<Song, String> songListDiscColum;
	@FXML
	private TableColumn<Song, String> songListArtistColum;
	@FXML
	private Button search;
	@FXML
	private Button disconet;

	@FXML
	private void initialize() {
		userSignleton = UserSingleton.getInstance();
		user = UserSingleton.getUser();
		userList=UserSingleton.getUserList();
		nameList.setText(userList.getName());
		descriptionList.setText(userList.getDescription());
		try {
			setSongsList(userList.getSongs());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// metodo que añade a la tabla las cancines resultado de la busqueda
	public void setSongSearchTable(List<Song> songsList) throws DAOException {

		songsSearch.setItems(FXCollections.observableArrayList(songsList));
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
		// carga las listas con el resultado de la base de datos
		List<Song> songsList = sDAO.getSongByName(searchBar.getText());
		if (songsList.size() != 0) {
			try {
				setSongSearchTable(songsList);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("No se ha encontrado una canción con ese nombre");
			alert.showAndWait();
		}

	}

	public void getSong() {

		Song s = songsSearch.getSelectionModel().getSelectedItem();
		if (this.userList.addSong(s)) {
			try {
				setSongsList(userList.getSongs());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void removeSong() {

		Song s = songsSearch.getSelectionModel().getSelectedItem();
		if (this.userList.removeSong(s)) {
			try {
				setSongsList(this.userList.getSongs());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void setSongsList(List<Song> songsList) throws DAOException {
		this.songsList.setItems(FXCollections.observableArrayList(songsList));
		this.songsListNameColum.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));
		this.songListDiscColum.setCellValueFactory(eachSong -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(eachSong.getValue().getDisc().getName());
			return v;
		});
		this.songListArtistColum.setCellValueFactory(eachSong -> {
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

	public void endList() {
		// si se guarda correctamente se pasa a la otra pantalla
		if (saveList()) {
			try {
				App.setRoot("secondary");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean saveList() {
		boolean result = false;
		UserListDaoImpMariaDB userListDAO;
		List<User> userl = new ArrayList<>();
		// se comprueba que el correo no existe y los campos de texto no esten vacios
		if ((!this.nameList.getText().equals("") && !this.descriptionList.getText().equals("")) && userList.getSongs().size() != 0) {
			userl.add(this.user);
			// se introducen los atributos a la lista
			userListDAO = new UserListDaoImpMariaDB(this.nameList.getText(), this.descriptionList.getText(), user,
					LocalDate.now(), 0);
			// se guarda la lista en la base de datos
			try {
				userListDAO.update();
				result=true;

			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			// si encuentra al usuario se vulve a poner la id en -1 para que no subreescriba
			// los usuarios en la base de datos
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			// se comprueba el campo vacio
			if (this.nameList.getText().equals("")) {
				alert.setContentText("campo de nombre vacio");
			} else if (this.descriptionList.getText().equals("")) {
				alert.setContentText("La descripción esta vacia vacio");
			} else if (userList.getSongs().size() == 0) {
				alert.setContentText("La lista esta vacia");
			}
			alert.showAndWait();
			result = false;
		}

		return result;
	}

	public void exit() {
		try {
			App.setRoot("secondary");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}