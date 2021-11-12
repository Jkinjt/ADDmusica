package es.joaquin.music.launch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import es.joaquin.music.model.Disc;
import es.joaquin.music.model.MariaDB.DiscDaoImpMariaDB;

public class DiscTest {
	public static void main(String[] args) {
		testGetDiscById();
		
	}

	static void testSave() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB("Las margaritas son flores del campo","./foto",LocalDate.now(),1000);
		d.save();
		}
	
	static void testUpdate() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB("Las margaritas son flores del campo","./foto",LocalDate.now(),1000);
		d.save();
		d.setName("Mill millones de copias vendidas");
		d.update();
	}
	static void testGetDiscById() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB();
		System.out.println(d.getDiscById(2).toString());
	}
	static void testGetDiscByName() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB();
		System.out.println(d.getDiscByName("Mill millones de copias vendidas").toString());
	}
	static void testDelete() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB("Las margaritas son flores del campo","./foto",LocalDate.now(),1000);
		d.setId(3);
		d.save();
		System.out.println(d.toString());
		d.delete();
	}
	static void testGetAll() {
		DiscDaoImpMariaDB d=new DiscDaoImpMariaDB();
		List<Disc> ld=d.getAll();
		System.out.println(ld.toString());
	}
}
