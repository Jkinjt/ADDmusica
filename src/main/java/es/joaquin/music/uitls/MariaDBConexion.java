package es.joaquin.music.uitls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MariaDBConexion {

	private static Connection con=null;
	private static String uri;
	private static String dataBase;
	private static String user;
	private static String password;
	
	
	
	
	public static Connection getConexion() {
		if(con==null) {
			try {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				ServerConnection sc= WrapperForXML.loadFile();
				System.out.println(sc.toString());
				con=DriverManager.getConnection(sc.getServer()+"/"+sc.getDatabase(),sc.getUsername(),sc.getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con=null;
			}
		}
		return con;
	}
	
	public void close() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
