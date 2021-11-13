package es.joaquin.music.launch;

import java.util.List;

import es.joaquin.music.model.Artist;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.ArtistDaoImpMariaDB;
import es.joaquin.music.uitls.ServerConnection;
import es.joaquin.music.uitls.WrapperForXML;



public class Executable {

	public static void main(String[] args) {
		
		
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
		try {
			a.save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void testGetAllArtist() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB();
		List<Artist> al;
		try {
			al = a.getAll();
			System.out.println(al.toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void testDeleteArtist() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB("Jose Mari","espa�ol","./foto");
		try {
			a.save();
			a.delete();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a.getId());
		
	}
	static void testUpdateArtist() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB("Jose Mari","espa�ol","./foto");
		try {
			a.getArtistById(8);
			System.out.println(a.getId()+" "+a.getName());
			a.setName("Leticia Sabater");
			System.out.println(a.getName());
			a.update();
			a.getArtistById(8);
			System.out.println(a.getId()+" "+a.getName());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void testGetArtistById() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB();
		try {
			System.out.println(a.getArtistById(7));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void testGetArtistByName() {
		ArtistDaoImpMariaDB a=new ArtistDaoImpMariaDB();
		try {
			System.out.println(a.getArtistByName("Juanita"));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
