package es.joaquin.music.model.MariaDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.joaquin.music.model.Disc;
import es.joaquin.music.model.Song;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.DAO.SongDAO;
import es.joaquin.music.uitls.MariaDBConexion;

public class SongDaoImpMariaDB extends Song implements SongDAO {

	//Consultas a la base de datos
		private static final String INSERT="INSERT INTO cancion(nombre,duracion,reproducciones,ID_disco,ID_genero) VALUES (?,?,?,?,(SELECT ID from genero where nombre like ?))";
		private static final String UPDATE="UPDATE cancion SET nombre=?,duracion=?,reproducciones=?,ID_disco=?,ID_genero=(SELECT ID from genero where nombre like ?) WHERE ID=?";
		private static final String DELETE="DELETE FROM cancion WHERE ID=?";
		private static final String SELECTALL="SELECT c.ID, c.nombre, c.duracion, c.reproducciones, c.ID_disco, g.nombre FROM cancion AS c, genero AS g WHERE c.ID_genero=g.ID";
		private static final String SELECTBYID="SELECT c.ID, c.nombre, c.duracion, c.reproducciones, c.ID_disco, g.nombre FROM cancion AS c, genero AS g WHERE c.ID_genero=g.ID AND c.ID=?";
		private static final String SELECTBYNAME="SELECT c.ID, c.nombre, c.duracion, c.reproducciones, c.ID_disco, g.nombre FROM cancion AS c, genero AS g WHERE c.ID_genero=g.ID AND c.nombre=?";
		private static final String INSERTGENRE="INSERT INTO genero(nombre) VALUES (?)";
		private static final String SELECTALLGENRE="SELECT ID, nombre FROM genero";
		private static final String SELECTGENREBYNAME="SELECT ID FROM genero WHERE nombre LIKE ?";
		private Connection con;
		
		
	

		public SongDaoImpMariaDB() {
			super();
			// TODO Auto-generated constructor stub
		}

		public SongDaoImpMariaDB(int id, String name, int duration, int nReprofuctions, Disc disc, String genre) {
			super(id, name, duration, nReprofuctions, disc, genre);
			// TODO Auto-generated constructor stub
		}

		public SongDaoImpMariaDB(Song song) {
			super(song);
			// TODO Auto-generated constructor stub
		}

		public SongDaoImpMariaDB(String name, int duration, int nReprofuctions, Disc disc, String genre) {
			super(name, duration, nReprofuctions, disc, genre);
			// TODO Auto-generated constructor stub
		}

		public boolean delete() {
			boolean result = false;
			con = MariaDBConexion.getConexion();
			if (con != null) {
				PreparedStatement ps = null;
				try {
					ps = con.prepareStatement(DELETE);
					ps.setInt(1, this.id);
					ps.executeUpdate();
					this.id = -1;
					result = true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
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
		boolean result = false;
		
		if (this.id != -1) {// si la id es -1 el artista no esta en la base de datos
			update();// por lo que se acutualiza
		} else {
			con = MariaDBConexion.getConexion();
			if (con != null&& this.disc.getId()!=-1 && !this.name.equals("")) {
				PreparedStatement ps = null;
				ResultSet rs = null;

				try {
					// se debe poner Statement.RETURN_GENERATED_KEYS para obtener el id en la base
					// de datos cuando se guarde
					ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.name);
					ps.setInt(2, this.duration);
					ps.setInt(3, this.nReprofuctions);
					ps.setInt(4,this.disc.getId() );
					ps.setString(5, this.genre);
					
					ps.executeUpdate();

					// se guarda la id para a�adirsela al usuario y no se guarde 2 veces en la base
					// de datos
					rs = ps.getGeneratedKeys();
					if (rs.next()) {
						this.id = rs.getInt(1);
					}
					result = true;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
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
		boolean result = false;

		if (this.id == -1) {// si la id es -1 la canción no esta en la base de datos
			save();// por lo que se guarda
		} else {
			con = MariaDBConexion.getConexion();
			if (con != null) {
				PreparedStatement ps = null;
				try {
					ps = con.prepareStatement(UPDATE);
					ps.setString(1, this.name);
					ps.setInt(2, this.duration);
					ps.setInt(3, this.nReprofuctions);
					ps.setInt(4,this.disc.getId() );
					ps.setString(5, this.genre);
					ps.setInt(6, this.id);
					ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
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

	public List<Song> getAll() {
		List<Song> result = new ArrayList<>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTALL);
				rs = ps.executeQuery();
				DiscDaoImpMariaDB dDAO=new DiscDaoImpMariaDB();
				Disc d=new Disc();
				while (rs.next()) {
					
					try {
						d=dDAO.getDiscById(rs.getInt("ID_disco"));
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result.add(new Song(
							rs.getInt("ID"), rs.getString("c.nombre"), rs.getInt("duracion"),
							rs.getInt("reproducciones"),d, rs.getString("g.nombre"))
							);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();

				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	public List<Song> getSongsByDisc() {
		// TODO Auto-generated method stub
		return null;
	}

	public Song getSongById(int id) throws DAOException {
		Song result = new Song();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTBYID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				//Se crea un disco
				DiscDaoImpMariaDB dDAO=new DiscDaoImpMariaDB();
				Disc d=new Disc();
				while (rs.next()) {
					//se busca la id del disco que devuelva la consulta
					try {
						d=dDAO.getDiscById(rs.getInt("ID_disco"));
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				result=	new Song(
							rs.getInt("c.ID"), rs.getString("c.nombre"), rs.getInt("c.duracion"),
							rs.getInt("c.reproducciones"),d, rs.getString("g.nombre"));
					}
			} catch (SQLException e) {
				throw new DAOException("Fallo al cargar la cancion de la base de datos");
			} finally {
				try {
					ps.close();
					rs.close();

				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	public List<Song> getSongByName(String name) {
		List<Song> result = new ArrayList<>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTBYNAME);
				ps.setString(1, name);
				rs = ps.executeQuery();
				//Se crea un disco
				DiscDaoImpMariaDB dDAO=new DiscDaoImpMariaDB();
				Disc d=new Disc();
				while (rs.next()) {
					//se busca la id del disco que devuelva la consulta
					try {
						d=dDAO.getDiscById(rs.getInt("ID_disco"));
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result.add(new Song(
							rs.getInt("c.ID"), rs.getString("c.nombre"), rs.getInt("c.duracion"),
							rs.getInt("c.reproducciones"),d, rs.getString("g.nombre"))
							);}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();

				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	
	public List<String> getAllGenres() {
		List<String> result=new ArrayList<String>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTALLGENRE);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					result.add(rs.getString("nombre"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();

				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	public int getGenreId(String name) {
		int result=-1;
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps=con.prepareStatement(SELECTGENREBYNAME);
				ps.setString(1, name);
				rs=ps.executeQuery();
				if(rs.next()) {
					result=rs.getInt("ID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}

	
	public boolean saveGenre() {
		boolean result=false;
		con = MariaDBConexion.getConexion();
		if(con!=null) {
			PreparedStatement ps=null;			
			try {
				ps=con.prepareStatement(INSERTGENRE);
				ps.setString(1, this.genre);
				ps.executeUpdate();
				result=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	

	

}
