package service;

import java.util.List;

import model.User;

public interface UserService {
	public Integer addUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);

	public User getUserById(int id);
	
	public User getUserByEmail(String email);

	public List<User> getAllUsers();
	
	public List<Integer> getAllUsersId();
	
	public boolean isAdmin(User user);
	
	public boolean login(String email, String pwd);
	
	public boolean dupEmail(String email, User user);
	
	public boolean register(User user);
}
