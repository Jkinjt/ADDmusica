package es.joaquin.music.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserList {

	
	protected int id;
	protected String name;
	protected String description;
	protected User creator;
	protected LocalDateTime date;
	protected List<User> users;
	protected List<Song> songs;
	
	
	
	public UserList() {
		this.id=-1;
		this.name="";
		this.description="";
		this.creator=new User();
		this.users=new ArrayList<User>();
		this.songs=new ArrayList<Song>();
		
	}



	public UserList(int id, String name, String description, User creator, LocalDateTime date, List<User> users,
			List<Song> songs) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.creator = creator;
		this.date = date;
		this.users = users;
		this.songs = songs;
	}

	public UserList(UserList userList) {
		this.id = userList.id;
		this.name = userList.name;
		this.description = userList.description;
		this.creator = userList.creator;
		this.date = userList.date;
		this.users = userList.users;
		this.songs = userList.songs;
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



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public User getCreator() {
		return creator;
	}



	public void setCreator(User creator) {
		this.creator = creator;
	}



	public LocalDateTime getDate() {
		return date;
	}



	public void setDate(LocalDateTime date) {
		this.date = date;
	}



	public List<User> getUsers() {
		return users;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}



	public List<Song> getSongs() {
		return songs;
	}



	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	
	
	
}
