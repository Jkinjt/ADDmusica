package es.joaquin.music.launch;

import java.time.LocalDate;
import java.util.List;

import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.UserListDaoImpMariaDB;

public class UserListTest {

	public static void main(String[] args) {
		getAllTest();

	}
	
	static void saveTest() {
		UserDaoImpMariaDB uDAO=new UserDaoImpMariaDB();
		uDAO.getUserById(1);
		User u=uDAO;
		UserListDaoImpMariaDB ul=new UserListDaoImpMariaDB("Rockabily","Selección de las mejores canciones",u,LocalDate.now(),3);
		try {
			ul.save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void updateTest() {
		UserDaoImpMariaDB uDAO=new UserDaoImpMariaDB();
		uDAO.getUserById(1);
		User u=uDAO;
		UserListDaoImpMariaDB ul=new UserListDaoImpMariaDB("Rockabily","Selección de las mejores canciones",u,LocalDate.now(),3);
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
		UserListDaoImpMariaDB ulDAO=new UserListDaoImpMariaDB();
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
		UserListDaoImpMariaDB ulDAO=new UserListDaoImpMariaDB();
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
		UserListDaoImpMariaDB ulDAO=new UserListDaoImpMariaDB();
		List<UserList> u;
		try {
			u=ulDAO.getAll();
			System.out.println(u.toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
