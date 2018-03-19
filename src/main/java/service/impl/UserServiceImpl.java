package service.impl;

import java.util.List;

import dao.UserDao;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	public Integer addUser(User user) {
		return userDao.save(user);
	}

	public void deleteUser(User user) {
		userDao.delete(user);
	}

	public void updateUser(User user) {
		userDao.update(user);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public List<Integer> getAllUsersId() {
		return userDao.getAllUsersId();
	}

	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public boolean isAdmin(User user) {
		return (user.getRole() == 1);
	}

	public boolean login(String email, String pwd) {
		User u = userDao.getUserByEmail(email);
		if (u != null) {
			String TruePwd = u.getPwd();
			if (TruePwd.equals(pwd)) {
				return true;
			}
		}
		return false;
	}

	public boolean dupEmail(String email, User user) {
		User utmp = userDao.getUserByEmail(email);
		if ((utmp != null) && (!user.getEmail().equals(utmp.getEmail()))){
			return true;
		}
		return false;
	}

	public boolean register(User user) {
		String email = user.getEmail();
		User tmp = userDao.getUserByEmail(email);
		if (tmp != null) return false;
		int id = userDao.save(user);
		user.setId(id);
		return true;
	}

}
