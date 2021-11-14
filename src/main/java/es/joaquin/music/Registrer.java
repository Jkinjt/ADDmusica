package es.joaquin.music;
import java.io.IOException;

import es.joaquin.music.singleton.UserSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Registrer {
	private UserSingleton userSingleton;
	
	private TextField name;
	private TextField email;
	private Button cancel;
	private Button save;

	
	public boolean saveUser() {
		boolean  result=false;
		
		return result;
	}
	
	public void back() {
		
	}
}
