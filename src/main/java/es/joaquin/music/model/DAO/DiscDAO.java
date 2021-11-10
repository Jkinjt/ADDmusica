package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Disc;
import es.joaquin.music.model.Song;



public interface DiscDAO extends DAO {
	
	List<Disc> getAll();
	
	
		
	List<Song> getSongs();
		
	Disc getDiscByArtist();
	Disc getDiscbySong();
	
	
	

}
