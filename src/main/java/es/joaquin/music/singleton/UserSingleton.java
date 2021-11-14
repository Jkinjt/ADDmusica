package es.joaquin.music.singleton;

import es.joaquin.music.model.User;
import es.joaquin.music.model.MariaDB.UserDaoImpMariaDB;

public class UserSingleton {

	private static UserSingleton _instance;
	private static UserDaoImpMariaDB user;
	public UserSingleton( UserDaoImpMariaDB user) {
		super();
		this.user = user;
	}

	
	public static UserSingleton getInstance(UserDaoImpMariaDB user) {
		if(_instance==null) {
			_instance=new UserSingleton(user);
		}
		return _instance;
	}
	
}
