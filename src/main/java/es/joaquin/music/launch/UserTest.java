package es.joaquin.music.launch;

import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.SongDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserListDaoImpMariaDB;

public class UserTest {
	public static void main(String[] args) {
		testDelete();
	}

	static void testSave() {
		UserDaoImpMariaDB u = new UserDaoImpMariaDB("pepiro", "algo@algo", "./foto");
		u.setId(3);
		u.save();
	}

	static void testUpdate() {
		UserDaoImpMariaDB u = new UserDaoImpMariaDB("Juanita", "algo@algo", "./foto");
		u.save();
		u.setEmail("falso@correo.es");
		u.update();
	}

	static void testUserGetAll() {
		UserDaoImpMariaDB u = new UserDaoImpMariaDB();
		List<User> ul = u.getAll();
		System.out.println(ul);
	}

	static void testGetById() {
		UserDaoImpMariaDB u = new UserDaoImpMariaDB();
		u.getUserById(4);
		System.out.println(u);
	}

	static void testGetByEmail() {
		UserDaoImpMariaDB u = new UserDaoImpMariaDB();
		u.getUserByEmail("algo@correo.es");
		System.out.println(u);
	}

	static void testGetByName() {
		UserDaoImpMariaDB u = new UserDaoImpMariaDB();
		List<User> ul = u.getUserByName("pepiro");
		System.out.println(ul);
	}

	static void testDelete() {
		UserDaoImpMariaDB u = new UserDaoImpMariaDB();
		u.getUserById(4);
		u.setSongs(u.getSongs());
		System.out.println(u.getId());
		try {
			u.setUserList(u.getUserList());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		u.delete();
	}

	static void testGetListUser() {
		UserDaoImpMariaDB u = new UserDaoImpMariaDB();
		u.getUserByEmail("algo@algo");
		System.out.println(u.toString());
		try {
			u.getUserList();
			System.out.println(u.getUserList().toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void testGetSongs() {
		UserDaoImpMariaDB u = new UserDaoImpMariaDB();
		u.getUserByEmail("algo@algo");
		System.out.println(u.toString());
		try {
			u.getUserList();
			System.out.println(u.getSongs().toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void testRemoveList() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserDaoImpMariaDB uDAO=new UserDaoImpMariaDB();
		UserList ul;
		try {
			ul=ulDAO.getUserListById(1);
			System.out.println(ul.getId());
			uDAO.getUserById(1);
			System.out.println(uDAO.getId());
			System.out.println(uDAO.removeList(ul));
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	static void testAddList() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserDaoImpMariaDB uDAO=new UserDaoImpMariaDB();
		UserList ul;
		try {
			ul=ulDAO.getUserListById(3);
			System.out.println(ul.getId());
			uDAO.getUserById(5);
			System.out.println(uDAO.getId());
			uDAO.addList(ul);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void testRemoveSong() {
		UserDaoImpMariaDB uDAO=new UserDaoImpMariaDB();
		SongDaoImpMariaDB d=new SongDaoImpMariaDB();
		User u=new User();
		Song a=new Song();
		try {
			uDAO.getUserById(1);
			a=d.getSongById(6);
			System.out.println(uDAO.getId());
			System.out.println(a.getId());
			uDAO.removeSongSubscribe(a);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
