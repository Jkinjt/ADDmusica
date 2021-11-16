/**
 * 
 */
package es.joaquin.music.model;

import java.util.ArrayList;
import java.util.List;

import es.joaquin.music.model.DAO.DAOException;

/**
 * @author Jkin
 *
 */
public class User {
	
	protected int id;
	protected String name;
	protected String email;
	protected String picture;
	protected List<UserList> userList;
	protected List<Song> songs;
	
	
	
	
	public User() {
		this.id=-1;
		this.name="";
		this.email="";
		this.picture="";
		this.userList=new ArrayList<UserList>();
		this.songs=new ArrayList<Song>();
	}




	public User(String name, String email, String picture) {
		this.id=-1;
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.songs=new ArrayList<Song>();
		this.userList=new ArrayList<UserList>();
	}

	public User(int id,String name, String email, String picture) {
		this.id=id;
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.songs=new ArrayList<Song>();
		this.userList=new ArrayList<UserList>();
	}


	public User(int id, String name, String email, String picture, List<UserList> userList, List<Song> songs) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.userList = userList;
		this.songs = songs;
	}

	public User(User user) {
		this.id = user.id;
		this.name = user.name;
		this.email = user.email;
		this.picture = user.picture;
		this.userList = user.userList;
		this.songs = user.songs;
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




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPicture() {
		return picture;
	}




	public void setPicture(String picture) {
		this.picture = picture;
	}




	public List<UserList> getUserList() throws DAOException {
		return userList;
	}




	public void setUserList(List<UserList> userList) {
		this.userList = userList;
	}




	public List<Song> getSongs() {
		return songs;
	}




	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	public boolean addList(UserList userList) {
		boolean result=false;
		if(this.userList.add(userList)) {
			result=true;
		}
		
		return result;
		
	}
	public boolean removeList(UserList userList) {
		boolean result=false;
		if(this.userList.remove(userList)) {
			result=true;
		}
		
		return result;
		
	}
	



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", picture=" + picture + ", userList="
				+ userList + ", songs=" + songs + "]";
	}
	
	
	
	

}
