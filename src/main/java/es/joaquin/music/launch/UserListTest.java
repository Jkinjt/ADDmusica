package es.joaquin.music.launch;

import java.time.LocalDate;
import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.SongDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserListDaoImpMariaDB;

public class UserListTest {

	public static void main(String[] args) {
		getSongsTest();

	}

	static void saveTest() {
		UserDaoImpMariaDB uDAO = new UserDaoImpMariaDB();
		uDAO.getUserById(1);
		User u = uDAO;
		UserListDaoImpMariaDB ul = new UserListDaoImpMariaDB("Rockabily", "Selección de las mejores canciones", u,
				LocalDate.now(), 3);
		try {
			ul.save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void updateTest() {
		UserDaoImpMariaDB uDAO = new UserDaoImpMariaDB();
		uDAO.getUserById(1);
		User u = uDAO;
		UserListDaoImpMariaDB ul = new UserListDaoImpMariaDB("Rockabily", "Selección de las mejores canciones", u,
				LocalDate.now(), 3);
		try {
			ul.save();
			ul.setName("Regge");
			ul.setDescription("Mejores canciones para estar en la playa");
			ul.update();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void getUserListByIdTest() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserList u;
		try {
			u = ulDAO.getUserListById(2);
			System.out.println(u.toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void getUserListByNameTest() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		List<UserList> u;
		try {

			u = ulDAO.getUserListByName("Rock");
			System.out.println(u.toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void getAllTest() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		List<UserList> u;
		try {
			u = ulDAO.getAll();
			System.out.println(u.toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void getSongByListTest() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserList u;
		try {
			u = ulDAO.getUserListById(2);

			System.out.println(ulDAO.getSongByList(2).toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void getSongsTest() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		try {

			UserList u = ulDAO.getUserListById(2);
			ulDAO = new UserListDaoImpMariaDB(u);
			System.out.println(u.getId());
			u.getSongs();
			System.out.println(ulDAO.getSongs().toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	static void getUserByListId() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserList u;
		try {
			u = ulDAO.getUserListById(2);

			System.out.println(ulDAO.getUserByIdList(2).toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void getUsersTest() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserList u;
		try {
			u = ulDAO.getUserListById(2);
			ulDAO = new UserListDaoImpMariaDB(u);

			System.out.println(ulDAO.getUsers().toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void addSongTest() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserList u;
		try {
			u = ulDAO.getUserListById(2);
			ulDAO = new UserListDaoImpMariaDB(u);
			SongDaoImpMariaDB d=new SongDaoImpMariaDB();
			Song a=new Song();
			a=d.getSongById(7);
			ulDAO.addSong(a);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void addUserTest() {
		UserDaoImpMariaDB uDAO = new UserDaoImpMariaDB();
		uDAO.getUserById(7);
		User u = uDAO;
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserList ul;
		try {
			ulDAO=new UserListDaoImpMariaDB(ulDAO.getUserListById(4));
			ulDAO.addUser(u);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void removeSongUsedTest() {
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserList u;
		try {
			u = ulDAO.getUserListById(2);
			ulDAO = new UserListDaoImpMariaDB(u);
			SongDaoImpMariaDB d=new SongDaoImpMariaDB();
			Song a=d.getSongById(7);
			ulDAO.removeSong(a);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void removeUserSubscribeTest() {
		UserDaoImpMariaDB uDAO = new UserDaoImpMariaDB();
		uDAO.getUserById(1);
		User u = uDAO;
		UserListDaoImpMariaDB ulDAO = new UserListDaoImpMariaDB();
		UserList ul;
		try {
			ulDAO=new UserListDaoImpMariaDB(ulDAO.getUserListById(2));
			ulDAO.removeUser(u);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
