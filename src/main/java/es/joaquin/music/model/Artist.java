/**
 * 
 */
package es.joaquin.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jkin
 *
 */
public class Artist {

	protected int id;
	protected String name;
	protected String nationality;
	protected String picture;
	protected List<Disc> discs;
	
	
	
	public Artist() {
		this.id=-1;
		this.name="";
		this.nationality="";
		this.picture="";
		this.discs=new ArrayList<Disc>();
	}


	
	//artista sin discos
	public Artist(int id, String name, String nationality, String picture) {
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.picture = picture;
		this.discs=new ArrayList<Disc>();
	}




	public Artist(int id, String name, String nationality, String picture, List<Disc> discs) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.picture = picture;
		this.discs = discs;
	}
	
	public Artist(String name, String nationality, String picture) {
		
		this.id = -1;
		this.name = name;
		this.nationality = nationality;
		this.picture = picture;
		this.discs = new ArrayList<Disc>();
	}
	
	public Artist(Artist artist) {
		this.id=artist.id;
		this.name = artist.name;
		this.nationality = artist.nationality;
		this.picture = artist.picture;
		this.discs = artist.discs;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getNationality() {
		return nationality;
	}



	public void setNationality(String nationality) {
		this.nationality = nationality;
	}



	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}



	public List<Disc> getDiscs() {
		return discs;
	}



	public void setDiscs(List<Disc> discs) {
		this.discs = discs;
	}



	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", nationality=" + nationality + ", picture=" + picture
				+ ", discs=" + discs + "]";
	}
	
	
	

}
