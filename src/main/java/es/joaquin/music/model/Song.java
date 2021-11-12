/**
 * 
 */
package es.joaquin.music.model;

/**
 * @author Jkin
 *
 */
public class Song {
	
	protected int id;
	protected String name;
	protected int duration;
	protected int nReprofuctions;
	protected Disc disc;
	protected String genre;
	
	
	
	
	public Song() {
		this.id=-1;
		this.name="";
		this.duration=-1;
		this.nReprofuctions=-1;
		this.disc=new Disc();
		this.genre="";
	}
	public Song(int id, String name, int duration,int nReprofuctions, Disc disc, String genre) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.nReprofuctions=nReprofuctions;
		this.disc = disc;
		this.genre = genre;
	}
	public Song(String name, int duration,int nReprofuctions, Disc disc, String genre) {
		super();
		this.id = -1;
		this.name = name;
		this.duration = duration;
		this.nReprofuctions=nReprofuctions;
		this.disc = disc;
		this.genre = genre;
	}
	
	public Song(Song song) {
		this.id = song.id;
		this.name = song.name;
		this.duration = song.duration;
		this.nReprofuctions=song.nReprofuctions;
		this.disc = song.disc;
		this.genre = song.genre;
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Disc getDisc() {
		return disc;
	}
	public void setDisc(Disc disc) {
		this.disc = disc;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", duration=" + duration + ", disc=" + disc.getName() + ", genre=" + genre
				+ "]";
	}
	
	
		

}
