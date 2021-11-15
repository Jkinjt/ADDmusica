package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;



public interface UserDAO extends DAO {
	
	
	boolean getUserById(int id);
	//devuelve una lista de usuarios por si hay más de uno con el mismo nombre
	List<User> getUserByName(String name);
	
	//devuelve las listas que el usuario ha creado
	List<UserList> getCreatedList();
	

}
