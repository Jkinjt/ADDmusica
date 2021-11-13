package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;



public interface UserListDAO extends DAO{
	
	List<User> getUsers()throws DAOException;
	List<Song> getSongs()throws DAOException;
	User getCreator()throws DAOException;
	List<UserList> getUserListByName(String name)throws DAOException;
	UserList getUserListById(int id)throws DAOException;
	
}
