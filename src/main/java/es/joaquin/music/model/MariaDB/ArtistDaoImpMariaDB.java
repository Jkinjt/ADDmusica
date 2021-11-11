package es.joaquin.music.model.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.joaquin.music.model.Artist;
import es.joaquin.music.model.Disc;
import es.joaquin.music.model.Song;
import es.joaquin.music.model.DAO.AstistDAO;
import es.joaquin.music.uitls.MariaDBConexion;

public class ArtistDaoImpMariaDB extends Artist implements AstistDAO {
	// Consultas a la base de datos
	private static final String INSERT = "INSERT INTO artista (nombre, nacionalidad, foto) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE artista SET nombre=?,nacionalidad=?,foto=? WHERE id=?";
	private static final String DELETE = "DELETE FROM artista WHERE id=?";
	private static final String SELECTALL = "SELECT id,nombre,nacionalidad,foto FROM artista";
	private static final String SELECTBYID = "SELECT `ID`, `nombre`, `nacionalidad`, `foto` FROM `artista` WHERE id=?";
	private static final String SELECTBYNAME = "SELECT `ID`, `nombre`, `nacionalidad`, `foto` FROM `artista` WHERE nombre LIKE?";
	private static final String SELECTARTISTALBUM = "";
	private static final String SELECTARTISTSONG = "";

	// conexi�n
	private Connection con;
	// boleano para saber si estan las caciones
	boolean loadedSongs = false;

	// boleano para saber si estan todos los discos
	boolean loadedAlbum = false;

	
	public ArtistDaoImpMariaDB() {
		super();
	}

	public ArtistDaoImpMariaDB(Artist artist) {
		super(artist);
		// TODO Auto-generated constructor stub
	}

	public ArtistDaoImpMariaDB(int id, String name, String nationality, String picture, List<Disc> discs) {
		super(id, name, nationality, picture, discs);
		// TODO Auto-generated constructor stub
	}

	// constructor con los atributos de la base de datos
	public ArtistDaoImpMariaDB(int id, String name, String nationality, String picture) {
		super(id, name, nationality, picture);
		// TODO Auto-generated constructor stub
	}
	
	
	//constructor para crear los artistas en java, sin ID
	public ArtistDaoImpMariaDB(String name, String nationality, String picture) {
		super(name, nationality, picture);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean delete() {
		boolean result=false;
		con=MariaDBConexion.getConexion();
		if(con!=null) {
			PreparedStatement ps=null;
			try {
				ps=con.prepareStatement(DELETE);
				ps.setInt(1, this.id);
				ps.executeUpdate();
				this.id=-1;
				result=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return result;
	}

	public boolean save() {
		boolean result=false;
		if(this.id!=-1) {//si la id es -1 el artista no esta en la base de datos
			update();//por lo que se acutualiza
		}else {
			con=MariaDBConexion.getConexion();
			if(con!=null&&!this.name.equals("")) {
				PreparedStatement ps=null;
				 ResultSet rs=null;
				 
				 try {
					 //se debe poner Statement.RETURN_GENERATED_KEYS para obtener el id en la base de datos cuando se guarde
					ps=con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.name);
					ps.setString(2,this.nationality);
					ps.setString(3, this.picture);
					ps.executeUpdate();
					
					//se guarda la id para a�adirsela al usuario y no se guarde 2 veces en la base de datos
					rs=ps.getGeneratedKeys();
					if(rs.next()) {
						this.id=rs.getInt(1);
					}
					result=true;
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						ps.close();
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		}
	
		return result;
	}

	
	
	public boolean update() {
		boolean result=false;
		
		if(this.id==-1) {//si la id es -1 el artista no esta en la base de datos
			save();//por lo que se guarda
		}else {
			con=MariaDBConexion.getConexion();
			if(con!=null) {
				PreparedStatement ps=null;
				try {
					ps=con.prepareStatement(UPDATE);
					ps.setString(1, this.name);
					ps.setString(2,this.nationality);
					ps.setString(3, this.picture);
					ps.setInt(4, this.id);
					ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			
			
			}
		}
		return result;
	}

	
	public List<Artist> getAll() {
		List<Artist> result = new ArrayList<Artist>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			//se delcaran aqui para que 
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				//ser prepara la consulta
				 ps = con.prepareStatement(SELECTALL);
				//se ejecuta la consulta
				 rs=ps.executeQuery();
				
				while(rs.next()) {
					result.add(new Artist(rs.getInt("ID"),rs.getString("nombre"),
							rs.getString("nacionalidad"),rs.getString("foto")));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				//se ejecuta independiente mente
			}finally {
				try {
					ps.close();
					rs.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public Artist getArtistById(int id) {
		Artist result=new Artist();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(SELECTBYID);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				if(rs.next()) {
					result=new Artist(id,
							rs.getString("nombre"),
							rs.getString("nacionalidad"),
							rs.getString("foto"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
					
				}catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	public Artist getArtistByName(String name) {
		Artist result=new Artist();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(SELECTBYNAME);
				ps.setString(1, name);
				rs=ps.executeQuery();
				if(rs.next()) {
					result=new Artist(rs.getInt("ID"),
							rs.getString("nombre"),
							rs.getString("nacionalidad"),
							rs.getString("foto"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
					
				}catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	
	
	public List<Disc> getArtitsDiscs() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Song> getArtitsSongs() {
		// TODO Auto-generated method stub
		return null;
	}

}
