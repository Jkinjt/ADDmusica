package es.joaquin.music.model.DAO;

import java.util.List;

import es.joaquin.music.model.Coment;


public interface ComentDAO extends DAO {
	
	List<Coment> getUserListComent();
			
	List<Coment> getComentAnswer();
	

}
