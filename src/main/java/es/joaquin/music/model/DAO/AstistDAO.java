package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Artist;
import es.joaquin.music.model.Disc;
import es.joaquin.music.model.Song;



public interface AstistDAO extends DAO {

	List<Artist> getAll() throws DAOException;
	
	
	Artist getArtistById(int id)throws DAOException;
	
	List<Artist> getArtistByName(String name) throws DAOException;
	
	List<Disc> getArtitsDiscs()throws DAOException;
	
	List<Song> getArtitsSongs()throws DAOException;
	
	
	
	
	
}
