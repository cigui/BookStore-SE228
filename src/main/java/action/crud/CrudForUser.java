package action.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.BaseAction;
import model.User;
import service.UserService;
import utils.JsonBean;

public class CrudForUser extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> resultMap;
	private List<JsonBean> resultList;
	private UserService userService;
	private int id;
	private String usrName;
	private String pwd;
	private int phone;
	private String email;
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public List<JsonBean> getResultList() {
		return resultList;
	}
	public void setResultList(List<JsonBean> resultList) {
		this.resultList = resultList;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String All() throws Exception {
		List<User> users = userService.getAllUsers();
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("total", users.size());  
		resultMap.put("rows", users);
		return "all";
	}
	
	public String Add() throws Exception {
		User user = new User(usrName, pwd, phone, email);
		userService.addUser(user);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "add";
	}
	
	public String Update() throws Exception {
		try {
			User user = userService.getUserById(id);
			user.setEmail(email);
			user.setPhone(phone);
			user.setPwd(pwd);
			user.setUsrName(usrName);
			userService.updateUser(user);
			resultMap = new HashMap<String, Object>(); 
			resultMap.put("success", true);
			return "update";
		} catch (Exception e) {
			e.printStackTrace();
			return "update";
		}
	}
	
	public String Delete() throws Exception {
		User user = userService.getUserById(id);
		userService.deleteUser(user);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "delete";
	}
	
	public String IdList() throws Exception {
		List<Integer> users = userService.getAllUsersId();
		resultList = new ArrayList<JsonBean>();
		for (int i = 0; i < users.size(); i++) {
			JsonBean tmp = new JsonBean(users.get(i).toString(), users.get(i).toString());
			resultList.add(tmp);
		}
		return "idList";
	}
}
