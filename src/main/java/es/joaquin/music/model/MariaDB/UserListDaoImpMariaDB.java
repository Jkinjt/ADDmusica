package es.joaquin.music.model.MariaDB;

import java.util.List;

import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.UserListDAO;

public class UserListDaoImpMariaDB extends UserList implements UserListDAO{
	
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

}
