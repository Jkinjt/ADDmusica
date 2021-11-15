package es.joaquin.music.singleton;

import es.joaquin.music.model.User;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;

public class UserSingleton {

	private static UserSingleton _instance;
	private static UserDaoImpMariaDB user;
	private UserSingleton( UserDaoImpMariaDB user) {
		super();
		this.user = user;
	}

	
	public static UserSingleton getInstance(UserDaoImpMariaDB user) {
		if(_instance==null) {
			_instance=new UserSingleton(user);
		}
		return _instance;
	}
	public static UserSingleton getInstance() {
		
		return _instance;
	}


	public static UserDaoImpMariaDB getUser() {
		return user;
	}


	public static void setUser(UserDaoImpMariaDB user) {
		UserSingleton.user = user;
	}
	
}
