package es.joaquin.music.launch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import es.joaquin.music.model.Disc;
import es.joaquin.music.model.DAO.DAOException;
import es.joaquin.music.model.MariaDB.DiscDaoImpMariaDB;

public class DiscTest {
	public static void main(String[] args) {
		testDelete();
		testGetAll();
		testGetDiscById();
		testGetDiscByName();
		testSave();
		testUpdate();
	}

	static void testSave() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB("Las margaritas son flores del campo","./foto",LocalDate.now(),1000);
		try {
			d.save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	static void testUpdate() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB("Las margaritas son flores del campo","./foto",LocalDate.now(),1000);
		try {
			d.save();
			d.setName("Mill millones de copias vendidas");
			d.update();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void testGetDiscById() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB();
		try {
			System.out.println(d.getDiscById(2).toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void testGetDiscByName() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB();
		try {
			System.out.println(d.getDiscByName("Mill millones de copias vendidas").toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void testDelete() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB("Las margaritas son flores del campo","./foto",LocalDate.now(),1000);
		d.setId(3);
		try {
			d.save();
			System.out.println(d.toString());
			d.delete();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void testGetAll() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB();
		List<Disc> ld;
		try {
			ld = d.getAll();
			System.out.println(ld.toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
