package es.joaquin.music;

import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.DiscDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.SongDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.singleton.UserSingleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateList {
	// tiene que ser estatica para que se pueda usar en el método setSongTable
	private static DiscDaoImpMariaDB dDAO = new DiscDaoImpMariaDB();
	private UserSingleton userSignleton;
	private UserDaoImpMariaDB user;

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
	private TableView<Song> songSList;
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

	private void initialize() {
		userSignleton = UserSingleton.getInstance();
		user = UserSingleton.getUser();

	}

	public void setSongTable(List<Song> songsList) throws DAOException {

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
		List<Song> songsList = sDAO.getSongByName(searchBar.getText());
		try {
			setSongTable(songsList);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
