package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Song;


public interface SongDAO extends DAO{
	
	List<Song> getAll();
	
	
	List<Song> getSongsByDisc();
}
