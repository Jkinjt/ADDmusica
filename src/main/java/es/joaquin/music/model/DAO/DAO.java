package es.joaquin.music.model.DAO;

import java.util.List;



public interface DAO<t> {

	List<t> getAll();

	boolean delete();

	boolean save();

	boolean update();
	
	

}
