package es.joaquin.music.model.MariaDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import es.joaquin.music.model.Coment;
import es.joaquin.music.model.User;
import es.joaquin.music.model.DAO.ComentDAO;
import es.joaquin.music.uitls.MariaDBConexion;

public class ComentDaoImpMariaDB extends Coment implements ComentDAO {
	private Connection con;

	// Consultas a la base de datos
	private static final String INSERT = "INSERT INTO comenta(texto, fecha, ID_lista, ID_usuario, ID_comenta) VALUES (?,?,?,?,?)";
	private static final String UPDATE = "";
	private static final String DELETE = "";
	private static final String SELECTALL = "";
	private static final String SELECTFIRTSCOMENT = "SELECT ID,nombre,fecha,ID_lista,ID_usuario,ID_comenta FROM comenta where ID_comenta=0 ";
	private static final String SELECTBYID = "";
	private static final String SELECTBYNAME = "";

	public ComentDaoImpMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComentDaoImpMariaDB(Coment coment) {
		super(coment);
		// TODO Auto-generated constructor stub
	}

	

	public ComentDaoImpMariaDB(int id, int id_List, int id_Father, User user, String text, LocalDateTime date,
			boolean firtsComent) {
		super(id, id_List, id_Father, user, text, date, firtsComent);
		// TODO Auto-generated constructor stub
	}

	public ComentDaoImpMariaDB(int id, int id_List, int id_Father, User user, String text, LocalDateTime date,
			List<Coment> coments, boolean firstComent) {
		super(id, id_List, id_Father, user, text, date, coments, firstComent);
		// TODO Auto-generated constructor stub
	}

	public ComentDaoImpMariaDB(int id_Father, User user, String text, LocalDateTime date, List<Coment> coments) {
		super(id_Father, user, text, date, coments);
		// TODO Auto-generated constructor stub
	}

	public List<Coment> getAll() {
		List<Coment> result = new ArrayList<>();

		return result;
	}

	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
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
					ps.setString(1, this.text);
					ps.setDate(2, Date.valueOf(this.date.toLocalDate()));
					ps.setInt(3, this.id_List;
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
	}*/

	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Coment> getUserListComent() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Coment> getComentAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

}
