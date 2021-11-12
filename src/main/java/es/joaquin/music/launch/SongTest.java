package es.joaquin.music.launch;

import java.util.List;

import es.joaquin.music.model.Disc;
import es.joaquin.music.model.Song;
import es.joaquin.music.model.MariaDB.DiscDaoImpMariaDB;
import es.joaquin.music.model.MariaDB.SongDaoImpMariaDB;

public class SongTest {

	public static void main(String[] args) {
		testGetSongByName();
	}
	
	public static void tesSaveGenre() {
		SongDaoImpMariaDB s=new SongDaoImpMariaDB("loka",12,1,new Disc(),"clasica");
		s.saveGenre();
	}
	
	public static void testGetAllGenres() {
		SongDaoImpMariaDB s=new SongDaoImpMariaDB("loka",12,2,new Disc(),"clasica");
		
		System.out.println(s.getAllGenres());
	}
	static void testGetGenre() {
		SongDaoImpMariaDB s=new SongDaoImpMariaDB();
		System.out.println(s.getGenreId("clasica"));
		
	}
	
	static void testSaveSong() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB();
		
		SongDaoImpMariaDB s=new SongDaoImpMariaDB("loka",2,2,d.getDiscById(2),"clasica");
		s.save();
	}
	static void testUpadate() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB();
		SongDaoImpMariaDB s=new SongDaoImpMariaDB("loka",12,2,d.getDiscById(1),"clasica");
		s.save();
		s.setName("Jeronima");
		s.update();
	}
	
	static void testGetAll() {
		SongDaoImpMariaDB d=new SongDaoImpMariaDB();
		List<Song> ls=d.getAll();
		System.out.println(ls.toString());
	}
	static void testGetSongByName() {
		SongDaoImpMariaDB d=new SongDaoImpMariaDB();
		List<Song> ls=d.getSongByName("loka");
		System.out.println(ls.toString());
	}
	static void testGetSongById() {
		SongDaoImpMariaDB d=new SongDaoImpMariaDB();
		Song a=new Song();
		a=d.getSongById(6);
		System.out.println(d.toString());
	}

}
