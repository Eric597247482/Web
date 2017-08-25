package service;

import dao.UserDao;
import dao.UserDaoImpl;
import vo.User;

public class UserServiceImpl implements UserService {

	public User login(User user) {
		UserDao dao = new UserDaoImpl();
		
		return dao.login(user);
	}

	@Override
	public void register(User user) {
		UserDao dao = new UserDaoImpl();
		dao.addUser(user);
	}

	@Override
	public boolean findUserByName(String username) {
		UserDao dao = new UserDaoImpl();
		return dao.findUserByName(username);
	}

	
}
