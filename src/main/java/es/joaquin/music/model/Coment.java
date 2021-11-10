/**
 * 
 */
package es.joaquin.music.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jkin
 *
 */
public class Coment {

	
	protected int id;
	protected User user;
	protected String text;
	protected LocalDateTime date;
	protected List<Coment> coments;
	
	
	
	public Coment() {
		this.id=-1;
		this.user=new User();
		this.text="";
		this.date=LocalDateTime.now();
		this.coments=new ArrayList<Coment>();
		
	}
	
	//constructor con comentarios vacios
	public Coment(int id, User user, String text, LocalDateTime date) {
		super();
		this.id = id;
		this.user = user;
		this.text = text;
		this.date = date;
		this.coments=new ArrayList<Coment>();
	}


	public Coment(int id, User user, String text, LocalDateTime date, List<Coment> coments) {
		super();
		this.id = id;
		this.user = user;
		this.text = text;
		this.date = date;
		this.coments = coments;
	}
	
	public Coment(Coment coment) {
		this.id = coment.id;
		this.user = coment.user;
		this.text = coment.text;
		this.date = coment.date;
		this.coments = coment.coments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public List<Coment> getComents() {
		return coments;
	}
	public void setComents(List<Coment> coments) {
		this.coments = coments;
	}
	
	
}
