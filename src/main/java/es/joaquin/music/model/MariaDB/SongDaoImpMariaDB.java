package es.joaquin.music.model.MariaDB;

import java.util.List;

import es.joaquin.music.model.Song;
import es.joaquin.music.model.DAO.SongDAO;

public class SongDaoImpMariaDB extends Song implements SongDAO {

	//Consultas a la base de datos
		private static final String INSERT="";
		private static final String UPDATE="";
		private static final String DELETE="";
		private static final String SELECTALL="";
		private static final String SELECTBYID="";
		private static final String SELECTBYNAME="";
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

	public List<Song> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Song> getSongsByDisc() {
		// TODO Auto-generated method stub
		return null;
	}

}
