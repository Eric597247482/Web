package service;

import vo.User;

public interface UserService {
	public User login(User user);
	
	public void register(User user);
	
	public boolean findUserByName(String name);
}
