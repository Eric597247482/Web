package service;

import dao.UserDao;
import dao.UserDaoImpl;
import vo.User;

public class UserServieImpl implements UserService {

	public User login(User user) {
		UserDao dao = new UserDaoImpl();
		
		return dao.login(user);
	}

}
