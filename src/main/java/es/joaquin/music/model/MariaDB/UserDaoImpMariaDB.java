package es.joaquin.music.model.MariaDB;

import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.UserDAO;

public class UserDaoImpMariaDB extends User implements UserDAO{

	//Consultas a la base de datos
		private static final String INSERT="";
		private static final String UPDATE="";
		private static final String DELETE="";
		private static final String SELECTALL="";
		private static final String SELECTBYID="";
		private static final String SELECTBYNAME="";
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserById() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByName() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserList> getListUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserList> getCreatedList() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Song> getSongsHeard() {
		// TODO Auto-generated method stub
		return null;
	}

}
