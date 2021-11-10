package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;



public interface UserDAO extends DAO {
	
	List<User> getAllUsers();
	User getUserById();
	User getUserByName();
	//te devuelve las listas a las que se esta suscrito
	List<UserList> getListUser();
	//devuelve las listas que el usuario ha creado
	List<UserList> getCreatedList();
	List<Song> getSongsHeard();

}
