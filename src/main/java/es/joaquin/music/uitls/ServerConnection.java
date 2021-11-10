package es.joaquin.music.uitls;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name ="server")
public class ServerConnection implements Serializable{
		
		private static int count=0;
		private int id;
		private String server;
		private String database;
		private String username;
		private String password;
		public ServerConnection() {
			super();
			id=++count;
		}
		/**
		 * @return the id
		 */
		@XmlAttribute(name ="id")
		public int getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}
		/**
		 * @return the server
		 */
		@XmlElement(name="serverName")
		public String getServer() {
			return server;
		}
		/**
		 * @param server the server to set
		 */
		public void setServer(String server) {
			this.server = server;
		}
		/**
		 * @return the database
		 */
		@XmlElement(name="dataBase")
		public String getDatabase() {
			return database;
		}
		/**
		 * @param database the database to set
		 */
		public void setDatabase(String database) {
			this.database = database;
		}
		/**
		 * @return the username
		 */
		@XmlElement(name="userName")
		public String getUsername() {
			return username;
		}
		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}
		/**
		 * @return the password
		 */
		@XmlElement(name="password")
		public String getPassword() {
			return password;
		}
		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "ServerConnection [id=" + id + ", server=" + server + ", database=" + database + ", username=" + username
					+ ", password=" + password + "]";
		}
		
		
		
		
}
