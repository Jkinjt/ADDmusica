package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Artist;
import es.joaquin.music.model.Disc;
import es.joaquin.music.model.Song;



public interface AstistDAO extends DAO {

	List<Artist> getAll();
	
	
	Artist getArtistById(int id);
	
	List<Artist> getArtistByName(String name);
	
	List<Disc> getArtitsDiscs();
	
	List<Song> getArtitsSongs();
	
	
	
	
	
}
