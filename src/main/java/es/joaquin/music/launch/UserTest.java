package es.joaquin.music.launch;

import java.util.List;

import es.joaquin.music.model.User;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;

public class UserTest {
public static void main(String[] args) {
	testGetSongs();
}

static void testSave() {
	UserDaoImpMariaDB u=new UserDaoImpMariaDB("pepiro","algo@algo","./foto");
	u.setId(3);
	u.save();
}

static void testUpdate() {
	UserDaoImpMariaDB u=new UserDaoImpMariaDB("Juanita","algo@algo","./foto");
	u.save();
	u.setEmail("falso@correo.es");
	u.update();
}

static void testUserGetAll() {
	UserDaoImpMariaDB u=new UserDaoImpMariaDB();
	List<User> ul=u.getAll();
	System.out.println(ul);
}

static void testGetById() {
	UserDaoImpMariaDB u=new UserDaoImpMariaDB();
	u.getUserById(4);
	System.out.println(u);
}
static void testGetByEmail() {
	UserDaoImpMariaDB u=new UserDaoImpMariaDB();
	u.getUserByEmail("algo@correo.es");
	System.out.println(u);
}

static void testGetByName() {
	UserDaoImpMariaDB u=new UserDaoImpMariaDB();
	List<User> ul= u.getUserByName("pepiro");
	System.out.println(ul);
}
static void testDelete() {
	UserDaoImpMariaDB u=new UserDaoImpMariaDB();
	u.getUserById(3);
	u.delete();
}

static void testGetListUser() {
	UserDaoImpMariaDB u=new UserDaoImpMariaDB();
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
	UserDaoImpMariaDB u=new UserDaoImpMariaDB();
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
}
