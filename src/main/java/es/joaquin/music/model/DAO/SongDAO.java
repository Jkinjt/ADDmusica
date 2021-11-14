package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Song;


public interface SongDAO extends DAO{
	
	List<Song> getAll();
	List<Song> getSongsByDisc();
	Song getSongById(int id) throws DAOException;
	List<Song> getSongByName(String name);
	List<String> getAllGenres();
	int getGenreId(String name);
	boolean saveGenre();
}
