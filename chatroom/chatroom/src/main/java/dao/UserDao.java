package dao;

import vo.User;

public interface UserDao {
	public User login(User user);
	
	public void addUser(User user);
	
	public boolean findUserByName(String username);
}
