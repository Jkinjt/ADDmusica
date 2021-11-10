package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;



public interface UserListDAO extends DAO{
	
	List<User> getUsers();
	List<Song> getSongs();
	User getCreator();
	
}
