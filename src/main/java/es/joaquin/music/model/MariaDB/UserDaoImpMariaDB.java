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
import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.DAO.UserDAO;
import es.joaquin.music.uitls.MariaDBConexion;

public class UserDaoImpMariaDB extends User implements UserDAO {
	private Connection con;
	// Consultas a la base de datos
	private static final String INSERT = "INSERT INTO usuario(nombre, correo, foto) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE usuario SET nombre=?,correo=?,foto=? WHERE ID=?";
	private static final String DELETE = "DELETE FROM `usuario` WHERE ID=?";
	private static final String SELECTALL = "SELECT ID, nombre, correo, foto FROM usuario";
	private static final String SELECTBYID = "SELECT ID, nombre,correo,foto FROM usuario WHERE ID=?";
	private static final String SELECTBYEMAIL = "SELECT ID, nombre,correo,foto FROM usuario WHERE correo LIKE ?";
	private static final String SELECTBYNAME = "SELECT ID, nombre,correo,foto FROM usuario WHERE nombre=?";
	private static final String SELECTUSERLIST="SELECT l.ID, l.nombre,l.descripcion,l.fecha_creacion,l.nsubscriptores,l.ID_usuario FROM lista as l JOIN  suscrito AS s on s.ID_lista=l.ID WHERE\r\n"
			+ "S.ID_usuario=?";
	private static final String SELECTSONGLISTENED="SELECT c.ID, c.nombre,c.duracion, c.reproducciones, c.ID_disco,g.nombre from cancion as c, genero AS g, escucha AS e WHERE c.ID_genero=g.ID AND c.ID=e.ID_cancion AND e.ID_usuario=?";
	public UserDaoImpMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDaoImpMariaDB(int id, String name, String email, String picture) {
		super(id, name, email, picture);
		// TODO Auto-generated constructor stub
	}

	public UserDaoImpMariaDB(int id, String name, String email, String picture, List<UserList> userList,
			List<Song> songs) {
		super(id, name, email, picture, userList, songs);
		// TODO Auto-generated constructor stub
	}

	public UserDaoImpMariaDB(String name, String email, String picture) {
		super(name, email, picture);
		// TODO Auto-generated constructor stub
	}

	public UserDaoImpMariaDB(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	public List getAll() {
		List<User> result = new ArrayList<User>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			// se delcaran aqui para que
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				// ser prepara la consulta
				ps = con.prepareStatement(SELECTALL);
				// se ejecuta la consulta
				rs = ps.executeQuery();

				while (rs.next()) {
					result.add(new User(rs.getInt("ID"), rs.getString("nombre"), rs.getString("correo"),
							rs.getString("foto")));

				}
			} catch (SQLException e) {
				e.printStackTrace();
				// se ejecuta independiente mente
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

		return result;
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
			if (con != null && !this.name.equals("")) {
				PreparedStatement ps = null;
				ResultSet rs = null;

				try {
					// se debe poner Statement.RETURN_GENERATED_KEYS para obtener el id en la base
					// de datos cuando se guarde
					ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.name);
					ps.setString(2, this.email);
					ps.setString(3, this.picture);
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

		if (this.id == -1) {// si la id es -1 el artista no esta en la base de datos
			save();// por lo que se guarda
		} else {
			con = MariaDBConexion.getConexion();
			if (con != null) {
				PreparedStatement ps = null;
				try {
					ps = con.prepareStatement(UPDATE);
					ps.setString(1, this.name);
					ps.setString(2, this.email);
					ps.setString(3, this.picture);
					ps.setInt(4, this.id);
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

	public boolean getUserById(int id) {
		boolean result = false;
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTBYID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					this.id=id;
					this.name=rs.getString("nombre");
					this.email=rs.getString("correo");
					this.picture= rs.getString("foto");
				}
				result=true;
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
	
	public boolean getUserByEmail(String email) {
		boolean result = false;
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTBYEMAIL);
				ps.setString(1, email);
				rs = ps.executeQuery();
				if (rs.next()) {
					this.id=rs.getInt("ID");
					this.name=rs.getString("nombre");
					this.email=rs.getString("correo");
					this.picture= rs.getString("foto");
				}
				if(this.id!=-1) {
					result=true;
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

	public List<User> getUserByName(String name) {
		List<User> result = new ArrayList<User>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTBYNAME);
				ps.setString(1, name);
				rs = ps.executeQuery();
				while (rs.next()) {
					result.add(new User(rs.getInt("ID"),
							rs.getString("nombre"),
							rs.getString("correo"),
							rs.getString("foto") ));
					
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
	@Override
	public List<UserList> getUserList() throws DAOException {
		List<UserList> result = new ArrayList<>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTUSERLIST);
				ps.setInt(1, this.id);
				rs = ps.executeQuery();
				UserDaoImpMariaDB u = new UserDaoImpMariaDB();
				Date date;
				while (rs.next()) {
					date = rs.getDate("l.fecha_creacion");
					date.toLocalDate();
					u.getUserById(rs.getInt("l.ID_usuario"));
					result.add(new UserList(rs.getInt("l.ID"), rs.getString("l.nombre"), rs.getString("l.descripcion"), u,
							date.toLocalDate(), rs.getInt("l.nsubscriptores")));
				}
			} catch (SQLException e) {
				throw new DAOException("Fallo al cargar de la base de datos", e);
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

	public List<UserList> getCreatedList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Song> getSongs() {
		List<Song> result = new ArrayList<>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTSONGLISTENED);
				ps.setInt(1, this.id);
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

}
