package es.joaquin.music.launch;

import java.util.List;

import es.joaquin.music.model.Artist;
import es.joaquin.music.model.MariaDB.ArtistDaoImpMariaDB;
import es.joaquin.music.uitls.ServerConnection;
import es.joaquin.music.uitls.WrapperForXML;



public class Executable {

	public static void main(String[] args) {
		
		testGetAllArtist();
	}
	static void testWrapperForXML() {
		ServerConnection sc=new ServerConnection();
		sc.setServer("jdbc:mysql://localhost");
		sc.setDatabase("musica");
		sc.setUsername("root");
		sc.setPassword("");
		WrapperForXML.saveFile(sc);
			
		
	}
	
	static void  testSaveArtist() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB("Jose Mari","espa�ol","./foto");
		a.save();
		
	}
	
	static void testGetAllArtist() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB();
		List<Artist> al=a.getAll();
		System.out.println(al.toString());
	}
	
	static void testDeleteArtist() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB("Jose Mari","espa�ol","./foto");
		a.save();
		System.out.println(a.getId());
		a.delete();
		
	}
	static void testUpdateArtist() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB("Jose Mari","espa�ol","./foto");
		a.getArtistById(7);
		System.out.println(a.getId()+" "+a.getName());
		a.setName("Leticia Sabater");
		System.out.println(a.getName());
		a.update();
	}
	static void testGetArtistById() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB();
		System.out.println(a.getArtistById(7));
	}

}
