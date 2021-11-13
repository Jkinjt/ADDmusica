package es.joaquin.music.model.MariaDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import es.joaquin.music.model.Disc;
import es.joaquin.music.model.Song;
import es.joaquin.music.model.User;
import es.joaquin.music.model.UserList;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.DAO.UserListDAO;
import es.joaquin.music.uitls.MariaDBConexion;

public class UserListDaoImpMariaDB extends UserList implements UserListDAO {

	// Consultas a la base de datos
	private static final String INSERT = "INSERT INTO lista(nombre, descripcion, fecha_creacion, nsubscriptores, ID_usuario) VALUES (?,?,?,?,?)";
	private static final String UPDATE = "UPDATE lista SET nombre=?,descripcion=?,fecha_creacion=?,nsubscriptores=?,ID_usuario=? WHERE ID=?";
	private static final String DELETE = "DELETE FROM `lista` WHERE ID=?";
	private static final String SELECTALL = "SELECT ID, nombre, descripcion, fecha_creacion, nsubscriptores, ID_usuario FROM lista";
	private static final String SELECTBYID = "SELECT ID, nombre, descripcion, fecha_creacion, nsubscriptores, ID_usuario FROM lista WHERE ID=?";
	private static final String SELECTBYNAME = "SELECT ID, nombre, descripcion, fecha_creacion, nsubscriptores, ID_usuario FROM lista WHERE nombre LIKE ?";
	// conexión
	private Connection con;

	public UserListDaoImpMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserListDaoImpMariaDB(int id, String name, String description, User creator, int nReproduction,
			LocalDate date, List<User> users, List<Song> songs) {
		super(id, name, description, creator, nReproduction, date, users, songs);
		// TODO Auto-generated constructor stub
	}

	public UserListDaoImpMariaDB(int id, String name, String description, User creator, LocalDate date,
			int nSubscription) {
		super(id, name, description, creator, date, nSubscription);
		// TODO Auto-generated constructor stub
	}

	public UserListDaoImpMariaDB(String name, String description, User creator, LocalDate date, int nSubscription) {
		super(name, description, creator, date, nSubscription);
		// TODO Auto-generated constructor stub
	}

	public UserListDaoImpMariaDB(String name, String description, User creator, LocalDate date, int nSubscription,
			List<User> users, List<Song> songs) {
		super(name, description, creator, date, nSubscription, users, songs);
		// TODO Auto-generated constructor stub
	}

	public UserListDaoImpMariaDB(UserList userList) {
		super(userList);
		// TODO Auto-generated constructor stub
	}

	public List<UserList> getAll() throws DAOException {
		List<UserList> result = new ArrayList<>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTALL);
				rs = ps.executeQuery();
				UserDaoImpMariaDB u = new UserDaoImpMariaDB();
				Date date;
				while (rs.next()) {
					date = rs.getDate("fecha_creacion");
					date.toLocalDate();
					u.getUserById(rs.getInt("ID_usuario"));
					result.add(new UserList(rs.getInt("ID"), rs.getString("nombre"), rs.getString("descripcion"), u,
							date.toLocalDate(), rs.getInt("nsubscriptores")));
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

	public boolean delete() throws DAOException {
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
				throw new DAOException("Fallo al borrar en la base de datos", e);
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

	public boolean save() throws DAOException {
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
					ps.setString(2, this.description);
					ps.setDate(3, Date.valueOf(this.date));
					ps.setInt(4, this.nSubscription);
					ps.setInt(5, this.creator.getId());
					// en caso de que no se guarde se lanza una excepción
					if (ps.executeUpdate() == 0) {
						throw new DAOException("Fallo al guardar");
					} else {
						rs = ps.getGeneratedKeys();
						if (rs.next()) {
							this.id = rs.getInt(1);
							result = true;
						}

					}

					// se guarda la id para a�adirsela al usuario y no se guarde 2 veces en la base
					// de datos

				} catch (SQLException e) {
					throw new DAOException("Fallo al cargar de la base de datos", e);
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

	public boolean update() throws DAOException {
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
					ps.setString(2, this.description);
					ps.setDate(3, Date.valueOf(this.date));
					ps.setInt(4, this.nSubscription);
					ps.setInt(5, this.creator.getId());
					ps.setInt(6, this.id);
					ps.executeUpdate();
				} catch (SQLException e) {
					throw new DAOException("Fallo al actualizar en la base de datos", e);
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

	@Override
	public List<UserList> getUserListByName(String name) throws DAOException {
		List<UserList> result = new ArrayList<>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTBYNAME);
				ps.setString(1, name);
				rs = ps.executeQuery();
				UserDaoImpMariaDB u = new UserDaoImpMariaDB();
				Date date;
				while (rs.next()) {
					date = rs.getDate("fecha_creacion");
					date.toLocalDate();
					u.getUserById(rs.getInt("ID_usuario"));
					result.add(new UserList(rs.getInt("ID"), rs.getString("nombre"), rs.getString("descripcion"), u,
							date.toLocalDate(), rs.getInt("nsubscriptores")));
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

	@Override
	public UserList getUserListById(int id) throws DAOException {
		UserList result = new UserList();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTBYID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				//se crea un usuario para buscar su id y añadirlo a la lista
				UserDaoImpMariaDB u = new UserDaoImpMariaDB();
				if (rs.next()) {
					Date date = rs.getDate("fecha_creacion");
					date.toLocalDate();
					u.getUserById(rs.getInt("ID_usuario"));

					result = new UserList(rs.getInt("ID"), rs.getString("nombre"), rs.getString("descripcion"), u,
							date.toLocalDate(), rs.getInt("nsubscriptores"));
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

}
