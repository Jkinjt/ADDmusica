package es.joaquin.music.model.DAO;

import java.util.List;



public interface DAO<t> {

	List<t> getAll() throws DAOException;

	boolean delete()throws DAOException;

	boolean save()throws DAOException;

	boolean update()throws DAOException;
	
	

}
