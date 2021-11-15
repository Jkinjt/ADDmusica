/**
 * 
 */
package es.joaquin.music.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import es.joaquin.music.model.DAO.DAOException;

/**
 * @author Jkin
 *
 */
public class Disc {
	protected int id;
	protected String name;
	protected String picture;
	protected LocalDate date;
	protected int reproductions;
	protected Artist artist;
	protected List<Song> songs;
	
	
	
	
	public Disc() {
		this.id=-1;
		this.name="";
		this.picture="";
		this.date=LocalDate.now();
		this.reproductions=-1;
		this.artist=new Artist();
		this.songs=new ArrayList<Song>();
	}
	
	//constructor sin cargar las canciones
	public Disc(int id, String name, String picture, LocalDate date, int reproductions, Artist artist) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.date = date;
		this.reproductions = reproductions;
		this.artist = artist;
	}


	public Disc(int id, String name, String picture, LocalDate date, int reproductions, Artist artist,
			List<Song> songs) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.date = date;
		this.reproductions = reproductions;
		this.artist = artist;
		this.songs = songs;
	}
	public Disc(String name, String picture, LocalDate date, int reproductions) {
		super();
		this.id = -1;
		this.name = name;
		this.picture = picture;
		this.date = date;
		this.reproductions = reproductions;
		this.artist = new Artist();
		this.songs = new ArrayList<Song>();
	}
	public Disc(int id,String name, String picture, LocalDate date, int reproductions) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.date = date;
		this.reproductions = reproductions;
		this.artist = new Artist();
		this.songs = new ArrayList<Song>();
	}
	
	public Disc(Disc disc) {
		this.id = disc.id;
		this.name = disc.name;
		this.picture = disc.picture;
		this.date = disc.date;
		this.reproductions = disc.reproductions;
		this.artist = disc.artist;
		this.songs = disc.songs;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Disc(String name) {
		super();
		this.name = name;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getReproductions() {
		return reproductions;
	}
	public void setReproductions(int reproductions) {
		this.reproductions = reproductions;
	}
	public Artist getArtist() throws DAOException {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Disc [id=" + id + ", name=" + name + ", picture=" + picture + ", date=" + date + ", reproductions="
				+ reproductions + ", artist=" + artist + ", songs=" + songs + "]";
	}
	
	

}
