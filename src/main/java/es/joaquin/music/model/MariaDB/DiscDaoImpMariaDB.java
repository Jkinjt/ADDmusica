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

import es.joaquin.music.model.Artist;
import es.joaquin.music.model.Disc;
import es.joaquin.music.model.Song;
import es.joaquin.music.model.DAO.DiscDAO;
import es.joaquin.music.uitls.MariaDBConexion;

public class DiscDaoImpMariaDB extends Disc implements DiscDAO {
	// Consultas a la base de datos
	private static final String INSERT = "INSERT INTO disco(nombre, foto, reproducciones, fecha) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE disco SET nombre=?, foto=?,reproducciones=?,fecha=? WHERE ID=?";
	private static final String DELETE = "DELETE FROM disco WHERE ID=?";
	private static final String SELECTALL = "SELECT ID, nombre, foto, reproducciones, fecha FROM disco";
	private static final String SELECTBYID = "SELECT ID, nombre, foto, reproducciones, fecha FROM disco WHERE id=?";
	private static final String SELECTBYNAME = "SELECT ID, nombre, foto, reproducciones,fecha FROM disco WHERE nombre=?";
	private Connection con;

	public DiscDaoImpMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiscDaoImpMariaDB(Disc disc) {
		super(disc);
		// TODO Auto-generated constructor stub
	}

	public DiscDaoImpMariaDB(int id, String name, String picture, LocalDate date, int reproductions, Artist artist,
			List<Song> songs) {
		super(id, name, picture, date, reproductions, artist, songs);
		// TODO Auto-generated constructor stub
	}

	public DiscDaoImpMariaDB(int id, String name, String picture, LocalDate date, int reproductions, Artist artist) {
		super(id, name, picture, date, reproductions, artist);
		// TODO Auto-generated constructor stub
	}

	public DiscDaoImpMariaDB(String name, String picture, LocalDate date, int reproductions) {
		super(name, picture, date, reproductions);
		// TODO Auto-generated constructor stub
	}

	public DiscDaoImpMariaDB(int id, String name, String picture, LocalDate date, int reproductions) {
		super(id, name, picture, date, reproductions);
		// TODO Auto-generated constructor stub
	}

	public DiscDaoImpMariaDB(String name) {
		super(name);
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
			if (con != null && !this.name.equals("")) {
				PreparedStatement ps = null;
				ResultSet rs = null;

				try {
					// se debe poner Statement.RETURN_GENERATED_KEYS para obtener el id en la base
					// de datos cuando se guarde
					ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.name);
					ps.setString(2, this.picture);
					ps.setInt(3, this.reproductions);
					ps.setDate(4, Date.valueOf(this.date));
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
					ps.setString(2, this.picture);
					ps.setInt(3, this.reproductions);
					ps.setDate(4, Date.valueOf(this.date));
					ps.setInt(5, this.id);
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

	public List<Disc> getAll() {
		List<Disc> result = new ArrayList<>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTALL);
				rs = ps.executeQuery();
				while (rs.next()) {
					Date date = rs.getDate("fecha");
					date.toLocalDate();
					result.add(new Disc(rs.getInt("ID"), rs.getString("nombre"), rs.getString("foto"),
							date.toLocalDate(), rs.getInt("reproducciones")));
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

	public Disc getDiscByArtist() {
		// TODO Auto-generated method stub
		return null;
	}

	public Disc getDiscbySong() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Disc> getDiscByName(String name) {
		List<Disc> result = new ArrayList<>();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTBYNAME);
				ps.setString(1, name);
				rs = ps.executeQuery();
				while (rs.next()) {
					Date date = rs.getDate("fecha");
					date.toLocalDate();
					result.add(new Disc(rs.getInt("ID"), rs.getString("nombre"), rs.getString("foto"),
							date.toLocalDate(), rs.getInt("reproducciones")));
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

	public Disc getDiscById(int id) {
		Disc result = new Disc();
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SELECTBYID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					Date date = rs.getDate("fecha");
					date.toLocalDate();

					result = new Disc(rs.getInt("ID"), rs.getString("nombre"), rs.getString("foto"), date.toLocalDate(),
							rs.getInt("reproducciones"));
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

}
