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

	protected int id_Father;
	protected int id;
	protected int id_List;
	protected User user;
	protected String text;
	protected LocalDateTime date;
	protected List<Coment> coments;
	protected boolean firtsComent;
	
	
	
	public Coment() {
		this.id_Father=-1;
		this.id=-1;
		this.id_List=-1;
		this.user=new User();
		this.text="";
		this.date=LocalDateTime.now();
		this.coments=new ArrayList<Coment>();
		this.firtsComent=true;
		
	}
	
	//constructor con comentarios vacios
	public Coment(int id,int id_List, int id_Father,User user, String text, LocalDateTime date, boolean firtsComent) {
		super();
		this.id = id;
		this.id_Father=id_Father;
		this.id_List=id_List;
		this.user = user;
		this.text = text;
		this.date = date;
		this.coments=new ArrayList<Coment>();
		this.firtsComent=firtsComent;
	}


	public Coment(int id, int id_List, int id_Father, User user, String text, LocalDateTime date, List<Coment> coments,boolean firstComent) {
		super();
		this.id = id;
		this.id_Father=id_Father;
		this.id_List=id_List;
		this.user = user;
		this.text = text;
		this.date = date;
		this.coments = coments;
		this.firtsComent=firstComent;
	}
	
	public Coment(int id_Father, User user, String text, LocalDateTime date, List<Coment> coments) {
		this.id=-1;
		this.id_List=-1;
		this.id_Father=id_Father;
		this.user = user;
		this.text = text;
		this.date = date;
		this.coments = coments;
		this.firtsComent=true;
	}
	
	public Coment(Coment coment) {
		this.id = coment.id;
		this.id_List=coment.id_List;
		this.user = coment.user;
		this.text = coment.text;
		this.date = coment.date;
		this.coments = coment.coments;
		this.firtsComent=coment.firtsComent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getId_List() {
		return id_List;
	}

	public void setId_List(int id_List) {
		this.id_List = id_List;
	}

	public boolean isFirtsComent() {
		return firtsComent;
	}

	public void setFirtsComent(boolean firtsComent) {
		this.firtsComent = firtsComent;
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
