package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Disc;
import es.joaquin.music.model.Song;



public interface DiscDAO extends DAO {
	
	List<Disc> getAll() throws DAOException;
	List<Disc> getDiscByName(String name)throws DAOException;
	Disc getDiscById(int id)throws DAOException;		
	List<Song> getSongs()throws DAOException;		
	Disc getDiscByArtist()throws DAOException;
	Disc getDiscbySong()throws DAOException;
	
	
	

}
